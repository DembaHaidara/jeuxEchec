

public class Reine extends Piece  {
  private String piece;
  private static final long serialVersionUID = 789246878L;

  public Reine(String couleur, int ligne, int colonne, Echiquier echiquier) {
    super(couleur, ligne, colonne, echiquier);
    /*if(couleur.equals("Blanc")) {
      this.piece = "\u2655";
    }
    else {
      this.piece = "\u265B";
    }*/
    piece = "Re";
  }

  public boolean verifMouvement(int ligneArriver, int colonneArriver){
    if(!(this.verifCouleurPiece(ligneArriver,colonneArriver))) {
      return false;
    }

    String direction = this.direction(ligneArriver, colonneArriver);
    if(direction == "haut"){
      for(int i = this.ligne - 1; i > ligneArriver; i--){
        if(this.plateau[8*i+colonneArriver] != null) return false;
      }
      return true;
    }
    if(direction == "bas"){
      for(int i = this.ligne + 1; i < ligneArriver; i++){
        if(this.plateau[8*i+colonneArriver] != null) return false;
      }
      return true;
    }
    if(direction == "gauche"){
      for(int i = this.colonne - 1; i > colonneArriver; i--){
        if(this.plateau[8*ligneArriver+i] != null) return false;
      }
      return true;
    }
    if(direction == "droite"){
      for(int i = this.colonne + 1; i < colonneArriver; i++){
        if(this.plateau[8*ligneArriver+i] != null) return false;
      }
      return true;
    }

    if(Math.abs(ligneArriver - this.ligne) == Math.abs(colonneArriver - this.colonne)){
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
