#include <iostream>
#include <ctime>
#include <unistd.h>
using namespace std;

class Timer{

    public:

        Timer(){
            start_ts = 0;
        }

        Timer(time_t s){
            setStart(s);
        }

        void start(){
            start_ts = time(NULL);
        }

        void setStart(time_t ts){
            start_ts = ts;
        }

        time_t getStart(){
            return start_ts;
        }

        int getElapsedTime(){
            return time(NULL) - getStart();
        }


    private:
        time_t start_ts;
};

int main(){

    Timer tmr1;
    Timer tmr2(time(NULL));

    tmr1.start();

    sleep(2);

    cout << "tmr1.start: " << tmr1.getStart() << ", elapsed time=" << tmr1.getElapsedTime() <<  endl;
    cout << "tmr2.start: " << tmr2.getStart() << ", elapsed time=" << tmr2.getElapsedTime() <<  endl;

    return 0;

}

// ------------------------------------------------------------------------------------------------

class Tire{

    public:
        Tire(){cout << "Tire consstructor" << endl;};

};

class Car{

    public:
        Car(){cout << "Car constructor" << endl;};
    private:
        Tire tireB;

};

int main(){
    Car objA;
    return 0;
}