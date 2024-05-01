#include <iostream>
#include <string>
using namespace std;

class Timer{
    public:
        void hello(string name){
            cout << "Hello " << name << endl;
        }
};

int main(){
    Timer tmr;
    string userame;
    cout << "please enter your name: ";
    getline(cin, userame);
    tmr.hello(userame);
    return 0;

}