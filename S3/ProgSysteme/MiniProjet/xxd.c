#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>

int main(int argc, char *argv[]){
    FILE* source_file = NULL;

    char* nom_fichier_source = argv[1];
    int c = 0;

    source_file = fopen(nom_fichier_source, "r");

    if (source_file == NULL){
        perror("Le fichier est pas bon");
        return 1;
    }

    do {
        c = fgetc(source_file);
        printf("%c", c);
    } while (c != EOF);

    if (fclose(source_file) != 0){
        perror("Le fichier ne s'est pas fermé");
        return 1;
    }
    
    return 0;
}