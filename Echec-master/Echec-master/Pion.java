

public class Pion extends Piece   {
  private boolean etat;
  private String piece;
  private static final long serialVersionUID = 8987302689856L;

  public Pion(String couleur, int ligne, int colonne, Echiquier echiquier) {
    super(couleur,ligne,colonne,echiquier);
    this.etat = false;
    /*if(couleur.equals("Blanc")) {
      piece = "\u2659"; //Code utf-8 pour le Pion blanc
    }
    else {
      piece = "\u265F"; //Code utf-8 pour le Pion noir
    }*/
    piece = "Pi";
  }

  public boolean verifMouvement(int ligneArriver, int colonneArriver) {
    int sens;
    if (this.couleur.equals("N")) {
      sens = 1;
    }
    else {
      sens = -1;
    }

    if (this.etat == false) {
      if(ligneArriver == this.ligne+sens && colonneArriver == this.colonne && this.plateau[8*ligneArriver+colonneArriver] == null) {
        this.etat = true;
        return true;
      } // Verifie que le mouvement d'une case est correct et qu'il n'y a pas de piece sur la case d'arriver
      if(ligneArriver == this.ligne+(sens*2) && colonneArriver == this.colonne && this.plateau[8*(ligneArriver-1)+colonneArriver] == null && this.plateau[8*ligneArriver+colonneArriver] == null) {
        this.etat = true;
        return true;
      } // Verifie que le mouvement de deux case est correct et qu'il n'y a pas de piece sur la case d'arriver
      if((colonneArriver == this.colonne+1 || colonneArriver == this.colonne-1) && ligneArriver == this.ligne+sens && this.plateau[8*ligneArriver+colonneArriver] != null && this.verifCouleurPiece(ligneArriver,colonneArriver) == true) {
        this.etat = true;
        return true;
      } // Verifie que le mouvement en diagonal est correct et qu'il y a une piece sur la case d'arriver
    }
    else {
      if(ligneArriver == this.ligne+sens && colonneArriver == this.colonne && this.plateau[8*ligneArriver+colonneArriver] != null) return true;
      if((colonneArriver == this.colonne+1 || colonneArriver == this.colonne-1) && ligneArriver == this.ligne+sens && this.plateau[8*ligneArriver+colonneArriver] != null && this.verifCouleurPiece(ligneArriver,colonneArriver) == true) return true; // Verifie que le mouvement en diagonal est correct et qu'il y a une piece sur la case d'arriver
    }
    return false;
  }

  public String toString() {
    return this.piece;
  }
}
