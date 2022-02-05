import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Fenetre extends JFrame{
  /**
   *
   */
  private static final long serialVersionUID = 1L;
  BoutonJeu[] boutons;
  Echiquier echiquier;
  ArrayList<Point> clics;
  JLabel labelTour;
  String tourJoueur = "Blanc";

  public Fenetre(final String nom) {
    super(nom);
    this.clics = new ArrayList<Point>();
    this.tourJoueur = "Blanc";
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.init();
    this.centreEcran(700, 700);
    this.setVisible(true);
  }

  // Methodes d'initialisation
  public void init() {
    this.setLayout(new BorderLayout());
    this.echiquier = new Echiquier();

    final JPanel panelJeu = this.getPanelJeu();
    this.add(panelJeu, BorderLayout.CENTER);
    panelJeu.requestFocus();

    final JPanel panelMenu = this.getPanelMenu();
    this.add(panelMenu, BorderLayout.SOUTH);

    this.refreshBoutons();
  }

  public void centreEcran(final int w, final int h) {
    final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    this.setBounds(dim.width / 2 - w / 2, dim.height / 2 - h / 2, w, h);
  }

  public JPanel getPanelJeu() {
    final JPanel panelJeu = new JPanel(new GridLayout(8, 8, 0, 0));

    this.boutons = new BoutonJeu[8];
    final ListenerJeu listener = new ListenerJeu();

    // Boucle pour créer les boutons
    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
        final BoutonJeu b = new BoutonJeu(x);
        this.boutons[x] = b;
        b.addActionListener(listener);
        b.setVisible(true);

        // Couleur de base (Blanc)
        Color couleur = Color.white;

        // Met le blanc 1 fois sur 2
        if ((x + y) % 2 == 0) {
          couleur = Color.white;
        } else {
          couleur = Color.black;
        }

        panelJeu.add(b);
        b.setBackground(couleur);
      }
    }

    return panelJeu;
  }

  public JPanel getPanelMenu() {
    final JPanel panelMenu = new JPanel();

    this.labelTour = new JLabel("Tour du joueur : " + tourJoueur);
    panelMenu.add(this.labelTour);

    final JButton sauver = new JButton("Sauver");
    // Ajouter listener
    panelMenu.add(sauver);

    final JButton charger = new JButton("Charger");
    // Ajouter listener
    panelMenu.add(charger);

    return panelMenu;
  }

  /*
   * Methodes
   */
  public ArrayList<Point> getClics() {
    return this.clics;
  }

  public Echiquier getEchiquier() {
    return this.echiquier;
  }

  // Recupère le tableau de pièce et l'affiche sur les boutons
  public void refreshBoutons() {
    final Piece[] plateau = echiquier.getEchiquier();
    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
        if (plateau[8 * x + y] != null) {
          this.boutons[x].setIcon(new ImageIcon("Image/pionBlanc.png"));
          // this.boutons[x][y].setIcon(new ImageIcon("Image/tourNoir.png"));
        } else {
          this.boutons[x].setIcon(new ImageIcon("Image/fondVide.png"));
        }
      }
    }
  }

  public void clicBouton() {
    // S'il y a eu 2 clics effectue le mouvement
    if (this.clics.size() == 2) {
      final Piece p = this.echiquier.getCase((int) this.clics.get(0).getX(), (int) this.clics.get(0).getY());
      // A changer erreur si premier clic pas sur une piece
      if (p != null && p.getCouleur() == this.tourJoueur) {
        if (!echiquier.testDeplacementEchec(p.getCouleur(), p, (int) this.clics.get(1).getX(),
            (int) this.clics.get(1).getY())) {
          final boolean mouvementFait = p.mouvement((int) this.clics.get(1).getX(), (int) this.clics.get(1).getY());
          this.refreshBoutons();
          if (mouvementFait) {
            this.swapTour();
            System.out.println(this.tourJoueur);
          }
        } else {
          System.out.println("Echec dans cette position");
        }
      }
      this.clics.clear();
    }
    //
    // if(echiquier.testEchec("Blanc")) System.out.println("Y A ECHEC LA");
    // if(echiquier.testPat("Blanc")) System.out.println("Y A PAT LA");
    // if(echiquier.testEchec("Blanc") && echiquier.testPat("Blanc"))
    // System.out.println("Y A MAAAAAAT LA");
    //
  }

  public void swapTour() {
    if (this.tourJoueur == "Blanc") {
      this.tourJoueur = "Noir";
    } else {
      this.tourJoueur = "Blanc";
    }
    // Fait disparaitre les cases
    // this.labelTour.setText("Tour du joueur : " + tourJoueur);
  }

  // Listener pour les boutons du plateau
  public class ListenerJeu implements ActionListener {

    public ListenerJeu() {
    }

    public void actionPerformed(final ActionEvent e) {
      final BoutonJeu bouton = (BoutonJeu) e.getSource();
      final int x = bouton.getX();
      final int y = bouton.getY();
      System.out.println("x : " + x + " | y : "+ y);
      if((clics.size() == 0 && echiquier.getCase(x,y) != null) || clics.size() == 1){
        clics.add(new Point(x,y));
        clicBouton();
      }
      //Ajoute un clic à l'ArrayList
      //Utilise la methode qui verifie s'il y a eu 2 clics
    }
  }

}
