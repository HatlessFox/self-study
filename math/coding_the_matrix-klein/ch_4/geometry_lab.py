# version code 80e56511a793+
# Please fill out this stencil and submit using the provided submission script.

# version code 05f5a0d767f0+
# Please fill out this stencil and submit using the provided submission script.

from mat import Mat
from vec import Vec
import math

from image_mat_util import file2mat, mat2display


## 4.15.1
#mat2display(*file2mat("img01.png"))

def show_transform(T, img):
    loc, clr = file2mat(img)
    mat2display(T*loc, clr)

def show_col_transform(CT, img):
    loc, clr = file2mat(img)
    mat2display(loc, CT*clr)
    
COORDS = set(['x', 'y', 'u'])
RGB = set(['r', 'g', 'b'])

## Task 1
def identity():
    '''
    Return the matrix that, when multiplied by a location vector, yields the same location vector.

    >>> identity() * Vec({'x','y','u'}, {'x':2, 'y':3, 'u':1}) == Vec({'x','y','u'}, {'x':2, 'y':3, 'u':1})
    True
    '''    
    return Mat((COORDS, COORDS), {(l,l):1 for l in COORDS})

#show_transform(identity(), "img01.png")

## Task 2
def translation(alpha,beta):
    '''
    Input:  a scalar alpha (the increase to the x-coordinate) and a scalar beta (the increase to the y-coordinate)
    Output:  3x3 matrix that, when multiplied by a location vector representing (x,y),
                          yields the location vector of the translated point (x+alpha, y+beta).

    >>> translation(4,-5) * Vec({'x','y','u'}, {'x':2, 'y':3, 'u':1}) == Vec({'x','y','u'}, {'x':6, 'y':-2, 'u':1})
    True
    '''
    data = {(l,l):1 for l in COORDS}
    data.update({('x', 'u'):alpha, ('y', 'u'):beta})
    return Mat((COORDS, COORDS), data)

#show_transform(translation(-50, 0), "img01.png")

## Task 3
def scale(alpha, beta):
    '''
    Input:  a scalar alpha (the multiplier for the x-coordinate) and a scalar beta (the multiplier for the y-coordinate) 
    Output:  3x3 matrix that, when multiplied by a locaiton vector representing (x,y),
                           yields the locaiton vector of the scaled point (alpha*x, beta*y).

    >>> scale(3,4)*Vec({'x','y','u'}, {'x':1,'y':2,'u':1}) == Vec({'x','y','u'}, {'x':3, 'y':8, 'u':1})
    True
    >>> scale(0,0)*Vec({'x','y','u'}, {'x':1,'y':1,'u':1}) == Vec({'x','y','u'}, {'u':1})
    True
    '''
    return Mat((COORDS, COORDS),
               {('x', 'x'):alpha, ('y', 'y'):beta, ('u', 'u'): 1})

#show_transform(scale(2, 0.5), "img01.png")

## Task 4
def rotation(theta):
    '''
    Input:  theta, the angle (in radians) to rotate an image.
    Output:  Corresponding 3x3 rotation matrix.
    Note that the math module is imported.

    >>> def normsq(v): return v*v
    >>> (rotation(math.pi) * Vec({'x','y','u'},{'x':1,'y':2,'u':1}) - Vec({'x','y','u'},{'x': -1, 'y': -2, 'u': 1})).is_almost_zero()
    True
    >>> (rotation(math.pi/2) * Vec({'x','y','u'},{'x':3,'y':1,'u':1}) - Vec({'x','y','u'},{'x': -1, 'y': 3, 'u': 1})).is_almost_zero()
    True
    >>> (rotation(3*math.pi/4) * Vec({'x','y','u'},{'x':4,'y':-3,'u':1}) - Vec({'x','y','u'},{'x':-1/math.sqrt(2), 'y':7/math.sqrt(2), 'u': 1})).is_almost_zero()
    True
    '''
    return Mat((COORDS, COORDS),
               {('x', 'x'):math.cos(theta), ('x', 'y'):-math.sin(theta),
                ('y', 'x'):math.sin(theta), ('y', 'y'):math.cos(theta), 
                ('u', 'u'): 1})

