OBJSIMPLE := listsimple.o testsimple.o
OBJDOUBLE := listdouble.o testdouble.o
EXESIMPLE := testsimple
EXEDOUBLE := testdouble

CFLAGS = -std=c99 -Wall

all : exe

exe : $(EXESIMPLE) $(EXEDOUBLE)

$(EXESIMPLE) : $(OBJSIMPLE)
	gcc $^ -o $@

$(EXEDOUBLE) : $(OBJDOUBLE)
	gcc $^ -o $@

clean :
	rm $(EXESIMPLE) $(EXEDOUBLE)
	rm $(OBJSIMPLE) $(OBJDOUBLE)
