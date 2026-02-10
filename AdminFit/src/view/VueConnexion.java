package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VueConnexion extends JFrame implements ActionListener, KeyListener {

    // Déclaration des composants graphiques
    private JPanel panelForm = new JPanel();
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");

    private JTextField txtEmail = new JTextField();
    private JPasswordField txtMdp = new JPasswordField();

    // Couleurs officielles Basic-Fit
    private final Color ORANGE = new Color(0xFF6C00);
    private final Color DARK = new Color(0x1A1A1A);

    public VueConnexion() {

        // Configuration de la fenêtre
        this.setTitle("FitConnect for Admin");
        this.setBounds(300, 50, 1100, 650);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        // Couleur de fond Basic-Fit
        this.getContentPane().setBackground(DARK);

        // -----------------------------
        // Chargement du logo Basic-Fit
        // -----------------------------
        // IMPORTANT : l'image doit être dans /src/images/
        // et chargée via getResource pour éviter les erreurs
        ImageIcon uneImage = null;
        try {
            uneImage = new ImageIcon(getClass().getResource("/images/image.png"));
        } catch (Exception e) {
            System.out.println("⚠ Image introuvable : vérifiez le chemin /src/images/");
        }

        JLabel imageLogo = new JLabel(uneImage);
        imageLogo.setBounds(40, 40, 450, 450); // position + taille
        this.add(imageLogo);

        // -----------------------------
        // Formulaire de connexion
        // -----------------------------
        this.panelForm.setBounds(600, 150, 400, 300);
        this.panelForm.setBackground(DARK);
        this.panelForm.setLayout(new GridLayout(3, 2, 10, 10)); // grille simple

        // Labels blancs pour contraster avec fond noir
        JLabel lblEmail = new JLabel("Email :");
        lblEmail.setForeground(Color.WHITE);

        JLabel lblMdp = new JLabel("Mot de passe :");
        lblMdp.setForeground(Color.WHITE);

        // Ajout des composants dans le panel
        this.panelForm.add(lblEmail);
        this.panelForm.add(this.txtEmail);

        this.panelForm.add(lblMdp);
        this.panelForm.add(this.txtMdp);

        // Boutons
        styliserBouton(btAnnuler);
        styliserBouton(btValider);

        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);

        this.add(this.panelForm);

        // -----------------------------
        // Ajout des listeners
        // -----------------------------
        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);
        this.txtEmail.addKeyListener(this);
        this.txtMdp.addKeyListener(this);

        this.setVisible(true);
    }

    // Méthode pour appliquer le style Basic-Fit aux boutons
    private void styliserBouton(JButton b) {
        b.setBackground(ORANGE);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    // -----------------------------
    // Gestion des clics sur boutons
    // -----------------------------
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.btAnnuler) {
            // Réinitialise les champs
            this.txtEmail.setText("");
            this.txtMdp.setText("");
        }

        if (e.getSource() == this.btValider) {
            // Ici tu mettras la connexion à la base
            System.out.println("Tentative de connexion...");
        }
    }

    // -----------------------------
    // Gestion des touches clavier
    // -----------------------------
    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        // Exemple : valider avec la touche Entrée
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            btValider.doClick();
        }
    }











    public static void main(String[] args) {
        new VueConnexion();
    }











}
