public class Principale{

    public static void main(String[] arg){

        // initialisation de la chaine
        String chaine = "2+3&4;"; //TEST2

        /*TEST 2: On teste l'analyseur avec "2+3&4;" et 
        ça revoie analyse correcte comme le prévoit le CDC.
        Explication: Ici, dès que le parseur voit '$' au lieu de '*', produit() et somme() considerent la somme "2+3"                                           
        comme terminée et valide, sans jamais verifier que le reste de la chaine ("$4;") a bien ete consommé.                                            
        Comme analyseur() ne verifie pas non plus la presence reelle du point-virgule final, l'erreur passe                                              
        inapercue.*/

        // creation de l'objet source et initialisation
        Source source = new Source(chaine);

        // creation de l'objet analyse et initialisation
        Analyseur analyse = new Analyseur(source);

        // lancement de l'analyseur
        analyse.analyseur();
    }
}
