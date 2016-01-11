# version code 80e56511a793+
# Please fill out this stencil and submit using the provided submission script.

from mat import Mat
from vec import Vec
from vecutil import list2vec, zero_vec
from matutil import listlist2mat, rowdict2mat, mat2rowdict, mat2coldict, coldict2mat


from orthogonalization import orthogonalize, aug_orthogonalize
from math import sqrt

def find_ortho_compl(u, w):
    ortho = orthogonalize(u + w)
    return [u for u in ortho[len(u):] if not u.is_almost_zero()]

## 1: (Problem 9.11.1) Generators for orthogonal complement
U_vecs_1 = [list2vec([0,0,3,2])]
W_vecs_1 = [list2vec(v) for v in [[1,2,-3,-1],[1,2,0,1],[3,1,0,-1],[-1,-2,3,1]]]
# Give a list of Vecs
ortho_compl_generators_1 = find_ortho_compl(U_vecs_1, W_vecs_1)

U_vecs_2 = [list2vec([3,0,1])]
W_vecs_2 = [list2vec(v) for v in [[1,0,0],[1,0,1]]]

# Give a list of Vecs
ortho_compl_generators_2 = find_ortho_compl(U_vecs_2, W_vecs_2)

U_vecs_3 = [list2vec(v) for v in [[-4,3,1,-2],[-2,2,3,-1]]]
W_vecs_3 = [list2vec(v) for v in [[1,0,0,0],[0,1,0,0],[0,0,1,0],[0,0,0,1]]]

# Give a list of Vecs
ortho_compl_generators_3 = find_ortho_compl(U_vecs_3, W_vecs_3)



## 2: (Problem 9.11.3) Basis for null space
# Your solution should be a list of Vecs
null_space_basis = find_ortho_compl([list2vec([-4, -1, -3, -2]),
                                     list2vec([0, 4, 0, -1])],
                                    [list2vec([1, 0, 0, 0]),
                                     list2vec([0, 1, 0, 0]),
                                     list2vec([0, 0, 1, 0]),
                                     list2vec([0, 0, 0, 1])])



## 3: (Problem 9.11.9) Orthonormalize(L)
def orthonormalize(L):
    '''
    Input: a list L of linearly independent Vecs
    Output: A list Lstar of len(L) orthonormal Vecs such that, for all i in range(len(L)),
            Span L[:i+1] == Span Lstar[:i+1]

    >>> from vec import Vec
    >>> D = {'a','b','c','d'}
    >>> L = [Vec(D, {'a':4,'b':3,'c':1,'d':2}), Vec(D, {'a':8,'b':9,'c':-5,'d':-5}), Vec(D, {'a':10,'b':1,'c':-1,'d':5})]
    >>> for v in orthonormalize(L): print(v)
    ... 
    <BLANKLINE>
        a     b     c     d
    -----------------------
     0.73 0.548 0.183 0.365
    <BLANKLINE>
         a     b      c      d
    --------------------------
     0.187 0.403 -0.566 -0.695
    <BLANKLINE>
         a      b      c     d
    --------------------------
     0.528 -0.653 -0.512 0.181
    '''
    return [v/sqrt(v*v) for v in orthogonalize(L) if 1e-50 < v*v]



