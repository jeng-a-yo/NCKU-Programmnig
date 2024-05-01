#include <stdio.h>
#include <string.h>

#define NUM_PLANTES 9

int main(int argc, char *argv[]){

    char *plantes[] = {"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto"};
    int i, j;

    for (i = 1; i < argc; i++){
        for (j = 0; j < NUM_PLANTES; j++){
            if (strcmp(argv[i], plantes[j]) == 0){
                printf("%s is a plant %d.", argv[i], j + 1);
                break
            }
        }
        if (j == NUM_PLANTES)
            printf("%s is not a plant", argv[i]);
    }

    return 0;
}