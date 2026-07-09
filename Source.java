public class Source{
    private String chaine;
    private int indice;

    // constructeur permettant d'initialiser la chaine et l'indice à la position 0
    public Source (String chaine){
        this.chaine = chaine;
        this.indice = 0;
    }

    // la focntion qui retourne le caratere pris dans la chaine
    public char premier(){
        return chaine.charAt(indice);
    }

    // la fonction qui incremente l'indice de la position
    public void suivant(){
        indice++;
    }
}