#include <stdio.h>
#define INCHES_PER_POUND 166

int main(){


    /*
    int i, x = 1, y = 2;

    printf("x = %d, y = %d\n", x, y);
    printf("x = %u, y = %u\n", &x, &y);

    scanf("%d", &i);
    printf("i = %d\n", i);
    */

    int height, length, width, volume, weight;

    printf("input the height: ");
    scanf("%d", &height);
    printf("input the length: ");
    scanf("%d", &length);
    printf("input the width: ");
    scanf("%d", &width);

    volume = height * length * width;

    printf("Dimensions: %d * %d * %d\n", height, length, width);
    printf("Volume: (cubic iniches): %d\n", volume);

    
    weight = (volume + (INCHES_PER_POUND - 1)) / INCHES_PER_POUND;
    printf("Dimensional weight (pounds): %d\n", weight);

    return 0;
}