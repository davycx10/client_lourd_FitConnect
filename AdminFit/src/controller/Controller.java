package controller;

import model.*;
import view.*;

public class Controller {

    // ---------------------------------------------------------
    // Références vers les vues
    // ---------------------------------------------------------
    private static VueAccueil uneVueAccueil;
    private static VueInscriptionAdmin uneVueInscriptionAdmin;
    private static VueConnexion uneVueConnexion;
    private static VueDashboard uneVueDashboard;

    // ---------------------------------------------------------
    // Point d'entrée de l'application
    // ---------------------------------------------------------
    public static void main(String[] args) {

        // Toujours afficher la vue d'accueil au lancement
        uneVueAccueil = new VueAccueil();
    }

    // ---------------------------------------------------------
    // Navigation vers la page d'inscription admin
    // ---------------------------------------------------------
    public static void ouvrirInscriptionAdmin() {
        uneVueAccueil.dispose();
        uneVueInscriptionAdmin = new VueInscriptionAdmin();
    }

    // ---------------------------------------------------------
    // Navigation vers la page de connexion
    // ---------------------------------------------------------
    public static void ouvrirConnexion() {
        uneVueAccueil.dispose();
        uneVueConnexion = new VueConnexion();
    }

    // ---------------------------------------------------------
    // Création d'un admin
    // ---------------------------------------------------------
    public static void creerAdmin(String nom, String prenom, String email, String mdp) {
        boolean ok = Model.creerAdmin(nom, prenom, email, mdp);

        if (ok) {
            uneVueInscriptionAdmin.afficherMessage("Compte admin créé. Vous pouvez maintenant vous connecter.");
            uneVueInscriptionAdmin.dispose();
            uneVueConnexion = new VueConnexion();
        } else {
            uneVueInscriptionAdmin.afficherMessage("Erreur lors de la création du compte admin.");
        }
    }

    // ---------------------------------------------------------
    // Connexion d'un admin
    // ---------------------------------------------------------
    public static void connexionAdmin(String email, String mdp) {
        Admin unAdmin = Model.connexionAdmin(email, mdp);

        if (unAdmin == null) {
            uneVueConnexion.afficherMessage("Identifiants incorrects.");
        } else {
            uneVueConnexion.dispose();
            uneVueDashboard = new VueDashboard(unAdmin);
        }
    }

    // ---------------------------------------------------------
    // Chargement des candidatures dans la vue dashboard
    // ---------------------------------------------------------
    public static void chargerCandidatures() {
        uneVueDashboard.remplirTableCandidatures(Model.getAllCandidatures());
    }

    // ---------------------------------------------------------
    // Validation d'une candidature
    // ---------------------------------------------------------
    public static void validerCandidature(Candidature c) {
        boolean ok = Model.validerCandidature(c);

        if (ok) {
            Model.updateStatutCandidature(c.getId(), "valide");
            uneVueDashboard.afficherMessage("Candidature validée.");
            chargerCandidatures();
        } else {
            uneVueDashboard.afficherMessage("Erreur lors de la validation.");
        }
    }

    // ---------------------------------------------------------
    // Refus d'une candidature
    // ---------------------------------------------------------
    public static void refuserCandidature(int idCandidature) {
        Model.updateStatutCandidature(idCandidature, "refuse");
        uneVueDashboard.afficherMessage("Candidature refusée.");
        chargerCandidatures();
    }

    // ---------------------------------------------------------
    // Chargement des coachs
    // ---------------------------------------------------------
    public static void chargerCoachs() {
        uneVueDashboard.remplirTableCoachs(Model.getAllCoachs());
    }

    // ---------------------------------------------------------
    // Suppression d'un coach
    // ---------------------------------------------------------
    public static void supprimerCoach(int idCoach) {
        boolean ok = Model.supprimerCoach(idCoach);

        if (ok) {
            uneVueDashboard.afficherMessage("Coach supprimé.");
            chargerCoachs();
        } else {
            uneVueDashboard.afficherMessage("Erreur lors de la suppression.");
        }
    }

    // ---------------------------------------------------------
    // Chargement des clients
    // ---------------------------------------------------------
    public static void chargerClients() {
        uneVueDashboard.remplirTableClients(Model.getAllClients());
    }
}
