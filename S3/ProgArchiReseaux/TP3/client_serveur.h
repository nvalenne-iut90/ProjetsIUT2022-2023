#ifndef CLIENT_SERVEUR_H
#define CLIENT_SERVEUR_H

#include <unistd.h>

extern int creer_serveur_tcp(int port, int debug);
extern int attendre_client_tcp(int socket_serveur, int debug);
extern char *address_client(void);

extern int creer_client_tcp(const char *nom, int port, int debug);

#endif // !CLIENT_SERVEUR_H
