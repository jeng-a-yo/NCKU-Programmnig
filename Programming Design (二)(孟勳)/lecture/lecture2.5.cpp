#include "lecture2.5.h"

Timer :: Timer(){start_st = 0;}
Timer :: Timer(time_t s){
    setStart(s);
}

void Timer :: start(){
    start_ts = time(NULL);
}
void Timer :: setStart(time_t ts){
    start_ts = ts;
}
time_t Timer :: getStart(){
    return start_ts;
}
int getElapsedTime(){
    return time(NULL) - getStart();
}