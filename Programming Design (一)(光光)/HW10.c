#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

typedef double (*F)(double, int);

double power(double x, int n){

    double result = 1;

    for(int i = 0; i < n; i++){

        result *= x;

    }

    return result;

 }

double multiply(double x, int n){

    return x * n;

 }

double divide(double x, int n){

    return x / n;

 }

double powerpower(F f, double x, int n, int m) {

    double result = f(x, n);
    // printf("%d\n");
    result = power(result, m);
    // printf("%d\n");

    return result;

}

int main(int argc, char *argv[]) {


    double x = atof(argv[2]);
    int n = atoi(argv[3]);
    int m = atoi(argv[4]);

    F functions[] = {power, multiply, divide};

    if(!(strcmp(argv[1], "power"))){

        printf("%f", powerpower(functions[0], x, n, m));

    }else if(!(strcmp(argv[1], "multiply"))){

        printf("%f", powerpower(functions[1], x, n, m));

    }else if(!(strcmp(argv[1], "divide"))){

        printf("%f", powerpower(functions[2], x, n, m));

    }

    return 0;

}



