from tkinter import *
from numpy import *
import time
import os 

#-----------------Conditions initiales (au tout début du jeu)---------------
#------------------------------À COMPLÉTER----------------------------------


#temps
global t
t=0

#ver de terre
global xver, yver
xver, yver = 800, 400

#Conditions initiales du tir (position, vitesse, angle)
global angle, v0
angle = -45
v0 = 100 #réglage?

global x0, y0, v0x, v0y
x0, y0, v0x, v0y = xver, yver, v0*cos(radians(angle)), v0*sin(radians(angle))

    
#Accélération


#Données physiques générale
global g
g = 100 #réglage ?
   
#booleens de clavier
global keyup,keydown,keyright,keyleft,keyspace 
keyup,keydown,keyright,keyleft,keyspace=False,False,False,False,False

 
#booléens de gestion du jeu 
global lancer
lancer = False
 
    
#--------------détection des touches enfoncées au clavier--------------

def press(event):
  if event.keysym=='Up':
   global keyup
   keyup=True
  if event.keysym=='Down':
   global keydown
   keydown=True
  if event.keysym=='Right':
   global keyright
   keyright=True
  if event.keysym=='Left':
   global keyleft
   keyleft=True

  if event.keysym=='space':
   global keyspace
   keyspace = True
   

# --------------Détection des touches relâchées au clavier-----------------
def release(event):
  if event.keysym=='Up':
    global keyup
    keyup=False
  if event.keysym=='Down':
    global keydown
    keydown=False
  if event.keysym=='Right':
    global keyright
    keyright=False
  if event.keysym=='Left':
    global keyleft
    keyleft=False
    
  if event.keysym=='space':
   global keyspace
   keyspace = False


#-----------------------Équations du mouvement-----------------------------
#-----------------------!!!!!!!!!!!!!!!!!!!!!!-----------------------------
def x(t):
    return v0x*t + x0

def y(t):
    return g*t*t/2 + v0y * t +y0

def vx(t):
    return v0x

def vy(t):
    return g*t+v0y

def xviseur(alpha):
  return xver + 50*cos(radians(alpha))

def yviseur(alpha):
  return yver + 50*sin(radians(alpha))

#----------------------Gestion de la fenêtre graphique----------------------

root = Tk()#création de la fenêtre
os.system('xset r on')
can = Canvas( root, width=1600, height=800 )#réglage de la taille de la fenêtre
can.bind_all("<KeyPress>",press)#lie la fenêtre aux événement clavier
can.bind_all("<KeyRelease>",release)#lie la fenêtre aux événement clavier
#init()#condition initiales du jeu
#T = Text(root, height=2, width=40)
#T.pack()
#T.insert(END, "Use arrow keys to move green point\nUse space bar to initialize position\n")#manuel utilisateur affiché à l'écran
can.pack()

grenade = 0
ver = 0
viseur = 0

#-----------------------BOUCLE D'ANIMATION----------------------------------
while True: 
  
    time.sleep(0.01)#contrôle de l'écoulement du temps
    #Affichage du mobile

    #clearscreen, incrémentation du temps
    root.update()
    t=t+0.01
    
    can.delete(ver)
    ver = can.create_oval(xver-10,yver-10,xver+10,yver+10,fill="blue")
    can.delete(viseur)
    viseur=can.create_oval(xviseur(angle)-3,yviseur(angle)-3, xviseur(angle)+3,yviseur(angle)+3, fill="red")

    if keyleft and not keyright:
      xver-=1

    if keyright and not keyleft:
      xver+=1
      
    if keyup and not keydown:
      angle-=1

    if keydown and not keyup:
      angle+=1
    
    

