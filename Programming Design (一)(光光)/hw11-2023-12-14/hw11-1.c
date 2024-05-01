#include <stdio.h>
#include <stdlib.h>
#include <string.h>


void cleanTheBuffer(){

    char c;
    while (  (c = getchar()) != '\n' && c != EOF);

}

int getPrefixLength(const char *ipWithPrefix) {

    const char *slash = strrchr(ipWithPrefix, '/');
    return atoi(slash + 1);

}

void intToBinary(int num, char *binary, int binarySize) {
    int i;
    for (i = binarySize - 1; i >= 0; i--) {
        binary[i] = (num % 2) + '0';
        num /= 2;
    }
    binary[binarySize] = '\0';
}

int power(int m, int n){
    int result = 1;
    for (int i = 0; i < n; i++){
        result *= m;
    }
    return result;
}


// ========================================================================================================
/*
struct prefix{

    unsigned ip;
    unsigned length;

};
*/
char DATA[100000][100] = {};
int LENGTH = -1;

// ========================================================================================================

void input(char *filename) {

    FILE *file = fopen(filename, "r");

    char prefix[100];

    while (fgets(prefix, sizeof(prefix), file) != NULL) {
        int length = strlen(prefix);
        if (length > 0 && prefix[length - 1] == '\n') {
            prefix[length - 1] = '\0';
        }
        LENGTH += 1;
        strcpy(DATA[LENGTH], prefix);
    }
    fclose(file);
    return;
}

void length_distribution(){

    int countingArray[33] = {0};


    for (int i = 0; i < LENGTH; i++){

        int a, b, c, d, l;

        sscanf(DATA[i], "%d.%d.%d.%d/%d", &a, &b, &c, &d, &l);

        countingArray[l] += 1;
    }
}


void segment(int n){

    int *countingArray = malloc(sizeof(int) * power(2, n));
    int a, b, c, d, l;
    char binary_a[9], binary_b[9], binary_c[9], binary_d[9];

    for (int i = 0; i < 256; i++){

        countingArray[i] = 0;

    }

    for (int i = 0; i < LENGTH; i++){

        sscanf(DATA[i], "%d.%d.%d.%d/%d", &a, &b, &c, &d, &l);
        intToBinary(a, binary_a, 8);
        intToBinary(b, binary_b, 8);
        intToBinary(c, binary_c, 8);
        intToBinary(d, binary_d, 8);

        countingArray[a] += 1;


    }

    for (int j = 0; j < power(2, n); j ++){

        printf("The number of prefixes in group %d = %d\n", j, countingArray[j]);

    }

}


void prefix_insert(char *filename){

    FILE *file = fopen(filename, "r");
    int index = 0;

    char prefix[100];



    while (fgets(prefix, sizeof(prefix), file) != NULL) {
        int length = strlen(prefix);
        if (length > 0 && prefix[length - 1] == '\n') {
            prefix[length - 1] = '\0';
        }

        LENGTH += 1;
        strcpy(DATA[LENGTH], prefix);

        // printf("prefix: %s\n", prefix);
        // printf("DATA[%d]: %s\n", index, DATA[index]);

    }

    fclose(file);

    return;

}



void prefix_delete(char *filename){

    FILE *file = fopen(filename, "r");
    int index = 0;

    char prefix[100];



    while (fgets(prefix, sizeof(prefix), file) != NULL) {
        int length = strlen(prefix);
        if (length > 0 && prefix[length - 1] == '\n') {
            prefix[length - 1] = '\0';
        }

        for (int i = 0; i < LENGTH; i++){

            if (!(strcmp(DATA[i], prefix))){

                for (int j = i; j < LENGTH; j++){

                    strcpy(DATA[j], DATA[j+1]);

                }

                LENGTH -= 1;

            }

        }

    }

    fclose(file);

    return;



}


void search(char ip){

    for (int i = 0; i < LENGTH; i++){

        return (!(strcmp(DATA[i], ip)));

    }
}



int main(int argc, char *argv[]) {

    const char *filename = "routing_table.txt";
    const char *insertfilename = "inserted_prefixes.txt";
    const char *deletdfilename = "deleted_prefixes.txt";
    const char *tracefilename = "trace_file.txt";


    /*
    char *filename = malloc(100);
    strcpy(filename, argv[1]);

    char *insertfilename = malloc(100);
    strcpy(insertfilename, argv[2]);

    char *deletdfilename = malloc(100);
    strcpy(deletdfilename, argv[3]);

    char *tracefilename = mallloc(100);
    strcpy(tracefilename, argv[4]);

    int d = atoi(argv[5]);

    */

    input(filename);

    // length_distribution();

    prefix_insert(insertfilename);

    prefix_delete(deletdfilename);


    printf("The total number of prefixes in the input file is : %d.\n", LENGTH + 1);

    segment(8);

    return 0;
}
