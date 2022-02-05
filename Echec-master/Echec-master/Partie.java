import java.util.Scanner;
import java.io.*;

public class Partie {
  private Echiquier echiquier;
  private String joueur = "B";
  private char ligneC;
  private int ligneI;
  private char colonneC;
  private int colonneI;
  File fichier =  new File("echiquier.ser");


  public Partie() {
    this.echiquier = new Echiquier();
  }


  public void menu(){
    final Scanner sc = new Scanner(System.in);
    System.out.println();
    System.out.println("Que souhaitez-vous faire?\n\nNouvelle partie : 1, Charger une partie : 2, Quitter : 3");
    final int choix = sc.nextInt();

    if (choix == 1) {
      this.echiquier = new Echiquier();
      this.jouer();
    }
    if (choix == 2) {
      this.charger();
      this.echiquier.afficherPlateau();
      this.jouer();
    }
    if (choix == 3)
      System.exit(0);

    if (choix == 4)
      this.jouer();
  }

  public void sauver() {
    try {
      final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier));
      oos.writeObject(this.echiquier);
    } catch (final Exception e) {
      System.out.println("Erreur : fichier non sauvegarder");
    }
  }

  public void charger() {
    try {
      final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier));
      this.echiquier = (Echiquier) ois.readObject();
      this.jouer2();
    } catch (final Exception e) {
      System.out.println("Erreur : pas de fichier");
    }
  }

  public String getJoueur() {
    return this.joueur;
  }

  public void alternanceJoueur() {
    if (this.joueur == "B")
      this.joueur = "N";
    else
      this.joueur = "B";
  }

  public void effacerTerminal() {
    for (int i = 0; i < 30; i++)
      System.out.println();
  }

  public int[] choixCase() {
    final boolean saisieOk = false;
    String selection;

    while (saisieOk != true) { // saisie controler
      final Scanner sc = new Scanner(System.in);
      selection = sc.nextLine();

      if (selection.length() == 1) {
        final char sauvegarder = selection.charAt(0); // met la premier lettre de ce qui y'a dans selection
        if (Character.toString(sauvegarder).equals("s")) { // si on appuie sur s
          this.sauver(); // sauvegarde
          System.out.println("Fichier sauvegarder.");
          System.exit(0); // stop le programme
        }
      }

      if (selection.length() == 2) {
        ligneC = selection.charAt(0); // on stock la premiere letre qui sera notre ligne
        ligneI = ((int) ligneC) - 96; // on convertit la letre en int
        colonneC = selection.charAt(1); // on prend notre deuxieme lettre qui sera notre colonne
        colonneI = Character.getNumericValue(colonneC); // convertit (renvoie sa valeur numerique)
       
        if ((colonneI >= 1 && colonneI <= 8) && (ligneI >= 1 && ligneI <= 8)) { // verifie les veleur donnee
          System.out.println("Votre ligne :" + ligneI + " | Votre colonne : " + colonneI); // on donne les coordonee
          final int[] cases = { ligneI - 1, colonneI - 1 }; // pour remetre en valeur tableau
          return cases;
        }
      }
    }
    
  }
//A chaque déplacement d’une pièce, on verifie si il y un pat puis mat 
  public void jouer() {
    while (this.echiquier.testPat(joueur) != true && this.echiquier.testMat(joueur) != true) { //
      this.effacerTerminal(); // clear le terminal
      this.echiquier.afficherPlateau(); // afficher l'echiquier
      System.out.println("Tour du joueur " + this.joueur + ""); // donne le tour des joueurs
      System.out.println("Selectionner une piece a deplacer, vous pouvez appuyer sur 's' pour sauvegarder et quitter.");

      final int[] tab = this.choixCase(); // on met le choix d'une case dans un tableau
      final Piece p = this.echiquier.getCase(tab[0], tab[1]);

      if(p.getCouleur() == this.getJoueur()) { // on verifie la couleur
        System.out.println("Ou souhaitez-vous deplacer cette piece ?");

        int[] choix = this.choixCase();

        while(p.mouvement(choix[0],choix[1]) == false) { // tant que le deplacement n'est pas possible
        	
          System.out.println("Deplacement impossible veuillez essayer une autre case");
          System.out.println("Ou souhaitez-vous deplacer cette piece ?");

          choix = this.choixCase();
        	
   
        }
        this.alternanceJoueur();
        this.echiquier.afficherPlateau();
      }
    }
    if(this.echiquier.testPat(this.joueur) && this.echiquier.testMat(this.joueur) == false) System.out.println("Egalite");
    if(this.echiquier.testMat(this.joueur)) System.out.println("Echec et Mat : le joueur " + this.joueur + " a perdu.");
  }

  public void test(){
    while(this.echiquier.testPat(joueur) != true && this.echiquier.testMat(joueur) != true){
  }
}
  public void jouer2() {
	      this.effacerTerminal(); // clear le terminal
	      this.echiquier.afficherPlateau(); // afficher l'echiquier
	      System.out.println("Tour du joueur " + this.joueur + ""); // donne le tour des joueurs
	      System.out.println("Selectionner une piece a deplacer, vous pouvez appuyer sur 's' pour sauvegarder et quitter.");

	      final int[] tab = this.choixCase(); // on met le choix d'une case dans un tableau
	      final Piece p = this.echiquier.getCase(tab[0], tab[1]);

	      if(p.getCouleur() == this.getJoueur()) { // on verifie la couleur
	        System.out.println("Ou souhaitez-vous deplacer cette piece ?");

	        int[] choix = this.choixCase();

	        while(p.mouvement(choix[0],choix[1]) == false) { // tant que le deplacement n'est pas possible
	        	
	          System.out.println("Deplacement impossible veuillez essayer une autre case");
	          System.out.println("Ou souhaitez-vous deplacer cette piece ?");

	          choix = this.choixCase();
	        	
	   
	        }
	        this.alternanceJoueur();
	        this.echiquier.afficherPlateau();
	        this.jouer();
	      }
	    }
}
