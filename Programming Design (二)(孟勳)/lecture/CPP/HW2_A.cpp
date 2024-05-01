#include <stdio.h>

int main()
{

    int a1, a2, b1, b2, n1, n2;
    scanf("%d/%d+%d/%d", &a1, &a2, &b1, &b2);

    n1 = a1 * b2 + a2 * b1;
    n2 = a2 * b2;

    printf("%d/%d", n1, n2);

    return 0;
}
