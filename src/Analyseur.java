public class Analyseur {

    private static final String RESET = "[0m";
    private static final String CYAN = "[36m";   // valeurs empilées
    private static final String YELLOW = "[33m"; // opérateurs
    private static final String GREEN = "[32m";  // titre et succès

    private Source source; // les caracteres qu'ont va analyser

    public Analyseur(Source source) {
        this.source = source;
    }



    // Analyse une somme : S -> P | P + S
    public void somme()  throws SyntaxException , Exception{

        produit();

        char caractereCourant = source.premier();

        if (caractereCourant == '+' || caractereCourant == '-') {
            source.suivant();
            somme();
            // Au lieu de calculer on print l'opération
            System.out.println(YELLOW + (caractereCourant == '+' ? "ADD" : "SUB") + RESET);
            
        }

    }

    // Analyse un produit : P -> T | T * P
    public void produit() throws SyntaxException , Exception{
        terme();
        char caractereCourant = source.premier();

        if (caractereCourant == '*' || caractereCourant == '/') {
            source.suivant();
            produit();
            // Au lieu de calculer on écrit l'opération
            System.out.println(YELLOW + (caractereCourant == '*' ? "MUL" : "DIV") + RESET);
        }
        // return true;
    }

    // Analyse un terme : T -> C | ( S )
    public void terme() throws SyntaxException , Exception{

        char caractereCourant = source.premier();

        if (caractereCourant == '(') {
            source.suivant();

            somme();

            char caractereFermant = source.premier();

            if (caractereFermant != ')') {
                throw new SyntaxException("Pas de parenthèse fermante");
            }
            source.suivant();

            } else {
            chiffre();
        }
    }

    // Analyse un chiffre : C -> 0 | 1 | ... | 9
    public void chiffre() throws SyntaxException , Exception{

        char caractereCourant = source.premier();

        if (caractereCourant >= '0' && caractereCourant <= '9') {
            System.out.println(CYAN + "PUSH " + (caractereCourant - '0') + RESET); //On imprime la valeur au lieu d'empilerrr !!
            source.suivant();

        }
        else throw new SyntaxException("On n'a pas de chiffre mais '"+ caractereCourant +"'");
    }

    // Verifie la presence du point-virgule final et affiche un message selon le résultat de l'analyse
    public void compilateur() throws Exception, SyntaxException{
        System.out.println(GREEN + "===== Programme compilé =====" + RESET);
        somme();
        if (source.premier() == ';') { //Test1
            System.out.println(GREEN + "==============================" + RESET);
            System.out.println(GREEN + "Analyse correcte." + RESET);
        } else {
            // System.out.println(source.premier());
            // System.out.println("Erreur de syntaxe.");
            /**
             * L'exception est levée dans deux cas :
             * - La première : lorsqu'on arrive à la fin de l'expression et qu'il n'y a pas de ';'
             * - La deuxième : lorsqu'on rencontre un caractère inconnu (comme $)
             */
            throw new SyntaxException("Pas de ';'");
        }
    }
}

/*TEST 1: On remplace expression() par somme() dans Analyseur()
et l'analyse fonctionne quand même. Ce qui confirme que la méthode expression() est bien inutile
et ne fait que déléguer. */
