#include<stdio.h>
#include <malloc.h>


void Homework6_1(){

    int n, *arr;

    scanf("%d", &n);

    arr = malloc(n * sizeof(int));

    for(int i = 0; i < n; i++){
        scanf("%d", &arr[i]);
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

    int n;
    scanf("%d", &n);

    int sum = 0;
    for (int i = 1; i <= n; i++) {
        sum += i;
    }

    for (int i = 0; i < n - 1; i++) {
        int num;
        scanf("%d", &num);
        sum -= num;
    }

    printf("%d", sum);


}

void Homework6_3() {

    int n;
    scanf("%d", &n);

    int *arr = malloc(n * sizeof(int));
    long long int *brr = malloc((n+1) * sizeof(long long int));

    for (int i = 0; i < n; i++) {
        scanf("%d", &arr[i]);
    }

    brr[0] = 0;


    for (int i = 1; i < n+1; i++) {
        brr[i] = brr[i - 1] + arr[i-1];
    }

    /*
    for (int i = 0; i < n+1; i++) {
        printf("%d ", brr[i]);
    }
    */

    int q, a, b;
    scanf("%d", &q);

    long long int *answerArr = malloc(q * sizeof(long long int));


    for (int i = 0; i < q; i++) {
        scanf("%d %d", &a, &b);
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
    int m, n;
    scanf("%d %d", &m, &n);

    int arr[m][n];

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            scanf("%d", &arr[i][j]);
        }
    }

    int a, b, c, d, temp;

    for (int i = 0; i < 5; i++) {
        scanf("%d %d %d %d", &a, &b, &c, &d);
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
