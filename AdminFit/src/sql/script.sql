-- 1. BASE DE DONNÉES
CREATE DATABASE IF NOT EXISTS fitconnect 
    CHARACTER SET utf8mb4 
    COLLATE utf8mb4_unicode_ci;
USE fitconnect;

-- 2. TABLE ADMIN
CREATE TABLE admin (
    id_admin INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    mot_de_passe VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- 3. TABLE CANDIDATURE (remplace totalement la table candidat)
CREATE TABLE candidature (
    id_candidature INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    adresse VARCHAR(255) NOT NULL,
    basic_fit TINYINT(1) NOT NULL,
    specialite ENUM('prise_masse','seche','remise_forme') NOT NULL,
    experience INT NOT NULL,
    cv_pdf VARCHAR(255) NOT NULL,
    linkedin VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    statut ENUM('en_attente','valide','refuse') DEFAULT 'en_attente',
    date_candidature TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_decision TIMESTAMP NULL,
    id_admin INT NULL,
    CONSTRAINT fk_candidature_admin
        FOREIGN KEY (id_admin) REFERENCES admin(id_admin)
        ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 4. TABLE COACH (coach validé uniquement)
CREATE TABLE coach (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    adresse VARCHAR(255) NOT NULL,
    basic_fit TINYINT(1) NOT NULL,
    specialite ENUM('prise_masse','seche','remise_forme') NOT NULL,
    experience INT NOT NULL,
    password VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 5. TABLE DES CLIENTS -----------------
CREATE TABLE client (
    id_client INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    mail VARCHAR(100) NOT NULL UNIQUE,
    mot_de_passe   VARCHAR(255) NOT NULL,
    poids INT NOT NULL,
    taille INT NOT NULL,
    genre ENUM('homme','femme','autre','prefere_pas','croissant') NOT NULL, 
    basic_fit TINYINT(1) DEFAULT 0,
    objectif VARCHAR(50) NOT NULL, 
    dispo_jours TEXT NULL, 
    dispo_creneaux VARCHAR(255) NULL, 
    motivation TEXT NOT NULL,
    date_inscription TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_coach INT NULL,
    CONSTRAINT fk_client_coach
        FOREIGN KEY (id_coach) REFERENCES coach(id)
        ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 6. CRÉATION DE LA TABLE PROGRAMME ---------------
-- (Sert de référence pour stocker les programmes en dur plus tard)
CREATE TABLE IF NOT EXISTS programme (
    id_programme INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    type VARCHAR(50) NOT NULL
) ENGINE=InnoDB;

-- 1. On vide la table pour effacer les anciennes versions
TRUNCATE TABLE programme;

-- 2. On insère les 3 programmes COMPLETS
-- Le HTML est stocké directement pour s'afficher proprement sur le site

INSERT INTO programme (nom, type, description) VALUES 
(
    'Programme Hypertrophie (Prise de Masse)', 
    'prise_masse', 
    '
    <p style="margin-bottom:20px;"><strong>Objectif :</strong> Maximiser le volume musculaire via un split 4 jours. Charges lourdes et récupération complète.</p>
    
    <h4 style="color:#fe7000; margin-top:20px; border-bottom:1px solid #eee; padding-bottom:10px;">SÉANCE 1 : PECTORAUX & TRICEPS</h4>
    <table class="custom-table">
        <thead><tr><th>Exercice</th><th>Séries</th><th>Reps</th><th>Repos</th></tr></thead>
        <tbody>
            <tr><td>Développé Couché Barre</td><td>4</td><td>8-10</td><td>2 min</td></tr>
            <tr><td>Développé Incliné Haltères</td><td>4</td><td>10-12</td><td>1 min 30</td></tr>
            <tr><td>Écarté Poulie Vis-à-vis</td><td>3</td><td>15</td><td>1 min</td></tr>
            <tr><td>Barre au front (Triceps)</td><td>4</td><td>10-12</td><td>1 min 30</td></tr>
            <tr><td>Extension Poulie Corde</td><td>3</td><td>12-15</td><td>1 min</td></tr>
        </tbody>
    </table>

    <h4 style="color:#fe7000; margin-top:30px; border-bottom:1px solid #eee; padding-bottom:10px;">SÉANCE 2 : DOS & BICEPS</h4>
    <table class="custom-table">
        <thead><tr><th>Exercice</th><th>Séries</th><th>Reps</th><th>Repos</th></tr></thead>
        <tbody>
            <tr><td>Tractions (Lestées si besoin)</td><td>4</td><td>8-10</td><td>2 min</td></tr>
            <tr><td>Rowing Barre Buste penché</td><td>4</td><td>10</td><td>1 min 30</td></tr>
            <tr><td>Tirage Vertical Prise serrée</td><td>3</td><td>12</td><td>1 min 30</td></tr>
            <tr><td>Curl Barre EZ</td><td>4</td><td>10-12</td><td>1 min 30</td></tr>
            <tr><td>Curl Marteau Haltères</td><td>3</td><td>12</td><td>1 min</td></tr>
        </tbody>
    </table>

    <h4 style="color:#fe7000; margin-top:30px; border-bottom:1px solid #eee; padding-bottom:10px;">SÉANCE 3 : JAMBES COMPLÈTES</h4>
    <table class="custom-table">
        <thead><tr><th>Exercice</th><th>Séries</th><th>Reps</th><th>Repos</th></tr></thead>
        <tbody>
            <tr><td>Squat Barre</td><td>4</td><td>6-8</td><td>3 min</td></tr>
            <tr><td>Presse à cuisses</td><td>4</td><td>10-12</td><td>2 min</td></tr>
            <tr><td>Leg Extension</td><td>3</td><td>15</td><td>1 min</td></tr>
            <tr><td>Soulevé de terre Roumain</td><td>4</td><td>10</td><td>2 min</td></tr>
            <tr><td>Mollets Debout</td><td>4</td><td>15</td><td>1 min</td></tr>
        </tbody>
    </table>

    <h4 style="color:#fe7000; margin-top:30px; border-bottom:1px solid #eee; padding-bottom:10px;">SÉANCE 4 : ÉPAULES & RAPPEL</h4>
    <table class="custom-table">
        <thead><tr><th>Exercice</th><th>Séries</th><th>Reps</th><th>Repos</th></tr></thead>
        <tbody>
            <tr><td>Développé Militaire</td><td>4</td><td>8-10</td><td>2 min</td></tr>
            <tr><td>Élévations Latérales</td><td>4</td><td>15</td><td>1 min</td></tr>
            <tr><td>Oiseau (Arrière d\'épaule)</td><td>4</td><td>15</td><td>1 min</td></tr>
            <tr><td>Shrugs (Trapèzes)</td><td>4</td><td>12</td><td>1 min</td></tr>
        </tbody>
    </table>
    '
),
(
    'Programme Sèche & Définition', 
    'seche', 
    '
    <p style="margin-bottom:20px;"><strong>Objectif :</strong> Brûler les graisses en maintenant la masse musculaire. Intensité élevée, temps de repos courts.</p>

    <h4 style="color:#fe7000; margin-top:20px; border-bottom:1px solid #eee; padding-bottom:10px;">SÉANCE 1 : HAUT DU CORPS + HIIT</h4>
    <table class="custom-table">
        <thead><tr><th>Exercice</th><th>Séries</th><th>Reps</th><th>Repos</th></tr></thead>
        <tbody>
            <tr><td>Développé Couché Haltères</td><td>4</td><td>12-15</td><td>45 sec</td></tr>
            <tr><td>Tirage Poitrine Large</td><td>4</td><td>12-15</td><td>45 sec</td></tr>
            <tr><td>Superset : Elev. Latérales / Curl</td><td>3</td><td>15</td><td>30 sec</td></tr>
            <tr><td>Dips</td><td>3</td><td>Max</td><td>45 sec</td></tr>
            <tr><td><strong>FINISHER :</strong> Tapis de course</td><td>15 min</td><td>HIIT</td><td>-</td></tr>
        </tbody>
    </table>

    <h4 style="color:#fe7000; margin-top:30px; border-bottom:1px solid #eee; padding-bottom:10px;">SÉANCE 2 : BAS DU CORPS + ABDOS</h4>
    <table class="custom-table">
        <thead><tr><th>Exercice</th><th>Séries</th><th>Reps</th><th>Repos</th></tr></thead>
        <tbody>
            <tr><td>Fentes Marchées (Lestées)</td><td>4</td><td>20 pas</td><td>45 sec</td></tr>
            <tr><td>Goblet Squat</td><td>4</td><td>15</td><td>45 sec</td></tr>
            <tr><td>Leg Curl Ischios</td><td>3</td><td>15</td><td>30 sec</td></tr>
            <tr><td>Mollets assis</td><td>4</td><td>20</td><td>30 sec</td></tr>
            <tr><td>Circuit Abdos (Crunch/Gainage)</td><td>4</td><td>Circuit</td><td>1 min</td></tr>
        </tbody>
    </table>

    <h4 style="color:#fe7000; margin-top:30px; border-bottom:1px solid #eee; padding-bottom:10px;">SÉANCE 3 : FULL BODY METABOLIC</h4>
    <table class="custom-table">
        <thead><tr><th>Exercice</th><th>Séries</th><th>Reps</th><th>Repos</th></tr></thead>
        <tbody>
            <tr><td>Burpees</td><td>4</td><td>15</td><td>30 sec</td></tr>
            <tr><td>Thrusters (Squat + Press)</td><td>4</td><td>12</td><td>45 sec</td></tr>
            <tr><td>Kettlebell Swing</td><td>4</td><td>20</td><td>45 sec</td></tr>
            <tr><td>Corde à sauter</td><td>5</td><td>1 min</td><td>30 sec</td></tr>
            <tr><td>Rameur</td><td>10 min</td><td>Intense</td><td>-</td></tr>
        </tbody>
    </table>
    '
),
(
    'Programme Remise en Forme (Santé)', 
    'remise_forme', 
    '
    <p style="margin-bottom:20px;"><strong>Objectif :</strong> Reprendre une activité physique saine et globale. 3 fois par semaine (Lundi / Mercredi / Vendredi).</p>

    <h4 style="color:#fe7000; margin-top:20px; border-bottom:1px solid #eee; padding-bottom:10px;">SÉANCE TYPE (FULL BODY)</h4>
    <p><em>Effectuez ce circuit complet à chaque séance. Augmentez les poids progressivement.</em></p>
    <table class="custom-table">
        <thead><tr><th>Ordre</th><th>Exercice</th><th>Séries</th><th>Reps</th><th>Repos</th></tr></thead>
        <tbody>
            <tr><td>1. Échauffement</td><td>Vélo ou Elliptique</td><td>10 min</td><td>Léger</td><td>-</td></tr>
            <tr><td>2. Jambes</td><td>Squat Poids du corps</td><td>3</td><td>15</td><td>1 min</td></tr>
            <tr><td>3. Poussée</td><td>Pompes (sur genoux si besoin)</td><td>3</td><td>10-12</td><td>1 min</td></tr>
            <tr><td>4. Tirage</td><td>Tirage Horizontal Machine</td><td>3</td><td>15</td><td>1 min</td></tr>
            <tr><td>5. Lombaires</td><td>Extension au banc</td><td>3</td><td>15</td><td>1 min</td></tr>
            <tr><td>6. Abdos</td><td>Gainage frontal</td><td>3</td><td>30-45s</td><td>1 min</td></tr>
            <tr><td>7. Retour au calme</td><td>Marche inclinée</td><td>15 min</td><td>Moyen</td><td>-</td></tr>
        </tbody>
    </table>
    '
);


-- ==========================================
-- DONNÉES DE TEST (FIXTURES)
-- ==========================================

-- ============================
-- 🔐 ADMIN DE TEST
-- ============================
INSERT INTO admin (nom, prenom, email, mot_de_passe)
VALUES (
    'Toto',
    'test',
    'admin@test.com',
    'test123'   -- mot de passe en clair pour test
);

-- ============================
-- 🏋️ COACH VALIDÉ DE TEST
-- ============================
INSERT INTO coach (nom, prenom, email, adresse, basic_fit, specialite, experience, password)
VALUES (
    'Durand',
    'Thomas',
    'coach@test.com',
    'Paris 75015',
    1,
    'prise_masse',
    5,
    'test123'   -- mot de passe en clair pour test
);

-- ============================
-- 👤 CLIENT DE TEST
-- ============================
INSERT INTO client (nom, prenom, mail, mot_de_passe, poids, taille, genre, basic_fit, objectif, dispo_jours, dispo_creneaux, motivation, id_coach)
VALUES (
    'Leroy',
    'Camille',
    'cliente@test.com',
    'test123',   -- mot de passe en clair pour test
    70,
    170,
    'femme',
    1,
    'Perte de poids',
    'Lundi,Mardi,Jeudi',
    '18h-20h',
    'Je veux reprendre ma santé en main',
    1  -- lié au coach Thomas
);
     