## 4: (Problem 9.11.10) aug_orthonormalize(L)
def aug_orthonormalize(L):
    '''
    Input:
        - L: a list of Vecs
    Output:
        - A pair Qlist, Rlist of lists such that:
            * coldict2mat(L) == coldict2mat(Qlist) * coldict2mat(Rlist)
            * Qlist = orthonormalize(L)
            
    >>> from vec import Vec
    >>> D={'a','b','c','d'}
    >>> L = [Vec(D, {'a':4,'b':3,'c':1,'d':2}), Vec(D, {'a':8,'b':9,'c':-5,'d':-5}), Vec(D, {'a':10,'b':1,'c':-1,'d':5})]
    >>> Qlist, Rlist = aug_orthonormalize(L)
    >>> from matutil import coldict2mat
    >>> print(coldict2mat(Qlist))
    <BLANKLINE>
               0      1      2
         ---------------------
     a  |   0.73  0.187  0.528
     b  |  0.548  0.403 -0.653
     c  |  0.183 -0.566 -0.512
     d  |  0.365 -0.695  0.181
    <BLANKLINE>
    >>> print(coldict2mat(Rlist))
    <BLANKLINE>
              0    1      2
         ------------------
     0  |  5.48 8.03   9.49
     1  |     0 11.4 -0.636
     2  |     0    0   6.04
    <BLANKLINE>
    >>> print(coldict2mat(Qlist)*coldict2mat(Rlist))
    <BLANKLINE>
           0  1  2
         ---------
     a  |  4  8 10
     b  |  3  9  1
     c  |  1 -5 -1
     d  |  2 -5  5
    <BLANKLINE>
    '''

    def to_R(vecs, sigma):
        r_rows = mat2rowdict(rowdict2mat(sigma).transpose())
        r_rows_scaled = [sqrt(v*v)*r_rows[i] for i, v in enumerate(vecs)]
        r_cols = mat2coldict(rowdict2mat(r_rows_scaled))
        return [r_cols[i] for i in sorted(r_cols.keys())]
        
    
    v, sigma = aug_orthogonalize(L)
    r = to_R(v, sigma)
    return (orthonormalize(v), r)

## 5: (Problem 9.11.11) QR factorization of small matrices
#Compute the QR factorization

#Please represent your solution as a list of rows, such as [[1,0,0],[0,1,0],[0,0,1]]


def qr_fact(cols):
    return [coldict2mat(cols) for cols in aug_orthonormalize(cols)]

#D=set(range(3))
#for m in qr_fact([Vec(D, {0:6, 1:2, 2:3}),
#                  Vec(D, {0:6, 1:0, 2:3})]):
#    print(m)
#for m in qr_fact([Vec(D, {0:2, 1:2, 2:1}),
#                  Vec(D, {0:3, 1:1, 2:1})]):
#    print(m)


part_1_Q = [[0.857, 0.256], [0.286, -0.958], [0.429, 0.128]]
part_1_R = [[7, 6.43], [0, 1.92]]

part_2_Q = [[0.667, 0.707], [0.667, -0.707], [0.333, 0]]
part_2_R = [[3, 3], [0, 1.41]]



## 6: (Problem 9.11.12) QR Solve
from matutil import mat2coldict, coldict2mat
from dictutil import dict2list, list2dict



def QR_factor(A):
    col_labels = sorted(A.D[1], key=repr)
    Acols = dict2list(mat2coldict(A),col_labels)
    Qlist, Rlist = aug_orthonormalize(Acols)
    #Now make Mats
    Q = coldict2mat(Qlist)
    R = coldict2mat(list2dict(Rlist, col_labels))
    return Q,R

def triangular_solve(rowlist, label_list, b):
    D = rowlist[0].D
    x = zero_vec(D)
    for j in reversed(range(len(D))):
        c = label_list[j]
        row = rowlist[j]
        x[c] = (b[j] - x*row)/row[c] if row[c] != 0 else 1 
    return x

def QR_solve(A, b):
    '''
    Input:
        - A: a Mat with linearly independent columns
        - b: a Vec whose domain equals the set of row-labels of A
    Output:
        - vector x that minimizes norm(b - A*x)
    Note: This procedure uses the procedure QR_factor, which in turn uses dict2list and list2dict.
           You wrote these procedures long back in python_lab.  Make sure the completed python_lab.py
           is in your matrix directory.
    Example:
        >>> domain = ({'a','b','c'},{'A','B'})
        >>> A = Mat(domain,{('a','A'):-1, ('a','B'):2,('b','A'):5, ('b','B'):3,('c','A'):1,('c','B'):-2})
        >>> Q, R = QR_factor(A)
        >>> b = Vec(domain[0], {'a': 1, 'b': -1})
        >>> x = QR_solve(A, b)
        >>> result = A.transpose()*(b-A*x)
        >>> result.is_almost_zero()
        True
    '''
    Q, R = QR_factor(A)
    R_rows = mat2rowdict(R)
    R_rows = [R_rows[r] for r in
              sorted(R_rows.keys(), key=repr)]
    return triangular_solve(R_rows,
                            sorted(A.D[1],key=repr),
                            Q.transpose()*b)

