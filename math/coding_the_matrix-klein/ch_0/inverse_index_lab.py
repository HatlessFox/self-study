#! /usr/bin/env python3
# version code 80e56511a793+
# Please fill out this stencil and submit using the provided submission script.

# 0.6.1
##import math
##print("[0.6.1]")
##print(math.pow(math.sqrt(3), 2))
###print(math.sqrt(-1))
##print(math.cos(math.pi), math.log(math.e))

## 1: (Task 0.6.2) Movie Review
## Task 1
from random import randint
def movie_review(name):
    """
    Input: the name of a movie
    Output: a string (one of the review options), selected at random using randint
    """
    return ["See it!", "A gem!", "Ideological claptrap!"][randint(0, 2)]

## 2: (Task 0.6.6) Make Inverse Index
import dictutil
def makeInverseIndex(strList):
    """
    Input: a list of documents as strings
    Output: a dictionary that maps each word in any document to the set consisting of the
            document ids (ie, the index in the strlist) for all documents containing the word.
    Distinguish between an occurence of a string (e.g. "use") in the document as a word
    (surrounded by spaces), and an occurence of the string as a substring of a word (e.g. "because").
    Only the former should be represented in the inverse index.
    Feel free to use a loop instead of a comprehension.

    Example:
    >>> makeInverseIndex(['hello world','hello','hello cat','hellolot of cats']) == {'hello': {0, 1, 2}, 'cat': {2}, 'of': {3}, 'world': {0}, 'cats': {3}, 'hellolot': {3}}
    True
    """
    index = {}
    for doc_i, doc in dictutil.listrange2dict(strList).items():
        for word in doc.split():
            index[word] = index[word] | {doc_i} if word in index else {doc_i}
            
    return index

from functools import reduce
def baseSearch(merge_policy, invIndex, query):
    return reduce(merge_policy, dictutil.dict2list(invIndex, query))

## 3: (Task 0.6.7) Or Search
def orSearch(inverseIndex, query):
    """
    Input: an inverse index, as created by makeInverseIndex, and a list of words to query
    Output: the set of document ids that contain _any_ of the specified words
    Feel free to use a loop instead of a comprehension.
    
    >>> idx = makeInverseIndex(['Johann Sebastian Bach', 'Johannes Brahms', 'Johann Strauss the Younger', 'Johann Strauss the Elder', ' Johann Christian Bach',  'Carl Philipp Emanuel Bach'])
    >>> orSearch(idx, ['Bach','the'])
    {0, 2, 3, 4, 5}
    >>> orSearch(idx, ['Johann', 'Carl'])
    {0, 2, 3, 4, 5}
    >>> orSearch(idx, ['Johann', 'Bach', 'Sebastian'])
    {0, 2, 3, 4, 5}
    >>> idx == makeInverseIndex(['Johann Sebastian Bach', 'Johannes Brahms', 'Johann Strauss the Younger', 'Johann Strauss the Elder', ' Johann Christian Bach',  'Carl Philipp Emanuel Bach'])
    True
    """
    return baseSearch(lambda a, b: a | b, inverseIndex, query)



## 4: (Task 0.6.8) And Search
def andSearch(inverseIndex, query):
    """
    Input: an inverse index, as created by makeInverseIndex, and a list of words to query
    Output: the set of all document ids that contain _all_ of the specified words
    Feel free to use a loop instead of a comprehension.

    >>> idx = makeInverseIndex(['Johann Sebastian Bach', 'Johannes Brahms', 'Johann Strauss the Younger', 'Johann Strauss the Elder', ' Johann Christian Bach',  'Carl Philipp Emanuel Bach'])
    >>> andSearch(idx, ['Johann', 'the'])
    {2, 3}
    >>> andSearch(idx, ['Johann', 'Bach'])
    {0, 4}
    >>> andSearch(idx, ['Johann', 'Bach', 'Sebastian'])
    {0}
    >>> idx == makeInverseIndex(['Johann Sebastian Bach', 'Johannes Brahms', 'Johann Strauss the Younger', 'Johann Strauss the Elder', ' Johann Christian Bach',  'Carl Philipp Emanuel Bach'])
    True
    """
    return baseSearch(lambda a, b: a & b, inverseIndex, query)

