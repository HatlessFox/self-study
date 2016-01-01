# version code c2eb1c41017f+
# Please fill out this stencil and submit using the provided submission script.

from matutil import *
from GF2 import one

DO_EXTRA_TESTS = False

## 1: (Problem 7.9.2) Recognizing Echelon Form
# Write each matrix as a list of row lists

echelon_form_1 = [[1, 2, 0, 2, 0],
                  [0, 1, 0, 3, 4],
                  [0, 0, 2, 3, 4],
                  [0, 0, 0, 2, 0],
                  [0, 0, 0, 0, 4]]

echelon_form_2 = [[0, 4, 3, 4, 4],
                  [0, 0, 4, 2, 0],
                  [0, 0, 0, 0, 1],
                  [0, 0, 0, 0, 0]]

echelon_form_3 = [[1, 0, 0, 1],
                  [0, 0, 0, 1],
                  [0, 0, 0, 0]]

echelon_form_4 = [[1, 0, 0, 0],
                  [0, 1, 0, 0],
                  [0, 0, 0, 0],
                  [0, 0, 0, 0]]

## 2: (Problem 7.9.3) Is it echelon?
def is_echelon(A):
    '''
    Input:
        - A: a list of row lists
    Output:
        - True if A is in echelon form
        - False otherwise
    Examples:
        >>> is_echelon([[1,1,1],[0,1,1],[0,0,1]])
        True
        >>> is_echelon([[0,1,1],[0,1,0],[0,0,1]])
        False
        >>> is_echelon([[1,1]])
        True
        >>> is_echelon([[1]])
        True
        >>> is_echelon([[1],[1]])
        False
        >>> is_echelon([[0]])
        True
        >>> is_echelon([[0],[1]])
        False
    '''
    pivot = -1
    for row in A:
        next_pivot = len(row) + 1
        for i, e in enumerate(row):
            if e == 0: continue
            next_pivot = i
            break
        if next_pivot <= pivot and next_pivot <= len(row):
            return False
        pivot = next_pivot
    return True

if DO_EXTRA_TESTS:
    assert is_echelon([[2, 1, 0], [0, -4, 0], [0, 0, -1]])
    assert not is_echelon([[2, 1, 0], [-4, 0, 0], [0, 0, 1]])
    assert not is_echelon([[2, 1, 2], [0, 3, 0], [1, 0, 1]])
    assert is_echelon([[1, 1, 1, 1, 1], [0, 2, 0, 1, 3], [0, 0, 0, 5, 3]])

## 3: (Problem 7.9.4) Solving with Echelon Form: No Zero Rows
# Give each answer as a list

echelon_form_vec_a = [1, 0, 3,0]
echelon_form_vec_b = [-3, 0, -2, 3]
echelon_form_vec_c = [-5, 0, 2, 0, 2]



## 4: (Problem 7.9.5) Solving with Echelon Form
# If a solution exists, give it as a list vector.
# If no solution exists, provide "None" (without the quotes).

solving_with_echelon_form_a = None
solving_with_echelon_form_b = [21, 0, 2, 0, 0]



## 5: (Problem 7.9.6) Echelon Solver
def echelon_solve(rowlist, label_list, b):
    '''
    Input:
        - rowlist: a list of Vecs
        - label_list: a list of labels establishing an order on the domain of
                      Vecs in rowlist
        - b: a vector (represented as a list)
    Output:
        - Vec x such that rowlist * x is b
    >>> D = {'A','B','C','D','E'}
    >>> U_rows = [Vec(D, {'A':one, 'E':one}), Vec(D, {'B':one, 'E':one}), Vec(D,{'C':one})]
    >>> b_list = [one,0,one]
    >>> cols = ['A', 'B', 'C', 'D', 'E']
    >>> echelon_solve(U_rows, cols, b_list) == Vec({'B', 'C', 'A', 'D', 'E'},{'B': 0, 'C': one, 'A': one})
    True
    '''
    x = Vec(rowlist[0].D, {})
    for row, b_row in zip(rowlist[::-1], b[::-1]):
        pivot_c = None
        for c in label_list:
            if row[c] != 0:
                pivot_c = c
                break
        if pivot_c == None:
            continue
        x[c] = (b_row - row * x) / row[c]
    return x
        
if DO_EXTRA_TESTS:
    print(" == 7.9.6 ==")
    D = {'A', 'B', 'C', 'D', 'E'}
    cols = ['A', 'B', 'C', 'D', 'E']
    print(echelon_solve([Vec(D, {'A':one, 'C':one, 'D':one}),
                         Vec(D, {'B':one, 'E':one}),
                         Vec(D, {'C':one, 'E':one}),
                         Vec(D, {'E':one})], cols,
                        [one, 0, one, one]))
    print(echelon_solve([Vec(D, {'A':one, 'B':one, 'D':one}),
                         Vec(D, {'B':one, 'D':one, 'E':one}),
                         Vec(D, {'C':one, 'E':one}),
                         Vec(D, {})], cols,
                        [one, 0, one, 0]))
    


## 6: (Problem 7.9.7) Solving General Matrices via Echelon
D = {'A', 'B', 'C', 'D'}
rowlist = [Vec(D, {'A':one, 'B':one, 'D':one}),
           Vec(D, {'B':one}),
           Vec(D, {'C':one}),
           Vec(D, {'D':one})]    # Provide as a list of Vec instances
label_list = ['A', 'B', 'C', 'D'] # Provide as a list
b = [one, one, 0, 0]          # Provide as a list of GF(2) values



## 7: (Problem 7.9.8) Nullspace A
null_space_rows_a = {3, 4} # Put the row numbers of M from the PDF



## 8: (Problem 7.9.9) Nullspace B
null_space_rows_b = {4} # Put the row numbers of M from the PDF

