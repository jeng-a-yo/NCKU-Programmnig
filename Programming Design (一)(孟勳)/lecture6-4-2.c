#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MAX_NUMBER 100

int secretNumber;

void InitalizeNumberGenerator(void){
    srand((unsigned) time(NULL));
}


void ChooseNewSecretNumber(void){
    secretNumber = rand() % MAX_NUMBER + 1;
}


void ReadGuesses(void){

    int guess, numGuesses = 0;

    for (;;){

        numGuesses++;

        printf("Enter guess: ");
        scanf("%d", &guess);

        if (guess == secretNumber){
            printf("You won in  %d guesses.\n", numGuesses);
            return;
        }else if (guess < secretNumber){
            printf("Too low; try again.\n");
        }else{
            printf("Too high; try again.\n");
        }

    }

}

int main(){

    char command;
    printf("Guess the secret number between 1 and %d.\n\n", MAX_NUMBER);

    InitalizeNumberGenerator();

    do{

        ChooseNewSecretNumber();
        printf("A new secret number has been chosen.\n\n");

        ReadGuesses();
        printf("Play again? (Y/N) ");
        scanf("%c", &command);
        printf("\n");

    } while (command == 'y' || command == 'Y');

    return 0;
}