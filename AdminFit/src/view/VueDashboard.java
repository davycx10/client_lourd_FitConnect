package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import controller.Controller;
import model.Admin;
import model.Coach;
import model.Client;
import model.Candidature;

public class VueDashboard extends JFrame implements ActionListener {

    /* ============================================================
       COULEURS CHARTE GRAPHIQUE
       ============================================================ */
    private final Color ORANGE      = new Color(0xFF5C00);
    private final Color DARK        = new Color(0x1C1C1C);
    private final Color GRIS_FONCE  = new Color(0x2A2A2A);
    private final Color GRIS_CARD   = new Color(0x313131);
    private final Color GRIS_CLAIR  = new Color(0xDEDEDE);
    private final Color BLANC       = new Color(0xFFFFFF);
    private final Color ROUGE       = new Color(0xE53935);
    private final Color VERT        = new Color(0x43A047);
    private final Color ORANGE_HOVER= new Color(0xFF7A2E);

    /* ============================================================
       VARIABLES
       ============================================================ */
    private Admin admin;

    private JPanel panelSidebar;
    private JPanel panelContent;
    private CardLayout cardLayout = new CardLayout();

    // Panels sections
    private JPanel panelCoachs;
    private JPanel panelClients;
    private JPanel panelCandidatures;

    // Tables
    private JTable tableCoachs;
    private DefaultTableModel modelCoachs;
    private JTable tableClients;
    private DefaultTableModel modelClients;
    private JTable tableCandidatures;
    private DefaultTableModel modelCandidatures;

    // Boutons sidebar
    private JButton btNavCoachs       = createSidebarButton("👤  Coachs");
    private JButton btNavClients      = createSidebarButton("🏋  Clients");
    private JButton btNavCandidatures = createSidebarButton("📋  Candidatures");
    private JButton btDeconnexion     = createSidebarButton("🚪  Déconnexion");

    // Boutons actions
    private JButton btSupprimerCoach      = createActionButton("Supprimer", ROUGE);
    private JButton btSupprimerClient     = createActionButton("Supprimer", ROUGE);
    private JButton btValiderCandidature  = createActionButton("✔  Valider", VERT);
    private JButton btRefuserCandidature  = createActionButton("✘  Refuser", ROUGE);

    // Label section active
    private JButton activeButton = null;

    /* ============================================================
       CONSTRUCTEUR
       ============================================================ */
    public VueDashboard(Admin admin) {
        this.admin = admin;

        this.setTitle("FitConnect — Dashboard Admin");
        this.setSize(1280, 780);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(DARK);

        buildSidebar();
        buildContent();

        this.add(panelSidebar, BorderLayout.WEST);
        this.add(panelContent, BorderLayout.CENTER);

        this.setVisible(true);
    }

