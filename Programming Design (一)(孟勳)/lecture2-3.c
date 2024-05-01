#include <stdio.h>

int main(){

    int height, length, width, volume, weight;

    height = 8;
    length = 12;
    width = 10;
    volume = height * length * width;
    weight = (volume + 165) / 166;
    
    printf("Dimensions: %d * %d * %d\n", height, length, width);
    printf("Volume: (cubic iniches): %d\n", volume);
    printf("Dimensional weight (pounds): %d\n", weight);

    return 0;
}