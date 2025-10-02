#include <stdlib.h>

#include "elevator.h"

Elevator *create_elevator(int capacity, int currentFloor, PersonList *persons) {
    Elevator* e = (Elevator*) malloc(sizeof(Elevator));
    e->capacity = capacity;
    e->currentFloor = currentFloor;
    e->persons = persons;
    return e;
}

Building *create_building(int nbFloor, Elevator *elevator, PersonList **waitingLists) {
    Building* b = (Building*) malloc(sizeof(Building));
    b->nbFloor = nbFloor;
    b->elevator = elevator;
    b->waitingLists = waitingLists;
    return b;
}

// lorsque l’ascenseur arrive à un étage cette fonction est appelée (par stepElevator) 
// cette fonction renvoie la liste des personnes qui sortent de l’ascenseur
// restent dans l’ascenseur les personnes qui ne sont pas sorties
PersonList* exitElevator(Elevator *e) {
    Person* p;
    PersonList* newList = NULL;
    PersonList* outList = NULL;
    while (e->persons) {
        p = e->persons->person;
        if (p->dest == e->currentFloor) {
            e->persons = e->persons->next; // on la fait sortir de l'ascenseur
            outList = insert(p, outList); // et ajouter à la liste de sortie
        } else {
            e->persons = e->persons->next;
            newList = insert(p, newList); // on la transfère vers la liste qui...
        };
    }
    e->persons = revert(newList); // ...qui sera la nouvelle liste de l'ascenseur.
                                // attention il faut la retourner, sinon en attente l'ascenseur alterne 
    return outList;
}

// lorsque l’ascenseur arrive à un étage cette fonction est appelée (par stepElevator)
// cette fonction fait entrer dans l’ascenseur les personnes qui attendent (waitingList) et renvoie la nouvelle liste d’attente (i.e. la liste initiale moins les personnes qui sont entrées dans l’ascenseur) 
// L’ascenseur ne peut pas accueillir plus de personnes que sa capacité maximale
PersonList* enterElevator(Elevator *e, PersonList *list) {
    int n = size(e->persons);
    Person* p;
    while (list) {
        if (n >= e->capacity) { // issue 1 : ascenseur plein
            return list;
        } else {
            p = list->person;
            e->persons = insert(p, e->persons);
            list = list->next;
            n++;
        }
    } // issue 2 : liste d'attente de l'étage vide
    return list; // NULL
}

// cette fonction simule le déplacement de l’ascenseur : 
// si l’étage courant de l’ascenseur correspond à sa destination, faire en sorte que les personnes entrent et sortent de la cabine
// sinon, l’étage courant est incrémenté ou décrémenté de 1 pour se rapprocher de sa destination
void stepElevator(Building *b) {
    int cf = b->elevator->currentFloor, tf = b->elevator->targetFloor;
    PersonList *liste;
    if (cf == tf) {
        liste = exitElevator(b->elevator);
        b->waitingLists[cf] = enterElevator(b->elevator, b->waitingLists[cf]);
    } else {
        if (cf > tf){
            b->elevator->currentFloor--;
        } else {
            b->elevator->currentFloor++;
        }
    }
}
