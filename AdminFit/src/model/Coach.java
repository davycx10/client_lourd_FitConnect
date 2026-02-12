package model;

/**
 * Représente un coach (table coach)
 */
public class Coach {

    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String adresse;
    private int basicFit;          // 0 ou 1
    private String specialite;     // prise_masse, seche, remise_forme
    private int experience;
    private String password;

    // Constructeur complet
    public Coach(int id, String nom, String prenom, String email,
                 String adresse, int basicFit, String specialite,
                 int experience, String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.basicFit = basicFit;
        this.specialite = specialite;
        this.experience = experience;
        this.password = password;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getBasicFit() {
        return basicFit;
    }

    public void setBasicFit(int basicFit) {
        this.basicFit = basicFit;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
