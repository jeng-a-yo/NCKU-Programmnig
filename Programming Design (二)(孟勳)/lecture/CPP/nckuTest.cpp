#include <stdio.h>
#include <math.h>

int main() {
	
	int a, b, c;
	float n1, n2, n3;
	
	scanf("%d %d %d", &a, &b, &c);		
	
	n1 = pow(a * c, 0.5);
	n2 = pow(a, b);
	n3 = log10(a * b * c);
	
	printf("%.3f", n1 + n2 + n3);																	

    return 0;
}

