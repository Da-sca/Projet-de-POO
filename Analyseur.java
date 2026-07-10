public class Analyseur {

    private Source source; // les caracteres qu'ont va analyser

    public Analyseur(Source source) {
        this.source = source;
    }

    // E -> S ;
    public boolean expression() {
        return somme();
    }

    // Analyse une somme : S -> P | P + S
    public boolean somme() {
        if (source.erreur()) return false;
        if (!produit()) {
            return false;
        }
        char caractereCourant = source.premier();
        if (source.erreur()) return false;
        if (caractereCourant == '+' || caractereCourant == '-') {
            source.suivant();
            return somme();
        }
        return true;
    }

    // Analyse un produit : P -> T | T * P
    public boolean produit() {
        if (source.erreur()) return false;
        if (!terme()) {
            return false;
        }
        char caractereCourant = source.premier();
        if (source.erreur()) return false;
        if (caractereCourant == '*' || caractereCourant == '/') {
            source.suivant();
            return produit();
        }
        return true;
    }

    // Analyse un terme : T -> C | ( S )
    public boolean terme() {
        if (source.erreur()) return false;
        char caractereCourant = source.premier();
        if (source.erreur()) return false;
        if (caractereCourant == '(') {
            source.suivant();
            if (!somme()) {
                return false;
            }
            char caractereFermant = source.premier();
            if (source.erreur()) return false;
            if (caractereFermant != ')') {
                return false;
            }
            source.suivant();
            return true;
        }
        return chiffre();
    }

    // Analyse un chiffre : C -> 0 | 1 | ... | 9
    public boolean chiffre() {
        if (source.erreur()) return false;
        char caractereCourant = source.premier();
        if (source.erreur()) return false;
        if (caractereCourant >= '0' && caractereCourant <= '9') {
            source.suivant();
            return true;
        }
        return false;
    }

    // Verifie la presence du point-virgule final et affiche un message selon le résultat de l'analyse
    public void analyseur() {
        if (somme() && source.premier() == ';') { //Test1
            System.out.println(source.premier());
            System.out.println("Analyse correcte.");
        } else {
            System.out.println(source.premier());
            System.out.println("Erreur de syntaxe.");
        }
    }
}

/*TEST 1: On remplace expression() par somme() dans Analyseur() 
et l'analyse fonctionne quand même. Ce qui confirme que la méthode expression() est bien inutile 
et ne fait que déléguer. */

