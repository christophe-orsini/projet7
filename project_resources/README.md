# BIBLIOTHEQUES DE BOUQUINVILLE

## Project N°7 Gestion du SI des bibliothèques de Bouquinville

* Développeur : Christophe ORSINI
* Version @my-version@

---
### Prérequis
- **Java** version **1.8.0_222**
- **Maven** version **3.6.2**
- **MySQL** version **5.7.21**
 
Le serveur MySQL doit être en fonction et les ports 8080 et 8084 doivent être libres

### Chargement
Clonez le dépôt à cette adresse [https://github.com/christophe-orsini/Projet7.git](https://github.com/christophe-orsini/Projet7.git)

### Deploiement, Installation et Exécution
1. **Mettre le serveur MySQL en fonction**
2. **Créer une base de données** nommée `biblio` 
3. Installer l'application  
    - Placez vous dans le dossier où vous avez cloné le dépôt  
    - Tapez la commande `install` si vous êtes en mode console ou cliquez sur `install.bat`
4. Démarrez d'application pour construire la structure de la base de données 
    - Tapez la commande `run` si vous êtes en mode console ou cliquez sur `run.bat` pour démarrer le serveur  
    - **N'utilisez pas encore l'application**
5. Importer le script `demodatas.sql` pour charger les données de démonstration dans la base
6. Entrer l'adresse [http://localhost:8084](http://localhost:8084) dans votre navigateur WEB préféré pour vous rendre sur le site WEB  

> *Vous trouverez ce fichier de script dans les livrables ou dans le dossier biblio/apibiblio/src/main/ressources*

L'application est prète à fonctionner avec 1 utilisateurs :
- `abonne@biblio.fr` mot de passe `abonne` pour le rôle d'utilisateur connecté

### Nettoyage
Si necessaire, vous pouvez supprimer les données de démonstration en exécutant le script `cleandatas.sql` et la base de données sera entièrement vidées.  
Si vous exécutez le script `productiondatas.sql`, la base de données sera entièrement vidées et seul le compte de l'administrateur sera créé avec les éléments ci-dessus

> *Vous trouverez ces fichiers de script dans le dossier biblio/apibiblio/src/main/ressources*