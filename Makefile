main: main.o elevator.o person.o
	gcc -o main main.o elevator.o person.o -lncursesw
	./main

main.o: main.c elevator.h
	gcc -c main.c

elevator.o: elevator.c elevator.h person.h
	gcc -c elevator.c

person.o: person.c person.h
	gcc -c person.c

demo: demo_ncurses.c
	gcc -o demo_ncurses demo_ncurses.c -lncursesw
	./demo_ncurses

clean:
	rm -f *.o main demo_ncurses
