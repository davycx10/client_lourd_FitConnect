package model;

/**
 * Représente un client (table client)
 */
public class Client {

    private int idClient;
    private String nom;
    private String prenom;
    private String mail;
    private String motDePasse;
    private int poids;
    private int taille;
    private String genre;
    private int basicFit;          // 0 ou 1
    private String objectif;
    private String dispoJours;
    private String dispoCreneaux;
    private String motivation;
    private int idCoach;           // peut être 0 si NULL

    // Constructeur complet
    public Client(int idClient, String nom, String prenom, String mail,
                  String motDePasse, int poids, int taille, String genre,
                  int basicFit, String objectif, String dispoJours,
                  String dispoCreneaux, String motivation, int idCoach) {
        this.idClient = idClient;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.poids = poids;
        this.taille = taille;
        this.genre = genre;
        this.basicFit = basicFit;
        this.objectif = objectif;
        this.dispoJours = dispoJours;
        this.dispoCreneaux = dispoCreneaux;
        this.motivation = motivation;
        this.idCoach = idCoach;
    }

    // Getters et setters
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getBasicFit() {
        return basicFit;
    }

    public void setBasicFit(int basicFit) {
        this.basicFit = basicFit;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public String getDispoJours() {
        return dispoJours;
    }

    public void setDispoJours(String dispoJours) {
        this.dispoJours = dispoJours;
    }

    public String getDispoCreneaux() {
        return dispoCreneaux;
    }

    public void setDispoCreneaux(String dispoCreneaux) {
        this.dispoCreneaux = dispoCreneaux;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public int getIdCoach() {
        return idCoach;
    }

    public void setIdCoach(int idCoach) {
        this.idCoach = idCoach;
    }
}
