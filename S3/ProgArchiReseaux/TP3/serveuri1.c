#include "client_serveur.h"
#include <stdio.h>
#include <stdlib.h>

#define DEBUG 0

int main(int argc, char *argv[]){
    int values[16];
    int port;
    int ret;

    if (argc < 2){
        printf("Usage : %s [port]\n", argv[0]);
        return EXIT_FAILURE;
    } else {
        port = atoi(argv[1]);
    }

    int lsock = creer_serveur_tcp(port, DEBUG);
    int sock = attendre_client_tcp(lsock, DEBUG);

    ret = read(sock, &values, sizeof(values));
    if (ret > 16){
        close(sock);
        close(lsock);
        printf("Nombre d'octets dépassé : %d octets de plus", 16-ret);
        return EXIT_FAILURE;
    }

}