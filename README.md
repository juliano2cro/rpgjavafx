## RPG Java Project

Ce projet est une simulation RPG en Java basée sur les principaux design patterns.

Toutes les fonctionnalités demandées sont présentes et peuvent être testées via le Launcher.

Fonctionnalités :
- Création de personnages avec Builder
- Règles centralisées via Singleton
- Validation des personnages (nom, statistiques, unicité)
- Ajout dynamique de capacités (Decorator)
- Persistance des personnages (DAO)
- Organisation en groupes hiérarchiques (Composite)
- Exécution d’actions de jeu (Command)
- Simulation de combats simples
- Historique et journal de combat (Observer)
- Interface graphique prévue (JavaFX, MVC)
- Interface graphique


### Le projet contient 4 fenêtres principales :

fenêtre créer personnage : Créer un personnage.
fenêtre listes personnages : Affiches les personnages
fenêtre equipes : Gestion des equipes et de l'armée
fenêtre combat : Permet a deux personnages de s'affronter


Règles du jeu

Chaque personnage possède : HP, STR, INT, AGI.
Les actions suivent la logique pierre-feuille-ciseaux :
Attaque < Pouvoir
Pouvoir < Bloquer
Bloquer < Attaque
Le combat dure un nombre maximum de tours (par défaut 5) ou jusqu’à ce qu’un personnage tombe à 0 HP.

### Les programmes
Launcher
Launcher.main() permet de test et démonstration de toutes les fonctionnalités. Permet de vérifier le builder, DAO, combat, composite, decorator, etc. C’est une simulation console, utile pour développement et tests.

MainApp (JavaFX)
Application.launch(MainApp.class) permet de lancer l’interface graphique.


### Comment jouer

Créer un ou plusieurs personnages.
Organiser les personnages en équipes et armées.
Lancer un combat et choisir vos actions chaque tour.
Observer le journal de combat et les résultats à la fin.