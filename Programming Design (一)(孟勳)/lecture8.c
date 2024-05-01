#include <stdio.h>

#define N 10

/*
void decompose(double x, long *intPart, double *fracPart){
    *intPart = (long int) x;
    *fracPart = x - *intPart;
}
*/

void MaxMin(int a[], int n, int *max, int *min);


int main(){

    int b[N], i, big, small;

    printf("Enter %d numbers: ", N);
    for (int i = 0; i < N; i++){
        scanf("%d", &b[i]);
    }
    MaxMin(b, N, &big, &small);
    printf("Largesr: %d\n", big);
    printf("Smallest %d\n", small);

    return 0;
}

void MaxMin(int a[], int n, int *max, int *min){

    int i;

    *max = *min = a[0];
    for (int i = 1; i < n; i++){
        if (a[i] > *max)
            *max = a[i];
        else if (a[i] < *min)
            *min = a[i];
    }

}