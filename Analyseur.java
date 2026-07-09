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
        if (!produit()) {
            return false;
        }
        if (source.premier() == '+') {
            source.suivant();
            return somme();
        }
        return true;
    }

    // Analyse un produit : P -> T | T * P
    public boolean produit() {
        if (!terme()) {
            return false;
        }
        if (source.premier() == '*') {
            source.suivant();
            return produit();
        }
        return true;
    }

    // Analyse un terme : T -> C | ( S )
    public boolean terme() {
        if (source.premier() == '(') {
            source.suivant();
            if (!somme()) {
                return false;
            }
            if (source.premier() != ')') {
                return false;
            }
            source.suivant();
            return true;
        }
        return chiffre();
    }

    // Analyse un chiffre : C -> 0 | 1 | ... | 9
    public boolean chiffre() {
        char c = source.premier();
        if (c >= '0' && c <= '9') {
            source.suivant();
            return true;
        }
        return false;
    }

    // Verifie la presence du point-virgule final et affiche un message selon le résultat.
    public void analyseur() {
        if (expression() && source.premier() == ';') {
            System.out.println("Analyse correcte.");
        } else {
            System.out.println("Erreur de syntaxe.");
        }
    }
}
