#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <string.h>

int main(int argc, char *argv[]){

    if (argc < 3){
        printf("Usage : compare command1 [arg1, ..., argn] -- command2 [arg1, ..., argn]\n");
        return 2;
    }

    int i = 0;
    while (i < argc && strcmp(argv[i], "--")) {
        i++;
    }

    char* tabA[i-1];
    char* tabB[argc-i];

    for(int j = 1; j<i; j++){
        tabA[j-1]=(argv[j]);
    }
    for(int j = i+1; j<argc; j++){
        tabB[j-1-i]=(argv[j]);
    }
    
    pid_t pid1 = fork(); // Création du processus exécutant la première commande
    if (pid1 == -1) {
        // Il y a une erreur
        perror("fork");
        return 2;
    } else if (pid1 == 0) {
        FILE *fp = freopen("TabA.txt", "w+", stdout);
        if (fp == NULL){
            perror("fopen TabA\n ");
            return 2;
        } 
        char * const argument_list[] = {tabA[0], tabA[1], NULL};
        int status_code = execvp(tabA[0], argument_list);
        if (status_code == -1) {
            perror("Erreur exec1\n");
            return 2;
        }
        if (fputs(tabA[1], fp) == EOF){
            perror("fputs TabA\n ");
            return 2;
        } 
        if (fclose(fp) == EOF){
            perror("close TabA\n ");
            return 2;
        } 
    }

    pid_t pid2 = fork(); // Création du processus exécutant la deuxième commande
    if (pid2 == -1) {
        perror("fork");
        return 2;
    } else if (pid2 == 0) {
        FILE *fp = freopen("TabB.txt", "w+", stdout);
        if (fp == NULL){
            perror("fopen TabB\n ");
            return 2;
        } 
        char * const argument_list[] = {tabB[0], tabB[1], NULL};
        int status_code = execvp(tabB[0], argument_list);
        if (status_code == -1) {
            perror("Erreur exec2\n");
            return 2;
        }
        if (fputs(tabB[1], fp) == EOF){
            perror("fputs TabB\n ");
            return 2;
        } 
        if (fclose(fp) == EOF){
            perror("close TabB\n ");
            return 2;
        } 
    }

    int status_code = execlp("diff", "-u", "TabA.txt", "TabB.txt", NULL);

    return 0;
}
