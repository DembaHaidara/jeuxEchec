
public class Cavalier extends Piece {
  private final String piece;
  private static final long serialVersionUID = 684894653264894l;

  public Cavalier(final String couleur, final int ligne, final int colonne, final Echiquier echiquier) {
    super(couleur, ligne, colonne, echiquier);
    /*
     * if(couleur.equals("Blanc")) { this.piece = "\u2658"; //Code utf-8 pour le
     * Cavalier blanc } else { this.piece = "\u265E"; //Code utf-8 pour le Cavalier
     * noir }
     */
    piece = "Ca";
  }

  public boolean verifMouvement(final int ligneArriver, final int colonneArriver) {
    if(Math.abs(ligneArriver - this.getLigne()) == 1 && Math.abs(colonneArriver - this.getColonne()) == 2 || Math.abs(ligneArriver - this.getLigne()) == 2 && Math.abs(colonneArriver - this.getColonne()) == 1 && this.verifCouleurPiece(ligneArriver,colonneArriver)) return true;
    return false;
  }

  public String toString() {
    return this.piece;
  }
}
