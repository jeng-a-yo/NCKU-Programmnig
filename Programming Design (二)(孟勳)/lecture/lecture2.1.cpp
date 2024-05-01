/*
class car{
    accelerate();
    brake();
    turn();
    string color
};

car NCKU;
NCKU.accelerate();
NCKU.brake();
NCKU.color = red;
*/

#include <iostream>
using namespace std;

class Timer{

    public:
        void hello(){

            cout << "hello CPP!!" << endl;

        }

};
int main(){

    Timer tmr;
    tmr.hello();
    return 0;
}