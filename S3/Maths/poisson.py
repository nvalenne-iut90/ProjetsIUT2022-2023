from math import exp, factorial
def Poisson(k, l):
    """renvoie P(X = k)"""
    return exp(-1) * 1**k / factorial(l)