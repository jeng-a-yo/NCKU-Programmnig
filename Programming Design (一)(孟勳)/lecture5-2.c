#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define NUM_SUITS 4
#define NUM_RANKS 13

int main(void){ 

    const char rankCode[] = {'2', '3', '4', '5', '6', '7', '8', '9', 't', 'j', 'q', 'k', 'a'};
    const char suitCode[] = {'c', 'd', 'h', 's'};

    bool inHand[NUM_SUITS][NUM_RANKS] = {false};

    int numCards, rank, suit;

    srand((unsigned) time(NULL));


    printf("Enter number of cards in hand: ");
    scanf("%d",&numCards);

    printf("Your hand:");
    while (numCards > 0){
        suit = rand() % NUM_SUITS;
        rank = rand() % NUM_RANKS;

        if (!inHand[suit][rank]){
            inHand[suit][rank] = true;
            numCards -= 1;

            printf(" %c%c", rankCode[rank], suitCode[suit]);
        }
    }
    printf("\n");

    return 0;
}