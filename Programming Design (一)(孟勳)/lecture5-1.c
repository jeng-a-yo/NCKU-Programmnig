#include <stdio.h>
#include <stdbool.h>


int main(){

    bool digitSeen[10] = {false};
    int digit;
    long long int n;

    printf("Enter a number: ");
    scanf("%lld", &n);

    while (n > 0){

        digit = n % 10;

        if (digitSeen[digit]) break;
        digitSeen[digit] = true;

        n /= 10;
    }

    if (n > 0){
        printf("Repeated digit\n"); 
    }else{
        printf("No repeated digit\n");
    }
    return 0;
}
