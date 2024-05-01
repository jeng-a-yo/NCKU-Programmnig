#include <stdio.h>
#include <stdlib.h>


int CheckInList(int n, int arr[], int arrLen) {
    for (int i = 0; i < arrLen; i++) {
        if (n == arr[i]) {
            return 1;
        }
    }
    return 0;
}


void generate_combinations(int array[], int data[], int start, int end, int index, int r, int** result, int* result_index) {
    if (index == r) {

        for (int i = 0; i < r; i++) {
            result[*result_index][i] = data[i];
        }
        (*result_index)++;
        return;
    }

    for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
        data[index] = array[i];
        generate_combinations(array, data, i + 1, end, index + 1, r, result, result_index);
    }
}

void deleteElement(int arr[], int size, int k) {

    if (k < 0 || k >= size) {
        return;
    }

    for (int i = k; i < size - 1; ++i) {
        arr[i] = arr[i + 1];
    }

}


int main() {
    int fileNameArr[21], fileSizeArr[21], arrLen = 0;

    while (1) {

        int opt;
        printf("Options: ");
        scanf("%d", &opt);

        if (opt == 1) {
            int fileName, fileSize;
            int array[21];

            for (int i = 0; i < 21; i++) {
                array[i] = i;
            }


            printf("Please input file name and file size: ");
            scanf("%d %d", &fileName, &fileSize);

            fileNameArr[arrLen] = fileName;
            fileSizeArr[arrLen] = fileSize;

            arrLen ++;
            // printf("%d\n", arrLen);


            if (arrLen > 20) {



                printf("Hard drive exceeds its capacity, please enter the number of files to be deleted: ");

                int k, m;
                scanf("%d %d", &k, &m);




                int data[k];

                int combinations_count = 1;


                for (int i = 1; i <= k; i++) {
                    combinations_count *= (21 - i + 1);
                    combinations_count /= i;
                }

                int** combinations_result = (int**)malloc(combinations_count * sizeof(int*));

                for (int i = 0; i < combinations_count; i++) {
                    combinations_result[i] = (int*)malloc(k * sizeof(int));
                }

                int result_index = 0;

                generate_combinations(array, data, 0, 20, 0, k, combinations_result, &result_index);

                int finalGap = 0;

                for (int i = 0; i < 21; i++){

                    finalGap += fileSizeArr[i];

                }

                finalGap += m;


                int tempSum = 0;
                int finalIndex[k], tempIndex[k];

                for (int i = 0; i < combinations_count; i++) {

                    for (int j = 0; j < k; j++) {

                        tempSum += fileSizeArr[combinations_result[i][j]];
                        tempIndex[j] = combinations_result[i][j];

                    }

                    if (abs(tempSum - m) < finalGap){

                        finalGap = abs(tempSum - m);


                        for (int l = 0; l < k; l++){

                            finalIndex[l] = tempIndex[l];

                        }

                    }

                    tempSum = 0;


                }

                // int size = sizeof(fileNameArr) / sizeof(fileNameArr[0]);


                for (int i = 0; i < k; i++){

                    if (i == k - 1){
                        printf("%d\n", fileNameArr[finalIndex[i]]);
                    }else{
                        printf("%d ",fileNameArr[finalIndex[i]]);
                    }

                    deleteElement(fileNameArr, arrLen, finalIndex[i]);
                    deleteElement(fileSizeArr, arrLen, finalIndex[i]);
                    arrLen --;

                }


                for (int i = 0; i < combinations_count; i++) {
                    free(combinations_result[i]);
                }
                free(combinations_result);






            }

        } else if (opt == 2) {

            int n;

            printf("Please input the file name: ");
            scanf("%d", &n);

            if (CheckInList(n, fileNameArr, arrLen)) {

                printf("YES\n");

            } else {

                printf("NO\n");


            }

        } else if (opt == 3) {

            break;

        }

    }

    return 0;
}
