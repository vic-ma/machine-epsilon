![](https://wikimedia.org/api/rest_v1/media/math/render/svg/23c795639c800469c2beb6a68c3197c43123e350)

# MACHINE_εpsilon
This program generates epsilon-delta proofs for single-variable polynomial limits.

## Install
Java 8+

## Syntax

lim x→c f(x) = L

`c` — An integer or a fraction 

`f(x)` — A sequence of terms seperated by addition (`+`) or subtraction (`-`) operators

`L` — An integer or a fraction

**Fractions** are of the form `a` or `a/b`, with an optional preceding minus sign (`-`)

**Terms** are of the form `a` or`a/b` or `ax` or `(a/b)x` or `x^n` or `ax^n`  or `(a/b)x^n`

*a, b, n ∈ ℕ; x is the character 'x'*

## Example

&nbsp;

![](https://latex.codecogs.com/png.latex?%5Cdpi%7B200%7D%20%5Clim_%7Bx%20%5Cto%20%5Cfrac23%7D%5Cleft%5B6x-%5Cfrac12%5Cright%5D%3D%5Cfrac72)

![](https://i.imgur.com/FnQar77.png)

&nbsp;

![](https://latex.codecogs.com/png.latex?%5Cdpi%7B200%7D%20%5Clim_%7Bx%20%5Cto%202%7D%5Cleft%5B3x%5E2-2x-3%5Cright%5D%3D5)

![](https://i.imgur.com/igS5j42.png)

&nbsp;

![](https://latex.codecogs.com/png.latex?%5Cdpi%7B200%7D%20%5Clim_%7Bx%20%5Cto%20-%5Cfrac%7B32%7D%7B56%7D%7D%20%5Cfrac%7B73%7D%7B62%7D%20%3D%20%5Cfrac%7B73%7D%7B62%7D)

![](https://i.imgur.com/AzLAHTo.png)

&nbsp;

![](https://latex.codecogs.com/png.latex?%5Cdpi%7B200%7D%20%5Clim_%7Bx%20%5Cto%20-5%7D%5Cleft%5B%5Cfrac%7B1%7D%7B20%7Dx%5E3-%5Cfrac34x%5E2&plus;2x%5Cright%5D%3D%5Cfrac72)

![](https://i.imgur.com/azzo3Kp.png)
