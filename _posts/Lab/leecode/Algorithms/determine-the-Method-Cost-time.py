import timeit
import random

print(f"{'n':10s}{'list':>10s}{'dict':>10s}")


# verify the contain() time for dic and list
# for i in range(10_000, 1_000_001, 20_000):
#     t = timeit.Timer(f"random.randrange({i}) in x", "from __main__ import random, x")
#     x = list(range(i))
#     lst_time = t.timeit(number=1000)
#     x = {j: None for j in range(i)}
#     dict_time = t.timeit(number=1000)
#     print(f"{i:<10,} {lst_time:>10.3f} {dict_time:>10.3f}")

# n               list      dict
# 10,000          0.085      0.001
# 30,000          0.225      0.001
# 50,000          0.381      0.001
# 70,000          0.542      0.001
# 90,000          0.770      0.001
# 110,000         1.104      0.001
# 130,000         0.993      0.001
# 150,000         1.121      0.001
# 170,000         1.243      0.001
# 190,000         1.375      0.001
# 210,000         1.546      0.001


# verify that the list index operator is 𝑂(1)
# for i in range(10_000, 1_000_001, 20_000):
#     x = list(range(i))
#     t = timeit.Timer(f"x[random.randrange({i})]", "from __main__ import random, x")
#     lst_time = t.timeit(number=1000)
#     print(f"{i:<10,} {lst_time:>10.3f}")


# verify that get item and set item are 𝑂(1) for dictionaries.
# for i in range(10_000, 1_000_001, 20_000):
#     x = {i : None for j in range(i)}
#     t = timeit.Timer(f"random.randrange({i}) in x", "from __main__ import random, x")
#     dict_time = t.timeit(number=1000)
#     print(f"{i:<10,} {'list':>10s} {dict_time:>10.3f}")


# compares the performance of the del operator on lists and dictionaries.
for i in range(10_000, 1_000_001, 20_000):

    x = list(range(i))
    t1 = timeit.Timer("del x[random.randrange(len(x))]", "from __main__ import random, x")
    lst_time = t1.timeit(number=1000)

    x = {j: None for j in range(i)}
    # x = dict.fromkeys(range(i))
    t2 = timeit.Timer("del x[random.choice(list(x.keys()))]", "from __main__ import random, x")
    dict_time = t2.timeit(number=1000)
    print(f"{i:<10,} {lst_time:>10.3f} {dict_time:>10.3f}")

# n               list      dict
# 10,000          0.002      0.088
# 30,000          0.005      0.249
# 50,000          0.009      0.418
# 70,000          0.017      0.704
# 90,000          0.016      0.863
# 110,000         0.020      1.280
# 130,000         0.031      1.174
# 150,000         0.027      1.538
# 170,000         0.044      1.579
# 190,000         0.035      1.669



