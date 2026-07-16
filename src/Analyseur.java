public class Analyseur {

    private Source source; // les caracteres qu'ont va analyser
    private Pile pile; // va etre utiilsée pour empiler et/ou calculer les valeurs
    public Analyseur(Source source) {
        this.source = source;
        this.pile = new Pile();
    }



    // Analyse une somme : S -> P | P + S
    public void somme()  throws  SyntaxException {

        produit();

        char caractereCourant = source.premier();

        if (caractereCourant == '+' || caractereCourant == '-') {
            source.suivant();
            somme();
            pile.operer(caractereCourant);
        }

    }

    // Analyse un produit : P -> T | T * P
    public void produit() throws SyntaxException {
        terme();
        char caractereCourant = source.premier();

        if (caractereCourant == '*' || caractereCourant == '/') {
            source.suivant();
            produit();
            // logique de calcul
            pile.operer(caractereCourant);
        }
        // return true;
    }

    // Analyse un terme : T -> C | ( S )
    public void terme() throws SyntaxException {

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
    public void chiffre() throws  SyntaxException {

        char caractereCourant = source.premier();

        if (caractereCourant >= '0' && caractereCourant <= '9') {
            pile.empiler(caractereCourant - '0');
            source.suivant();

        }
        else throw new SyntaxException("On n'a pas de chiffre mais '"+ caractereCourant +"'");
    }

    // Verifie la presence du point-virgule final et affiche un message selon le résultat de l'analyse
    public void interpreteur() throws  SyntaxException{
        somme();
        if (source.premier() == ';') { //Test1
            // System.out.println(source.premier());
            System.out.println("Analyse correcte.");
            System.out.println("Resultat = "+ pile.sommet());
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
