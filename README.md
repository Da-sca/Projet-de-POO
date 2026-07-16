# Analyseur Syntaxique d'Expressions Arithmétiques

**Mini-projet de Programmation Orientée Objet (POO)**

---
# Plan

- [Introduction](#introduction)
- [Grammaire utilisée](#grammaire-utilisée)
- [Les différentes versions du projet](#les-différentes-versions-du-projet)
  - [Version 1 : Analyseur syntaxique de base](#version-1--analyseur-syntaxique-de-base)
  - [Version 2 : Gestion du débordement](#version-2--gestion-du-débordement)
  - [Version 3 : Gestion des erreurs par exceptions](#version-3--gestion-des-erreurs-par-exceptions)
  - [Version 4 : Interpréteur](#version-4--interpréteur)
  - [Version 5 : Compilateur](#version-5--compilateur)
- [Architecture du projet](#architecture-du-projet)
- [Compilation et exécution](#compilation-et-exécution)
- [Navigation entre les versions](#navigation-entre-les-versions)
- [Exemples de fonctionnement](#exemples-de-fonctionnement)
- [Équipe](#équipe)
---

## Introduction
Ce projet, réalisé dans le cadre du module de Programmation Orientée Objet, consiste à concevoir un analyseur syntaxique d'expressions arithmétiques en langage Java. Il s'appuie sur une grammaire formelle et met en œuvre la technique de l'analyse descendante récursive afin de reconnaître des expressions telles que 2+3*4;.

Le développement a été mené de manière progressive, en faisant évoluer une même architecture à travers cinq étapes successives : un analyseur syntaxique de base, une gestion des erreurs par valeurs booléennes, une gestion des erreurs par exceptions, un interpréteur capable d'évaluer les expressions, puis un compilateur générant des instructions destinées à une machine à pile.

L'objectif de ce projet est de montrer qu'une architecture bien conçue peut être enrichie progressivement sans remise en cause de sa structure initiale, tout en illustrant plusieurs notions fondamentales de la programmation orientée objet, de l'analyse syntaxique et des principes de compilation.

---

## Grammaire utilisée

L'analyseur s'appuie sur la grammaire suivante :

```
E → S ;

S → P
  | P + S
  | P - S

P → T
  | T * P
  | T / P

T → C
  | ( S )

C → 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
```

Chaque règle de cette grammaire est implémentée par une méthode Java, ce qui correspond à la technique de l'**analyse descendante récursive**.

| Non terminal | Méthode associée |
|--------------|------------------|
| E | `expression()` |
| S | `somme()` |
| P | `produit()` |
| T | `terme()` |
| C | `chiffre()` |

---

## Les différentes versions du projet

Le projet a été développé de manière incrémentale. Chaque version repose sur la précédente et introduit une évolution fonctionnelle ou architecturale sans remettre en cause la structure générale de l'application.

### Version 1 : Analyseur syntaxique de base

**Objectif :** Mettre en place l'architecture du projet et reconnaître les expressions conformes à la grammaire.

Cette première version implémente un analyseur descendant récursif dans lequel chaque symbole non terminal est associé à une méthode de la classe `Analyseur`. Les erreurs de syntaxe sont propagées au moyen de valeurs booléennes.

**Limites :**

* aucune gestion du débordement de la chaîne d'entrée ;
* prise en charge des seuls chiffres isolés ;

---

### Version 2 : Gestion du débordement

**Objectif :** Rendre l'analyseur plus robuste face aux erreurs de lecture.

La classe `Source` est enrichie afin de détecter les dépassements de la chaîne analysée. Les erreurs continuent d'être propagées par des valeurs booléennes, mais le programme ne s'interrompt plus brutalement lorsqu'une expression est incomplète.

**Évolutions principales :**

* détection du débordement de chaîne ;
* sécurisation des méthodes de lecture ;
* validation systématique de la fin de l'expression.

---

### Version 3 : Gestion des erreurs par exceptions

**Objectif :** Simplifier la propagation des erreurs et améliorer la lisibilité du code.

Les retours booléens sont remplacés par le mécanisme d'exceptions de Java. Une classe `SyntaxException` est introduite afin de distinguer les erreurs de syntaxe des erreurs liées au débordement de la chaîne.

**Évolutions principales :**

* introduction de `SyntaxException` ;
* suppression des tests booléens répétitifs ;
* propagation automatique des erreurs grâce aux exceptions.

---

### Version 4 : Interpréteur

**Objectif :** Calculer la valeur d'une expression valide.

L'analyseur ne se limite plus à reconnaître une expression : il l'évalue. Une classe `Pile`, reposant sur le principe **LIFO (Last In, First Out)**, est introduite afin de mémoriser les opérandes et de réaliser les opérations arithmétiques.

Dans une première implémentation, les méthodes `somme()` et `produit()` dépilaient elles-mêmes les opérandes, effectuaient le calcul puis empilaient le résultat. Afin d'éviter cette duplication, cette logique a ensuite été regroupée dans une méthode `operer(char operateur)` de la classe `Pile`, permettant de réduire le code et de mieux répartir les responsabilités entre les classes.

**Exemple :**

```text
Expression :
2+3*4;

Résultat :
14
```

---

### Version 5 : Compilateur

**Objectif :** Générer des instructions plutôt que calculer immédiatement le résultat.

Les opérations effectuées par l'interpréteur sont remplacées par l'émission d'instructions destinées à une machine à pile. Le résultat est ainsi produit sous forme de code intermédiaire en notation postfixée (Reverse Polish Notation).

**Exemple :**

```text
Expression :
2+3*4;

Instructions générées :
PUSH 2
PUSH 3
PUSH 4
MUL
ADD
```

Cette dernière évolution illustre le passage d'un interpréteur à un compilateur pédagogique, dans lequel l'exécution est remplacée par la génération d'une séquence d'instructions.

---

## Architecture du projet

```
src/
│
├── Source.java          // Lecture caractère par caractère
├── Analyseur.java       // Cœur de l'analyseur syntaxique
├── Principale.java      // Point d'entrée du programme
├── SyntaxException.java // Exception personnalisée
└── Pile.java            // Pile pour l'interpréteur
```

### Description des classes

| Classe | Rôle |
|--------|------|
| `Source` | Gère la lecture de la chaîne, caractère par caractère. Fournit les méthodes `premier()` et `suivant()`. |
| `Analyseur` | Implémente la grammaire. Contient les méthodes `somme()`, `produit()`, `terme()`, `chiffre()`. Selon la version, il valide, interprète ou compile. |
| `Pile` | Introduite dans la version 4. Stocke les opérandes et exécute les opérations arithmétiques. |
| `SyntaxException` | Exception personnalisée pour signaler les erreurs de syntaxe. |
| `Principale` | Point d'entrée. Initialise les objets, lance l'analyse et affiche les résultats. |

---

## Compilation et exécution

**Compilation :**

```bash
javac *.java
```

**Exécution :**

```bash
java Principale
```

---
## Navigation entre les versions

Chaque évolution du projet est associée à un **tag Git**, permettant de retrouver l'état exact du code correspondant à une étape donnée.

### Afficher tous les tags

```bash
git tag
```

Exemple de résultat :

```text
v1
v2
v3
v4
v5
```

### Se positionner sur une version

Pour consulter une version précise du projet :

```bash
git checkout <nom_du_tag>
```

Par exemple, pour accéder à la version de l'interpréteur (sans aucune ambiguitée sur le nommage d'un commit ou d'un tag):

```bash
git checkout tags/v4
```

Ou à la version du compilateur :

```bash
git checkout tags/v5
```

### Revenir à la dernière version du projet

Pour revenir sur la branche principale :

```bash
git checkout main
```

### Créer une branche à partir d'un tag (optionnel)

Si vous souhaitez modifier une ancienne version sans impacter les autres :

```bash
git checkout -b ma-branche v4
```

Une nouvelle branche est alors créée à partir du tag sélectionné, ce qui permet de travailler librement sur cette version.


###### (en cas de modification ou poursuite du projet dans le futur)

---

## Exemples de fonctionnement

**Entrée :**

```
2+3;
```

**Sortie :**

```
Analyse correcte.
Résultat = 5
```

---

**Entrée :**

```
(2+3)*4;
```

**Sortie :**

```
Analyse correcte.
Résultat = 20
```

---

**Entrée :**

```
2+3$4;
```

**Sortie :**

```
Erreur de syntaxe.
```

---

## Équipe

Ce mini-projet a été réalisé dans le cadre du module de **Programmation Orientée Objet** par l'équipe suivante :

| Membres de l'équipe |
|---------------|
| Ndeye Awa SENE |  
| Seynabou NDIAYE | 
| Abdou Aziz MBODJ | 
| Ahmadou Bamba DIOP  |
| Abdelsalam Mahamat NASSOUR |  
| Mouhamadou Ben Abdoulaye DIOP | 


---

**Université Cheikh Anta Diop de Dakar (UCAD)**  
**École Supérieure Polytechnique (ESP)**  
**Département Génie Informatique**  
**DIC1 — Génie Informatique**  
**Année universitaire 2025-2026**
