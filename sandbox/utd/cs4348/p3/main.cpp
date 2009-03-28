#include <iostream>
#include <netdb.h>
#include <netinet/in.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>

#define BUFSIZE 1024
#define SYMBOL_NUMBER 5

using namespace std;

char symbols[SYMBOL_NUMBER][20] = {"BHS", "GOOG", "UPL", "DIOD", "QSII"};
char symbol_titles[SYMBOL_NUMBER][100] = {"BrookField Homes WI", "Google Inc", "Ultra Petroleum Corp", "Diodes Inc", "Quality Systems Inc"};
float quotes[SYMBOL_NUMBER] = {46.82, 299.94, 37.44, 36.01, 52.71};

/**
 * Get the quote for given symbol
 */
float get_quote(char symbol[])
{
    float ret = 0.0;
    for(int i = 0; i < SYMBOL_NUMBER; i++)
    {
        if(strcmp(symbol, symbols[i]) == 0)
        {
            ret = quotes[i];
            break;
        }
    }
    return ret;
}

/**
 * Get the title for given symbol
 */
char* get_symbol_title(char symbol[])
{
    char* ret = NULL;
    for(int i = 0; i < SYMBOL_NUMBER; i++)
    {
        if(strcmp(symbol, symbols[i]) == 0)
        {
            ret = symbol_titles[i];
            break;
        }
    }
    return ret;
}


/**
 * Show some help message
 */
void help()
{
	cout << "Usage: " << endl;
	cout << "./main server[ port]              Start server proces" << endl;
	cout << "./main client[ port]               Start client console" << endl;
	cout << "./main help or other               Show this help message" << endl;
	cout << "Default port number is 9527" << endl;
}

/**
 * Server side service process
 */
void server(int port)
{
	cout << "Start server process" << endl;
    
    struct   sockaddr_in sin;
    struct   sockaddr_in pin;
    
    int sd = socket(AF_INET, SOCK_STREAM, 0);
    cout << "Creating socket ... " << sd << " is returned" << endl;
    memset(&sin, 0, sizeof(sin));
    sin.sin_family = AF_INET;
    sin.sin_addr.s_addr = INADDR_ANY;
    sin.sin_port = htons(port);

    int ret = bind(sd, (struct sockaddr *) &sin, sizeof(sin));
    cout << "Binding rocket to localhost:" << port << "... ";
    cout << ret << " is returned" << endl;

    ret = listen(sd, 5);
    cout << "Listeninig ... " << ret << " is returned" << endl;
    
    /* wait for a client to talk to us */
    int addrlen = sizeof(pin);
    int sd_current = accept(sd, (struct sockaddr *)  &pin, (socklen_t*)&addrlen);

	char buf[BUFSIZE];  /* used for incoming string, and outgoing data */
    char in[BUFSIZE];  
    while(strcmp(in, "stop") != 0)
    {
    	strcpy(buf, "");
	    int count = read(sd_current, buf, sizeof(buf));
	    
	    strncpy(in, buf, count - 2);
	    in[count - 2] = 0;

        char* symbol_title = get_symbol_title(in);
        if(symbol_title == NULL)
        {
            sprintf(buf, "No such symbol [%s]!\n", in);
        }
        else
        {
            float quote = get_quote(in);
	        sprintf(buf, "Last trade of [%s] (%s) is %f\n", in, symbol_title, quote);
        }
	    write(sd_current, buf, strlen(buf) + 1);
    }
    
    cout << "Stop command is received, stopping service..." << endl;
    close(sd_current); 
    close(sd);
    
    cout << "Bye bye" << endl;
    return;
}

/**
 * Client console process
 */
void client(int port)
{
	cout << "Start client console" << endl;

	struct hostent *hp;
   	hp = gethostbyname("localhost");

   	struct sockaddr_in pin;
   	memset(&pin, 0, sizeof(pin));
   	pin.sin_family = AF_INET;
   	pin.sin_addr.s_addr = ((struct in_addr *)(hp->h_addr))->s_addr;
   	pin.sin_port = htons(port);

   	/* create an Internet domain socket */
   	int sd = socket(AF_INET, SOCK_STREAM, 0);
   	cout << "Connecting socket... " << sd << " is returned" << endl;
   	connect(sd,(struct sockaddr *)  &pin, sizeof(pin));

    while(true)
    {
        cout << ">>Enter a symbol name, or quit|stop|list" << endl;
        char name[100];
       	scanf("%s", name);
        if(strcmp(name, "list") == 0)
        {
            cout << ">>Available symbols" << endl;
            for(int i = 0; i < SYMBOL_NUMBER; i++)
            {
                cout << symbols[i] << ": " << symbol_titles[i] << endl;
            }
        }
        else if(strcmp(name, "quit") == 0)
        {
            break;
        }
        else
        {
           	write(sd, name, strlen(name) + 2);
           	char buf[BUFSIZE];
           	read(sd, buf, BUFSIZE);
       	    cout << ">>" << buf << endl;
        }
    }
   	close(sd);
}

int main(int argc, char** args)
{
	if(argc < 2)
	{
		help();
		return 1;
	}
	//The first argument is command
	char* command = args[1];
	
	//The optional second argument is port number
	int port = 9527;
	if(argc >= 3)
	{
		port = atoi(args[2]);
	}
	
	if(strcmp(command, "server") == 0)
	{
		server(port);
	}
	else if(strcmp(command, "client") == 0)
	{
		client(port);
	}
	else
	{
		help();
	}
	return 0;
}