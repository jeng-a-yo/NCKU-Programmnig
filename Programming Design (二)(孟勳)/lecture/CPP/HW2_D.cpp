#include <stdio.h>

int main()
{

	
	int a, b, N, result;
	scanf("%d, %d, %d", &a, &b, &N);
	result = (b << a) + (a | b) - (N >> b) * (a ^ b) - (a & b);
	printf("%d", result);


    return 0;
}
