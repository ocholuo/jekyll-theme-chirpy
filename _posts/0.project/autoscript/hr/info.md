
[toc]


[link](https://linuxacademy.com/cp/exercises/view/id/716/module/168)


## Exercise: Creating a Python Project

Over the course of the next few exercises, you’ll be creating a Python package to manage users on a server based on an “inventory” JSON file.
The first step in this process is going to be setting up the project’s directory structure and metadata.
Do the following:
- Create a project folder called hr (short for “human resources”).
- Set up the directories to put the project’s source code and tests.
- Create the setup.py with metadata and package discovery.
- Utilize pipenv to create a virtualenv and Pipfile.
- Add pytest and pytest-mock as development dependencies.
- Set the project up in source control and make your initial commit.

$ mkdir hr
$ cd hr
$ mkdir -p src/hr tests
$ touch src/hr/__init__.py tests/.keep README.rst
$ pipenv --python python3.6 install --dev pytest pytest-mock

initialize the git repository:
$ git init
$ curl https://raw.githubusercontent.com/github/gitignore/master/Python.gitignore -o .gitignore
$ git add --all .
$ git commit -m 'Initial commit.'


To manually test, (temporarily) run the following from within your project’s directory:

$ sudo pip3.6 install -e .


## Exercise: Implementing User Management

Create a module in your package to work with user information. You’ll want to be able to do the following:

- Received a list of user dictionaries and ensure that the system’s users match.
- Have a function that can create a user with the given information if no user exists by that name.
- Have a function that can update a user based on a user dictionary.
- Have a function that can remove a user with a given username.
- The create, update, and remove functions should print that they are creating/updating/removing the user before executing the command.
- The user information will come in the form of a dictionary shaped like this:

{
  'name': 'kevin',
  'groups': ['wheel', 'dev'],
  'password': '$6$HXdlMJqcV8LZ1DIF$LCXVxmaI/ySqNtLI6b64LszjM0V5AfD.ABaUcf4j9aJWse2t3Jr2AoB1zZxUfCr8SOG0XiMODVj2ajcQbZ4H4/'
}

The password values will be SHA512 encrypted.

Hint: You can generate an encrypted password in Python that is usable with usermod -p with this snippet:

```py
import crypt

crypt.crypt('password', crypt.mksalt(crypt.METHOD_SHA512))
```

Then will be able to run the following to be able to use your module in a REPL without getting permissions errors for calling out to usermod, userdel, and useradd:

```py
sudo python3.6
>>> from hr import users
>>> password = '$6$HXdlMJqcV8LZ1DIF$LCXVxmaI/ySqNtLI6b64LszjM0V5AfD.ABaUcf4j9aJWse2t3Jr2AoB1zZxUfCr8SOG0XiMODVj2ajcQbZ4H4/'
>>> user_dict = {
...     'name': 'kevin',
...     'groups': ['wheel'],
...     'password': password
... }
>>> users.add(user_dict)
Adding user 'kevin'

>>> user_dict['groups'] = []

>>> users.update(user_dict)
Updating user 'kevin'

>>> users.remove(user_dict)
Removing user 'kevin'
>>>
```

## Exercise: JSON Parsing and Exporting

interacting with the user inventory file, a JSON file holds user information.

The module needs to:
- Have a function to read a given inventory file, parse the JSON, and return a list of user dictionaries.
- Have a function that takes a path, and produces an inventory file based on the current state of the system.
    - An optional parameter could be the specific users to export.

Python modules you’ll want to research:
- json - Interact with JSON from Python.
- grp - Group database.
- pwd - Password/user database.
- spwd - Shadow Password database. (Used to get current encrypted password)


Example inventory JSON file:

```JSON
[
  {
    "name": "kevin",
    "groups": ["wheel", "dev"],
    "password": "$6$HXdlMJqcV8LZ1DIF$LCXVxmaI/ySqNtLI6b64LszjM0V5AfD.ABaUcf4j9aJWse2t3Jr2AoB1zZxUfCr8SOG0XiMODVj2ajcQbZ4H4/"
  },
  {
    "name": "lisa",
    "groups": ["wheel"],
    "password": "$6$HXdlMJqcV8LZ1DIF$LCXVxmaI/ySqNtLI6b64LszjM0V5AfD.ABaUcf4j9aJWse2t3Jr2AoB1zZxUfCr8SOG0XiMODVj2ajcQbZ4H4/"
  },
  {
    "name": "jim",
    "groups": [],
    "password": "$6$HXdlMJqcV8LZ1DIF$LCXVxmaI/ySqNtLI6b64LszjM0V5AfD.ABaUcf4j9aJWse2t3Jr2AoB1zZxUfCr8SOG0XiMODVj2ajcQbZ4H4/"
  }
]
```

Hint: If you’re writing tests for this code you’ll need to heavily rely on mocking to make the interactions with modules like grp, pwd, and spwd consistent.


## Manually Test the Module
Load the Python3.6 REPL as root to interact with the new inventory module:

```py
$ sudo python3.6
>>> from hr import inventory
>>> inventory.dump('./inventory.json')
>>> exit()

```

look at the new inventory.json file to see that it dumped the users properly.

```c
$ cat inventory.json
[{"name": "kevin", "groups": ["wheel"], "password": "$6$HXdlMJqcV8LZ1DIF$LCXVxmaI/ySqNtLI6b64LszjM0V5AfD.ABaUcf4j9aJWse2t3Jr2AoB1zZxUfCr8SOG0XiMODVj2ajcQbZ4H4/"}]
```

---

## Exercise: Creating the Console Script

implemented all of the functionality that the hr tools needs, wire the pieces together and modify the package metadata to create a console script when installed.

- Implement `main` function that ties all of the modules together based on input to the CLI parser.
- Modify the `setup.py` so that when installed there is an hr console script.

```c
$ sudo pip3.6 install -e .
$ sudo hr --help
usage: hr [-h] [--export] path

positional arguments:
  path        the path to the inventory file (JSON)

optional arguments:
  -h, --help  show this help message and exit
  --export    export current settings to inventory file
```

---

## Exercise: Building a Wheel Distribution

Extra metadata file:

MANIFEST.in
```
include README.rst
recursive-include tests *.py
```

```c
$ python setup.py bdist_wheel
$ sudo pip3.6 install --upgrade dist/hr-0.1.0-py3-none-any.whl
```






.
