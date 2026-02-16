package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import controller.Controller;

/**
 * VueAccueil :
 * Première page affichée au lancement de l'application.
 * L'utilisateur choisit :
 *  - Première authentification (création du compte admin)
 *  - Connexion (si un admin existe déjà)
 */
public class VueAccueil extends JFrame implements ActionListener {

    // Panel principal
    private JPanel panelMain = new JPanel();

    // Boutons
    private JButton btPremiereAuth = new JButton("Première authentification");
    private JButton btConnexion = new JButton("Se connecter");

    // Couleurs Basic-Fit
    private final Color ORANGE = new Color(0xFF6C00);
    private final Color DARK = new Color(0x1A1A1A);

    public VueAccueil() {

        // Configuration de la fenêtre
        this.setTitle("FitConnect - Accueil Admin");
        this.setBounds(300, 50, 800, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.getContentPane().setBackground(DARK);

        // Panel principal centré
        panelMain.setBounds(150, 120, 500, 250);
        panelMain.setBackground(DARK);
        panelMain.setLayout(new GridLayout(3, 1, 20, 20));

        // Titre
        JLabel lblTitre = new JLabel("Bienvenue sur FitConnect Admin", SwingConstants.CENTER);
        lblTitre.setForeground(Color.WHITE);
        lblTitre.setFont(new Font("Arial", Font.BOLD, 22));

        // Style des boutons
        styliserBouton(btPremiereAuth);
        styliserBouton(btConnexion);

        // Ajout au panel
        panelMain.add(lblTitre);
        panelMain.add(btPremiereAuth);
        panelMain.add(btConnexion);

        this.add(panelMain);

        // Listeners
        btPremiereAuth.addActionListener(this);
        btConnexion.addActionListener(this);

        this.setVisible(true);
    }

    /**
     * Applique le style Basic-Fit aux boutons
     */
    private void styliserBouton(JButton b) {
        b.setBackground(ORANGE);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        b.setFont(new Font("Arial", Font.BOLD, 15));
    }

    /**
     * Gestion des clics
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btPremiereAuth) {
            // Lien avec le CONTROLLER : ouverture de la vue d'inscription admin
            Controller.ouvrirInscriptionAdmin();
            this.dispose();
        }

        if (e.getSource() == btConnexion) {
            // Lien avec le CONTROLLER : ouverture de la vue de connexion
            Controller.ouvrirConnexion();
            this.dispose();
        }
    }
}