#show_transform(rotation(math.pi / 6), "img01.png")

## Task 5
def rotate_about(x, y, theta):
    '''
    Input:  an angle theta (in radians) by which to rotate, and x- and y- coordinates of a point to rotate about
    Output:  Corresponding 3x3 rotation matrix.
    It might be helpful to use procedures you already wrote.
    >>> rotate_about(3, 4, math.pi/3)*Vec({'x','y','u'}, {'x':1, 'y':0, 'u':1}) == Vec({'y', 'x', 'u'},{'y': 0.26794919243112214, 'x': 5.4641016151377535, 'u': 1})
    True
    '''
    return translation(x, y)*rotation(theta)*translation(-x, -y)

#show_transform(rotate_about(200, 200, math.pi/6), "img01.png")

## Task 6
def reflect_y():
    '''
    Input:  None.
    Output:  3x3 Y-reflection matrix.

    >>> reflect_y()*Vec({'x','y','u'}, {'x':1, 'y':1, 'u':1}) == Vec({'x','y','u'}, {'x':-1, 'y':1, 'u':1})
    True
    >>> reflect_y()* Vec({'x','y','u'}, {'u':1}) == Vec({'x','y','u'},{'u':1})
    True
    '''
    return Mat((COORDS, COORDS), {('x', 'x'):-1, ('y', 'y'):1, ('u', 'u'):1})

## Task 7
def reflect_x():
    '''
    Input:  None.
    Output:  3x3 X-reflection matrix.

    >>> reflect_x()*Vec({'x','y','u'}, {'x':1, 'y':1, 'u':1}) == Vec({'x','y','u'}, {'x':1, 'y':-1, 'u':1})
    True
    >>> reflect_x()*Vec({'x','y','u'}, {'u':1}) == Vec({'x','y','u'},{'u':1})
    True
    '''
    return Mat((COORDS, COORDS), {('x', 'x'):1, ('y', 'y'):-1, ('u', 'u'):1})

## Task 8    
def scale_color(scale_r,scale_g,scale_b):
    '''
    Input:  3 scaling parameters for the colors of the image.
    Output:  Corresponding 3x3 color scaling matrix.

    >>> scale_color(1,2,3)*Vec({'r','g','b'},{'r':1,'g':2,'b':3}) == Vec({'r','g','b'},{'r':1,'g':4,'b':9})
    True
    '''
    return Mat((RGB, RGB),
               {('r', 'r'):scale_r, ('g', 'g'):scale_g, ('b', 'b'):scale_b});

#show_col_transform(scale_color(.5, 5, 1), "img01.png")

## Task 9
def grayscale():
    '''
    Input: None
    Output: 3x3 greyscale matrix.
    '''
    return scale_color(77.0/256, 151.0/256, 28.0/256)

#show_col_transform(grayscale(), "img01.png")

## Task 10
def reflect_about(x1, y1, x2, y2):
    '''
    Input: 2 points that define a line to reflect about.
    Output:  Corresponding 3x3 reflect about matrix.

    >>> (reflect_about(0,1,1,1) * Vec({'x','y','u'}, {'u':1}) - Vec({'x', 'u', 'y'},{'x': 0.0, 'u': 1, 'y': 2.0})).is_almost_zero()
    True
    >>> (reflect_about(0,0,1,1) * Vec({'x','y','u'}, {'x':1, 'u':1}) - Vec({'x', 'u', 'y'},{'u': 1, 'y': 1})).is_almost_zero()
    True
    '''
    dx, dy = x2 - x1, y2 - y1
    angle = math.atan2(dy, dx)
    return translation(x1, y1)*rotation(angle)*reflect_x()*rotation(-angle)*translation(-x1, -y1)

#show_transform(reflect_about(70, 150, 100, 100), "img01.png")
