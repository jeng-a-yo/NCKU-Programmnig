#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#include <malloc.h>


void shuffleArray(int arr[], int n) {
    for (int i = n - 1; i > 0; i--) {
        int j = rand() % (i + 1);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

void Homework6_1(){

    srand(time(NULL));

    int n, *arr;

    n = rand() % (200000 - 5000 + 1) + 5000;
    int ele;
    printf("%d\n", n);



    arr = malloc(n * sizeof(int));

    for(int i = 0; i < n; i++){

        arr[i] = rand() % 100000000;

        if (i == n-1){
            printf("%d\n", arr[i]);
        }else{
            printf("%d ", arr[i]);
        }
    }

    for(int i = n - 1; i >= 0; i--){
        if (i == 0){
            printf("%d", arr[i]);
        }else{
            printf("%d ", arr[i]);
        }
    }
}

void Homework6_2(){

    srand(time(NULL));

    int n = rand() % (200000 - 5000 + 1) + 5000;

    int *numbers = malloc(n * sizeof(int));
    printf("%d\n", n);

    for (int i = 0; i < n; i++) {
        numbers[i] = i + 1;
    }

    shuffleArray(numbers, n);

    int missingNumber = rand() % n + 1;

    for (int i = 0; i < n; i++) {
        if (numbers[i] != missingNumber) {
            if (i != n-1){
                printf("%d ", numbers[i]);
            }else{
                printf("%d\n", numbers[i]);
            }

        }
    }

    printf("%d", missingNumber);

}

void Homework6_3() {

    srand(time(NULL));

    int n = rand() % (100000 - 50000 + 1) + 50000, ele;
    printf("%d\n", n);

    long long int *arr = malloc(n * sizeof(long long int));
    long long int *brr = malloc((n+1) * sizeof(long long int));

    for (int i = 0; i < n; i++) {

        arr[i] = rand() % 100000000;

        if (i == n-1){
            printf("%lld\n", arr[i]);
        }else{
            printf("%lld ", arr[i]);
        }
    }

    brr[0] = 0;


    for (int i = 1; i < n+1; i++) {
        brr[i] = brr[i - 1] + arr[i-1];
    }

    int q, a, b, c;

    q = rand() % (100000 - 50000 + 1) + 50000;
    printf("%d\n", q);

    long long int *answerArr = malloc(q * sizeof(long long int));

    for (int i = 0; i < q; i++) {

        a = rand() % n + 1;
        b = rand() % n + 1;

        if (a > b){
            c = b;
            b = a;
            a = c;
        }

        printf("%d %d\n", a, b);

        answerArr[i] = brr[b] - brr[a-1];
    }


    for (int i = 0; i < q; i++) {
        if (i == q-1){
            printf("%lld", answerArr[i]);
        }else {
            printf("%lld\n", answerArr[i]);
        }

    }

}

void Homework6_4() {

    srand(time(NULL));

    int m, n;
    n = rand() % (1000 - 500 + 1) + 500;
    m = rand() % (1000 - 500 + 1) + 500;

    printf("%d %d\n", m, n);


    int arr[m][n];

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            arr[i][j] = rand() % 1000 + 1;

            if (j != n-1){
                printf("%d ", arr[i][j]);
            }else{
                printf("%d\n", arr[i][j]);
            }


        }
    }

    int a, b, c, d, temp;

    for (int i = 0; i < 5; i++) {


        a = rand() % m +1;
        b = rand() % n +1;
        c = rand() % m +1;
        d = rand() % n +1;

        printf("%d %d %d %d\n", a, b, c, d);

        temp = arr[a-1][b-1];
        arr[a-1][b-1] = arr[c-1][d-1];
        arr[c-1][d-1] = temp;
    }

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (j != n-1){
                printf("%d ", arr[i][j]);
            }else{
                printf("%d", arr[i][j]);
            }

        }
        if (i != m-1){
            printf("\n");

        }

    }

}



int main(){

    int opt;
    scanf("%d", &opt);

    if (opt == 1){

        Homework6_1();

    }else if (opt == 2){

        Homework6_2();

    }else if (opt == 3){

        Homework6_3();

    }else if (opt == 4){

        Homework6_4();

    }

    return 0;

}