    /* ============================================================
       SIDEBAR
       ============================================================ */
    private void buildSidebar() {
        panelSidebar = new JPanel();
        panelSidebar.setPreferredSize(new Dimension(240, 0));
        panelSidebar.setBackground(GRIS_FONCE);
        panelSidebar.setLayout(new BorderLayout());

        // Logo / Titre
        JPanel panelLogo = new JPanel();
        panelLogo.setBackground(GRIS_FONCE);
        panelLogo.setLayout(new BoxLayout(panelLogo, BoxLayout.Y_AXIS));
        panelLogo.setBorder(new EmptyBorder(30, 20, 20, 20));

        JLabel lblLogo = new JLabel("FitConnect");
        lblLogo.setFont(new Font("Arial", Font.BOLD, 26));
        lblLogo.setForeground(ORANGE);
        lblLogo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblRole = new JLabel("Administration");
        lblRole.setFont(new Font("Arial", Font.PLAIN, 12));
        lblRole.setForeground(new Color(0x888888));
        lblRole.setAlignmentX(Component.LEFT_ALIGNMENT);

        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(0x444444));
        sep.setBackground(new Color(0x444444));
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));

        panelLogo.add(lblLogo);
        panelLogo.add(Box.createVerticalStrut(4));
        panelLogo.add(lblRole);
        panelLogo.add(Box.createVerticalStrut(20));
        panelLogo.add(sep);

        // Info admin
        JPanel panelAdmin = new JPanel();
        panelAdmin.setBackground(GRIS_CARD);
        panelAdmin.setLayout(new BoxLayout(panelAdmin, BoxLayout.Y_AXIS));
        panelAdmin.setBorder(new EmptyBorder(12, 16, 12, 16));
        panelAdmin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));

        JLabel lblAdminNom = new JLabel("Bonjour, " + admin.getPrenom() + " " + admin.getNom());
        lblAdminNom.setFont(new Font("Arial", Font.BOLD, 13));
        lblAdminNom.setForeground(BLANC);
        lblAdminNom.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblAdminEmail = new JLabel(admin.getEmail());
        lblAdminEmail.setFont(new Font("Arial", Font.PLAIN, 11));
        lblAdminEmail.setForeground(new Color(0x888888));
        lblAdminEmail.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelAdmin.add(lblAdminNom);
        panelAdmin.add(Box.createVerticalStrut(4));
        panelAdmin.add(lblAdminEmail);

        // Nav buttons
        JPanel panelNav = new JPanel();
        panelNav.setBackground(GRIS_FONCE);
        panelNav.setLayout(new BoxLayout(panelNav, BoxLayout.Y_AXIS));
        panelNav.setBorder(new EmptyBorder(20, 12, 20, 12));

        JLabel lblMenu = new JLabel("MENU");
        lblMenu.setFont(new Font("Arial", Font.BOLD, 10));
        lblMenu.setForeground(new Color(0x666666));
        lblMenu.setBorder(new EmptyBorder(0, 8, 10, 0));
        lblMenu.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelNav.add(lblMenu);
        panelNav.add(btNavCoachs);
        panelNav.add(Box.createVerticalStrut(6));
        panelNav.add(btNavClients);
        panelNav.add(Box.createVerticalStrut(6));
        panelNav.add(btNavCandidatures);

        // Déconnexion en bas
        JPanel panelBottom = new JPanel();
        panelBottom.setBackground(GRIS_FONCE);
        panelBottom.setLayout(new BoxLayout(panelBottom, BoxLayout.Y_AXIS));
        panelBottom.setBorder(new EmptyBorder(0, 12, 24, 12));

        JSeparator sep2 = new JSeparator();
        sep2.setForeground(new Color(0x444444));
        sep2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));

        panelBottom.add(sep2);
        panelBottom.add(Box.createVerticalStrut(12));
        panelBottom.add(btDeconnexion);

        // Assemblage sidebar
        JPanel panelTop = new JPanel();
        panelTop.setBackground(GRIS_FONCE);
        panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.Y_AXIS));
        panelTop.add(panelLogo);
        panelTop.add(panelAdmin);
        panelTop.add(panelNav);

        panelSidebar.add(panelTop, BorderLayout.NORTH);
        panelSidebar.add(panelBottom, BorderLayout.SOUTH);

        // Listeners
        btNavCoachs.addActionListener(this);
        btNavClients.addActionListener(this);
        btNavCandidatures.addActionListener(this);
        btDeconnexion.addActionListener(this);

        // Activer coachs par défaut
        setActiveButton(btNavCoachs);
    }

    /* ============================================================
       CONTENU PRINCIPAL
       ============================================================ */
    private void buildContent() {
        panelContent = new JPanel(cardLayout);
        panelContent.setBackground(DARK);

        panelCoachs      = buildPanelCoachs();
        panelClients     = buildPanelClients();
        panelCandidatures = buildPanelCandidatures();

        panelContent.add(panelCoachs,      "COACHS");
        panelContent.add(panelClients,     "CLIENTS");
        panelContent.add(panelCandidatures,"CANDIDATURES");
    }

    /* ============================================================
       PANEL COACHS
       ============================================================ */
    private JPanel buildPanelCoachs() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(DARK);
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));

        // Header section
        JPanel header = buildSectionHeader("Gestion des Coachs", "Liste de tous les coachs enregistrés");
        panel.add(header, BorderLayout.NORTH);

        // Table
        String[] cols = {"ID", "Nom", "Prénom", "Email", "Spécialité", "Expérience"};
        modelCoachs = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tableCoachs = buildStyledTable(modelCoachs);
        JScrollPane sp = buildStyledScrollPane(tableCoachs);
        panel.add(sp, BorderLayout.CENTER);

        // Actions
        JPanel actions = buildActionBar(btSupprimerCoach);
        panel.add(actions, BorderLayout.SOUTH);

        btSupprimerCoach.addActionListener(this);
        return panel;
    }

    /* ============================================================
       PANEL CLIENTS
       ============================================================ */
    private JPanel buildPanelClients() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(DARK);
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));

        JPanel header = buildSectionHeader("Gestion des Clients", "Liste de tous les clients inscrits");
        panel.add(header, BorderLayout.NORTH);

        String[] cols = {"ID", "Nom", "Prénom", "Email", "Objectif", "Coach assigné"};
        modelClients = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tableClients = buildStyledTable(modelClients);
        JScrollPane sp = buildStyledScrollPane(tableClients);
        panel.add(sp, BorderLayout.CENTER);

        JPanel actions = buildActionBar(btSupprimerClient);
        panel.add(actions, BorderLayout.SOUTH);

        btSupprimerClient.addActionListener(this);
        return panel;
    }

    /* ============================================================
       PANEL CANDIDATURES
       ============================================================ */
    private JPanel buildPanelCandidatures() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(DARK);
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));

        JPanel header = buildSectionHeader("Candidatures Coachs", "Validez ou refusez les candidatures en attente");
        panel.add(header, BorderLayout.NORTH);

        String[] cols = {"ID", "Nom", "Prénom", "Email", "Spécialité", "Expérience", "Statut"};
        modelCandidatures = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tableCandidatures = buildStyledTable(modelCandidatures);
        // Coloriser les statuts
        tableCandidatures.getColumnModel().getColumn(6).setCellRenderer(new StatutRenderer());
        JScrollPane sp = buildStyledScrollPane(tableCandidatures);
        panel.add(sp, BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        actions.setBackground(DARK);
        actions.setBorder(new EmptyBorder(16, 0, 0, 0));
        actions.add(btRefuserCandidature);
        actions.add(btValiderCandidature);
        panel.add(actions, BorderLayout.SOUTH);

        btValiderCandidature.addActionListener(this);
        btRefuserCandidature.addActionListener(this);
        return panel;
    }

    /* ============================================================
       HELPERS UI
       ============================================================ */
    private JPanel buildSectionHeader(String titre, String sousTitre) {
        JPanel p = new JPanel();
        p.setBackground(DARK);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(new EmptyBorder(0, 0, 20, 0));

        JLabel lbl = new JLabel(titre);
        lbl.setFont(new Font("Arial", Font.BOLD, 22));
        lbl.setForeground(BLANC);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel sub = new JLabel(sousTitre);
        sub.setFont(new Font("Arial", Font.PLAIN, 13));
        sub.setForeground(new Color(0x888888));
        sub.setAlignmentX(Component.LEFT_ALIGNMENT);

        p.add(lbl);
        p.add(Box.createVerticalStrut(4));
        p.add(sub);
        return p;
    }

    private JPanel buildActionBar(JButton btn) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        p.setBackground(DARK);
        p.setBorder(new EmptyBorder(16, 0, 0, 0));
        p.add(btn);
        return p;
    }

    private JTable buildStyledTable(DefaultTableModel model) {
        JTable table = new JTable(model);
        table.setBackground(GRIS_CARD);
        table.setForeground(GRIS_CLAIR);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.setRowHeight(40);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setSelectionBackground(new Color(0xFF5C00, false).darker());
        table.setSelectionForeground(BLANC);
        table.setSelectionBackground(new Color(0x4A2A1A));
        table.setFillsViewportHeight(true);

        // Header
        JTableHeader header = table.getTableHeader();
        header.setBackground(GRIS_FONCE);
        header.setForeground(new Color(0x888888));
        header.setFont(new Font("Arial", Font.BOLD, 12));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, ORANGE));
        header.setReorderingAllowed(false);

        // Centrer colonnes
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.LEFT);
        centerRenderer.setBackground(GRIS_CARD);
        centerRenderer.setForeground(GRIS_CLAIR);

        // Alternance lignes
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object val,
                                                           boolean sel, boolean foc, int row, int col) {
                super.getTableCellRendererComponent(t, val, sel, foc, row, col);
                setBorder(new EmptyBorder(0, 12, 0, 12));
                if (sel) {
                    setBackground(new Color(0x3A2010));
                    setForeground(BLANC);
                } else {
                    setBackground(row % 2 == 0 ? GRIS_CARD : new Color(0x2D2D2D));
                    setForeground(GRIS_CLAIR);
                }
                return this;
            }
        });

        return table;
    }

    private JScrollPane buildStyledScrollPane(JTable table) {
        JScrollPane sp = new JScrollPane(table);
        sp.setBackground(GRIS_CARD);
        sp.getViewport().setBackground(GRIS_CARD);
        sp.setBorder(BorderFactory.createLineBorder(new Color(0x3A3A3A)));
        return sp;
    }

    private JButton createSidebarButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.PLAIN, 14));
        btn.setForeground(new Color(0xAAAAAA));
        btn.setBackground(GRIS_FONCE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(true);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(new EmptyBorder(10, 12, 10, 12));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (btn != activeButton) {
                    btn.setBackground(new Color(0x3A3A3A));
                    btn.setForeground(BLANC);
                }
            }
            public void mouseExited(MouseEvent e) {
                if (btn != activeButton) {
                    btn.setBackground(GRIS_FONCE);
                    btn.setForeground(new Color(0xAAAAAA));
                }
            }
        });
        return btn;
    }

    private JButton createActionButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setForeground(BLANC);
        btn.setBackground(color);
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(10, 24, 10, 24));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(color.darker()); }
            public void mouseExited(MouseEvent e)  { btn.setBackground(color); }
        });
        return btn;
    }

    private void setActiveButton(JButton btn) {
        if (activeButton != null) {
            activeButton.setBackground(GRIS_FONCE);
            activeButton.setForeground(new Color(0xAAAAAA));
            activeButton.setBorder(new EmptyBorder(10, 12, 10, 12));
        }
        activeButton = btn;
        btn.setBackground(new Color(0x3A1A00));
        btn.setForeground(ORANGE);
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 3, 0, 0, ORANGE),
                new EmptyBorder(10, 9, 10, 12)
        ));
    }

    /* ============================================================
       RENDERER STATUT
       ============================================================ */
    class StatutRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable t, Object val,
                                                       boolean sel, boolean foc, int row, int col) {
            super.getTableCellRendererComponent(t, val, sel, foc, row, col);
            setBorder(new EmptyBorder(0, 12, 0, 12));
            String statut = val != null ? val.toString() : "";
            if (statut.equals("valide")) {
                setForeground(VERT);
                setText("✔ Validé");
            } else if (statut.equals("refuse")) {
                setForeground(ROUGE);
                setText("✘ Refusé");
            } else {
                setForeground(new Color(0xFFAA00));
                setText("⏳ En attente");
            }
            setBackground(sel ? new Color(0x3A2010) : (row % 2 == 0 ? GRIS_CARD : new Color(0x2D2D2D)));
            return this;
        }
    }

    /* ============================================================
       REMPLISSAGE TABLES
       ============================================================ */
    public void remplirTableCoachs(ArrayList<Coach> lesCoachs) {
        modelCoachs.setRowCount(0);
        for (Coach c : lesCoachs) {
            modelCoachs.addRow(new Object[]{
                    c.getId(), c.getNom(), c.getPrenom(), c.getEmail(),
                    c.getSpecialite(), c.getExperience() + " ans"
            });
        }
    }

    public void remplirTableClients(ArrayList<Client> lesClients) {
        modelClients.setRowCount(0);
        for (Client c : lesClients) {
            modelClients.addRow(new Object[]{
                    c.getIdClient(), c.getNom(), c.getPrenom(), c.getMail(),
                    c.getObjectif(), c.getIdCoach() == 0 ? "Non assigné" : "Coach #" + c.getIdCoach()
            });
        }
    }

    public void remplirTableCandidatures(ArrayList<Candidature> lesCandidatures) {
        modelCandidatures.setRowCount(0);
        for (Candidature c : lesCandidatures) {
            modelCandidatures.addRow(new Object[]{
                    c.getId(), c.getNom(), c.getPrenom(), c.getEmail(),
                    c.getSpecialite(), c.getExperience() + " ans", c.getStatut()
            });
        }
    }

    public void afficherMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "FitConnect", JOptionPane.INFORMATION_MESSAGE);
    }

    /* ============================================================
       ACTION LISTENER
       ============================================================ */
    @Override
    public void actionPerformed(ActionEvent e) {

        // Navigation
        if (e.getSource() == btNavCoachs) {
            cardLayout.show(panelContent, "COACHS");
            setActiveButton(btNavCoachs);
            Controller.chargerCoachs();
        }

        if (e.getSource() == btNavClients) {
            cardLayout.show(panelContent, "CLIENTS");
            setActiveButton(btNavClients);
            Controller.chargerClients();
        }

        if (e.getSource() == btNavCandidatures) {
            cardLayout.show(panelContent, "CANDIDATURES");
            setActiveButton(btNavCandidatures);
            Controller.chargerCandidatures();
        }

        if (e.getSource() == btDeconnexion) {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Voulez-vous vraiment vous déconnecter ?",
                    "Déconnexion", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                this.dispose();
                new VueAccueil();
            }
        }

        // Supprimer coach
        if (e.getSource() == btSupprimerCoach) {
            int ligne = tableCoachs.getSelectedRow();
            if (ligne == -1) { afficherMessage("Veuillez sélectionner un coach."); return; }
            int id = (int) modelCoachs.getValueAt(ligne, 0);
            String nom = modelCoachs.getValueAt(ligne, 1) + " " + modelCoachs.getValueAt(ligne, 2);
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Supprimer le coach " + nom + " ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) Controller.supprimerCoach(id);
        }

        // Supprimer client
        if (e.getSource() == btSupprimerClient) {
            int ligne = tableClients.getSelectedRow();
            if (ligne == -1) { afficherMessage("Veuillez sélectionner un client."); return; }
            int id = (int) modelClients.getValueAt(ligne, 0);
            String nom = modelClients.getValueAt(ligne, 1) + " " + modelClients.getValueAt(ligne, 2);
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Supprimer le client " + nom + " ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) Controller.supprimerClient(id);
        }

        // Valider candidature
        if (e.getSource() == btValiderCandidature) {
            int ligne = tableCandidatures.getSelectedRow();
            if (ligne == -1) { afficherMessage("Veuillez sélectionner une candidature."); return; }
            int id = (int) modelCandidatures.getValueAt(ligne, 0);
            ArrayList<Candidature> liste = Controller.getAllCandidaturesForDashboard();
            for (Candidature c : liste) {
                if (c.getId() == id) { Controller.validerCandidature(c); break; }
            }
        }

        // Refuser candidature
        if (e.getSource() == btRefuserCandidature) {
            int ligne = tableCandidatures.getSelectedRow();
            if (ligne == -1) { afficherMessage("Veuillez sélectionner une candidature."); return; }
            int id = (int) modelCandidatures.getValueAt(ligne, 0);
            Controller.refuserCandidature(id);
        }
    }
}