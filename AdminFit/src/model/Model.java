package model;

import  java.sql.SQLException;
import  java.sql.ResultSet;
import  java.sql.Statement;
import  java.util.ArrayList;

public class Model {

    //instancier la classe bdd  et création de la connexion
    private static BDD uneBdd = new BDD("localhost", "admin", "myadmin", "Fitconnect");

    public static User selectWhereUser(String email, String mdp) {
        User unUser = null;
        String requete = "select * from user where email = '"+email+"' and mdp = '"+mdp+"';";
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement(); // cette ligne correspond à prepare de PDP
            ResultSet unRes = unStat.executeQuery(requete);  //cette ligne correspond à fetch de PDP

            if(unRes.next()) {
                unUser = new User(unRes.getInt("iduser"), unRes.getString("nom"),
                        unRes.getString("prenom"), unRes.getString("email"),
                        unRes.getString("mdp"), unRes.getString("role"));
            }
            unStat.close();

            uneBdd.seDeconnecter();
        }catch(SQLException exp) {
            System.out.println("Erreur d'execution de la requete :"+requete);
        }
        return unUser;
    }

    public static void insertPromotion(Promotion unePromotion) {
        String requete = "insert into promotion values (null, '" + unePromotion.getNom()
                + "','" + unePromotion.getSalle()+"','"
                + unePromotion.getDiplome()+"');";
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeconnecter();
        }
        catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete:" + requete);
        }
    }

    public static ArrayList<Promotion> selectAllPromotions(String filtre){
        ArrayList<Promotion> lesPromotions = new ArrayList<Promotion>();
        String requete;

        if(filtre.equals("")) {
            requete = "select * from promotion;";
        } else {
            requete = "SELECT * FROM promotion WHERE nom LIKE '%"+filtre+
                    "%' OR salle LIKE '%"+filtre+
                    "%' OR diplome LIKE '%"+filtre+"%';";
        }

        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet desResultats = unStat.executeQuery(requete);

            while (desResultats.next()) {
                Promotion unePromotion = new Promotion (desResultats.getInt("idPromotion"),
                        desResultats.getString("nom"), desResultats.getString("salle"),
                        desResultats.getString("diplome"));
                //ajout de la promotion dans l'ArrayList
                lesPromotions.add(unePromotion);
            }
            unStat.close();
            uneBdd.seDeconnecter();
        }
        catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete:" + requete);
        }

        return lesPromotions;
    }
}
