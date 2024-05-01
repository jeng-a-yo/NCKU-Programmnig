#include <stdio.h>
#include <math.h>

int main() {

    int a, b, c;
    scanf("%d-%d-%d", &a, &b, &c);

    int money = 0;

    if(a+b<c){
        money += 150;
    }

    if(a==c){
        money += 100;
    }

    if(a==c && a==b){
        money += 300;
    }

    if(a<b){
        money += 50;
    }

    pirntf("%d", money);

    return 0;
}