## 7: (Problem 9.11.13) Least Squares Problem
# Please give each solution as a Vec

def least_squares(Q, R, b):
    R_rows = mat2rowdict(R)
    R_rows = [R_rows[r] for r in
              sorted(R_rows.keys(), key=repr)]
    return triangular_solve(R_rows,
                            sorted(R.D[1],key=repr),
                            Q.transpose()*b)

least_squares_A1 = listlist2mat([[8, 1], [6, 2], [0, 6]])
least_squares_Q1 = listlist2mat([[.8,-0.099],[.6, 0.132],[0,0.986]])
least_squares_R1 = listlist2mat([[10,2],[0,6.08]])
least_squares_b1 = list2vec([10, 8, 6])

#print(least_squares(least_squares_Q1, least_squares_R1,
#                    least_squares_b1))

x_hat_1 = list2vec([1.08, 0.984])


#print(least_squares_A1.transpose()*
#      (least_squares_b1-least_squares_A1*x_hat_1))

least_squares_A2 = listlist2mat([[3, 1], [4, 1], [5, 1]])
least_squares_Q2 = listlist2mat([[.424, .808],[.566, .115],[.707, -.577]])
least_squares_R2 = listlist2mat([[7.07, 1.7],[0,.346]])
least_squares_b2 = list2vec([10,13,15])

x_hat_2 = list2vec([2.5, 2.66])

#print(least_squares_A2.transpose()*
#      (least_squares_b2-least_squares_A2*x_hat_2))


## 8: (Problem 9.11.14) Small examples of least squares
#Find the vector minimizing (Ax-b)^2

#Please represent your solution as a list

#D = set(range(3))
#print(QR_solve(coldict2mat([Vec(D, {0:8, 1:6}),
#                            Vec(D, {0:1, 1:2, 2:6})]),
#               Vec(D, {0:10, 1:8, 2:6})))

#D = set(range(2))
#print(QR_solve(coldict2mat([Vec(D, {0:3, 1:4}),
#                            Vec(D, {0:1, 1:1})]),
#               Vec(D, {0:10, 1:13})))


your_answer_1 = [1.08, 0.984]
your_answer_2 = [3, 1]



## 9: (Problem 9.11.15) Linear regression example
#Find a and b for the y=ax+b line of best fit

from read_data import read_vectors

def find_a_b(fname):
    data = read_vectors(fname)

    a_row_d = set(range(2))
    a_rows, b = [], Vec(set(range(len(data))), {})
    for i, v in enumerate(data):
        a_rows.append(Vec(a_row_d, {0: v['age'], 1:1}))
        b[i] = v['height']

    coeffs = QR_solve(rowdict2mat(a_rows), b)
    return (coeffs[0], coeffs[1])
    

#print(find_a_b("age-height.txt"))    
a = 0.6349650349650368
b = 64.92832167832164

### Task 9.11.16

from cancer_data import *

def signum(u): return Vec(u.D, {d:1 if 0 <= u[d] else -1 for d in u.D})
def fraction_wrong(A, b, w): return .5 - signum(A*w)*b / len(b.D) / 2

def test_cancer():
    A, b = read_training_data("train.data")
    coeffs = QR_solve(A, b)
    print("Train: {:.1%} (wrong)".format(fraction_wrong(A, b, coeffs)))
    A, b = read_training_data("validate.data")
    print("Validate: {:.1%} (wrong)".format(fraction_wrong(A, b, coeffs)))

test_cancer()
