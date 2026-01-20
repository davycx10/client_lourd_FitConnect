package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class VueConnexion extends JFrame implements ActionListener, KeyListener {
    private JPanel panelForm = new JPanel();
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");

    private JTextField txtEmail = new JTextField();
    private JTextField txtMdp = new JPasswordField();

    public VueConnexion(){
        this.setTitle("FitConnect for Admin");
        this.setBounds(600, 20, 600, 400);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        // color
        this.getContentPane().setBackground(Color.DARK_GRAY);

        ImageIcon uneImage = new ImageIcon("src/images/image.png");
        JLabel imageLogo = new JLabel(uneImage);
        imageLogo.setBounds(10, 20, 300, 300);
        this.add(imageLogo);

        // configuration du formulaire de connexion
        this.panelForm.setBounds(340, 50, 200, 200);
        this.panelForm.setBackground(Color.cyan);
        this.panelForm.setLayout(new GridLayout(3,2));
        this.panelForm.add(new JLabel("Email ;"));
        this.panelForm.add(this.txtEmail);

        this.panelForm.add(new JLabel("Password ;"));
        this.panelForm.add(this.txtMdp);

        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);

        this.add(this.panelForm);

        // rendre les boutons écoutables
        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);
        this.txtEmail.addKeyListener(this);
        this.txtMdp.addKeyListener(this);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

/*    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == this.btAnnuler){
            this.txtEmail.setText("");
            this.txtMdp.setText("");
        }
        else if (e.getSource() == this.btValider) {

        }
    }*/




}
