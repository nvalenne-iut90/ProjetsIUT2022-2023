#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

#define LIMIT 16

int main(int argc, char *argv[]){

    if (argc <1){
        return -1;
    }

    FILE* source_file = NULL;
    char buffer[LIMIT];
    int caracs;
    int ligne = 0;
    char* nom_fichier_source = argv[1];

    source_file = fopen(nom_fichier_source, "r");

    if (source_file == NULL){
        perror("Le fichier est pas bon");
        return 1;
    }

    do {
        caracs= fread(buffer, sizeof(char), LIMIT , source_file);
        if (caracs > 0){
            printf("%.7d0:", ligne);
            ligne += 1;
            for (int i = 0; i < LIMIT; i++){
                if (i%2 == 0) printf(" ");
                if (i<caracs){
                    printf("%02x", buffer[i]);
                }
                else
                    printf("  ");
            }
            printf("  ");
            for (int i = 0; i < caracs; i++ ){
                if (isprint(buffer[i]) == 0) printf(".");
                else printf("%c", buffer[i]);
            }
            printf("\n");
        }
    } while (caracs == 16);

    

    if (fclose(source_file) != 0){
        perror("Le fichier ne s'est pas fermé");
        return 1;
    }
    
    return 0;
}