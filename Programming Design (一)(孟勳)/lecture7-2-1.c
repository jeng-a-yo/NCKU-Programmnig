#include <stdio.h>

void HanoiTower(int n, char a, char b, char c){

    if (n == 1){
        printf("[Disk %d] %c -> %c\n", n, a, c);
    }else{

        HanoiTower(n - 1, a, c, b);
        printf("[Disk %d] %c -> %c\n", n, a, c);
        HanoiTower(n - 1, b, a, c);

    }
}

int main(){

    int n;
    printf("Input number of disks: ");
    scanf("%d", &n);
    HanoiTower(n, 'A', 'B', 'C');

    return 0;
}
