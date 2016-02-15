#! /usr/bin/env python3

from math import sqrt, fabs
from image import file2image, color2gray, image2display

PRINT_FUNC_RUNS = False

def to_haar_unnormalized(v):
    '''
    >>> to_haar_unnormalized([1, 2, 3, 4]) == \
        {(2, 0):-1, (1, 0):-2.0, (0, 0):2.5, (2,1):-1}
    True
    >>> to_haar_unnormalized([4,5,3,7,4,5,2,3,9,7,3,5,0,0,0,0]) \
         == \
        {(8,3):-1, (0,0):3.5625, (8,2):-1, (8,1):-4, (4,1):2.0, (4,3):0.0,    \
         (8,0):-1, (2,1):6.0, (2,0):1.25, (8,7):0, (4,2):4.0, (8,6):0,        \
         (1,0):1.125, (4,0):-0.5, (8,5):-2, (8,4):2}
    True
    '''
    assert len(v) & (len(v) - 1) == 0  # ensure n is power of 2
    haar_coeffs = {}
    while 1 < len(v):
        k = len(v) // 2
        downsampled_v = [(v[2*i]+v[2*i+1])/2 for i in range(k)]
        w_coeffs = [v[2*i] - v[2*i+1] for i in range(k)]
        haar_coeffs.update({(k,i):w_coeffs[i] for i in range(k)})
        v = downsampled_v
    haar_coeffs[(0, 0)] = v[0]
    return haar_coeffs

if PRINT_FUNC_RUNS:
    print("== [10.9.1] ==")
    v = [1, 1, 2, 2]
    print("{} -> {}".format(v, to_haar_unnormalized(v)))
    v = [1, 100, 1, 100]
    print("{} -> {}".format(v, to_haar_unnormalized(v)))   

def normalize_haar_coeffs(n, coeffs):
    '''
    >>> normalize_haar_coeffs(4, {(2,0):1, (2,1):1, (1,0):1, (0,0):1}) == \
        {(2,0):0.7071067811865476, (1,0):1.0, (0,0):2.0, (2,1):0.7071067811865476}
    True
    >>> normalize_haar_coeffs(4, to_haar_unnormalized([1, 2, 3, 4])) == \
        {(2,0):-0.7071067811865476, (1,0):-2.0, (0,0):5.0, (2,1):-0.7071067811865476}
    True
    '''
    return {k:v*(sqrt(n) if k[0] == 0 else sqrt(n/4/k[0])) for k, v in coeffs.items()}

def to_haar_basis(v):
    '''
    >>> from math import sqrt
    >>> to_haar_basis([1, 2, 3, 4]) == \
        {(2,0):-sqrt(1/2), (1,0):-2.0, (0,0):5.0, (2,1):-sqrt(1/2)}
    True
    '''
    return normalize_haar_coeffs(len(v), to_haar_unnormalized(v))

if PRINT_FUNC_RUNS:
    print("== [10.9.3] ==")
    v = [1, 1, 2, 2]
    print("{} -> {}".format(v, to_haar_basis(v)))
    v = [1, 100, 1, 100]
    print("{} -> {}".format(v, to_haar_basis(v))) 

def suppress(coeffs, threshold):
    '''
    >>> suppress(to_haar_basis([1, 2, 3, 4]), 1) == \
        {(2,0):0, (1,0):-2.0, (0,0):5.0, (2,1):0}
    True
    '''
    return {k:(0 if fabs(v) < threshold else v) for k, v in coeffs.items()}

def sparsity(coeffs):
    '''
    >>> coeffs = to_haar_basis([1, 2, 3, 4])
    >>> sparsity(coeffs)
    1.0
    >>> sparsity(suppress(coeffs, 1))
    0.5
    '''
    return len(list(filter(lambda x: x != 0, coeffs.values()))) / len(coeffs)

def unnormalize_haar_coeffs(n, coeffs):
    return {k:v/(sqrt(n) if k[0] == 0 else sqrt(n/4/k[0])) for k, v in coeffs.items()}

