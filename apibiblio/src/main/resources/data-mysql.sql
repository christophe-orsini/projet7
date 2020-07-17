INSERT INTO theme (id, nom) VALUES
(1, "Inconnu"),
(2, "Aventure pour tous"),
(3, "Aventure"),
(4, "Roman fantaisie"),
(5, "Enfants");

INSERT INTO ouvrage (id, auteur, nbre_exemplaire, resume, titre, theme_id, annee_edition) VALUES
(1, "Antoine de Saint-Exupéry", 1, "À la suite d’une panne de moteur, un aviateur se retrouve dans le désert du Sahara. Il rencontre le petit prince qui lui demande de lui dessiner un mouton.", "Le Petit Prince", 2, 1943),
(2, "Herman Melville", 4, "Moby Dick est ce chef-d'oeuvre total que tout le monde peut lire comme le plus formidable des romans d'aventures.", "Moby Dick", 3, 1851),
(3, "J.K. Rowling", 0, "Après la mort de ses parents, Harry Potter est recueilli par sa tante maternelle Pétunia et son oncle Vernon à l'âge d'un an.", "Harry Potter à l'école des sorciers", 4, 1997),
(4, "Charles Perrault", 1, "Un bûcheron et sa femme n'ont plus de quoi nourrir leurs sept garçons. .", "Le petit poucet", 5, 1697);

INSERT INTO utilisateur (id, email, nom, password, prenom, role) VALUES
(1, "admin@biblio.fr", "Administrateur", "1234", NULL, "ROLE_ADMINISTRATEUR"),
(2, "abonne@biblio.fr", "Abonné", "4321", NULL, "ROLE_ABONNE");

INSERT INTO pret (id, date_debut, date_fin_prevu, date_retour, statut, abonne_id, ouvrage_id, nbre_prolongations) VALUES
(1, "2020-07-10", "2020-08-07", NULL, "EN_COURS", 1, 1, 1),
(2, "2020-07-08", "2020-08-05", NULL, "EN_COURS", 2, 2, 0);