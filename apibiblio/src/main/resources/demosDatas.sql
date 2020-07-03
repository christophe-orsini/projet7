SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

INSERT INTO `theme` (`id`, `libelle`) VALUES
(1, 'Aventure pour tous');

INSERT INTO `ouvrage` (`id`, `annee_parution`, `auteur`, `nbre_exemplaire`, `resume`, `titre`, `theme_id`) VALUES
(1, 1943, 'Antoine de Saint-Exupéry', 2, 'À la suite d’une panne de moteur, un aviateur se retrouve dans le désert du Sahara. Il rencontre le petit prince qui lui demande de lui dessiner un mouton.', 'Le Petit Prince', 1);

INSERT INTO `utilisateur` (`id`, `email`, `nom`, `password`, `prenom`, `role`) VALUES
(1, 'admin@biblio.fr', 'Administrateur', '1234', NULL, 'ROLE_ADMINISTRATEUR');

INSERT INTO `pret` (`id`, `date_debut`, `date_fin_prevu`, `date_retour`, `statut`, `abonne_id`, `ouvrage_id`) VALUES
(1, '2020-07-02 10:00:00.000000', '2020-07-30 10:00:00.000000', NULL, 'EN_COURS', 1, 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
