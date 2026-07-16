public class Source{
    private String chaine;
    private int indice;
    // private boolean erreur; //gestion du débordement

    // constructeur permettant d'initialiser la chaine et l'indice à la position 0
    public Source (String chaine){
        this.chaine = chaine;
        this.indice = 0;
    }

    // la focntion qui retourne le caratere pris dans la chaine
    public char premier() throws SyntaxException{
      if (indice >= chaine.length()) {
            throw new SyntaxException("Debordement de la chaîne: indice ="+ indice);
         }
      return chaine.charAt(indice);
  }

    // la fonction qui incremente l'indice de la position
    public void suivant() throws SyntaxException{
         if (indice >= chaine.length()) {
            throw new SyntaxException("Debordement de la chaîne: indice");
         }
        indice++;
    }

}
