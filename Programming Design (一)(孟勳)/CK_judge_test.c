#include<stdio.h>

int main () {
    
    int d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, a, b, x, y, z;

    scanf("%1d%1d%1d%1d%1d%1d%1d%1d%1d%1d%1d%1d", &d1, &d2, &d3, &d4, &d5, &d6, &d7, &d8, &d9, &d10, &d11, &d12);
    a = d2 + d4 + d6 + d8 + d10 + d12;
    b = d1 + d3 + d5 + d7 + d9 + d11;
    x = 3 * a + b;
    y = x - 1;
    z = y % 10;
    printf("%d", 9 - z);

    
    return 0;
}