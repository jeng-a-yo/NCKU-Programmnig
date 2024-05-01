#include <stdio.h>
#include <stdlib.h>



union FloatUnion  {
    float floatValue;
    unsigned int bitPattern;
};

union DoubleUnion  {
    double doubleValue;
    unsigned long long bitPattern;
};

int main(){

    union FloatUnion  FU;
    union DoubleUnion  DU;

    scanf("%f", &FU.floatValue);
    scanf("%lf", &DU.doubleValue);

    unsigned int* floatIntPointer = &FU.bitPattern;
    unsigned long long* doubleLongLongPointer = &DU.bitPattern;

    printf("float: %f\n", * (float*)floatIntPointer);
    printf("double: %lf\n", * (double*)doubleLongLongPointer);



    return 0;
}




