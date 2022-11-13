# Enterprises

## **F1**

```http
GET /entreprise
```
> :outbox_tray: Afficher la liste des entreprises


## **F3**

```http
POST /entreprise/new?nomEntreprise=X
```
> :outbox_tray: Ajouter une entreprise


## **F7**

```http
DELETE /entreprise/delete?idEntreprise=X
```
> :outbox_tray: Supprimer une entreprise. On passera en paramètre l’id de l’entreprise à supprimer et on vérifiera que l’entreprise à supprimer n’est pas liée à un Étudiant. Si c’est le cas on interdira la suppression.



# Professors

## **F2**

```http
GET /prof/?nbSoutenances=5
```
> :outbox_tray: Afficher la liste des professeurs devant assister à plus de 5 soutenances


## **F4**
```http
POST /prof/new
```
> :outbox_tray: Ajouter un professeur



# Jury

## **F5**

```http
POST /jury/new?idSalle=X&nomJury=Y
```
> :outbox_tray: Ajouter un Jury en vérifiant que idSalle existe bien dans la base de données



# Soutenances

## **F6**

```http
POST /soutenance/new?noEtudiant=X&idJury=X&datesout=X&note=X
```
> :outbox_tray: Ajouter une Soutenance, en vérifiant que noEtudiant et idJury existent bien dans la base et que note est compris entre 0 et 20.

