#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

#define LIMIT 16    // (16 valeurs par ligne)

int main(int argc, char *argv[]){

    FILE* source_file = NULL;           // Descripteur du fichier source
    char buffer[LIMIT];                 // Tableau de caractères par ligne
    int caracs;                         // Nombre d'octets écrits dans le tableau 
    int ligne = 0;                      // Numéro de ligne
    char* nom_fichier_source = argv[1]; // Nom du fichier source

    if (argc < 2){      // DANS LE CAS OU LE FICHIER N'EST PAS MIS EN ARGUMENT => ./xxd

        source_file=stdin;
        
    } else {            // DANS LE CAS OU LE FICHIER EST MIS EN ARGUMENT => ./xxd exemple.txt

        source_file = fopen(nom_fichier_source, "r");   // Ouverture du fichier source en lecture

        if (source_file == NULL){      // Si Erreur d'ouverture du fichier
            perror("Le fichier est pas bon");
            return 1;
        }
    }
    
    do {
        /*
        Insertion du contenu du fichier dans le tableau + calcul du nombre d'octets écrits dans le tableau
        */
        caracs= fread(buffer, sizeof(char), LIMIT , source_file);


        if (caracs > 0){
            printf("%.7d0:", ligne);        // 1ere colonne : Affiche le numéro de la ligne (ex : 00000010)
            ligne += 1;

            /*
            Boucle for : 2e colonne
            - Affiche les valeurs du tableau en héxadécimal
                séparées d'un espace toutes les 2 valeurs
            */
            for (int i = 0; i < LIMIT; i++){
                if (i%2 == 0) printf(" ");  // Séparation visuelle
                if (i<caracs){
                    printf("%02x", buffer[i]);  // Affichage de la valeur en 2 chiffres
                }
                else
                    printf("  ");   // Si nbOctets écrits < LIMIT ==> Affichage terminée mais 
            }
            printf("  ");

            /*
                Boucle for : 3e colonne
                - Pour chaque caractère : 
                    - Si caractère non imprimable = affiche un point
                    - Sinon affiche le caractère
            */
            for (int i = 0; i < caracs; i++ ){
                if (isprint(buffer[i]) == 0) printf(".");
                else printf("%c", buffer[i]);
            }
            printf("\n");
            }
        } while (caracs == 16);

        if (fclose(source_file) != 0){      // Fermeture du fichier source
            perror("Le fichier ne s'est pas fermé");
            return 1;
        }
    
    
    return 0;
}