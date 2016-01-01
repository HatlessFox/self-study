# version code c2eb1c41017f+
# Please fill out this stencil and submit using the provided submission script.

import random
from GF2 import one
from vecutil import list2vec
from independence import is_independent


## 1: (Task 7.7.1) Choosing a Secret Vector
def randGF2(): return random.randint(0,1)*one

a0 = list2vec([one, one,   0, one,   0, one])
b0 = list2vec([one, one,   0,   0,   0, one])

def choose_secret_vector(s,t):
    for _ in range(100000):
        candidate = list2vec([randGF2() for _ in range(len(a0.D))])
        if candidate * a0 == s and candidate * b0 == t:
            return candidate
    raise Exception("Unable to choose secret vector")


## 2: (Task 7.7.2) Finding Secret Sharing Vectors
# Give each vector as a Vec instance

def is_token_pair_suitable(tokens, candidate):
    if len(tokens) == 2:
        return is_independent(tokens + candidate)
    
    pairs_cnt = len(tokens) // 2
    for i in range(pairs_cnt):
        for j in range(i + 1, pairs_cnt):
            if not is_independent([tokens[2*i], tokens[2*i + 1],
                                   tokens[2*j], tokens[2*j + 1]] +
                                   candidate):
                    return False
    return True

def choose_tokens(a, b):
    tokens = [a, b]
    for _ in range(1000000):
        token_A = list2vec([randGF2() for _ in range(len(a0.D))])
        token_B = list2vec([randGF2() for _ in range(len(a0.D))])
        if is_token_pair_suitable(tokens, [token_A, token_B]):
            tokens = tokens + [token_A, token_B]
        if len(tokens) == 10:
            return tokens
    raise Exception("Unable to pick tokens")

tokens = choose_tokens(a0, b0)

secret_a0 = tokens[0]
secret_b0 = tokens[1]
secret_a1 = tokens[2]
secret_b1 = tokens[3]
secret_a2 = tokens[4]
secret_b2 = tokens[5]
secret_a3 = tokens[6]
secret_b3 = tokens[7]
secret_a4 = tokens[8]
secret_b4 = tokens[9]
