#include <iostream>
#include <cstring>
using namespace std;

int main(int argc, char** argv){

    #include "includedFile.h";

    std::cout<< "included file!?" << endl;


    #ifdef DEBUG
        std::cout<< argv[1] <<'\n';
    #endif

    return 0;
}