## 2.3.2

from plotting import plot

l = [[2, 2], [3, 2], [1.75, 1], [2, 1], \
     [2.25, 1], [2.5, 1], [2.75, 1], \
     [3, 1], [3.25, 1]]

#plot(l, 4)

## 2.4.3

def add2(v1, v2):
    return [i+j for i,j in zip(v1, v2)]

#plot([add2(v, [1, 2]) for v in l], 4)

## 2.5.4

def scalar_vec_mult(a, v):
    return [a*e for e in v]

#plot([scalar_vec_mult( .5, v) for v in l], 4)
#plot([scalar_vec_mult(-.5, v) for v in l], 4)

## 2.6.9

def segment(p1, p2):
    return [[float(p1[0]*i + p2[0]*(100-i))/100, \
             float(p1[1]*i + p2[1]*(100-i))/100] \
             for i in range(101)]

#plot(segment([3.5,3], [.5,1]), 4)

## 2.11.4

from vec import Vec

def zero_vec(D): return Vec(D, {})

def triangular_solve_n(rowlist, b):
    D = rowlist[0].D
    n = len(D)
    assert D == set(range(n))
    x = zero_vec(D)
    for i in reversed(range(n)):
        x[i] = (b[i] - rowlist[i] * x) / rowlist[i][i]
    return x

#print(triangular_solve_n([Vec({0, 1, 2}, {0:1, 1:-3, 2:-2}), \
#                          Vec({0, 1, 2}, {1:2, 2:4}),        \
#                          Vec({0, 1, 2}, {2:-10})],          \
#                         Vec({0, 1, 2}, {0:7, 1:4, 2:12})))