def from_haar_unnormalized(coeffs):
    '''
    >>> from_haar_unnormalized(to_haar_unnormalized([1, 2, 3, 4]))
    [1.0, 2.0, 3.0, 4.0]
    >>> from_haar_unnormalized(to_haar_unnormalized([4,5,3,7,4,5,2,3,9,7,3,5,0,0,0,0]))
    [4.0, 5.0, 3.0, 7.0, 4.0, 5.0, 2.0, 3.0, 9.0, 7.0, 3.0, 5.0, 0.0, 0.0, 0.0, 0.0]
    '''
    n = len(coeffs)
    std_coeffs = [coeffs[(0,0)]]
    while len(std_coeffs) < n:
        w_basis = [coeffs[(len(std_coeffs), i)] for i in range(len(std_coeffs))]
        k = 2 * len(std_coeffs)
        std_coeffs = sum([[(2*v+w)/2, (2*v-w)/2] for v, w in zip(std_coeffs, w_basis)], [])
    return std_coeffs

def from_haar_basis(coeffs):
    '''
    >>> from_haar_basis(to_haar_basis([1, 2, 3, 4]))
    [1.0, 2.0, 3.0, 4.0]
    >>> from_haar_basis(to_haar_basis([4,5,3,7,4,5,2,3,9,7,3,5,0,0,0,0]))
    [4.0, 5.0, 3.0, 7.0, 4.0, 5.0, 2.0, 3.0, 9.0, 7.0, 3.0, 5.0, 0.0, 0.0, 0.0, 0.0]
    '''
    return from_haar_unnormalized(unnormalize_haar_coeffs(len(coeffs), coeffs))

############# 2D

def dlist_helper(ds, k):
    return [d[k] for d in ds]

def to_haar2d(ls):
    haar_rows = [to_haar_basis(l) for l in ls]
    return {k:to_haar_basis(dlist_helper(haar_rows, k))
            for k in haar_rows[0].keys()}

def suppress2d(dds, threshold):
    '''
    >>> suppress2d({0:{0:2, 1:-3}, 1:{1:3, 0:1}}, 3) == \
        {0:{0:0, 1:-3}, 1:{0:0, 1:3}}
    True
    '''
    return {k:suppress(v, threshold) for k, v in dds.items()}

def sparsity2d(dds):
    '''
    >>> sparsity2d({0:{0:0, 1:-3}, 1:{1:0, 0:0}})
    0.25
    '''
    return sum([sparsity(d) for d in dds.values()]) / len(dds.values())

def listdict2dict(listdict, i):
    return {k:listdict[k][i] for k in listdict.keys()}

def listdict2dictlist(listdict):
    return [listdict2dict(listdict, i) for i in range(len(listdict[(0,0)]))]

def from_haar2d(dictdict):
    '''
    >>> from_haar2d(to_haar2d([[1,2,3,4]]))
    [[1.0, 2.0, 3.0, 4.0]]
    >>> [[int(e) for e in l] for l in from_haar2d(to_haar2d([[1,2], [3,4]]))]
    [[1, 2], [3, 4]]
    '''
    listdict = {k:from_haar_basis(v) for k, v in dictdict.items()}
    dictlist = listdict2dictlist(listdict)
    return [from_haar_basis(d) for d in dictlist]

def image_round(image):
    return [[int(255 if 255 < e else e) for e in row] for row in image]

def read_image(fname):
    return color2gray(file2image(fname))

def haar2d_test(fname):
    return image_round(from_haar2d(to_haar2d(read_image(fname))))

def find_threashold(fname, target_sparsity):
    haar_image = to_haar2d(read_image(fname))
    lo, hi = 0, 255
    while lo <= hi:
        th = (lo + hi) / 2
        non_zero_frac = sparsity2d(suppress2d(haar_image, th))
        if fabs(non_zero_frac - target_sparsity) <= 0.001:
            return th
        elif  non_zero_frac < target_sparsity:
            hi = th - 1
        else:
            lo = th + 1
    return th if 0 < th else 0

def haar2d_compress_th(fname, threshold):
    img = to_haar2d(read_image(fname))
    print("Init sparcity: {:.2%}".format(sparsity2d(img)))
    compressed_haar = suppress2d(img, threshold)
    print("Apply th: {:4.2f} -> {:.2%}".format(
        threshold, sparsity2d(compressed_haar)))
    return image_round(from_haar2d(compressed_haar))

def haar2d_compress_sp(fname, sparsity):
    return haar2d_compress_th(fname, find_threashold(fname, sparsity))

if PRINT_FUNC_RUNS:
    image2display(haar2d_compress_sp("flag.png", .03))




