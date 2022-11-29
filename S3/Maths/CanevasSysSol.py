from tkinter import *
from numpy import sin,cos,pi, radians, array, dot
import time
import os 

#-----------------Conditions initiales (au tout début du jeu)---------------
#------------------------------À COMPLÉTER----------------------------------


def init():
    #temps
    global t
    t=0
    
    #Conditions initiales
    
    
    #Accélération
    

    #Données physiques générale
    global g
    
   
    #booleens de direction
    global keyup,keydown,keyright,keyleft,keyspace 
    global up,down,right,left,space
    keyup,keydown,keyright,keyleft,keyspace=False,False,False,False,False
    up,down,right,left,space=False,False,False,False,False
    
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

def xsol(t):
  return 800
def ysol(t):
  return 400

def xterre(t):
  return xsol(t)+300*cos(-2*pi/20 *t -pi/2)
def yterre(t):
  return ysol(t)+100*sin(-2*pi/20 *t -pi/2)

def xlune(t):
  return xterre(t)+30*cos(-2*pi/20 *13.21*t -pi/2)
def ylune(t):
  return yterre(t)+10*sin(-2*pi/20 *13.21*t -pi/2)

def xroche(t):
  return xlune(t)+10*cos(2*pi/20 *13.21*5*t -pi/2)
def yroche(t):
  return ylune(t)+3.33*sin(2*pi/20 *13.21*5*t -pi/2)

def xmars(t):
  return xsol(t)+400*cos(-2*pi/20 *t -pi/2)
def ymars(t):
  return ysol(t)+130*sin(-2*pi/20 *t -pi/2)

#----------------------Gestion de la fenêtre graphique----------------------

root = Tk()#création de la fenêtre
os.system('xset r on')
can = Canvas( root, width=1600, height=800 )#réglage de la taille de la fenêtre
can.bind_all("<KeyPress>",press)#lie la fenêtre aux événement clavier
can.bind_all("<KeyRelease>",release)#lie la fenêtre aux événement clavier
init()#condition initiales du jeu
T = Text(root, height=2, width=40)
T.pack()
T.insert(END, "Use arrow keys to move green point\nUse space bar to initialize position\n")#manuel utilisateur affiché à l'écran
can.pack()

can.configure(bg="white")

soleil=terre=lune=roche=mars = 0
#-----------------------BOUCLE D'ANIMATION----------------------------------
while True: 
  
    time.sleep(0.005)#contrôle de l'écoulement du temps
    
    #clearscreen, incrémentation du temps
    root.update()

    
        
    #Affichage du mobile
    if keyspace and not space :
      t=0
      space=True
      
    if space :
      t+=0.005
      can.delete(soleil)
      can.delete(terre)
      can.delete(lune)
      can.delete(roche)
      can.delete(mars)

      x, y = xmars(t), ymars(t)
      x,y = x-xsol(t), y-ysol(t)
      # rotation
      mars = array([
        [x],
        [y]
      ])
      rot = array([
        [cos(-radians(10)), -sin(-radians(10))],
        [sin(-radians(10)), cos(-radians(10))]
        ])
      
      if yterre(t) > ysol(t):
        soleil = can.create_oval(xsol(t)-200,ysol(t)-200,xsol(t)+200,ysol(t)+200, fill='yellow')
        terre = can.create_oval(xterre(t)-10,yterre(t)-10,xterre(t)+10,yterre(t)+10, fill='blue')
        lune = can.create_oval(xlune(t)-5,ylune(t)-5,xlune(t)+5,ylune(t)+5, fill='grey')
        roche = can.create_oval(xroche(t)-1,yroche(t)-1,xroche(t)+1,yroche(t)+1, fill='black')
        mars = can.create_oval(xmars(t)-1,ymars(t)-1,xmars(t)+1,ymars(t)+1, fill='red')
      else :
        terre = can.create_oval(xterre(t)-10,yterre(t)-10,xterre(t)+10,yterre(t)+10, fill='blue')
        lune = can.create_oval(xlune(t)-5,ylune(t)-5,xlune(t)+5,ylune(t)+5, fill='grey')
        roche = can.create_oval(xroche(t)-1,yroche(t)-1,xroche(t)+1,yroche(t)+1, fill='black')
        mars = can.create_oval(xmars(t)-10,ymars(t)-10,xmars(t)+10,ymars(t)+10, fill='red')
        soleil = can.create_oval(xsol(t)-200,ysol(t)-200,xsol(t)+200,ysol(t)+200, fill='yellow')


   
    
             
    
    
    
    

