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
