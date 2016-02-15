# version code c2eb1c41017f+
# Please fill out this stencil and submit using the provided submission script.

from vec import Vec
from mat import Mat

import svd
from matutil import mat2rowdict, rowdict2mat

import eigenfaces

from image import image2display

DOCTEST_MODE = False

WIDTH = 166
HEIGHT = 189

# see documentation of eigenfaces.load_images

def imgRows2imgVec(img_rows):
    D = {(i, j) for i in range(WIDTH) for j in range(HEIGHT)}
    return Vec(D, {(i, j):img_rows[j][i] for i, j in D})

def imgVec2image(img_vec):    
    return [[img_vec[(i,j)] for i in range(WIDTH)] for j in range(HEIGHT)]

def vec2display(vec):
    image2display(imgVec2image(vec))

def readImages(dir_name, n=20):
    # dict of Vecs
    return {i:imgRows2imgVec(img_rows)
            for i, img_rows in eigenfaces.load_images(dir_name, n).items()};

if not DOCTEST_MODE:
    ## Task 1
    face_images = readImages("./faces")

    ## Task 2
    centroid = sum(face_images.values()) / len(face_images)
    #image2display(imgVec2image(centroid))
    centered_face_images = {i:img - centroid for i, img in face_images.items() }

    ## Task 3
    A = Mat((set(range(len(centered_face_images))), face_images[0].D),
            {(i, k):val for i, img in centered_face_images.items()
                        for k, val in img.f.items()}) # centered image vectors
    orthonormal_basis = \
        rowdict2mat(dict(list(mat2rowdict(svd.factor(A)[2].transpose()).items())[:10]))
    #image2display(imgVec2image(list(mat2rowdict(svd.factor(A)[2]).values())[0]))

## Task 4

#This is the "transpose" of what was specified in the text.
#Follow the spec given here.
def projected_representation(M, x):
    '''
    Input:
        - M: a matrix with orthonormal rows with M.D[1] == x.D
        - x: a vector
    Output:
        - the projection of x onto the row-space of M
    Examples:
        >>> from vecutil import list2vec
        >>> from matutil import listlist2mat
        >>> x = list2vec([1, 2, 3])
        >>> M = listlist2mat([[1, 0, 0], [0, 1, 0]])
        >>> projected_representation(M, x)
        Vec({0, 1},{0: 1, 1: 2})
        >>> M = listlist2mat([[3/5, 1/5, 1/5], [0, 2/3, 1/3]])
        >>> projected_representation(M, x)
        Vec({0, 1},{0: 1.6, 1: 2.333333333333333})
         >>>
        >>> from eigenfaces import test_M, test_x
        >>> projected_representation(test_M.transpose(), test_x)
        Vec({0, 1},{0: 21.213203435596423, 1: 11.547005383792516})
    '''
     # projection is a dot product with each basis element (i.e. row)
    return M*x

## Task 5

#This is the "transpose" of what was specified in the text.
#Follow the spec given here.
def projection_length_squared(M, x):
    '''
    Input:
        - M: matrix with orthonormal rows with M.D[1] == x.D
        - x: vector
    Output:
        - the square of the norm of the projection of x into the
          row-space of M
    Example:
        >>> from vecutil import list2vec
        >>> from matutil import listlist2mat
        >>> x = list2vec([1, 2, 3])
        >>> M = listlist2mat([[1, 0, 0], [0, 1, 0]])
        >>> projection_length_squared(M, x)
        5
        >>> M = listlist2mat([[3/5, 1/5, 1/5], [0, 2/3, 1/3]])
        >>> projection_length_squared(M, x)
        5.644424691358024
        >>>
        >>> from eigenfaces import test_M, test_x
        >>> projection_length_squared(test_M.transpose(), test_x)
        583.3333333333331
    '''
    proj_in_st_basis = projected_representation(M, x)*M
    return proj_in_st_basis * proj_in_st_basis;
## Task 6

#This is the "transpose" of what was specified in the text.
#Follow the spec given here.
def distance_squared(M, x):
    '''
    Input:
        - M: matrix with orthonormal rows with M.D[1] == x.D
        - x: vector
    Output:
        - the square of the distance from x to the row-space of M
    Example:
        >>> from vecutil import list2vec
        >>> from matutil import listlist2mat
        >>> x = list2vec([1, 2, 3])
        >>> M = listlist2mat([[1, 0, 0], [0, 1, 0]])
        >>> distance_squared(M, x)
        9
        >>> M = listlist2mat([[3/5, 1/5, 1/5], [0, 2/3, 1/3]])
        >>> distance_squared(M, x)
        8.355575308641976
        >>>
        >>> from eigenfaces import test_M, test_x
        >>> distance_squared(test_M.transpose(), test_x)
        816.6666666666669
    '''
    return x*x - projection_length_squared(M, x)

if not DOCTEST_MODE:
    ## Task 7
    distances_to_subspace = [distance_squared(orthonormal_basis, face)
                             for face in centered_face_images.values()]
    avg_face_dist = 2.6 * max(distances_to_subspace)
    
    ## Task 8
    unclassified_images = readImages("./unclassified", 10)
    centered_uncl_images = {i:img - centroid
                            for i, img in unclassified_images.items() }
    classified_as_faces = \
        {k for k, uncl in centered_uncl_images.items()
         if distance_squared(orthonormal_basis, uncl) <= avg_face_dist}
    ## Task 9
    threshold_value = avg_face_dist

## Task 10

#This is the "transpose" of what was specified in the text.
#Follow the spec given here.
def project(M, x):
    '''
    Input:
        - M: an orthogonal matrix with row-space equal to x's domain
        - x: a Vec
    Output:
        - the projection of x into the column-space of M
    Example:
        >>> from vecutil import list2vec
        >>> from matutil import listlist2mat
        >>> x = list2vec([1, 2, 3])
        >>> M = listlist2mat([[1, 0, 0], [0, 1, 0]])
        >>> project(M, x)
        Vec({0, 1, 2},{0: 1, 1: 2, 2: 0})
        >>> M = listlist2mat([[3/5, 1/5, 1/5], [0, 2/3, 1/3]])
        >>> project(M, x)
        Vec({0, 1, 2},{0: 0.96, 1: 1.8755555555555554, 2: 1.0977777777777777})
    '''
    return projected_representation(M, x)*M

## Task 11

if not DOCTEST_MODE:
    vec2display(project(orthonormal_basis, face_images[1]));
    vec2display(project(orthonormal_basis, centered_uncl_images[1]));
    vec2display(project(orthonormal_basis, centered_uncl_images[3]));

## Task 12

if not DOCTEST_MODE:
    vec2display(project(orthonormal_basis, centered_uncl_images[7]));
