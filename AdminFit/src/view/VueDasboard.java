package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import controller.Controller;
import model.Admin;
import model.Coach;
import model.Client;
import model.Candidature;

/**
 * VueDashboard :
 * Fenêtre principale affichée après la connexion de l'admin.
 * Contient :
 *  - Un header (navigation)
 *  - Un CardLayout pour afficher Coachs / Clients / Candidatures
 *  - Des tables dynamiques
 *  - Des boutons d'action (supprimer coach, valider/refuser candidature)
 */
public class VueDashboard extends JFrame implements ActionListener {

    /* ============================================================
       ===============   VARIABLES PRINCIPALES   ===================
       ============================================================ */

    private Admin admin;

    // Header
    private JPanel panelHeader = new JPanel();

    // Contenu principal (CardLayout)
    private JPanel panelContent = new JPanel();
    private CardLayout cardLayout = new CardLayout();

    // Panels des différentes sections
    private JPanel panelCoachs = new JPanel();
    private JPanel panelClients = new JPanel();
    private JPanel panelCandidatures = new JPanel();

    // Boutons du header
    private JButton btCoachs = new JButton("Coachs");
    private JButton btClients = new JButton("Clients");
    private JButton btCandidatures = new JButton("Candidatures");
    private JButton btDeconnexion = new JButton("Déconnexion");

    // Tables + modèles
    private JTable tableCoachs;
    private DefaultTableModel modelCoachs;

    private JTable tableClients;
    private DefaultTableModel modelClients;

    private JTable tableCandidatures;
    private DefaultTableModel modelCandidatures;

    // Boutons d'action
    private JButton btSupprimerCoach = new JButton("Supprimer le coach sélectionné");
    private JButton btValiderCandidature = new JButton("Valider");
    private JButton btRefuserCandidature = new JButton("Refuser");

    // Couleurs Basic-Fit
    private final Color ORANGE = new Color(0xFF6C00);
    private final Color DARK = new Color(0x1A1A1A);
    private final Color GRIS_FOND = new Color(0x2B2B2B);


    /* ============================================================
       =====================   CONSTRUCTEUR   ======================
       ============================================================ */

    public VueDashboard(Admin admin) {
        this.admin = admin;

        this.setTitle("FitConnect - Dashboard Admin");
        this.setBounds(200, 50, 1200, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.getContentPane().setBackground(DARK);

        /* ------------------------------------------------------------
           ---------------------- HEADER ------------------------------
           ------------------------------------------------------------ */

        panelHeader.setBounds(0, 0, 1200, 70);
        panelHeader.setBackground(DARK);
        panelHeader.setLayout(new BorderLayout());

        JLabel lblTitre = new JLabel("FitConnect Admin - Bonjour " + admin.getPrenom(), SwingConstants.LEFT);
        lblTitre.setForeground(Color.WHITE);
        lblTitre.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitre.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        panelHeader.add(lblTitre, BorderLayout.WEST);

        JPanel panelNav = new JPanel();
        panelNav.setBackground(DARK);
        panelNav.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 15));

        styliserBoutonHeader(btCoachs);
        styliserBoutonHeader(btClients);
        styliserBoutonHeader(btCandidatures);
        styliserBoutonHeader(btDeconnexion);

        panelNav.add(btCoachs);
        panelNav.add(btClients);
        panelNav.add(btCandidatures);
        panelNav.add(btDeconnexion);

        panelHeader.add(panelNav, BorderLayout.EAST);
        this.add(panelHeader);


        /* ------------------------------------------------------------
           ---------------------- CONTENU -----------------------------
           ------------------------------------------------------------ */

        panelContent.setBounds(0, 70, 1200, 630);
        panelContent.setLayout(cardLayout);
        panelContent.setBackground(GRIS_FOND);

        initPanelCoachs();
        initPanelClients();
        initPanelCandidatures();

        panelContent.add(panelCoachs, "COACHS");
        panelContent.add(panelClients, "CLIENTS");
        panelContent.add(panelCandidatures, "CANDIDATURES");

        this.add(panelContent);


        /* ------------------------------------------------------------
           ---------------------- LISTENERS ----------------------------
           ------------------------------------------------------------ */

        btCoachs.addActionListener(this);
        btClients.addActionListener(this);
        btCandidatures.addActionListener(this);
        btDeconnexion.addActionListener(this);

        btSupprimerCoach.addActionListener(this);
        btValiderCandidature.addActionListener(this);
        btRefuserCandidature.addActionListener(this);

        // Chargement initial
        Controller.chargerCoachs();

