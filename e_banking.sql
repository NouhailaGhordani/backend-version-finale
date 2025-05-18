-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3307
-- Généré le : lun. 19 mai 2025 à 01:04
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `e_banking`
--

-- --------------------------------------------------------

--
-- Structure de la table `abonne`
--

CREATE TABLE `abonne` (
  `id` bigint(20) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `type_piece_identite` varchar(255) DEFAULT NULL,
  `numero_piece_identite` varchar(255) NOT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `langue` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `code_agence` varchar(255) DEFAULT NULL,
  `sexe` varchar(255) DEFAULT NULL,
  `statut` varchar(255) DEFAULT NULL,
  `date_creation` varchar(255) DEFAULT NULL,
  `numero_abonne` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPRESSED;

--
-- Déchargement des données de la table `abonne`
--

INSERT INTO `abonne` (`id`, `nom`, `prenom`, `type_piece_identite`, `numero_piece_identite`, `telephone`, `email`, `langue`, `created_at`, `updated_at`, `code_agence`, `sexe`, `statut`, `date_creation`, `numero_abonne`) VALUES
(20, 'kaba', 'Imane', 'CIN', 'AB123456', '0611223344', NULL, NULL, '2025-05-18 15:24:07', '2025-05-18 20:07:05', 'AGC001', NULL, 'Actif', '2025-05-18', 'ABN-948DBE59'),
(22, 'Kabab', 'khaoula', 'PERMIS', 'AQ77777', '0677777772', 'kabab.fst@uhp.ac.ma', '', '2025-05-18 15:58:38', '2025-05-18 15:58:38', '00530', 'F', NULL, '2025-05-18', 'ABN-E3FB43F4');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `id` bigint(20) NOT NULL,
  `intitule_client` varchar(100) DEFAULT NULL,
  `numero_client` varchar(20) DEFAULT NULL,
  `segment_client` varchar(50) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `charge_client` varchar(100) DEFAULT NULL,
  `date_creation` date DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id`, `intitule_client`, `numero_client`, `segment_client`, `adresse`, `charge_client`, `date_creation`, `email`, `telephone`) VALUES
(5, 'tecnopark', 'CLT2025863114', 'Entreprise', '123 Rue dakhla', 'Maryam', '2025-05-17', 'contact@556.com', '0600067893'),
(6, 'ZTE', 'CLT2025613836', 'entreprise', 'california', 'AHMED', '2025-05-17', 'contact@gmail.com', '0668067899'),
(12, 'citafix', 'CLT2025167613', 'entreprise', 'settat', 'nouhaila el ghordani', '2025-05-18', 'cfx@gmail.ma', '0987453631'),
(13, 'IBM', 'CLT2025693610', 'Enreprise', 'casa', 'nouhaila el ghordani', '2025-05-18', 'ibm@mail.com', '0678546327');

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE `compte` (
  `id` bigint(20) NOT NULL,
  `code_agence` varchar(5) DEFAULT NULL,
  `consultation_autorisee` bit(1) NOT NULL,
  `demande_service_autorisee` bit(1) NOT NULL,
  `devise` varchar(3) DEFAULT NULL,
  `intitule` varchar(100) DEFAULT NULL,
  `numero_compte` varchar(20) DEFAULT NULL,
  `remise_ordre_autorisee` bit(1) NOT NULL,
  `virement_autorise` bit(1) NOT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  `solde` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `contrat`
--

CREATE TABLE `contrat` (
  `id` bigint(20) NOT NULL,
  `code_agence` varchar(5) NOT NULL,
  `date_creation` date DEFAULT NULL,
  `date_souscription` date DEFAULT NULL,
  `offre_commerciale` varchar(100) NOT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  `compte_facturation_id` bigint(20) DEFAULT NULL,
  `numero_contrat` varchar(20) NOT NULL,
  `statut` varchar(20) DEFAULT 'desactive',
  `charge_client` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5qv5tcfmwu2mli0brm27y13gl` (`client_id`);

--
-- Index pour la table `contrat`
--
ALTER TABLE `contrat`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKat9lypkckqib2kk4sj9u4iw9l` (`client_id`),
  ADD KEY `FKbyb3iw0m5g3jewq52k3t0ss9g` (`compte_facturation_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `compte`
--
ALTER TABLE `compte`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `contrat`
--
ALTER TABLE `contrat`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `compte`
--
ALTER TABLE `compte`
  ADD CONSTRAINT `FK5qv5tcfmwu2mli0brm27y13gl` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`);

--
-- Contraintes pour la table `contrat`
--
ALTER TABLE `contrat`
  ADD CONSTRAINT `FKat9lypkckqib2kk4sj9u4iw9l` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `FKbyb3iw0m5g3jewq52k3t0ss9g` FOREIGN KEY (`compte_facturation_id`) REFERENCES `compte` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
