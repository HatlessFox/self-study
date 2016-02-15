# version code a08e0d324fa0+
# Please fill out this stencil and submit using the provided submission script.

import time
import svd
from matutil import *
from mnist_loader import load_data
import random
from orthonormalization import orthonormalize



## 1: Squared Distance
def sq_dist(u, v):
    '''
    Input:
        - u, v: two Vecs with the same domain
    Output:
        - the square of the Euclidean distance between u and v
    Example:
        >>> u = list2vec([1, 2])
        >>> v = list2vec([2, 3])
        >>> sq_dist(u, v)
        2
    '''
    pass



## 2: Nearest Neighbor
def nn(u, veclist):
    '''
    Input:
        - u: a Vec
        - veclist: a list of Vecs
    Output:
        - The index of the vector in veclist nearest to u
    Example:
        >>> from vecutil import list2vec
        >>> nn(list2vec([1,2]), [list2vec(l) for l in [[2,5],[1,3],[1.5,2]]])
        2
    '''
    pass



## 3: Nearest Neighbor Label
def nn_label(u, veclist, labels):
    '''
    Input:
        - u: a Vec
        - veclist: a list of Vecs
        - labels: a list of labels, one for each Vec in veclist
    Output:
        - the label of the vector in veclist nearest to u
    Example:
        >>> from vecutil import list2vec
        >>> u = list2vec([1,2])
        >>> veclist = [list2vec(l) for l in [[2,5],[1,3],[1.5,2]]]
        >>> labels = [0, 1, 0]
        >>> nn_label(u, veclist, labels)
        0
    '''
    pass



## 4: Error Rate
def error_rate(guessed_labels, correct_labels):
    '''
    Input:
        - guessed_labels: a list of guessed labels
        - correct_labels: a list of true labels
    Output:
        - the fraction of guessed labels that are not equal to the corresponding correct label
    Example:
        >>> error_rate([0, 1, 0, 1, 1], [0, 1, 2, 3, 4])
        0.6
    '''
    pass



## 5: Find Centroid
def find_centroid(veclist):
    '''
    Input:
        - veclist: a list of Vecs
    Output:
        - a Vec, the centroid of veclist
    Example:
        >>> from vecutil import list2vec
        >>> vs = [list2vec(l) for l in [[1,2,3],[2,3,4],[9,10,11]]]
        >>> find_centroid(vs)
        Vec({0, 1, 2},{0: 4.0, 1: 5.0, 2: 6.0})
    '''
    pass


# Load training and testing data
n_train, n_test = 3000, 100
images, labels = load_data(n_train+n_test)
train_images, test_images = images[:n_train], images[n_train:]
train_labels, test_labels = labels[:n_train], labels[n_train:]



## 6: Raw Error Rate
raw_nn_error_rate = ...



## 7: Training Centroid
centroid = ...



## 8: Centered Training Images
centered_train_images = ...



## 9: Right Singular Vectors
right_singular_vs = ...



## 10: Centered Test Images
centered_test_images  = ...



## 11: 10 Principal Components
M10 = ...



## 12: 10 Principal Components Error Rate
# Use matrix-vector multiplication to find the coordinate representations
# of the projections of all centered training and centered test images onto
# the span of the first ten right singular vectors, and find the error rate
# of nearest-neighbor classification using these
svd10_nn_error_rate = ...




## 13: 20 Principal Components
M20 = ...



## 14: 20 Principal Components Error Rate
svd20_nn_error_rate = ...