        this.setVisible(true);
    }


    /* ============================================================
       =====================   STYLE BOUTONS   =====================
       ============================================================ */

    private void styliserBoutonHeader(JButton b) {
        b.setBackground(ORANGE);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        b.setFont(new Font("Arial", Font.BOLD, 13));
    }


    /* ============================================================
       =====================   PANEL COACHS   ======================
       ============================================================ */

    private void initPanelCoachs() {
        panelCoachs.setLayout(new BorderLayout());
        panelCoachs.setBackground(GRIS_FOND);

        JLabel lbl = new JLabel("Liste des coachs", SwingConstants.CENTER);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Arial", Font.BOLD, 18));
        lbl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelCoachs.add(lbl, BorderLayout.NORTH);

        String[] entetes = {"ID", "Nom", "Prénom", "Email"};
        modelCoachs = new DefaultTableModel(entetes, 0);
        tableCoachs = new JTable(modelCoachs);
        JScrollPane sp = new JScrollPane(tableCoachs);
        panelCoachs.add(sp, BorderLayout.CENTER);

        JPanel panelBas = new JPanel();
        panelBas.setBackground(GRIS_FOND);
        styliserBoutonHeader(btSupprimerCoach);
        panelBas.add(btSupprimerCoach);
        panelCoachs.add(panelBas, BorderLayout.SOUTH);
    }


    /* ============================================================
       =====================   PANEL CLIENTS   =====================
       ============================================================ */

    private void initPanelClients() {
        panelClients.setLayout(new BorderLayout());
        panelClients.setBackground(GRIS_FOND);

        JLabel lbl = new JLabel("Liste des clients", SwingConstants.CENTER);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Arial", Font.BOLD, 18));
        lbl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelClients.add(lbl, BorderLayout.NORTH);

        String[] entetes = {"ID", "Nom", "Prénom", "Email", "Objectif", "Coach"};
        modelClients = new DefaultTableModel(entetes, 0);
        tableClients = new JTable(modelClients);
        JScrollPane sp = new JScrollPane(tableClients);
        panelClients.add(sp, BorderLayout.CENTER);
    }


    /* ============================================================
       ==================   PANEL CANDIDATURES   ==================
       ============================================================ */

    private void initPanelCandidatures() {
        panelCandidatures.setLayout(new BorderLayout());
        panelCandidatures.setBackground(GRIS_FOND);

        JLabel lbl = new JLabel("Candidatures coachs", SwingConstants.CENTER);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Arial", Font.BOLD, 18));
        lbl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelCandidatures.add(lbl, BorderLayout.NORTH);

        String[] entetes = {"ID", "Nom", "Prénom", "Email", "Spécialité", "Expérience", "Statut"};
        modelCandidatures = new DefaultTableModel(entetes, 0);
        tableCandidatures = new JTable(modelCandidatures);
        JScrollPane sp = new JScrollPane(tableCandidatures);
        panelCandidatures.add(sp, BorderLayout.CENTER);

        JPanel panelBas = new JPanel();
        panelBas.setBackground(GRIS_FOND);

        styliserBoutonHeader(btValiderCandidature);
        styliserBoutonHeader(btRefuserCandidature);

        panelBas.add(btValiderCandidature);
        panelBas.add(btRefuserCandidature);

        panelCandidatures.add(panelBas, BorderLayout.SOUTH);
    }


    /* ============================================================
       =====================   REMPLISSAGE TABLES   ===============
       ============================================================ */

    public void remplirTableCoachs(ArrayList<Coach> lesCoachs) {
        modelCoachs.setRowCount(0);
        for (Coach c : lesCoachs) {
            modelCoachs.addRow(new Object[]{
                c.getId(),
                c.getNom(),
                c.getPrenom(),
                c.getEmail()
            });
        }
    }

    public void remplirTableClients(ArrayList<Client> lesClients) {
        modelClients.setRowCount(0);
        for (Client c : lesClients) {
            modelClients.addRow(new Object[]{
                c.getIdClient(),
                c.getNom(),
                c.getPrenom(),
                c.getMail(),
                c.getObjectif(),
                c.getIdCoach()
            });
        }
    }

    public void remplirTableCandidatures(ArrayList<Candidature> lesCandidatures) {
        modelCandidatures.setRowCount(0);
        for (Candidature c : lesCandidatures) {
            modelCandidatures.addRow(new Object[]{
                c.getId(),
                c.getNom(),
                c.getPrenom(),
                c.getEmail(),
                c.getSpecialite(),
                c.getExperience(),
                c.getStatut()
            });
        }
    }


    /* ============================================================
       =====================   ACTION LISTENER   ==================
       ============================================================ */

    @Override
    public void actionPerformed(ActionEvent e) {

        /* ------------------- Navigation ------------------- */

        if (e.getSource() == btCoachs) {
            cardLayout.show(panelContent, "COACHS");
            Controller.chargerCoachs();
        }

        if (e.getSource() == btClients) {
            cardLayout.show(panelContent, "CLIENTS");
            Controller.chargerClients();
        }

        if (e.getSource() == btCandidatures) {
            cardLayout.show(panelContent, "CANDIDATURES");
            Controller.chargerCandidatures();
        }

        if (e.getSource() == btDeconnexion) {
            this.dispose();
            new VueAccueil();
        }


        /* ------------------- Supprimer coach ------------------- */

        if (e.getSource() == btSupprimerCoach) {
            int ligne = tableCoachs.getSelectedRow();
            if (ligne == -1) {
                afficherMessage("Veuillez sélectionner un coach.");
                return;
            }

            int idCoach = (int) modelCoachs.getValueAt(ligne, 0);
            Controller.supprimerCoach(idCoach);
        }


        /* ------------------- Valider candidature ------------------- */

        if (e.getSource() == btValiderCandidature) {
            int ligne = tableCandidatures.getSelectedRow();
            if (ligne == -1) {
                afficherMessage("Veuillez sélectionner une candidature.");
                return;
            }

            int id = (int) modelCandidatures.getValueAt(ligne, 0);

            // On récupère l'objet complet depuis le Model
            ArrayList<Candidature> liste = Controller.getAllCandidaturesForDashboard();
            Candidature cand = null;

            for (Candidature c : liste) {
                if (c.getId() == id) {
                    cand = c;
                    break;
                }
            }

            if (cand != null) {
                Controller.validerCandidature(cand);
            }
        }


        /* ------------------- Refuser candidature ------------------- */

        if (e.getSource() == btRefuserCandidature) {
            int ligne = tableCandidatures.getSelectedRow();
            if (ligne == -1) {
                afficherMessage("Veuillez sélectionner une candidature.");
                return;
            }

            int id = (int) modelCandidatures.getValueAt(ligne, 0);
            Controller.refuserCandidature(id);
        }
    }


    /* ============================================================
       =====================   MESSAGE POPUP   ====================
       ============================================================ */

    public void afficherMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
