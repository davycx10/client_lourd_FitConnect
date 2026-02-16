package view;

import controller.Controller;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * VueConnexion :
 * Permet à l'admin de se connecter.
 */
public class VueConnexion extends JFrame implements ActionListener, KeyListener {

    private JPanel panelForm = new JPanel();
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");

    private JTextField txtEmail = new JTextField();
    private JPasswordField txtMdp = new JPasswordField();

    private JLabel lblMessage = new JLabel("", SwingConstants.CENTER);

    private final Color ORANGE = new Color(0xFF6C00);
    private final Color DARK = new Color(0x1A1A1A);

    public VueConnexion() {

        this.setTitle("FitConnect for Admin");
        this.setBounds(300, 50, 1100, 650);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(DARK);

        ImageIcon uneImage = null;
        try {
            uneImage = new ImageIcon(getClass().getResource("/images/image.png"));
        } catch (Exception e) {
            System.out.println("Image introuvable : /src/images/");
        }

        JLabel imageLogo = new JLabel(uneImage);
        imageLogo.setBounds(40, 40, 450, 450);
        this.add(imageLogo);

        panelForm.setBounds(600, 150, 400, 300);
        panelForm.setBackground(DARK);
        panelForm.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel lblEmail = new JLabel("Email :");
        JLabel lblMdp = new JLabel("Mot de passe :");

        lblEmail.setForeground(Color.WHITE);
        lblMdp.setForeground(Color.WHITE);

        panelForm.add(lblEmail);
        panelForm.add(txtEmail);

        panelForm.add(lblMdp);
        panelForm.add(txtMdp);

        styliserBouton(btAnnuler);
        styliserBouton(btValider);

        panelForm.add(btAnnuler);
        panelForm.add(btValider);

        this.add(panelForm);

        lblMessage.setBounds(550, 480, 500, 30);
        lblMessage.setForeground(Color.WHITE);
        this.add(lblMessage);

        btAnnuler.addActionListener(this);
        btValider.addActionListener(this);
        txtEmail.addKeyListener(this);
        txtMdp.addKeyListener(this);

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
            txtEmail.setText("");
            txtMdp.setText("");
            lblMessage.setText("");
        }

        if (e.getSource() == btValider) {
            String email = txtEmail.getText();
            String mdp = new String(txtMdp.getPassword());

            Controller.connexionAdmin(email, mdp);
        }
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            btValider.doClick();
        }
    }

    
}
