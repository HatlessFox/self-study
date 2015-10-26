#! /usr/bin/env python3

# 0.5.1
print("Minutes in a week {}".format(7*24*60))

# 0.5.2
assert ((2304811 - (2304811 // 47) * 47) == 2304811 % 47)

# 0.5.3
print("Sum of 673 and 909 is divisible by 3: {}".format((673 + 909) % 3 == 0))

# 0.5.4
x, y = -9, 1/2
assert 2**(y + 1/2) if x + 10 < 0 else 2**(y - 1/2) == 1

# 0.5.5
print("[0.5.5] {}".format({x**2 for x in {1, 2, 3, 4, 5}}))

# 0.5.6
print("[0.5.6] {}".format({2**x for x in {0, 1, 2, 3, 4}}))

# 0.5.7
assert len({x * y for x in {1, 2, 3} for y in {1, 5, 7}}) == 9

# 0.5.8
assert len({x * y for x in {3, 6, 12} for y in {1, 2, 4}}) == 5

# 0.5.9
s, t = {1, 2, 3, 4, 5}, {3, 4, 5, 6}
assert { x for x in s if x in t} == s & t

# 0.5.10
print("[0.5.10] Avg: {}".format(sum([20, 10, 15, 75]) / 4))

# 0.5.11
print("[0.5.11] {}".format([[x, y] for x in ['A', 'B', 'C'] for y in [1, 2, 3]]))

# 0.5.12
data_12 = [[.25, .75, .1], [-1, 0], [4, 4, 4, 4]]
print("[0.5.12] {}".format(sum( [sum(lst) for lst in data_12] )))

# 0.5.13
# [x, y, z] = [1, 2] -- error
# [x, y] = [1, 2, 3] -- error

# 0.5.14
data_14 = {-4, -2, 1, 2, 5, 0}
print("[0.5.14] {}".format([(i, j, k) for i in data_14 for j in data_14 for k in data_14 if i + j + k == 0]))

# 0.5.15
print("[0.5.15] {}".format([(i, j, k) for i in data_14 for j in data_14 for k in data_14 if i + j + k == 0 and (i, j, k) != (0, 0, 0)]))

# 0.5.16
print("[0.5.16] {}".format([(i, j, k) for i in data_14 for j in data_14 for k in data_14 if i + j + k == 0 and (i, j, k) != (0, 0, 0)][0]))

# 0.5.17
data_17 = [1, 2, 1]
assert len(data_17) != len(list(set(data_17)))

# 0.5.18
print("[0.5.18] {}".format(list(range(1,100, 2))))

# 0.5.19
data_19 = ['A', 'B', 'C', 'D', 'E']
print("[0.5.19] {}".format(list(zip(range(len(data_19)), data_19))))

# 0.5.20
print("[0.5.20] {}".format([ i + j for (i, j) in zip([10, 25, 40], [1, 15, 20])]))

# 0.5.21
data_21 = [{ 'James': 'Sean', 'directior': 'Tarence'}, {'James': 'Roger', 'director': 'Lewis'}, {'James': 'Pierce', 'director': 'Roger'}];
print("[0.5.21] {}".format([d['James'] for d in data_21]))

# 0.5.22
data_22 = [{'Bilbo': 'Ian', 'Frodo': 'Elijah'}, {'Bilbo': 'Martin', 'Thorin': 'Richard'}];
k22_a = 'Bilbo'
print("[0.5.22-a] {}".format([d[k22_a] if k22_a in d else "NOT PRESENT" for d in data_22]))
k22_b = 'Frodo'
print("[0.5.22-b] {}".format([d[k22_b] if k22_b in d else "NOT PRESENT" for d in data_22]))

# 0.5.23
print("[0.5.23] {}".format({ i:i**2 for i in range(100)}))

# 0.5.24
print("[0.5.24] {}".format({ c:c for c in {'red', 'white', 'blue'}}))

# 0.5.25
def task_25(base):
    digits = set(range(base))
    return { ((i * base) + j) * base + k:[i, j, k] for i in digits for j in digits for k in digits}

print("[0.5.25] {}".format(task_25(2)))

# 0.5.26
data_26 = {0: 1000.0, 3:990, 1:1200.50}
print("[0.5.26] {}".format({ name: data_26[i] for i, name in enumerate(['Larry', 'Curly', '', 'Moe']) if i in data_26}))

# 0.5.28
def nextInts(l): return [i+1 for i in l]
print("[0.5.29] {}".format(nextInts([1, 5, 7])))

# 0.5.29
def cubes(l): return [i**3 for i in l]
print("[0.5.29] {}".format(cubes([1, 2, 3])))

# 0.5.30
def dict2list(d, kl): return [d[k] for k in kl if k in d]
print("[0.5.30] {}".format(dict2list({'a': 'A', 'b': 'B', 'c': 'C'}, ['b', 'c', 'a'])))

# 0.5.31
def list2dict(l, kl): return {kl[i]: l[i] for i in range(len(kl))}
print("[0.5.31] {}".format(list2dict(['A', 'B', 'C'], ['a', 'b', 'c'])))

# 0.5.32
def all_3_dig_nums(base, digs): return [((i*base)+j)*base+k for i in digs for j in digs for k in digs]
print("[0.5.32] {}".format(all_3_dig_nums(3, {0, 1, 2})))
