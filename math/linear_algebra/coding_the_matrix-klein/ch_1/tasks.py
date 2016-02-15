### NB: task numeration is burded with def/quiz ect. numeration in the book

from plotting import plot

## 1.4.1

s = {2+2j, 3+2j, 1.75+1j, 2+1j, 2.25+1j,
     2.75+1j, 3+1j, 3.25+1j}

#plot(s, 4)

## 1.4.3

#plot({1+2j + z for z in s}, 4)

## 1.4.7

#plot({.5 * z for z in s}, 4)

## 1.4.8

#plot({ .5j * z for z in s}, 4)

## 1.4.9

#plot({ .5j * z + 2-1j for z in s}, 4)

## 1.4.10

from image import file2image

def file2cmlx(fname):
    img = file2image(fname)
    return [ complex(col_i, len(img) - row_i)
             for row_i, row in enumerate(img) \
             for col_i, (intensity, _, _) in enumerate(row) \
             if intensity < 120]

pts = file2cmlx("img01.png")
#plot(file2cmlx("img01.png"), 200)

## 1.4.11 & 1.4.12
### Looks like I don't understand what I'm suppoused to do,
### so f takes a set and a "transformer"

from functools import reduce

def center_offset(s, k):
    min = reduce(lambda x, y: x if x < k(y) else k(y), s, float("inf"))
    max = reduce(lambda x, y: x if k(y) < x else k(y), s, float("-inf"))
    return min + (max - min) / 2

def f(s, z):
    new_s = { z * e for e in s}
    offset = complex(center_offset(new_s, lambda x: x.real), \
                     center_offset(new_s, lambda x: x.imag))
    return { e - offset for e in new_s}

#plot(f(s, 1j))
#plot(f(pts, 1j + 1), 400)

## 1.4.17

from math import e, pi

n = 20
w = e**(complex(0, 2*pi) / n)
#plot({ w**e for e in range(n) })

## 1.4.18

#plot({ el * (e**complex(0, pi/4)) for el in s}, 4)

## 1.4.19
#plot({ el * (e**complex(0, pi/4)) for el in pts}, 200)

## 1.4.20

#plot({ el * e**complex(0, pi/4) / 2 for el in f(pts, 1)}, 200)

## 1.5.1 (brute force was used)

def num_to_ch(num): return ' ' if num == 26 else chr(num + ord('a'))

def decypher(c, k, k_len):
    return "".join(num_to_ch(int(c[i:i+k_len], 2) ^ k) for i in range(0, len(c), k_len))
    
def gen_plaintexts(cyphertext, key_len):
    return [decypher(cyphertext, key, key_len) for key in range(2**key_len)]

def print_possible_plaintexts(cyphertext):
    for pt in gen_plaintexts(cyphertext, 5):
        print(pt)

print_possible_plaintexts("1010100100101010101111001000110101110101001001100111010")

        
