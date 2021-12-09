" Projet de Conception Logiciel conçu par :

LUCAS Valentin
LEGOT Quentin
NEVEU Thomas

# Attention

Ce projet utilise la possibilité de séparé son projet en plusieurs modules en avec Gradle ainsi la disposition des dossiers demandé pour le rendu n'a pas pu être respecté
afin que le programme fonctionne.

# Dépendances

Le projet nécessite le ressources suivante pour fonctionner:

- Java 17 (téléchargeable depuis apt sur Debian 11 ou en téléchargeant sur Oracle ou openJDK)
- Gradle 7.3 ou supérieur (téléchargeable depuis https://gradle.org/releases/ puis en suivez les indications données sur cette page: https://docs.gradle.org/7.3.1/userguide/installation.html#installing_manually )

Accédez ensuite à la racine du projet (dossier livraison/ normalement ou du moins le dossier contenant le fichier settings.gradle) pour pouvoir execute le programme

# Les commandes

-  `gradle :client:run --args="[ARGUMENTS]"` permet de lancer le jeu avec les arguments suivant la logique suivant
    - le premier argument concerne le type de vue, indiquer `terminal` pour jouer en mode terminal ou `window` pour jouer en mode fenêtre avec JavaFX
    - les arguments suivants concernent les joueurs, ils suivent une forme "<type du joueur> [classe du joueur]"
        - `type du joueur` pour indiquer si le joueur est:
            - `human` pour indiquer un joueur humain utilisant le clavier ou la souris
            - `computer` indiquant un joueur ordinateur jouant des coups aléatoire
            - `computerS` indiquant un joueur ordinateur semi-aléatoire
        - `classe du joueur` est un paramètre facultatif correspond à:
            - `tank` indiquant que le joueur est de type tank et qu'il subit moins de dégâts des explosions que les autres ses coups en energies pour faire des actions sont plus élevés
            - `dps` ou `default` et un joueur standard, infligeant le plus de dégâts d'attaque et ayant un coup de déplacement équilibré, c'est la valeur par défaut si la classe de ce joueur n'est pas donné
            - `support` est un joueur basé sur la pose d'explosif, n'allant que peu au combat direct, ses coups de déplacement sont standard mais ses coups en energies de pose de mine ou de bombes sont les plus faibles.
        - Un exemple de ce que cela peux donner est: `window computer default human support computerS dps human tank`
            - ici On joue en vue fenêtre avec 4 joueurs:
                - computer de classe default (ou dps)
                - human de classe support
                - computerS de classe dps (ou default)
                - human de classe tank
- `gradle build` permet de compiler, exécuter les tests et archiver les classes dans un fichier `.jar` pour chaque module (client et server)
- `gradle javadoc` pour générer la javadoc, une javadoc est générer par projet (soit 2 javadocs), nous vous mettons à disposition dans le dossier livraison un dossier javadoc pour faciliter la navigation
    - Si vous voulez naviguer dans la nouvelle javadoc que vous aurez générer, vous pouvez y accéder en allant dans livraison/MODULE/build/doc/javadoc
        - les modules étant `client` et `server`
- `gradle test` pour exécuter les tests unitaires