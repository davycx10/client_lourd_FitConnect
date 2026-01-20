# client_lourd_FitConnect
partie client lourd pour l'administrateur


Contexte du projet : Basic-Fit Training – Application Admin (Java Swing)
L’application Admin Basic-Fit Training est un outil interne développé en Java (Swing), destiné au personnel de Basic-Fit. Elle permet de gérer les coachs et les adhérents de la plateforme web Basic-Fit Training, afin d’assurer un suivi sérieux, contrôlé et conforme aux attentes de l’enseigne.
L’admin n’est pas un coach ni un client : c’est un employé Basic-Fit qui supervise ce qui se passe sur la plateforme.

Rôle de l’Admin
L’admin a pour mission de :
Gérer les candidatures des coachs.
Contrôler les profils coachs et clients.
Supprimer des comptes en cas de résiliation ou de problème.
Vérifier la répartition des clients entre les coachs.
L’application Java Swing lui offre une interface simple pour effectuer ces actions sans passer par le site web public.

Gestion des coachs
Quand un coach remplit le formulaire « Postuler comme coach » sur le site :
Sa candidature remonte dans l’application Admin.
L’admin peut :
Consulter la candidature : nom, prénom, e-mail, spécialité (prise de masse, sèche, remise en forme, etc.), éventuellement quelques infos de base sur son expérience.
Accepter la candidature : le coach devient alors coach actif sur la plateforme et peut se connecter à son espace coach.
Refuser la candidature : le coach n’apparaît pas dans la liste des coachs et ne peut pas accéder à l’espace coach.
Une fois accepté, l’admin peut ensuite :
Voir la liste des coachs actifs.
Supprimer un coach (fin de collaboration, problème, etc.).
Voir le nombre de clients associés à chaque coach pour vérifier la charge ou repérer des anomalies.

Gestion des clients (adhérents)
L’admin peut également :
Consulter le profil d’un client :
nom, prénom, e-mail, poids, taille, objectif (prise de masse, sèche, remise en forme), disponibilités, coach assigné.
Supprimer un client :
par exemple lorsqu’un adhérent arrête son abonnement ou demande la suppression de son compte.
Vérifier les relations coach ↔ client :
voir quels clients sont suivis par quel coach.

Technologie et intégration
L’application Admin en Java Swing est connectée à la même base de données que le site web PHP.
Ainsi :
Quand un admin accepte un coach, celui-ci peut se connecter sur le site.
Quand un admin supprime un coach ou un client, cela se reflète directement sur la plateforme web.


