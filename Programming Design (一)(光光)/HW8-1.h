void init(int*** mp){

    int i, j, k;

    for(int i = 0; i < 130; i++){

        mp[i] = (int**)malloc(130 * sizeof(int*));

        for(int j = 0; j < 130; j++){

            mp[i][j] = (int**)malloc(130 * sizeof(int*));

            for(int k = 0; k < 130; k++){

                mp[i][j][k] = 1;

            }
        }
    }

}


void modify(int* a,int val){

    *a = val;

}


int query(int**** mp,char a[4],char b[4]){



    int elementA = (*mp)[a[0]][a[1]][a[2]];
    int elementB = (*mp)[b[0]][b[1]][b[2]];

    if ((elementA + elementB) < 0){

        return ( ((elementA + elementB) * (-1)) % 2 == 1 ? 1 : 0 );

    }else{

        return ( (elementA + elementB) % 2 == 1 ? 1 : 0 );

    }


}















