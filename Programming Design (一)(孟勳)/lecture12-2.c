#include <stdio.h>
#include <string.h>
#include <stdlib.h>

char *concat(const char *s1, const char *s2){

    char *result;

    result = malloc(sizeof(s1) + sizeof(s2));

    if (result == NULL){
        printf("Error: malloc failed in concat\n");
        exit(EXIT_FAILURE);
    }
    strcpy(result, s1);
    strcat(result, s2);
    return result;

}

int main(){

    char *p;
    p = concat("abc", "def");
    printf("%s\n", p);

    return 0;
}