/*

<target1>: <dependence>
<tab><command>
<tab><command>

<target2>: <dependence>
<tab><command>
<tab><command>

*/

timer6: timer6.o main6.o
    g++ -o timer6 main.o timer6.o

timer6.o: timer6.cpp timer6.h
    g++ -o timer6.cpp

main6.cpp: main6.cpp timer6.h
    g++ -o main6.cpp

clean:
    rm timer6 *.o