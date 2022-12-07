#include "client_serveur.h"
#include <stdio.h>
#include <stdlib.h>

#define DEBUG 0

int main(int argc, char *argv[]){
    int values[16];
    int port;
    int ret;
    char *serveur = argv[1];
    int port = atoi(argv[2]);
    if (argc < 3){
        fprintf(stderr, "Usage: %s serveur port\n", argv[0]);
        return EXIT_FAILURE;
    } else {
        port = atoi(argv[1]);
    }
    // initialise tableau
    for (int i = 0; i < 16; i++){
        if (i%2==0) 
            values[i] = 0;
        else
            values[i] = i;
    }
    int sock = creer_client_tcp(serveur, port, DEBUG);
    
}