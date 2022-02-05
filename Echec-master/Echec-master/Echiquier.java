import java.io.Serializable;

public class Echiquier implements Serializable {
  private Piece[] echiquier;
  private static final long serialVersionUID = 1L;

  public Echiquier() {
    this.initialisation();
  }

  public void initialisation() {
      this.echiquier = new Piece[64];

      // test Pion et cavalier
      //Pièces Noir :         
      /*c L
      this.echiquier[0] = new Tour("N",0,0,this);
      this.echiquier[1] = new Cavalier("N",0,1,this);
      this.echiquier[2] = new Fou("N",0,2,this);
      this.echiquier[3] = new Reine("N",0,3,this);
      this.echiquier[4] = new Roi("N",0,4,this);
      this.echiquier[5] = new Fou("N",0,5,this);
      this.echiquier[6] = new Cavalier("N",0,6,this);
      this.echiquier[7] = new Tour("N",0,7,this);
*/
      //Pion Noir
 //     for(int i=0;i<8;i++) this.echiquier[8*1+i] = new Pion("N",1,i,this);


      //Pièces blanche :               C  L

      /*this.echiquier[56] = new Tour("B",7,0,this);
      this.echiquier[57] = new Cavalier("B",7,1,this);
      this.echiquier[58] = new Fou("B",7,2,this);
      this.echiquier[60] = new Roi("B",7,4,this);
      this.echiquier[59] = new Reine("B",7,3,this);
      this.echiquier[61] = new Fou("B",7,5,this);
      this.echiquier[62] = new Cavalier("B",7,6,this);
      this.echiquier[63] = new Tour("B",7,7,this);
*/
      //Pion blanc
   //   for(int i=0;i<8;i++) this.echiquier[8*6+i] = new Pion("B",6,i,this);


      /*// test pour le roi
      this.echiquier[2] = new Pion("B",0,2,this);
      this.echiquier[10] = new Pion("N",1,2,this);
      this.echiquier[33] = new Reine("N",4,1,this);
      this.echiquier[37] = new Reine("B",4,5,this);
      this.echiquier[0] = new Roi("N",0,0,this);
      this.echiquier[44] = new Roi("B",5,4,this);


       // test pour la reine
      this.echiquier[34] = new Reine("N",4,2,this);
      this.echiquier[37] = new Reine("B",4,5,this);
      this.echiquier[0] = new Roi("N",0,0,this);
      this.echiquier[63] = new Roi("B",7,7,this);

   
     //test pour le pat
   /*
    * roi contre roi,
	  roi et fou contre roi,
      roi et cavalier contre roi,
      roi et fou contre roi et fou de m�me couleur,
      roi et reine contre roi
    *      
    *      
    */
     this.echiquier[0] = new Roi("N",0,0,this);
     this.echiquier[33] = new Reine("B",4,1,this);
     this.echiquier[26] = new Roi("B",3,2,this);


       //test pour la Tour
     // this.echiquier[34] = new Tour("N",4,2,this);
      //this.echiquier[36] = new Tour("B",4,4,this);
      //this.echiquier[0] = new Roi("N",0,0,this);
      //this.echiquier[63] = new Roi("B",7,7,this);


      //test pour le Fou
      /*
      this.echiquier[34] = new Fou("N",4,2,this);
      this.echiquier[37] = new Fou("B",4,5,this);
      this.echiquier[0] = new Roi("N",0,0,this);
      this.echiquier[63] = new Roi("B",7,7,this);
      */
    }

    //affichage
    public void afficherPlateau() {
      String blancNoir = "B = Blanc     N = Noir";
      String first_ligne = "******************************************";
      String second_ligne = "   *          "; //6 //10


      for(int i = 0; i<7;i++) {
        first_ligne += "*******";
      }
      first_ligne += "*";
      for(int i = 0;i<8;i++) {
        second_ligne += "*          ";
      }
      System.out.println(first_ligne);

      for(int i=0; i<8; i++) {
        System.out.println(second_ligne);
        int oui = 97;
        oui += i;
        String ligne = " " + (char) oui + " ";
        for(int j = 0;j<8 ;j++) {
          Piece terrain = this.echiquier[8*i+j];
          if (terrain == null) {
            ligne += "*   "+"       ";
          }
          else{
            ligne += "*   "+terrain.toString() + ": " + terrain.getCouleur()+"  "; //4
          }
        }
        ligne += "*";
        System.out.println(ligne);
        System.out.println(second_ligne);
        System.out.println(first_ligne);
      }
      System.out.println("         1          2          3          4          5          6          7          8");
      System.out.println(first_ligne);
      System.out.println(blancNoir);
    }

    public Piece getCase(int i, int j) {
        return this.echiquier[8*i+j];
    }

    public Piece[] getEchiquier(){
      return this.echiquier;
    }

    //on prend la case du roi celon la couleur donner en parametre
    public Piece getRoi(String couleur){
      for(int i = 0; i < 8; i++){
        for(int j = 0; j < 8; j++){
          Piece p = this.echiquier[8*i+j];
          if(p instanceof Roi && p.getCouleur() == couleur){
            return p;
          }
        }
      }
      return null;
    }

