#include <stdio.h>
#include <stdbool.h>

#define SUDOKU_SIZE 81

int question[SUDOKU_SIZE], answer[SUDOKU_SIZE];

void PrintMap(int map[]){

    for (int j = 0; j < SUDOKU_SIZE; j++){
        printf("%d ", map[j]);
        if (j % 9 == 8) printf("\n");
    }

}

int GetFirstZeroIndex(int map[]){
    
    for (int i = 0; i < SUDOKU_SIZE; ++i)
        if (map[i]  == 0)
            return i;
    return -1;

}

bool CheckUnique(int arr[]){

    int arrUnity[9];

    for (int i = 0; i < 9; ++i)
        arrUnity[i] = 0;
    
    for (int i = 0; i < 9; ++i)
        ++arrUnity[arr[i] - 1];
    
    for (int i = 0; i < 9; ++i)
        if (arrUnity[i] != 1)
            return false;

    return true;

}

bool IsCorrect(int map[]){

    int checkArr[9];
    int location;

    // check rows
    for (int i = 0; i < 81; i += 9){

        for (int j = 0; j < 9; ++j)
            checkArr[j] = map[i+j];

        return CheckUnique(checkArr);

    }

    // check columns
    for (int i = 0; i < 9; ++i){

        for (int j = 0; j < 9; ++j)
            checkArr[j] = map[i + j * 9];
        
        return CheckUnique(checkArr);

    }

    // check cells
    for (int i = 0; i < 9; ++i){

        for (int j = 0; j < 9; ++j){

            location = 27 * (i / 3) + 3 * (i % 3) + 9 * (j / 3) + (j % 3);
            checkArr[j] = map[location];

        }
    
        return CheckUnique(checkArr);

    }

    return true;
}

bool solve(int question[]){

    int firstZero;
    int map[SUDOKU_SIZE];

    firstZero = GetFirstZeroIndex(question);
    if (firstZero == -1){

        if (IsCorrect(question)){
            for (int i = 0; i < SUDOKU_SIZE; i++)
                answer[i] = question[i];
            return true;
        }else{
            return false;
        }

    }else{
        
        for (int i = 0; i < SUDOKU_SIZE;  i++)
            map[i] = question[i];

        for (int num = 1; num  <= 9; num++){
            map[firstZero]  = num;
            if (solve(map))
                return true;
        }

        return false;

    }

}


int main(){

    for (int i = 0; i < SUDOKU_SIZE; i++){
        scanf("%d", &question[i]);
    }

    if (solve(question) == true){
        printf("Solvable!\n");
        PrintMap(answer);
    }else{
        printf("Unsolvable!\n");
    }

    return 0;
}
