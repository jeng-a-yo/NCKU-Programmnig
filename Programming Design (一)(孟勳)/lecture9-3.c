#include <stdio.h>

#define NUM_ROW 10
#define NUM_COL 10

int main(){

    int a[NUM_ROW][NUM_COL];

    for (int i = 0; i < NUM_ROW; i++){
        for (int j = 0; j < NUM_COL; j+){
            a[i][j] = 0;
        }
    }

    int *p;
    for (p = &a[0][0]; p < &a[NUM_ROW - 1][NUM_COL - 1]; p++)
        *p = 0;


    return 0;
}