    //test si le roi de la couleur donner en parametre est en echec(si il peut se faire manger au prochain tour par un pion adverse)
    public boolean echec(String couleur){
      Piece roi = this.getRoi(couleur); // recupere la couleur du roi
      int ligneRoi = roi.getLigne(); // recupere la ligne du roi
      int colonneRoi = roi.getColonne(); // recupere la colonne du roi

      for(int i = 0; i < 8; i++){ // parcour les lignes
        for(int j = 0; j < 8; j++){ // parcour les colonnes
          if((this.echiquier[8*i+j] != null) && this.echiquier[8*i+j].getCouleur() != couleur){ // si il y a une piece sur la case et qui est de la couleur opposer a celle du roi
            Piece p = this.echiquier[8*i+j]; // on met la case dans un type piece
            if(p.verifMouvement(ligneRoi, colonneRoi)) return true; // on test pour savoir si la piece peut aller manger le roi, si oui on retourne true
          }
        }
      }
      return false; // on retourne false si le roi n'est pas en echec
    }
// test si on se met pas en echec et mat 
    public boolean testDeplacementEchec(String couleur,Piece p, int ligneArriver, int colonneArriver){
      Piece caseArrivee = this.getCase(ligneArriver,colonneArriver); // On recupère le contenu de la case de destination
      int x = p.getLigne(); // On sauvegarde la ligne de la piece
      int y = p.getColonne(); // On sauvegarde la colonne de la piece
      boolean echec = false;


      this.echiquier[8*x+y] = null; // On passe a null le contenu de la case de depart
      this.echiquier[8*ligneArriver+colonneArriver] = p; // On met dans la case d'arriver la piece
      if(this.echec(couleur) == true){ // Si cela met en echec le roi adverse alors on passe echec a true
        echec = true;
      }
      this.echiquier[8*x+y] = p; // On remet la piece a sa position initiale
      this.echiquier[8*ligneArriver+colonneArriver] = caseArrivee; // On remet le contenu de la case d'arriver
      return echec;
    }


    /*public boolean testPat(String couleur){
      if(echec(couleur) == true){
        return false;
      }
      Piece roi = this.getRoi(couleur);
      int ligneRoi = roi.getLigne();
      int colonneRoi = roi.getColonne();
      int possibilite = 0;
      int nbEchec = 0;
      boolean pat = false;

      for(int i = 0; i < 8; i++){ // parcour les lignes
        for(int j = 0; j < 8; j++){ // parcour les colonnes
          Piece p = this.echiquier[8*i+j];
          if(p != null && p.getCouleur() != couleur){
            if(roi.verifMouvement(ligneRoi,colonneRoi-1)) possibilite += 1; //gauche du roi
            if(roi.verifMouvement(ligneRoi-1,colonneRoi-1)) possibilite += 1; // haut gauche du roi
            if(roi.verifMouvement(ligneRoi-1,colonneRoi)) possibilite += 1; // haut du roi
            if(roi.verifMouvement(ligneRoi-1,colonneRoi+1)) possibilite += 1; // haut droite du roi
            if(roi.verifMouvement(ligneRoi,colonneRoi+1)) possibilite += 1; // droite du roi
            if(roi.verifMouvement(ligneRoi+1,colonneRoi+1)) possibilite += 1; // bas droite du roi
            if(roi.verifMouvement(ligneRoi+1,colonneRoi)) possibilite += 1; // bas du roi
            if(roi.verifMouvement(ligneRoi+1,colonneRoi-1)) possibilite += 1; // bas gauche du roi

            if(p.verifMouvement(ligneRoi,colonneRoi-1)) nbEchec += 1; // gauche du roi
            if(p.verifMouvement(ligneRoi-1,colonneRoi-1)) nbEchec += 1; // haut gauche du roi
            if(p.verifMouvement(ligneRoi-1,colonneRoi)) nbEchec += 1; //haut du roi
            if(p.verifMouvement(ligneRoi-1,colonneRoi+1)) nbEchec += 1; // haut droite du roi
            if(p.verifMouvement(ligneRoi,colonneRoi+1)) nbEchec += 1; // droite du roi
            if(p.verifMouvement(ligneRoi+1,colonneRoi+1)) nbEchec += 1; // bas droite du roi
            if(p.verifMouvement(ligneRoi+1,colonneRoi)) nbEchec += 1; // bas du roi
            if(p.verifMouvement(ligneRoi+1,colonneRoi-1)) nbEchec += 1; // bas gauche du roi

            if(possibilite == nbEchec){
              pat = true;
            }

            for(int x = 0; x < 8; x++){
              for(int y = 0; y < 8; y++){
                if(p.testAutoEchec(i,j) && !this.testDeplacementEchec(couleur,p,i,j) && p.verifMouvement(i,j)){
                  pat = false;
                }
              }
            }
          }
        }
      }
      return pat;
  }*/

  public boolean testPat(String couleur){
    for(int y=0; y<8 ; y++){ // On parcourt les lignes du plateau
      for(int x=0; x<8 ; x++){ // On parcourt les colonnes du plateau
        Piece p = this.getCase(x,y); // On récupère le contenu de chaque case
        if(p != null && p.getCouleur() == couleur){ // On verifie que la case n'est pas vide et que la piece est de la couleur du joueur
          for(int i=0; i<8 ; i++){ // On reparcourt les lignes du plateau
            for(int j=0; j<8 ; j++){ // On reparcourt les colonnes du plateau
              if(p.testAutoEchec(i,j) && !this.testDeplacementEchec(couleur,p,i,j) && p.verifMouvement(i,j)) { // On verifie que la pièce peut faire le mouvement et qu'après son deplacement il n'y a pas d'échec
                return false; // Il n'y a pas de Pat | si il y a pas d echec ba c est egaliter
              }
            }
          }
        }
      }
    }
    return true; // Il y a un Pat
  }
// test echec mat 
    public boolean testMat(String couleur) {
      if(this.testPat(couleur) == true && this.echec(couleur) == true) return true;
      else return false;
    }
}
