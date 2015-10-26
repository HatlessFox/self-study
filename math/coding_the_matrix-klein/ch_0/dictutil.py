# Copyright 2013 Philip N. Klein
# Implemented by Hatless Fox, 2015

# 0.6.3

def dict2list(dct, keylist): return [dct[k] for k in keylist if k in dct]

def list2dict(L, keylist): return {keylist[i]:L[i] for i in range(len(keylist))}

# 0.6.4

def listrange2dict(L): return { i:e for i, e in enumerate(L)}
