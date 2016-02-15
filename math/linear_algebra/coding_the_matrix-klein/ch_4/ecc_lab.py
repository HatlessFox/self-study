# version code 80e56511a793+
# Please fill out this stencil and submit using the provided submission script.

from vec import Vec
from mat import Mat
from bitutil import bits2mat, str2bits, noise
from bitutil import bits2str, mat2bits
from GF2 import one, zero
from matutil import listlist2mat, coldict2mat, mat2coldict


## Task 1
""" Create an instance of Mat representing the generator matrix G. You can use
the procedure listlist2mat in the matutil module (be sure to import first).
Since we are working over GF (2), you should use the value one from the
GF2 module to represent 1"""
G = listlist2mat(
    [[one,  zero, one,  one ],
     [one,  one,  zero, one ],
     [zero, zero, zero, one ],
     [one,  one,  one,  zero],
     [zero, zero, one,  zero],
     [zero, one,  zero, zero],
     [one,  zero, zero, zero]])

## Task 2
# Please write your answer as a list. Use one from GF2 and 0 as the elements.
encoding_1001 = [zero, zero, one, one, zero, zero, one]


## Task 3
# Express your answer as an instance of the Mat class.
R = listlist2mat(
    [[zero, zero, zero, zero, zero, zero, one ],
     [zero, zero, zero, zero, zero, one,  zero],
     [zero, zero, zero, zero, one,  zero, zero],
     [zero, zero, one,  zero, zero, zero, zero]])

#print(R*G)

## Task 4
# Create an instance of Mat representing the check matrix H.
H = listlist2mat(
    [[zero, zero, zero, one,  one,  one,  one],
     [zero, one,  one,  zero, zero, one,  one],
     [one,  zero, one,  zero, one,  zero, one]]) 

#print(H*G)

## Task 5
def find_error(syndrome):
    """
    Input: an error syndrome as an instance of Vec
    Output: the corresponding error vector e
    Examples:
        >>> find_error(Vec({0,1,2}, {0:one})) == Vec({0, 1, 2, 3, 4, 5, 6},{3: one})
        True
        >>> find_error(Vec({0,1,2}, {2:one})) == Vec({0, 1, 2, 3, 4, 5, 6},{0: one})
        True
        >>> find_error(Vec({0,1,2}, {1:one, 2:one})) == Vec({0, 1, 2, 3, 4, 5, 6},{2: one})   
        True
        >>> find_error(Vec({0,1,2}, {})) == Vec({0,1,2,3,4,5,6}, {})
        True
    """
    err_idx = [2**(len(syndrome.D)-i-1) for i in syndrome.D
               if i in syndrome.f and syndrome.f[i] == one]
    return Vec(set(range(7)), {(sum(err_idx) - 1):one} if err_idx else {})

## Task 6
# Use the Vec class for your answers.
non_codeword = Vec({0,1,2,3,4,5,6}, {0: one, 1:0, 2:one, 3:one, 4:0, 5:one, 6:one})
error_vector = find_error(H*non_codeword)
code_word = non_codeword + error_vector
original = R * code_word

## Task 7
def find_error_matrix(S):
    """
    Input: a matrix S whose columns are error syndromes
    Output: a matrix whose cth column is the error corresponding to the cth column of S.
    Example:
        >>> S = listlist2mat([[0,one,one,one],[0,one,0,0],[0,0,0,one]])
        >>> find_error_matrix(S) == Mat(({0, 1, 2, 3, 4, 5, 6}, {0, 1, 2, 3}), {(1, 3): 0, (3, 0): 0, (2, 1): 0, (6, 2): 0, (5, 1): one, (0, 3): 0, (4, 0): 0, (1, 2): 0, (3, 3): 0, (6, 3): 0, (5, 0): 0, (2, 2): 0, (4, 1): 0, (1, 1): 0, (3, 2): one, (0, 0): 0, (6, 0): 0, (2, 3): 0, (4, 2): 0, (1, 0): 0, (5, 3): 0, (0, 1): 0, (6, 1): 0, (3, 1): 0, (2, 0): 0, (4, 3): one, (5, 2): 0, (0, 2): 0})
        True
    """
    return coldict2mat({c:find_error(v) for c, v in mat2coldict(S).items()})

## Task 8
s = "I'm trying to free your mind, Neo. But I can only show you the door. You're the one that has to walk through it."
P = bits2mat(str2bits(s))

#assert s == bits2str(str2bits(s))
#assert s == bits2str(mat2bits(bits2mat(str2bits(s))))

## Task 4.14.11
#print(bits2str(mat2bits(P + noise(P, 0.02))))

## Task 9
C = G*P
bits_before = len(mat2bits(P))
bits_after = len(mat2bits(C))

#print("Before: {}; After: {}. Rate: {}\n".format(
#    bits_before, bits_after, float(bits_after) / bits_before))


## Ungraded Task
CTILDE = C + noise(C, 0.02)
#print(bits2str(mat2bits(R*CTILDE)))


## Task 10
def correct(A):
    """
    Input: a matrix A each column of which differs from a codeword in at most one bit
    Output: a matrix whose columns are the corresponding valid codewords.
    Example:
        >>> A = Mat(({0,1,2,3,4,5,6}, {1,2,3}), {(0,3):one, (2, 1): one, (5, 2):one, (5,3):one, (0,2): one})
        >>> correct(A) == Mat(({0, 1, 2, 3, 4, 5, 6}, {1, 2, 3}), {(0, 1): 0, (1, 2): 0, (3, 2): 0, (1, 3): 0, (3, 3): 0, (5, 2): one, (6, 1): 0, (3, 1): 0, (2, 1): 0, (0, 2): one, (6, 3): one, (4, 2): 0, (6, 2): one, (2, 3): 0, (4, 3): 0, (2, 2): 0, (5, 1): 0, (0, 3): one, (4, 1): 0, (1, 1): 0, (5, 3): one})
        True
    """
    return A + find_error_matrix(H*A)

# Task 4.14.15
#print("*Corrupted:\n\t" + bits2str(mat2bits(R*CTILDE)))
#print("*Corrected:\n\t" + bits2str(mat2bits(R*correct(CTILDE))))

# Task 4.14.16
def error_test(s, prob):
    msg = bits2mat(str2bits(s))
    cw = G*msg
    rcvd = cw + noise(cw, prob)
    msg_rcvd = R*correct(rcvd)
    return s == bits2str(mat2bits(msg_rcvd))


def test_correction_rate(trials, base_prob):
    for i in range(0, 6):
        success_num = 0
        for _ in range(0, trials):
            if error_test(s, i * base_prob):
                success_num += 1
        print("Error {:.2%} -- {:.2%}".format(i*base_prob, float(success_num)/trials))

#test_correction_rate(100, 0.01)
