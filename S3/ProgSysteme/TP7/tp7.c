
#include <pthread.h>
#include <stdio.h>

/*

                Gestion des terminaisons

void *print_salut(void *arg)
{
    printf("Salut !\n", arg);
    return NULL;
}

int main(int argc, char *argv[])
{
    for (size_t i = 0; i < 3; i++)
    {
        pthread_t th;
        pthread_create(&th, NULL, print_salut, valeur);
        pthread_join(th, NULL);
    }
    printf("Fini !\n");
    return 0;
}


                Passage de paramètres

void *print_salut(void *arg)
{
    printf("Salut ! %d\n", arg);
    return NULL;
}

int main(int argc, char *argv[])
{
    for (size_t i = 0; i < 3; i++)
    {
        pthread_t th;
        int valeur;
        int res = scanf("%d", &valeur);
        if( res == 1){
            pthread_create(&th, NULL, print_salut, valeur);
            pthread_join(th, NULL);
        } else {
            return 1;
        }
    }
    printf("Fini !\n");
    return 0;
}

*/

void *calcul_partielle(void *arg){
    return NULL;
}
int main(int argc, char const *argv[])
{
    return 0;
}

