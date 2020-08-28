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
2. **Installer l'application**  
    - Placez vous dans le dossier où vous avez cloné le dépôt  
    - Tapez la commande `install` si vous êtes en mode console ou cliquez sur `install.bat`
    **N'utilisez pas encore l'application**
3. Importer le script `create-database.sql` pour créer la base de données et les tables
4. Importer le script `data-mysql.sql` pour charger les données de démonstration dans la base
5. Démarrez l'application pour exécuter les différents modules 
    - Tapez la commande `run` si vous êtes en mode console ou cliquez sur `run.bat` pour démarrer le serveur  
6. Entrer l'adresse [http://localhost:8084](http://localhost:8084) dans votre navigateur WEB préféré pour vous rendre sur le site WEB  

> Vous trouverez les fichiers de script *.sql dans les livrables ou dans le dossier biblio/apibiblio/src/main/ressources

L'application est prète à fonctionner avec l'utilisateur :
- `abonne@biblio.fr` mot de passe `abonne` pour le rôle d'utilisateur connecté

### Nettoyage
Si necessaire, vous pouvez supprimer les données de démonstration en exécutant le script `clean-datas.sql` et la base de données sera entièrement vidées.  
Recommencez ensuite à l'étape 4 ci-dessus.