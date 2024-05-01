#include <stdbool.h>
#include <stdio.h>

bool IsPrime(int n){

    int divisor;

    if (n <= 1) return false;

    for (divisor = 2; divisor * divisor < n; divisor++){
        if (n % divisor == 0) return false;
    }
    return true;

}

int main(){

    int n;

    printf("Entera number: ");
    scanf("%d",&n);

    if (IsPrime(n)) printf("Prime\n");
    else printf("Not prime\n");

}