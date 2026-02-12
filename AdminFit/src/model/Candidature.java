package model;

/**
 * Représente une candidature de coach (table candidature)
 */
public class Candidature {

    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String adresse;
    private int basicFit;
    private String specialite;
    private int experience;
    private String cvPdf;
    private String linkedin;
    private String password;
    private String statut;

    // Constructeur complet (utilisé dans Model.getAllCandidatures)
    public Candidature(int id, String nom, String prenom, String email,
                       String adresse, int basicFit, String specialite,
                       int experience, String cvPdf, String linkedin,
                       String password, String statut) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.basicFit = basicFit;
        this.specialite = specialite;
        this.experience = experience;
        this.cvPdf = cvPdf;
        this.linkedin = linkedin;
        this.password = password;
        this.statut = statut;
    }

    // Constructeur vide (utile quand on crée un objet minimal dans la vue)
    public Candidature() {}

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

    public String getCvPdf() {
        return cvPdf;
    }

    public void setCvPdf(String cvPdf) {
        this.cvPdf = cvPdf;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
