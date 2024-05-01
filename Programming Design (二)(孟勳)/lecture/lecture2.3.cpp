#include <iostream>
#include <ctime>
#include <unistd.h>
using namespace std;

class Timer{

    public:

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

    Timer tmr;
    time_t ts;

    ts = time(NULL);
    tmr.setStart(ts);
    sleep(1);

    cout << "Start time: " << tmr.getStart() << endl;
    cout << "Elapsed time: " << tmr.getElapsedTime() << endl;

    return 0;

}