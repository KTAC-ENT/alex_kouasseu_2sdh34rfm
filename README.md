# alex_kouasseu_2sdh34rfm

### Créez une structure de base de données en fonction des exigences commerciales suivantes.

- Gestion scolaire qui contient la structure des étudiants
- Les étudiants sont liés à une seule classe
- Un seul enseignant par classe
- L'étudiant doit contenir les informations suivantes: ID, Prénom, Nom de famille
- L'enseignant doit contenir les informations suivantes: ID, Prénom, Nom de famille
- La classe doit contenir les informations suivantes: ID, nom

### De plus, créez une API RESTful en utilisant Java Spring Boot avec les éléments suivants :

- Sécuriser l'API après la connexion en utilisant un jeton JWT généré.
- Obtenir la liste des étudiants avec les éléments suivants :
  - Filtres : Nom de la classe et/ou Nom complet de l'enseignant
  - Tous les étudiants de la liste seront retournés en cas d'absence de valeur de filtres
  - Paginé
- Tests unitaires


## Stack et outils utilisés
- Outil de construction : **Maven**
- Language et version : **Java 17**
- Spring boot version : **3.0.2** 
- Sécurisation des API REST : **Token JWT via Spring Security**
- SGBD : **H2**
- ORM: **Hibernate**
- Migration de données : **Liquibase**

# Structure de la base de données
La structure de notre base de données sera comme suit, pour pemettre une certaine évolutivité de l'application et d'avoir un couplage faible entre nos entités
![](Anywr.png "Diagramme de classe")

Outils utilisé: [Creately](https://app.creately.com/)

# Sécurisation de nos API par un Token JWT avec Spring Security
Le Schéma suiviant inspiré de celui qu'on peut trouver sur le site [Aliboucoding.com](https://aliboucoding.com/p/securing-your-spring-boot-3-0-applications-with-json-web-tokens-jwt) présente comment nos API seront sécurisées par un token JWT
![](How_JWT_Secure_our_API_H2.png "How JWT Secure our API")

# Quelques informations utiles


## BD
- H2
- PostgresSql (Avec des données de test)

## Construction et Démarrage de l'application

### Construction

``./mvnw clean package install``

### Démarrage

- **Profile postgres**: exécutez ``./mvnw spring-boot:run -Dspring-boot.run.profiles=postgres`` à la racine du projet
    - **NB:** Créer une base de données **anywr** sur votre SGBD posgres, avec votre credential, les mettre à jours dans le fichier application-postgres.yml
    - Ce SGBD est utilisé pour vous permettre d'insérer automatiquement les données de test avec liquibase dans la base de données au deuxième démarrage de l'application, et après avoir changé la config **LIQUIBASE_ENABLE** à true
- **Profile H2**: exécutez ``./mvnw spring-boot:run -Dspring-boot.run.profiles=h2`` à la racine du projet

## API Docs
  [Open API Link (Localhost)](http://localhost:8008/swagger-ui/index.html)

## Variable D'environnement
- **DB_URL** : Url de la base de données
- **DB_USERNAME** : Nom d'utilisateur de la base de données
- **DB_PASSWORD** : Password de l'utilisateur de la base de données
- **LIQUIBASE_ENABLE** : Activer ou non la prise en charge de Liquibase. (true, false)
- **LIQUIBASE_DROOP_FIRST** : Indique s'il faut d'abord supprimer le schéma de la base de données (true, false)
- **TOKEN_KEY**: Clé utilisée pour générer le token encodée en B64
- **TOKEN_EXPIRATION**: Durée d'expiration du token (en miliseconde)