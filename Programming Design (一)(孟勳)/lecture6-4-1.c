#include <stdbool.h>

#define STACK_SIZE 100

int contents[STACK_SIZE];
int top = 0;

void MakeEmpty(void){
    top = 0;
}


bool IsEmpty(void){
    return top == 0;
}


bool IsFull(void){
    return top == STACK_SIZE;
}


void StackOverflow(void){
    printf("Stack overflow!\n");
}

void StackUnderflow(void){
    printf("Stack underflow!\n");
}


void Push(int i){

    if (IsFull()){
        StackOverflow();
    }else{
        contents[top++] = i;
    }

}


int Pop(void){

    if (IsEmpty()){
        StackUnderflow();
    }else{
        return contents[--top];
    }

}