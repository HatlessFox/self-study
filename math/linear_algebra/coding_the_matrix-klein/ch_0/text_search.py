#! /usr/bin/env python3

import sys
import dictutil
from functools import reduce

def make_inverse_index(strList):
    index = {}
    for doc_i, doc in dictutil.listrange2dict(strList).items():
        for word in doc.split():
            index[word] = index[word] | {doc_i} if word in index else {doc_i}
            
    return index

def __base_search__(merge_policy, invIndex, query):
    return reduce(merge_policy, dictutil.dict2list(invIndex, query.split()))

def or_search(invIndex, query):
    return __base_search__(lambda a, b: a | b, invIndex, query)

def and_search(invIndex, query):
    return __base_search__(lambda a, b: a & b, invIndex, query)

SEARCH_TYPES = {"or", "and"}

if __name__ == "__main__":
    if len(sys.argv) == 1:
        print("Args: filename to index")
        sys.exit(-1)
    with open(sys.argv[1]) as f:
        idx = make_inverse_index(line for line in f)
        
    while (True):
        srch_type = input("Enter type {}: ".format(SEARCH_TYPES))
        if srch_type not in SEARCH_TYPES:
            print("Wrong search type... Try again")
            continue
        
        executor = and_search if srch_type == "and" else or_search
        query = input("Enter query: ")
        print(executor(idx, query))
