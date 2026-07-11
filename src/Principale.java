package src;

public class Principale{

    public static void main(String[] arg){

        // initialisation de la chaine
        /**
         * 2*+3;
         * 2+*3;
         * 2+3
         * 2+3$4;
         * 2+3;
         * 2/0;
         */
        String chaine = "2/0;"; //TEST3

        /*TEST 2: On teste l'analyseur avec "2+3&4;" et 
        ça revoie analyse correcte comme le prévoit le CDC.
        Explication: Ici, dès que le parseur voit '$' au lieu de '*', produit() et somme() considerent la somme "2+3"                                           
        comme terminée et valide, sans jamais verifier que le reste de la chaine ("$4;") a bien ete consommé.                                            
        Comme analyseur() ne verifie pas non plus la presence reelle du point-virgule final, l'erreur passe                                              
        inapercue.*/


        /*TEST 3: Expression sans point virgule; 
        provoque une exception comme prévu: StringIndexOutOfBoundsException */

        // creation de l'objet source et initialisation
        Source source = new Source(chaine);

        // creation de l'objet analyse et initialisation
        Analyseur analyse = new Analyseur(source);

        // lancement de l'analyseur
        try {
            analyse.analyseur();
        } catch (SyntaxException e){
            System.out.println("Erreur de syntaxe : " + e.getMessage());
        } catch (Exception e){
            System.out.println("Erreur : Debordement de la chaîne : " + e.getMessage());
        }

    }
}
