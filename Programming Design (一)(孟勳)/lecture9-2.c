#include <stdio.h>

#define SIZE 10

void Bubble(int *p){

    int temp;
    for (int i = 0; i < SIZE - 1; i++){
        for (int j = 0; j < SIZE - 1; j++){
            if (p[j] < p[j + 1]){
                temp = p[j];
                p[j] = p[j + 1];
                p[j + 1] = temp;
            }
        }
    }
    return;
}

int main(){

    int a[SIZE] = {1, 3, 5, 7, 9, 2, 4, 6, 8, 10};

    Bubble(a);
    for (int i = 0; i < SIZE; i++){
        printf("%d ", a[i]);
    }

    return 0;
}