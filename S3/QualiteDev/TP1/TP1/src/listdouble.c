#include <stdio.h>
#include <stdlib.h>
#include "listdouble.h"

List* listCreate() {
  List* l = (List*)malloc(sizeof(List));
  if (l != NULL) {
    l->head = NULL;
    l->size = 0;
  }
  return l;
}

Cell* listFind(List* l, int value) {
  if (l->head == NULL) return NULL;
  Cell* cell = l->head;
  while (cell->value != value) {
    cell = cell->next;
    if (cell == l->head) return NULL;
  }
  return cell;
}

Cell* listGet(List* l, int index) {
  if ((index <0) || (index >= l->size)) return NULL;
  Cell* cell = l->head;
  if (index <= l->size/2) {
    for(int i=0;i<index;i++) {
      cell = cell->next;
    }
  }
  else {
    for(int i=l->size;i>index;i--) {
      cell = cell->prev;
    }
  }
  return cell;
}

Cell* listPrepend(List* l, int value) {
  Cell* new = listAppend(l,value);
  l->head = new;
  return new;
}

Cell* listAppend(List* l, int value) {

  Cell* new = NULL;
  new = (Cell*)malloc(sizeof(Cell));
  new->value = value;

  if (l->size == 0) {
    new->next = new;
    new->prev = new;
    l->head = new;
  }
  else {
    new->prev = l->head->prev;
    new->next = l->head;
    new->next->prev = new;
    new->prev->next = new;
  }
  l->size += 1;
  return new;
}

Cell* listInsert(List* l, int value, int index) {
  if (index >= l->size) {
    return listAppend(l,value);
  }
  else if (index <= 0) {
    return listPrepend(l,value);
  }
  else {
    Cell* new = NULL;
    new = (Cell*)malloc(sizeof(Cell));
    new->value = value;
    Cell* c = listGet(l,index);
    new->prev = c->prev;
    new->next = c;
    new->prev->next = new;
    new->next->prev = new;
    l->size += 1;
    return new;
  }
}

Cell* listReplace(List* l, int value, int index) {
  Cell* c = listGet(l,index);
  if (c != NULL) {
    c->value = value;
  }
  return c;
}

Cell* listRemoveAt(List* l, int index) {
  Cell* c = listGet(l,index);
  if (c != NULL) {
    c->prev->next = c->next;
    c->next->prev = c->prev;
    if (c == l->head) {
      l->head = c->next;
    }
    l->size -= 1;
  }
  return c;
}

Cell* listRemove(List* l, int value) {
  
  Cell* c = listFind(l,value);
  if (c != NULL) {
    c->prev->next = c->next;
    c->next->prev = c->prev;
    if (c == l->head) {
      l->head = c->next;
    }
    l->size -= 1;
  }
  return c;
}

void listPrint(List* l) {

  Cell* cell = l->head;
  if (cell != NULL) {
    printf("%d -> ",cell->value);
    cell = cell->next;    
  }
  
  while (cell != l->head) {
    printf("%d -> ",cell->value);
    cell = cell->next;
  }
  printf("\n");
}
