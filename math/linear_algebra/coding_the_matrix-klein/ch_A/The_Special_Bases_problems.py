## author bichybich (w/o hat)

from math import fabs

EPS = 0.001

def feq_vec(v1, v2):
    if v1.D != v2.D: return False
    return [k for k in v1.D if 0.001 < fabs(v1[k] - v2[k])] != 0

## Task 10.11.1
def orthogonal_vec2rep(Q, b):
    '''
    >>> from vec import Vec
    >>> from mat import Mat
    >>> from math import sqrt
    >>>
    >>> domain = set(range(3))
    >>> b = Vec(domain, {0:10, 1:20, 2:30})
    >>> x, y, z = 1/sqrt(2), 1/sqrt(3), 1/sqrt(6)
    >>> q = Mat((domain, domain), {          \
              (0,0): x, (0,1): x,            \
              (1,0): y, (1,1):-y, (1,2):  y, \
              (2,0):-z, (2,1): z, (2,2):2*z })
    >>> feq_vec(orthogonal_vec2rep(q,b), \
                Vec(domain, {0:21.213, 1:11.547, 2:28.57}))
    True
    '''
    return b*Q.transpose()

## Tast 10.11.2

def orthogonal_change_of_basis(A, B, a):
    '''
    >>> from vec import Vec
    >>> from mat import Mat
    >>> from math import sqrt
    >>>
    >>> domain = set(range(3))
    >>> x, y, z = 1/sqrt(2), 1/sqrt(3), 1/sqrt(6)
    >>> A = B = Mat((domain, domain), {          \
                  (0,0): x, (0,1): x,            \
                  (1,0): y, (1,1):-y, (1,2):  y, \
                  (2,0):-z, (2,1): z, (2,2):2*z })
    >>> a = Vec(domain, {0:1/x, 1:y, 2:2*z})
    >>> print(feq_vec(orthogonal_change_of_basis(A, B, a), \
                      Vec(domain, {0:0.876, 1:.538, 2:1.393})))
    True
    '''
    return a*A*B

## Tast 10.11.3

def orthonormal_projection_orthogonal(W, b):
    '''
    >>> from vec import Vec
    >>> from mat import Mat
    >>> from math import sqrt
    >>>
    >>> domain = set(range(3))
    >>> x, y, z = 1/sqrt(2), 1/sqrt(3), 1/sqrt(6)
    >>> W = Mat((set(range(2)), domain), {          \
              (0,0): x, (0,1): x,            \
              (1,0): y, (1,1):-y, (1,2):  y})
    >>> b = Vec(domain, {0:10, 1:20, 2:30})
    >>> feq_vec(orthonormal_projection_orthogonal(W, b), \
                Vec(domain, {0:-35/3, 1:35/3, 2:70/3}))
    True
    '''
    return b - W*b*W
