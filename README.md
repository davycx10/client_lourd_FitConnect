
# FitConnect Admin – Documentation (v0.01)

## 1. Présentation du projet

FitConnect Admin est une application Java Swing permettant à un administrateur de gérer :

- les candidatures des coachs,
- les coachs validés,
- les clients,
- la création du premier compte administrateur,
- la connexion et la navigation dans un tableau de bord.

Cette version 0.01 constitue la base fonctionnelle (théorique) du back-office administrateur.

L’application suit une architecture **MVC** (Model – View – Controller) claire et modulaire.

---

## 2. Architecture générale

Le projet est organisé en trois packages :

```
src/
 ├── controller/
 │     └── Controller.java
 ├── model/
 │     ├── Model.java
 │     ├── BDD.java
 │     ├── Admin.java
 │     ├── Coach.java
 │     ├── Client.java
 │     └── Candidature.java
 └── view/
       ├── VueAccueil.java
       ├── VueInscriptionAdmin.java
       ├── VueConnexion.java
       └── VueDashboard.java
```

---

## 3. Description des fichiers

### 3.1. Package model

#### BDD.java  
Classe responsable de la connexion MySQL.  
Elle gère :
- le chargement du driver JDBC,
- l’ouverture de la connexion,
- la fermeture de la connexion.

Elle ne contient aucune logique métier.

#### Model.java  
C’est le cœur du backend.  
Il contient toutes les requêtes SQL nécessaires :

- creerAdmin  
- connexionAdmin  
- getAllCandidatures  
- validerCandidature  
- updateStatutCandidature  
- getAllCoachs  
- supprimerCoach  
- getAllClients  

Il renvoie des objets Java (Admin, Coach, Client, Candidature).

#### Admin.java / Coach.java / Client.java / Candidature.java  
Ce sont des classes de données (POJO).  
Elles représentent une ligne de table de la base de données.  
Elles contiennent uniquement :

- des attributs,
- un constructeur,
- des getters/setters.

Aucune logique métier.

---

### 3.2. Package controller

#### Controller.java  
C’est le point central de l’application.  
Il gère :

- le lancement de l’application (méthode main),
- la navigation entre les vues,
- les appels au Model,
- la mise à jour des vues.

Il sert d’intermédiaire entre les vues et la base de données.

---

### 3.3. Package view

Toutes les vues utilisent Swing et respectent la charte graphique Basic-Fit (orange, noir, gris foncé).

#### VueAccueil.java  
Première fenêtre affichée.  
Elle propose deux choix :

- Première authentification (création du compte admin)
- Connexion

#### VueInscriptionAdmin.java  
Formulaire permettant de créer le premier compte administrateur.  
Une fois validé, l’utilisateur est redirigé vers la page de connexion.

#### VueConnexion.java  
Formulaire de connexion de l’administrateur.  
En cas de succès, l’application ouvre le tableau de bord.

#### VueDashboard.java  
Fenêtre principale après connexion.  
Elle contient :

- un header de navigation,
- un CardLayout permettant d’afficher :
  - la liste des coachs,
  - la liste des clients,
  - la liste des candidatures.

Fonctionnalités disponibles :
- suppression d’un coach,
- validation/refus d’une candidature,
- affichage dynamique des données.

---

## 4. Fonctionnement du MVC

### Vue → Controller  
Les vues ne contiennent aucune logique métier.  
Elles appellent uniquement des méthodes du Controller.

### Controller → Model  
Le Controller transmet les actions utilisateur au Model.

### Model → Base de données  
Le Model exécute les requêtes SQL via la classe BDD.

### Model → Controller → Vue  
Les résultats sont renvoyés sous forme d’objets Java, puis affichés dans les vues.

---

## 5. Point d’entrée de l’application

Le point d’entrée est la méthode `main` dans `Controller.java` :

```java
public static void main(String[] args) {
    uneVueAccueil = new VueAccueil();
}
```

C’est cette classe qu’il faut exécuter pour lancer l’application.

---

## 6. Comment lancer l’application

### Avec IntelliJ IDEA  
1. Ouvrir le projet.  
2. Ouvrir `Controller.java`.  
3. Cliquer sur le bouton Run à côté de `main`.

### Avec Eclipse  
1. Clic droit sur `Controller.java`.  
2. Run As → Java Application.

### Avec VS Code  
1. Ouvrir `Controller.java`.  
2. Cliquer sur Run au-dessus de la méthode main.

### En ligne de commande  
Depuis le dossier `src` :

```
javac controller/Controller.java
java controller.Controller
```

---

## 7. Base de données

Le projet utilise une base MySQL nommée `fitconnect`.  
Les tables nécessaires sont :

- admin  
- candidature  
- coach  
- client  
- programme  

Le fichier SQL doit être importé avant de lancer l’application.

---

## 8. Design et ergonomie

Le design suit une charte simple et moderne :

- Couleur principale : orange Basic-Fit (#FF6C00)
- Couleur secondaire : noir foncé (#1A1A1A)
- Fond des panneaux : gris foncé (#2B2B2B)
- Boutons stylisés de manière uniforme
- Layouts propres (GridLayout, BorderLayout, CardLayout)

Le tableau de bord est organisé en sections claires et facilement navigables.

---

## 9. Version actuelle

  
Statut : Base fonctionnelle (en théorie) complète pour l’administrateur.

Fonctionnalités implémentées :
- Création du premier admin
- Connexion admin
- Dashboard complet
- Gestion des coachs
- Gestion des clients
- Gestion des candidatures



---

## 10. Contribution

Pour contribuer :

1. Créer une branche dédiée.  
2. Ajouter ou modifier les fichiers nécessaires.  
3. Documenter les changements dans le README.  ou dans doc/


WARNING dans aucun cas toucher a script.sql