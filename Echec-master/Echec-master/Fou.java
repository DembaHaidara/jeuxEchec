
public class Fou extends Piece {
  private String piece;
  private static final long serialVersionUID = 5498465164l;

  public Fou(String couleur, int ligne, int colonne, Echiquier echiquier) {
    super(couleur, ligne, colonne, echiquier);
    /*if(couleur.equals("Blanc")) {
      this.piece = "\u2657"; //Code utf-8 pour le Fou blanc
    }
    else {
      this.piece = "\u265D"; //Code utf-8 pour le Fou noir
    }*/
    piece = "Fo";
  }

  public boolean verifMouvement(int ligneArriver, int colonneArriver){
    if(!(this.verifCouleurPiece(ligneArriver,colonneArriver))) {
      return false;
    }

    if(Math.abs(ligneArriver - this.ligne) == Math.abs(colonneArriver - this.colonne)){
      String direction = this.direction(ligneArriver, colonneArriver);
      int ligneFou = this.ligne;
      int colonneFou = this.colonne;

      if(direction == "haut gauche"){
      ligneFou += -1;
      colonneFou += -1;
      }
      if(direction == "haut droite"){
        ligneFou += -1;
        colonneFou += 1;
      }
      if(direction == "bas gauche"){
        ligneFou += 1;
        colonneFou += -1;
      }
      if(direction == "bas droite"){
        ligneFou += 1;
        colonneFou += 1;
      }
      if(direction == "gauche"){
        ligneFou += 0;
        colonneFou += -1;
      }
      if(direction == "droite"){
        ligneFou += 0;
        colonneFou += 1;
      }
      if(direction == "bas"){
        ligneFou += 1;
        colonneFou += 0;
      }
      if(direction == "haut"){
        ligneFou += -1;
        colonneArriver += 0;
      }

      while(ligneFou != ligneArriver && colonneFou != colonneArriver){
        if(this.plateau[8*ligneFou+colonneFou] != null) return false;
        if(direction == "haut gauche"){
        ligneFou += -1;
        colonneFou += -1;
        }
        if(direction == "haut droite"){
          ligneFou += -1;
          colonneFou += 1;
        }
        if(direction == "bas gauche"){
          ligneFou += 1;
          colonneFou += -1;
        }
        if(direction == "bas droite"){
          ligneFou += 1;
          colonneFou += 1;
        }
        if(direction == "gauche"){
          ligneFou += 0;
          colonneFou += -1;
        }
        if(direction == "droite"){
          ligneFou += 0;
          colonneFou += 1;
        }
        if(direction == "bas"){
          ligneFou += 1;
          colonneFou += 0;
        }
        if(direction == "haut"){
          ligneFou += -1;
          colonneArriver += 0;
        }
      }
      return true;
    }
    return false;
  }

  public String toString() {
    return this.piece;
  }
}
