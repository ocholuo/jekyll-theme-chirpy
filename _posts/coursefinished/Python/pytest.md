
# pytest

[toc]

---

## first test

```py
# $ vim text_1.py

def func(x):
    return x+1

def test_answer():
    assert func(3) == 5

---------

# $ py.test test_1.py

=================== FAILURES ===================
_________________ test_answer __________________

    def test_answer():
>       assert func(3) == 5
E       assert 4 == 5
E        +  where 4 = func(3)

tests/test_1.py:5: AssertionError
=========== short test summary info ============
FAILED tests/test_1.py::test_answer - assert ...
============== 1 failed in 0.05s ===============
```

---

## testing Exceptions

```py
# $ vim test_sysexit.py
import pytest
def f():
    raise SystemExit(1)

def test_mytest():
    with pytest.raises(SystemExit):
        f()  # see if this will raise the Exception
# if raise the same Exception
# this test will pass.

---------------------------

# $ py.test tests/test_2.py
tests/test_2.py .                        [100%]
============== 1 passed in 0.01s ===============
```

---

## context-sensitive comparisons

will tell the different.

```py
# $ vim text_3.py
def test_answer():
    assert set(['0','1','2']) == set(['0','2','3'])
    assert 'foo1' == 'foo2'
    assert {'a':0,'b':1,'c':0} == {'a':0,'b':0,'d':0}

---------------------------

# $ py.test tests/test_3.py

=================== FAILURES ===================
_________________ test_answer __________________

    def test_answer():
>       assert set(['0','1','2']) == set(['0','2','3'])
E       AssertionError: assert {'0', '1', '2'} == {'0', '2', '3'}
E         Extra items in the left set:
E         '1'
E         Extra items in the right set:
E         '3'
E         Use -v to get the full diff


E       AssertionError: assert 'foo1' == 'foo2'
E         - foo2
E         ?    ^
E         + foo1
E         ?    ^

tests/test_3.py:5: AssertionError
=========== short test summary info ============
FAILED tests/test_3.py::test_answer - Asserti...
============== 1 failed in 0.05s ===============
(pgbackup) pgbackup[master !?] $
```

---

## fixtures

- the purpose of test fixtures is to provide a fixed baseline
    - upon which tests can reliably and repeatedly execute.
- fixtures have explicit names
- and are activated by declaring their use from test function, modules, classes or whole projects. <- dependency injection

```py
# vim test_4.py
import pytest

class Person:
    def greet(self):
        return "hello, there!"

@pytest.fixture
def person():
    return Person()

def test_greet(person):
    greeting = person.greet()
    assert greeting == "hi, there!"
# pytest see test_greet needs a function argument named person
# find matching fixture-marked function named person.
# person() is called to create an instance
# test_greet(<Person instance) is called.

---------------------------

# $ py.test tests/test_4.py

=================== FAILURES ===================
    def test_greet(person):
        greeting = person.greet()
>       assert greeting == "hi, there!"
E       AssertionError: assert 'hello, there!' == 'hi, there!'
E         - hi, there!
E         ?  ^
E         + hello, there!
E         ?  ^^^^

tests/test_4.py:13: AssertionError
=========== short test summary info ============
FAILED tests/test_4.py::test_greet - Assertio...
============== 1 failed in 0.07s ===============
```

---

# pytest-mock
- This plugin provides a mocker fixture
- a thin-wrapper around the patching API provided by the mock package.

## unittest.mock

Python3.3 新增用来在单元测试的时候进行 mock 操作的 unittest.mock 模块。

---

## 常见用法

### 定义返回值 `mock.return_value`

```py
>>> from unittest.mock import MagicMock
>>> mock = MagicMock(return_value=3)
# or
# mock = MagicMock()
# mock.return_value = 3
>>> mock()
3
```

### 定义变化的返回值 `side_effect=xxx`

```py
>>> mock = MagicMock(side_effect=[1, 2, 3])
# or
# >>> mock = MagicMock()
# >>> mock.side_effect = [1, 2, 3]
>>> mock()
1
>>> mock()
2
>>> mock()
3
>>> mock()
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
  File "/xxx/lib/python3.6/unittest/mock.py", line 939, in __call__
    return _mock_self._mock_call(*args, **kwargs)
  File "/xxx/lib/python3.6/unittest/mock.py", line 998, in _mock_call
    result = next(effect)
StopIteration


>>> def side_effect(arg=1):
        return arg
>>> m = MagicMock(side_effect=side_effect)
>>> m()
1
>>> m(1)
1
>>> m(2)
2
```

---

### 定义抛出异常 `side_effect = KeyError`

```py
>>> m.side_effect = KeyError
>>> m()
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
  File "/xxx/python3.6/unittest/mock.py", line 939, in __call__
    return _mock_self._mock_call(*args, **kwargs)
  File "/xxx/python3.6/unittest/mock.py", line 995, in _mock_call
    raise effect
KeyError
```

---

### mock 变量/属性

```py
# example.py:

class FooBar:
    def __init__(self):
        self.msg = 'test'
    def hello(self):
        return 'hello'

foo = {}

def bar():
    return foo
    # return {}

def foobar():
    return FooBar().hello()  
    # return 'hello'

fb = FooBar()

def hello():
    return fb.msg     
    # self.msg = 'test'
```

```py
>>> from unittest.mock import patch
>>> m = MagicMock()
>>> m.test = MagicMock(return_value=233)

>>> m()
<MagicMock name='mock()' id='4372854824'>
>>> m.test
<MagicMock name='mock.test' id='4372854768'>

>>> m.test()
233


>>> import example
>>> example.foo
{}
>>> example.hello()
'test'
>>> with patch.object(example, 'foo', {'lalala': 233}):
        example.foo
{'lalala': 233}

>>> example.foo
{}
>>> with patch.object(example.fb, 'msg', 666):
        example.hello()
666
>>> example.hello()
'test'
```

