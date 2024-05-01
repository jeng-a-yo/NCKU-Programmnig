#include <stdio.h>
#include <stdbool.h>
#include <limits.h>

int main() {
    long long a, b;

    scanf("%lld %lld", &a, &b);

    bool check1, check2;

    check1 = ((a - 4611686018427387903) + (b - 4611686018427387904)) > 0;
    check2 = ((a + 4611686018427387904) + (b + 4611686018427387904)) < 0;

    if (a > 0 && check1 || a < 0 && check2) {
        printf("Yes");
    } else {
        printf("No");
    }

    return 0;
}
