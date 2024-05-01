#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(){

    int *p, *q;

    p = malloc(sizeof(int));
    q = malloc(sizeof(int));

    free(p);
    p = q;

    return 0;
}