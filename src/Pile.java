public class Pile {

    private int[] elements;  // tableau contenant les valeurs empilées
    private int sommet;      // indice du sommet de pile (nombre d'éléments)

    public Pile() {
        this(100); // capacité par défaut
    }


    public Pile(int capacite) {
        elements = new int[capacite];
        sommet = 0;
    }

    public void empiler(int valeur) {
        if (sommet == elements.length) {
            // agrandit le tableau si la pile est pleine
            elements = java.util.Arrays.copyOf(elements, elements.length * 2);
        }
        elements[sommet] = valeur;
        sommet++;
    }

    public int depiler() {
        if (estVide()) {
            throw new RuntimeException("Pile vide : impossible de dépiler.");
        }
        sommet--;
        return elements[sommet];
    }

    public int sommet() {
        if (estVide()) {
            throw new RuntimeException("Pile vide : pas de sommet.");
        }
        return elements[sommet - 1];
    }

    public boolean estVide() {
        return sommet == 0;
    }

    public int taille() {
        return sommet;
    }
}
