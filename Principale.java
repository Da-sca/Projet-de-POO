public class Principale(){

    public static void main(String[] arg){

        // initialisation de la chaine
        String chaine = "2+3;"; 

        // creation de l'objet source et initialisation
        Source source = new Source(chaine);  

        // creation de l'objet analyse et initialisation 
        Analyseur analyse = new Analyseur(source); 

        // lancement de l'analyseur
        analyse.analyseur(); 
    }
}