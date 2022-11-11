# Enterprises
## **F1**
```GET /entreprise``` : Afficher la liste des entreprises
## **F3**
```POST /entreprise/new?nomEntreprise=X``` : Ajouter une entreprise
## **F7**
```DELETE /entreprise/delete?idEntreprise=X``` : Supprimer une entreprise. On passera en paramètre l’id de l’entreprise à
supprimer et on vérifiera que l’entreprise à supprimer n’est pas liée à un
Étudiant. Si c’est le cas on interdira la suppression.

# Professors
## **F2**
```GET /prof/?nbSoutenances=5``` : Afficher la liste des professeurs devant assister à plus de 5 soutenances
## **F4**
```POST /prof/new``` : Ajouter un professeur

# Jury
## **F5**
```POST /jury/new?idSalle=X&nomJury=Y``` : Ajouter un Jury en vérifiant que idSalle existe bien dans la base de données

# Soutenances
## **F6**
```POST /soutenance/new?noEtudiant=X&idJury=X&datesout=X&note=X``` : Ajouter une Soutenance, en vérifiant que noEtudiant et idJury existent
bien dans la base et que note est compris entre 0 et 20.

