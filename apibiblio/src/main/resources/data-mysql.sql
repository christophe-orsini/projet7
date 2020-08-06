INSERT INTO theme (id, nom) VALUES
(1, "Inconnu"),
(2, "Aventure pour tous"),
(3, "Aventure"),
(4, "Roman fantaisie"),
(5, "Enfants"),
(6, "Roman");

INSERT INTO ouvrage (id, auteur, nbre_exemplaire, resume, titre, theme_id, annee_edition) VALUES
(1, "Antoine de Saint-Exupéry", 1, "À la suite d’une panne de moteur, un aviateur se retrouve dans le désert du Sahara. Il rencontre le petit prince qui lui demande de lui dessiner un mouton.", "Le Petit Prince", 2, 1943),
(2, "Herman Melville", 4, "Moby Dick est ce chef-d'oeuvre total que tout le monde peut lire comme le plus formidable des romans d'aventures.", "Moby Dick", 3, 1851),
(3, "J.K. Rowling", 0, "Après la mort de ses parents, Harry Potter est recueilli par sa tante maternelle Pétunia et son oncle Vernon à l'âge d'un an.", "Harry Potter à l'école des sorciers", 4, 1997),
(4, "Charles Perrault", 1, "Un bûcheron et sa femme n'ont plus de quoi nourrir leurs sept garçons.", "Le petit poucet", 5, 1697),
(5, "Stendhal", 2, "Fils de charpentier, Julien Sorel est trop sensible et trop ambitieux pour suivre la carrière familiale dans la scierie d’une petite ville de province. ", "Le Rouge et le Noir", 6, 1830);

INSERT INTO utilisateur (id, email, nom, password, prenom, role) VALUES
(1, "admin@biblio.fr", "Administrateur", "$2a$10$eZPZMT/NtMtoJANyhmIIUuRXakpVesT0wNMC4NHLd2r9UF/sVNFxu", NULL, "ROLE_ADMINISTRATEUR"),
(2, "abonne@biblio.fr", "Abonné", "$2a$10$nxSnMr9s6rYmqCefnlWnse3R6FSMT8aYPVIeada9IAam2fNbfoW1a", NULL, "ROLE_ABONNE");

INSERT INTO pret (id, date_debut, date_fin_prevu, date_retour, statut, abonne_id, ouvrage_id, periodes, prolongations_possible) VALUES
(1, "2020-07-16", "2020-08-13", NULL, "EN_COURS", 1, 1, 1, 1),
(2, "2020-07-06", "2020-08-31", NULL, "PROLONGE", 2, 3, 2, 0),
(3, "2020-07-06", "2020-08-03", NULL, "EN_COURS", 2, 4, 1, 1),
(4, "2020-08-03", "2020-08-31", NULL, "EN_COURS", 2, 5, 1, 1);