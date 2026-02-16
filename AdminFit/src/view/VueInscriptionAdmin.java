package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import controller.Controller;

/**
 * VueInscriptionAdmin :
 * Permet de créer le premier compte admin.
 */
public class VueInscriptionAdmin extends JFrame implements ActionListener {

    private JPanel panelForm = new JPanel();

    private JTextField txtNom = new JTextField();
    private JTextField txtPrenom = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JPasswordField txtMdp = new JPasswordField();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");

    private JLabel lblMessage = new JLabel("", SwingConstants.CENTER);

    private final Color ORANGE = new Color(0xFF6C00);
    private final Color DARK = new Color(0x1A1A1A);

    public VueInscriptionAdmin() {

        this.setTitle("FitConnect - Première authentification");
        this.setBounds(300, 50, 800, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.getContentPane().setBackground(DARK);

        panelForm.setBounds(150, 80, 500, 260);
        panelForm.setBackground(DARK);
        panelForm.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel lblNom = new JLabel("Nom :");
        JLabel lblPrenom = new JLabel("Prénom :");
        JLabel lblEmail = new JLabel("Email :");
        JLabel lblMdp = new JLabel("Mot de passe :");

        lblNom.setForeground(Color.WHITE);
        lblPrenom.setForeground(Color.WHITE);
        lblEmail.setForeground(Color.WHITE);
        lblMdp.setForeground(Color.WHITE);

        panelForm.add(lblNom);
        panelForm.add(txtNom);

        panelForm.add(lblPrenom);
        panelForm.add(txtPrenom);

        panelForm.add(lblEmail);
        panelForm.add(txtEmail);

        panelForm.add(lblMdp);
        panelForm.add(txtMdp);

        styliserBouton(btAnnuler);
        styliserBouton(btValider);

        panelForm.add(btAnnuler);
        panelForm.add(btValider);

        this.add(panelForm);

        lblMessage.setBounds(100, 360, 600, 30);
        lblMessage.setForeground(Color.WHITE);
        this.add(lblMessage);

        btAnnuler.addActionListener(this);
        btValider.addActionListener(this);

        this.setVisible(true);
    }

    private void styliserBouton(JButton b) {
        b.setBackground(ORANGE);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void afficherMessage(String message) {
        this.lblMessage.setText(message);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btAnnuler) {
            txtNom.setText("");
            txtPrenom.setText("");
            txtEmail.setText("");
            txtMdp.setText("");
            lblMessage.setText("");
        }

        if (e.getSource() == btValider) {
            String nom = txtNom.getText();
            String prenom = txtPrenom.getText();
            String email = txtEmail.getText();
            String mdp = new String(txtMdp.getPassword());

            Controller.creerAdmin(nom, prenom, email, mdp);
        }
    }
}
