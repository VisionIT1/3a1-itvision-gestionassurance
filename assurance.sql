-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  Dim 10 fév. 2019 à 18:23
-- Version du serveur :  10.1.37-MariaDB
-- Version de PHP :  5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `assurance`
--

-- --------------------------------------------------------

--
-- Structure de la table `assure_entreprise`
--

CREATE TABLE `assure_entreprise` (
  `id` int(11) NOT NULL,
  `nomEntr` varchar(20) DEFAULT NULL,
  `emailEntr` varchar(50) DEFAULT NULL,
  `numtelEntr` varchar(8) DEFAULT NULL,
  `adresseEntr` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `assure_particulier`
--

CREATE TABLE `assure_particulier` (
  `id` int(11) NOT NULL,
  `nom` varchar(20) DEFAULT NULL,
  `prenom` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `numtel` varchar(8) DEFAULT NULL,
  `adresse` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `contrat`
--

CREATE TABLE `contrat` (
  `id` int(11) NOT NULL,
  `nom` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `description` text COLLATE utf8_unicode_ci NOT NULL,
  `id_assure` int(11) NOT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date_debut` datetime DEFAULT NULL,
  `date_echeance` datetime DEFAULT NULL,
  `etat` int(11) NOT NULL,
  `prime` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `expert`
--

CREATE TABLE `expert` (
  `idEx` int(11) NOT NULL,
  `cinEX` int(11) NOT NULL,
  `faxEx` int(11) NOT NULL,
  `nomEx` varchar(20) NOT NULL,
  `prenomEx` varchar(20) NOT NULL,
  `mailEx` varchar(60) DEFAULT NULL,
  `numeroEx` int(11) NOT NULL,
  `adresseEx` varchar(20) NOT NULL,
  `etatEx` varchar(20) NOT NULL,
  `descriptionEx` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `fos_user`
--

CREATE TABLE `fos_user` (
  `id` int(11) NOT NULL,
  `username` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `fos_user`
--

INSERT INTO `fos_user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`) VALUES
(1, 'kais', 'kais', 'kais.bettaieb@esprit.tn', 'kais.bettaieb@esprit.tn', 1, NULL, '$2y$13$l1PudQiz5UQEGRbLseZBBeqrbkQe8Cyy2A9PUxpheenXPHgx4cAOW', '2019-02-06 19:11:01', NULL, NULL, 'a:0:{}');

-- --------------------------------------------------------

--
-- Structure de la table `garantie`
--

CREATE TABLE `garantie` (
  `id_garantie` int(11) NOT NULL,
  `lib` varchar(500) NOT NULL,
  `prime` decimal(10,0) NOT NULL,
  `categorie` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `habitation`
--

CREATE TABLE `habitation` (
  `id_habitat` int(11) NOT NULL,
  `baieVitre` int(11) NOT NULL,
  `nbPieces` int(11) NOT NULL,
  `valmobile` int(11) NOT NULL,
  `sysAlarm` int(11) NOT NULL,
  `natureLocal` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `ligne_garantie`
--

CREATE TABLE `ligne_garantie` (
  `idgh` int(11) NOT NULL,
  `id_type` int(11) NOT NULL,
  `id_garantie` int(11) NOT NULL,
  `prime` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `marque`
--

CREATE TABLE `marque` (
  `id_marque` int(11) NOT NULL,
  `libelle` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `offrebonconducteur`
--

CREATE TABLE `offrebonconducteur` (
  `idOffre` int(11) NOT NULL,
  `libOffre` varchar(300) NOT NULL,
  `dateDebutOffre` date NOT NULL,
  `dateFinOffre` date NOT NULL,
  `pourcentageReduction` int(11) NOT NULL,
  `numReglement` int(11) NOT NULL,
  `nbrAnnee` int(11) NOT NULL,
  `descripOffre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `offrefidelite`
--

CREATE TABLE `offrefidelite` (
  `idOffre` int(11) NOT NULL,
  `libOffre` varchar(30) NOT NULL,
  `dateDebutOffre` date NOT NULL,
  `dateFinOffre` date NOT NULL,
  `pourcentageReduction` int(11) NOT NULL,
  `nbrContratMin` int(11) NOT NULL,
  `descripOffre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `offrepartenaire`
--

CREATE TABLE `offrepartenaire` (
  `idOffre` int(11) NOT NULL,
  `libOffre` varchar(30) NOT NULL,
  `dateDebutOffre` date NOT NULL,
  `dateFinOffre` date NOT NULL,
  `pourcentageReduction` int(11) NOT NULL,
  `partenaire` varchar(30) NOT NULL,
  `descripOffre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE `reclamation` (
  `idR` int(11) NOT NULL,
  `typeRec` varchar(50) NOT NULL,
  `descRec` varchar(100) NOT NULL,
  `dateSaisiRec` date NOT NULL,
  `idCli` int(11) NOT NULL,
  `traitementRec` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `reglement`
--

CREATE TABLE `reglement` (
  `code_regl` int(11) NOT NULL,
  `date_regl` date DEFAULT NULL,
  `frais_expert` int(11) DEFAULT NULL,
  `mode_regl` int(11) DEFAULT NULL,
  `montant_regl` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `reparateur`
--

CREATE TABLE `reparateur` (
  `idRep` int(11) NOT NULL,
  `cinRep` int(11) NOT NULL,
  `faxRep` int(11) NOT NULL,
  `nomRep` varchar(20) NOT NULL,
  `prenomRep` varchar(20) NOT NULL,
  `mailrep` varchar(60) DEFAULT NULL,
  `numeroRep` int(11) NOT NULL,
  `adresseRep` varchar(20) NOT NULL,
  `etatRep` varchar(20) NOT NULL,
  `descriptionRep` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `sinistre`
--

CREATE TABLE `sinistre` (
  `code_sinistre` int(11) NOT NULL,
  `date_declaration` date DEFAULT NULL,
  `date_sinistre` date DEFAULT NULL,
  `lieu_sinistre` varchar(50) DEFAULT NULL,
  `numero_sinistre` int(11) DEFAULT NULL,
  `dommage_mat` int(11) DEFAULT NULL,
  `dommage_corp` int(11) DEFAULT NULL,
  `code_assureur` int(11) DEFAULT NULL,
  `description` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `vehicule`
--

CREATE TABLE `vehicule` (
  `id_vehicule` int(11) NOT NULL,
  `puissance` int(11) NOT NULL,
  `valNeuf` int(11) NOT NULL,
  `valVenale` int(11) NOT NULL,
  `anneConst` int(11) NOT NULL,
  `id_marque` int(11) NOT NULL,
  `immat` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `voyage`
--

CREATE TABLE `voyage` (
  `id_voyage` int(11) NOT NULL,
  `destination` varchar(100) NOT NULL,
  `dureeSejour` int(11) NOT NULL,
  `trancheAge` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `assure_entreprise`
--
ALTER TABLE `assure_entreprise`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `assure_particulier`
--
ALTER TABLE `assure_particulier`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `contrat`
--
ALTER TABLE `contrat`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `expert`
--
ALTER TABLE `expert`
  ADD PRIMARY KEY (`idEx`);

--
-- Index pour la table `fos_user`
--
ALTER TABLE `fos_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_957A647992FC23A8` (`username_canonical`),
  ADD UNIQUE KEY `UNIQ_957A6479A0D96FBF` (`email_canonical`),
  ADD UNIQUE KEY `UNIQ_957A6479C05FB297` (`confirmation_token`);

--
-- Index pour la table `garantie`
--
ALTER TABLE `garantie`
  ADD PRIMARY KEY (`id_garantie`);

--
-- Index pour la table `habitation`
--
ALTER TABLE `habitation`
  ADD PRIMARY KEY (`id_habitat`);

--
-- Index pour la table `ligne_garantie`
--
ALTER TABLE `ligne_garantie`
  ADD PRIMARY KEY (`idgh`);

--
-- Index pour la table `marque`
--
ALTER TABLE `marque`
  ADD PRIMARY KEY (`id_marque`);

--
-- Index pour la table `offrebonconducteur`
--
ALTER TABLE `offrebonconducteur`
  ADD PRIMARY KEY (`idOffre`);

--
-- Index pour la table `offrefidelite`
--
ALTER TABLE `offrefidelite`
  ADD PRIMARY KEY (`idOffre`);

--
-- Index pour la table `offrepartenaire`
--
ALTER TABLE `offrepartenaire`
  ADD PRIMARY KEY (`idOffre`);

--
-- Index pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`idR`);

--
-- Index pour la table `reparateur`
--
ALTER TABLE `reparateur`
  ADD PRIMARY KEY (`idRep`);

--
-- Index pour la table `vehicule`
--
ALTER TABLE `vehicule`
  ADD PRIMARY KEY (`id_vehicule`);

--
-- Index pour la table `voyage`
--
ALTER TABLE `voyage`
  ADD PRIMARY KEY (`id_voyage`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `contrat`
--
ALTER TABLE `contrat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `fos_user`
--
ALTER TABLE `fos_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `garantie`
--
ALTER TABLE `garantie`
  MODIFY `id_garantie` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `habitation`
--
ALTER TABLE `habitation`
  MODIFY `id_habitat` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `ligne_garantie`
--
ALTER TABLE `ligne_garantie`
  MODIFY `idgh` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `marque`
--
ALTER TABLE `marque`
  MODIFY `id_marque` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `offrebonconducteur`
--
ALTER TABLE `offrebonconducteur`
  MODIFY `idOffre` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `offrefidelite`
--
ALTER TABLE `offrefidelite`
  MODIFY `idOffre` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `offrepartenaire`
--
ALTER TABLE `offrepartenaire`
  MODIFY `idOffre` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `idR` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `vehicule`
--
ALTER TABLE `vehicule`
  MODIFY `id_vehicule` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `voyage`
--
ALTER TABLE `voyage`
  MODIFY `id_voyage` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
