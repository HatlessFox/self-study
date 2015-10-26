#! /usr/bin/env python3

# 0.6.1

import math

print("[0.6.1]")
print(math.pow(math.sqrt(3), 2))
#print(math.sqrt(-1))
print(math.cos(math.pi), math.log(math.e))

# 0.6.2

from random import randint

print("[0.6.2]")
def movie_review(name): return ["See it!", "A gem!", "Ideological claptrap!"][randint(0, 2)]
print({m: movie_review(m) for m in ['Terminator', 'Star Wars']})

import dictutil

# 0.6.6
def makeInverseIndex(strList):
    index = {}
    for doc_i, doc in dictutil.listrange2dict(strList).items():
        for word in doc.split():
            index[word] = index[word] | {doc_i} if word in index else {doc_i}
            
    return index
print("[0.6.6]")
print(makeInverseIndex(["blah boom bim", "two one boom"]))

# 0.6.7
from functools import reduce
def baseSearch(merge_policy, invIndex, query):
    return reduce(merge_policy, dictutil.dict2list(invIndex, query.split()))

def orSearch(invIndex, query):
    return baseSearch(lambda a, b: a | b, invIndex, query)

inv_ind_7 = makeInverseIndex(["blah boom bim", "two one boom"])
print("[0.6.7]")
print(orSearch(inv_ind_7, "five blah"))

# 0.6.8
def andSearch(invIndex, query):
    return baseSearch(lambda a, b: a & b, invIndex, query)

inv_ind_8 = makeInverseIndex(["blah boom bim", "two one boom"])
print("[0.6.8]")
print(orSearch(inv_ind_8, "boom blah"))