---

### mock dict
```py
>>> foo = {'a': 233}
>>> foo['a']
233

>>> with patch.dict(foo, {'a': 666, 'b': 222}):
        print(foo['a'])   # 666
        print(foo['b'])   # 222

>>> foo['a']
233

>>> 'b' in foo
False
```

---

## 常用检查方法
- mock 的对象拥有一些可以用于单元测试的检查方法，
- 可以用来测试 mock 对象的调用情况。

---

### 检查调用次数

```py
# 待检查的 mock 对象:
>>> m = MagicMock()
>>> m(1)
<MagicMock name='mock()' id='4372904760'>
>>> m(2)
<MagicMock name='mock()' id='4372904760'>
>>> m(3)
<MagicMock name='mock()' id='4372904760'>
>>>
```

---

### `.called`: 是否被调用过

```py
>>> m.called
True
```

---

### `.call_count`: 获取调用次数

```py
>>> m.call_count
3
```

---

### .assert_called():
- 检查是否被调用过
- 如果没有被调用过，则会抛出 AssertionError 异常

```py
>>> m.assert_called()
>>>
```

### `.assert_called_once()`: 确保调用过一次
- 如果没调用或多于一次，否则抛出 AssertionError 异常

```py
>>> m.assert_called_once()
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
  File "/xxx/lib/python3.6/unittest/mock.py", line 795, in assert_called_once
    raise AssertionError(msg)
AssertionError: Expected 'mock' to have been called once. Called 3 times.
```

### `.assert_not_called()`:
- 确保没被调用过，否则抛出 AssertionError 异常

```py
>>> m.assert_not_called()
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
  File "/xxx/lib/python3.6/unittest/mock.py", line 777, in assert_not_called
    raise AssertionError(msg)
AssertionError: Expected 'mock' to not have been called. Called 3 times.
```

---

## 检查调用时使用的参数¶

待检查的 mock 对象:
```py
>>> m = MagicMock()

>>> m(1, 2, foo='bar')
<MagicMock name='mock()' id='4372980792'>
```

### .call_args: 最后调用时的参数
- 最后一次调用时使用的参数，未调用则返回 None

```py
>>> m.call_args
call(1, 2, foo='bar')
```

### `.assert_called_once_with(*args, **kwargs): `

确保只调用过一次，并且使用特定参数调用

```py
>>> m.assert_called_once_with(1, 2, foo='bar')

>>> m(2)
<MagicMock name='mock()' id='4372980792'>

>>> m.assert_called_once_with(1, 2, foo='bar')
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
  File "/xxx/lib/python3.6/unittest/mock.py", line 824, in assert_called_once_with
    raise AssertionError(msg)
AssertionError: Expected 'mock' to be called once. Called 2 times.
```

### `.assert_any_call(*args, **kwargs)`: 检查某次用特定参数进行过调用

```py
>>> m.assert_any_call(1, 2, foo='bar')
>>>
```

### `.assert_called_with(*args, **kwargs)`: 检查最后一次调用时使用的参数

```py
>>> m.assert_called_with(2)
>>>

>>> m.assert_called_with(1, 2, foo='bar')
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
  File "/xxx/lib/python3.6/unittest/mock.py", line 814, in assert_called_with
    raise AssertionError(_error_message()) from cause
AssertionError: Expected call: mock(1, 2, foo='bar')
Actual call: mock(2)
>>>
```

### .call_args_list: 所有调用时使用的参数列表

```py
>>> m.call_args_list
[call(1, 2, foo='bar'), call(2)]

>>> m(3)
<MagicMock name='mock()' id='4372980792'>

>>> m.call_args_list
[call(1, 2, foo='bar'), call(2), call(3)]
```


### .assert_has_calls(calls, any_order=False):
- 检查某几次调用时使用的参数
- any_order 为 False 时必须是挨着的调用顺序, 可以是中间的几次调用
- any_order 为 True 时 calls 中的记录可以是无序的

```py
>>> from unittest.mock import call

>>> m.call_args_list
[call(1, 2, foo='bar'), call(2), call(3)]

>>> m.assert_has_calls([call(2), call(3)])
>>>

>>> m.assert_has_calls([call(3), call(2)])
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
  File "/xxx/lib/python3.6/unittest/mock.py", line 846, in assert_has_calls
    ) from cause
AssertionError: Calls not found.
Expected: [call(3), call(2)]
Actual: [call(1, 2, foo='bar'), call(2), call(3)]

>>> m.assert_has_calls([call(3), call(2)], any_order=True)
>>>
```


### .method_calls: mock 对象的方法调用记录

```py
>>> m.test_method(2, 3, 3)
<MagicMock name='mock.test_method()' id='4372935456'>

>>> m.method_calls
[call.test_method(2, 3, 3)]
```

### .mock_calls:
- 记录 mock 对象的所有调用
- 包含方法、magic method 以及返回值 mock

```py
>>> m.mock_calls
[call(1, 2, foo='bar'), call(2), call(3), call.test_method(2, 3, 3)]

>>> m.call_args_list
[call(1, 2, foo='bar'), call(2), call(3)]
```

## 手动重置 mock 调用记录
可以使用 .reset_mock() 重置 mock 对象记录的调用记录:

```py
>>> m.mock_calls
[call(1, 2, foo='bar'), call(2), call(3), call.test_method(2, 3, 3)]

>>> m.call_args_list
[call(1, 2, foo='bar'), call(2), call(3)]

>>> m.reset_mock()

>>> m.call_args_list
[]
>>> m.mock_calls
[]
>>>
```




.
