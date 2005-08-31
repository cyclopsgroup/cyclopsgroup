Compile main.cpp with command
g++ -o main.o -lnsl -lsocket main.cpp

"./main.o server" or "./main.o server 11111" will start the socket server on port 9527(default) or 11111
"./main.o client" or "./main.o client 11111" will start the client console talking throught port 9527 or 11111
"./main.o" or other will show the usage message

In client sonsole, user can input a command or symbol at a time
Available commands are:
list: to show all available symbols
quit: to quit current console
stop: to stop the socket server
Available symbols are:
BHS: BrookField Homes WI
GOOG: Google Inc
UPL: Ultra Petroleum Corp
DIOD: Diodes Inc
QSII: Quality Systems Inc


Note: The uploaded main.o file is compiled on my local machine(Fedora Core 3)
It may not be able to run on other systems.
Please recompile it before run.

------------------Sample session-------------------
{apache:~} g++ -o main.o -lnsl -lsocket main.cpp
main.cpp:202:2: warning: no newline at end of file
{apache:~} ./main.o server &
[1] 21622
{apache:~} Start server process
Creating socket ... 3 is returned
Binding rocket to localhost:9527... 0 is returned
Listeninig ... 0 is returned

{apache:~} ./main.o client
Start client console
Connecting socket... 3 is returned
>>Enter a symbol name, or quit|stop|list
list
>>Available symbols
BHS: BrookField Homes WI
GOOG: Google Inc
UPL: Ultra Petroleum Corp
DIOD: Diodes Inc
QSII: Quality Systems Inc
>>Enter a symbol name, or quit|stop|list
GOOG
>>Last trade of [GOOG] (Google Inc) is 299.940002

>>Enter a symbol name, or quit|stop|list
QSII
>>Last trade of [QSII] (Quality Systems Inc) is 52.709999

>>Enter a symbol name, or quit|stop|list
stop
>>No such symbol [stop]!
Stop command is received, stopping service...

>>Enter a symbol name, or quit|stop|list
Bye bye
quit
[1]+  Done                    ./main.o server
{apache:~}