#include <ncurses.h> 

#define HEIGHT 15
#define WIDTH 20

int main() {
    initscr();
    WINDOW * win = newwin(HEIGHT, WIDTH, 0, 0);
    keypad(win, TRUE); // active les touches ↑ ↓ ← →

    bool run = true;
    int i = 0, j = 0;
    box(win, 0,0); // display border of window
    mvwaddch(win, i, j, 'x');
    wrefresh(win); // actual display function
    while(run) { 
        int input = wgetch(win);
        switch (input) {
            case 'q': // si 'q' quitter
                run = false;
                break;
            case KEY_UP: // se balader ↑ ↓ ← → dans la fenêtre
                if (i>0) {
                    i--;
                }
                break;
            case KEY_DOWN:
                if (i<HEIGHT-1) {
                    i++;
                }
                break;
            case KEY_LEFT:
                if (j>0) {
                    j--;
                }
                break;
            case KEY_RIGHT:
                if (j<WIDTH-1) {
                    j++;
                }
                break;
        }
        wclear(win);   // clear display area
        box(win, 0,0); // display border of window
        mvwaddch(win, i, j, 'x');
        wrefresh(win); // actual display function
    }
    endwin();
    return 0;
}
