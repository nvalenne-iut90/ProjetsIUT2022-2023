import math
import random
def simulation(k,n):
    Mn = 0
    liste = []
    for i in range(k):
        for j in range(n):
            Mn += random.randint(0) #....
        Mn /= n
        liste.append(Mn)
    return liste
        
    

