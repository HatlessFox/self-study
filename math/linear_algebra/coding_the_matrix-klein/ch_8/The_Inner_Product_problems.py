# version code 80e56511a793+
# Please fill out this stencil and submit using the provided submission script.

from vec import *
from mat import *
from math import sqrt
from matutil import *

DO_EXTRA_TESTS = False

## Task 8.3.15

def proj_mtx(v):
    return coldict2mat([v])*rowdict2mat([v]) * (1/sqrt(v*v))

if DO_EXTRA_TESTS:
    print("== Task 8.3.15 ==")
    print(proj_mtx(Vec({1, 2}, {1:1, 2:1})))

## 1: (Problem 8.6.1) Norm
norm1 = 3
norm2 = 4
norm3 = 3



## 2: (Problem 8.6.2) Closest Vector
# Write each vector as a list
closest_vector_1 = [8/5, 16/5]
closest_vector_2 = [0, 1, 0]
closest_vector_3 = [3, 2, 1, -4]



## 3: (Problem 8.6.3) Projection Orthogonal to and onto Vectors
# Write each vector as a list
# round up to 6 decimal points if necessary
project_onto_1 = [2, 0]
projection_orthogonal_1 = [0, 1]

project_onto_2 = [-1/6, -1/3, 1/6]
projection_orthogonal_2 = [7/6, 4/3, 23/6]

project_onto_3 = [1, 1, 4]
projection_orthogonal_3 = [0, 0, 0]

