import javax.swing.*;

public class BoutonJeu extends JButton{
  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private final int x;
  private int y;

  public BoutonJeu(final int x) {
    super();
    this.x = x;
  }

  public BoutonJeu(final String nom, final int x, final int y) {
    super(nom);
    this.x = x;
    this.y = y;
  }

  //Methodes

  public int getX(){
    return this.x;
  }

  public int getY(){
    return this.y;
  }

}
