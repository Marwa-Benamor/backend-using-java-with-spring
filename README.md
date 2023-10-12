## 1. Sujet

Il s'agit de mettre en place une application `Java` qui devra permettre :
1. D'enregistrer un nouveau patient dans une base de données ;
2. De récupérer les données d'un patient existant dans la base de données ;
3. De récupérer les données de tous les patients enregistrés dans la base, de manière paginée ;
4. De compter l'ensemble des patients de la base de données, par genre ;
5. De créer un nouveau patient, sur la base des ID de deux patients déjà enregistrés dans la base (ses parents).
    - **Le nouveau patient devra être persisté dans la base de données** ;
    - **Le nouveau patient devra pouvoir restituer l'ID de ses deux parents** ;
    - **Le groupe sanguin du nouveau patient devra être compatible avec le groupe sanguin de ses parents** 

L'application est codée en `Java` avec le FrameWork `Spring Boot`, permettant d'exposer des `API RESTfull`. Les `API` devront être accessibles via un `Swagger`.

Le déploiement de l'application est documenté dans ce `README.md` disponible à la racine du projet.

Le code est versionné avec `Git`.

## 2. Persistance des données

Les données devront être persistées dans la base de données disponible sur l'ordinateur personnel.



## 3. Spécification du domain

Patient : il devra au minimum contenir comme caractéristiques :
- Les **5 traits stricts d'identités** (voir [Référentiel national d'identitovigilance](https://esante.gouv.fr/sites/default/files/media_entity/documents/RNIV%201%20Principes%20communs_1.pdf) ; hors matricule INS et en ne concervant que le premier prénom de naissance).
- Les éléments de son **groupe sanguin, selon le système `ABO`**. Le groupe sanguin `ABO` est dû à un gène présent sur le chromosome 19. Ce gène est responsable de l'expression d'une protéine différente en fonction de la séquence du gène :
    - Le gène `A` code pour la protéine `A`, qui sera exprimée à la surface des globules rouges.
    - Le gène `B` code pour la protéine `B`, qui sera exprimée à la surface des globules rouges.
    - Le gène `O` code pour une protéine non fonctionelle, qui ne sera pas exprimée à la surface des globules rouges.

L'être humain possédant 2 paires de chaque chromosome, c'est la combinaison de l'expression de chacune des versions du gène (appelée allèle) au niveau du chromosome 19 qui est responsable du groupe sanguin `ABO` d'un individu. Par exemple :
- Chromosome19.Allele1 [`B`] & Chromosome19.Allele2 [`O`] => génotype [`BO`] => groupe [`B`]
- Chromosome19.Allele1 [`B`] & Chromosome19.Allele2 [`A`] => génotype [`BA`] => groupe [`AB`]
- Chromosome19.Allele1 [`A`] & Chromosome19.Allele2 [`A`] => génotype [`AA`] => groupe [`A`]
- Chromosome19.Allele1 [`O`] & chromosome19.Allele2 [`O`] => génotype [`OO`] => groupe [`O`]

Un tableau récapitulatif de la transmission du groupe sanguin entre les parents et les enfants est disponible ci-après :

![](https://expertadn.fr/wp-content/webp-express/webp-images/uploads/2014/03/groupe-3.jpg.webp)  
Image from : [https://expertadn.fr/](https://expertadn.fr/)


## 4. Spécification des API RESTfull
### 4.1. Enregistrer un patient

- Path : `api/patient`
- Method : `POST`
- Paramètre(s) d'entrée(s) : un `DTO`, qui contient les caractéristiques du patient que l'on cherche à créer, **sans son ID**. Le `DTO` devra être annoté avec un `@RequestBody`.
- Paramètre de sortie : un patient

### 4.2. Récupérer les données d'un patient

- Path : `api/patient/{id}`
- Method : `GET`
- Paramètre(s) d'entrée(s) : un `ID`. L'`ID` devra être annotée avec une `@PathVariable` ([voir example dans le cours](../3_java/3_spring_boot.md#353-exposition-des-méthodes-du-service-au-travers-dun-api-restfull)).
- Paramètre de sortie : un patient

### 4.3. Récupérer les données de tous les patients de manière paginée

- Path : `api/patient/all`
- Method : `GET`
- Paramètre(s) d'entrée(s) : une `Pageable` de `Spring`. La `Pageable` ne doit pas être annotée (sans quoi, `Spring` n'est pas capable de faire le mapping de la `Pageable` correctement). Plus d'information sur la pagination [ici](https://www.baeldung.com/spring-data-web-support).
- Paramètre de sortie : une `Page` de patients

### 4.4. Compter l'ensemble des patients par genre

- Path : `api/patient/count`
- Method : `GET`
- Paramètre(s) d'entrée(s) : aucun
- Paramètre de sortie : le résultat du dénombrement, par genre

### 4.5. Créer un nouveau patient sur la base des ID de deux patients déjà enregistrés dans la base

- Path : `api/patient/create`
- Method : `POST`
- Paramètre(s) d'entrée(s) : un `DTO` qui contient l'ID des deux parents, ainsi que les caractéristiques propres au patient que l'on cherche à créer (hors groupage `ABO` dont la logique métier est codée en `Java`)
- Paramètre de sortie : un patient (celui qui a été créé)
