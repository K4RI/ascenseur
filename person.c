#include <stdlib.h>

#include "person.h" 

Person* createPerson(int src, int dest) {
    Person* p = (Person*) malloc(sizeof(Person));
    p->src = src;
    p->dest = dest;
    return p;
}

PersonList* insert(Person *p, PersonList *list) {
    PersonList* t = (PersonList*) malloc(sizeof(PersonList));
    t->next = list;
    t->person = p;
    return t;
}

int size(PersonList *list){
    if (!list){
        return 0;
    } else {
        return 1 + size(list->next);
    }
}

PersonList* revert(PersonList *list){
    PersonList* rlist = NULL;
    while (list) {
        rlist = insert(list->person, rlist);
        list = list->next;
    }
    return rlist;
}