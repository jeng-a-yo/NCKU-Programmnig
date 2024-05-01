#include <stdio.h>

int main()
{

    int n, k1, k2, k3, sum;
    
    scanf("%d %d %d %d", &n, &k1, &k2, &k3);
	
	sum = n * (k1/3 + k2/3 + k3/3) * 2 + n * (k1%3 + k2%3 + k3%3);
	printf("%d", sum);
	


    return 0;
}
