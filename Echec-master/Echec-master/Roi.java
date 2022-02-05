

public class Roi extends Piece   {
  private final String piece;
  private static final long serialVersionUID = 1548938997L;

  public Roi(final String couleur, final int ligne, final int colonne, final Echiquier echiquier) {
    super(couleur, ligne, colonne, echiquier);
    /*
     * if(couleur.equals("Blanc")) { this.piece = "\u2654"; } else { this.piece =
     * "\u265A"; }
     */
    piece = "Ro";
  }

  public boolean verifMouvement(final int ligneArriver, final int colonneArriver) {
    if((Math.abs(ligneArriver - this.getLigne()) == 1 || Math.abs(ligneArriver - this.getLigne()) == 0) && (Math.abs(colonneArriver - this.getColonne()) == 1 || Math.abs(colonneArriver - this.getColonne()) == 0) && this.verifCouleurPiece(ligneArriver,colonneArriver) == true) return true;
    return false;
  }

  public String toString() {
    return this.piece;
  }
}
