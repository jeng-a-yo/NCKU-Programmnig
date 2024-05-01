#include <stdio.h>

int shopOpen = 0, money = 0, KirbyA = 0, KirbyB = 0, KirbyC = 0;

int GCD(int m, int n){
    if (m > n){
        return GCD(n, m);
    }

    if (n % m == 0){
        return m;
    }

    return GCD(m, n % m);
}

int CheckInList(int options){

    int list[] = {1, 2, 3, 4};
    for (int i = 0; i < 4; i++){
        if (list[i] == options){
            return 1;
        }
    }
    return 0;
}


void Openning(){

    if (shopOpen == 0){
        printf("Your shop is openning!\n");
        shopOpen = 1;
    }else {
        printf("Your Kirby shop has already opened :(\n");
    }
}

void List(int KirbyA, int KirbyB, int KirbyC, int money){

    printf("===================================\n");
    printf("Kirby-A: %d\n", KirbyA);
    printf("Kirby-B: %d\n", KirbyB);
    printf("Kirby-C: %d\n", KirbyC) ;
    printf("Kirby: %d, Money: %d\n", KirbyA + KirbyB + KirbyC, money);
    printf("===================================\n");
}

void CleanBuffer(){
    char c;
    while ((c = getchar()) != '\n' && c != EOF);
}


void Adding(){

    int addingKirbyA, addingKirbyB, addingKirbyC, gcd, price, inputValue;
    char character;


    printf("Please enter three numbers: ");

    inputValue = scanf("%d %d %d", &addingKirbyA, &addingKirbyB, &addingKirbyC);
    character = getchar();

    if (!(inputValue== 3 && addingKirbyA > 0  &&  addingKirbyB > 0  &&  addingKirbyC > 0 && character == '\n')) {

        ungetc(character, stdin);
        CleanBuffer();

        while(1){

            printf("Error: Please try again or enter '-1 -1 -1' to make a new options: ");

            inputValue = scanf("%d %d %d", &addingKirbyA, &addingKirbyB, &addingKirbyC);
    	    character = getchar();


            if (inputValue == 3 && addingKirbyA > 0  &&  addingKirbyB > 0  &&  addingKirbyC > 0 && character == '\n'){
                break;
            }else if (addingKirbyA == -1 && addingKirbyB == -1 && addingKirbyC == -1) {
                return;
            }else{
                ungetc(character, stdin);
                CleanBuffer();
            }
        }
    }
    gcd = GCD(GCD(addingKirbyA, addingKirbyB), addingKirbyC);
    price = addingKirbyA + addingKirbyB + addingKirbyC - gcd * 2;

    KirbyA += addingKirbyA;
    KirbyB += addingKirbyB;
    KirbyC += addingKirbyC;
    money += price;

    printf("Divide these Kirbys into %d boxes, with the number of Kirby in each box being %d, %d, and %d\n", gcd, (addingKirbyA / gcd), (addingKirbyB / gcd), (addingKirbyC / gcd));
    printf("Price: %d\n", price);
    return;

}



int main(){
    printf("//////////Welcome to NCKU-PD1-Kirby-Shop\\\\\\\\\\\\\\\\\\\\\n");
    int options;

    while (1){

        printf("Options: (1)Openning (2)List (3)Adding (4)Exit\n");

        scanf("%d", &options);


        if (CheckInList(options)){

            if (options == 1){
                Openning();
            }else if (options == 4){
                break;
            }else if (options == 2){
                List(KirbyA, KirbyB, KirbyC, money);
            }else if(options == 3 && shopOpen == 1){
                    Adding();
            }else {
                printf("Your Kirby shop has not opened\n");
            }
        }else{
            printf("Error: Please try again!\n");
        }
    }

    return 0;
}
