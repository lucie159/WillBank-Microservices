# WillBank - Architecture Microservices

Ce projet est une implémentation éducative d'un système bancaire basé sur une architecture **Microservices** avec Spring Boot. L'objectif est de migrer une application bancaire monolithique vers une architecture distribuée, résiliente et scalable.

## Architecture Globale

Le système est composé de plusieurs services autonomes communiquant entre eux.

| Service | Rôle | Technologie | Port | Base de Données |
| :--- | :--- | :--- | :--- | :--- |
| **Discovery Service** | Annuaire des services (Service Registry) | Eureka Server | `8761` | N/A |
| **Gateway Service** | Point d'entrée unique & Routage | Spring Cloud Gateway | `8888` | N/A |
| **Client Service** | Gestion des clients (KYC) | Spring Boot / Data JPA | `8081` | PostgreSQL (`client_db`) |
| **Account Service** | Gestion des comptes bancaires | Spring Boot / Data JPA | `8082` | PostgreSQL (`account_db`) |
| **Transaction Service** | Gestion des virements | Spring Boot / RabbitMQ | `8083` | PostgreSQL (`transaction_db`) |
| **Notification Service** | Envoi d'emails/push | Spring Boot / Firebase | `8084` | PostgreSQL (`notification_db`) |

### Infrastructure Technique
*   **Docker & Docker Compose** : Pour l'orchestration des bases de données et du broker de messages.
*   **PostgreSQL** : Une base de données isolée par microservice (Pattern *Database per Service*).
*   **RabbitMQ** : Pour la communication asynchrone (événements).
*   **GitHub Actions** : Pipeline CI/CD pour la compilation automatique.

---

## Comment lancer le projet ?

### 1. Prérequis
Assurez-vous d'avoir installé :
*   **Java 17** (JDK)
*   **Docker Desktop** (lancé)
*   **Maven** (ou utiliser le wrapper inclus)
*   **Git**

### 2. Démarrer l'infrastructure (Docker)
Avant de lancer le code Java, il faut démarrer les bases de données et RabbitMQ.


À la racine du projet
docker-compose up -d
Vérification : Assurez-vous que les 4 conteneurs PostgreSQL et le conteneur RabbitMQ sont bien verts dans Docker Desktop.
### 3. Démarrer les Microservices (Ordre conseillé)
Vous pouvez lancer les services via votre IDE (IntelliJ / VS Code) ou via le terminal.
L'ordre de démarrage est important :
Discovery Service : Attendre qu'il soit démarré.
URL Dashboard : http://localhost:8761
Gateway Service : Le point d'entrée.
Les Services Métiers (Client, Account...).
### 4. Tester l'application
Via le Navigateur (Lecture seule)


Liste des clients : http://localhost:8081/api/clients
Via la Gateway : http://localhost:8888/client-service/api/clients
Via le Terminal (Création de données)


Exemple pour créer un client (via curl) :

curl -X POST http://localhost:8081/api/clients \
-H "Content-Type: application/json" \
-d "{\"nom\":\"Kungne\", \"prenom\":\"Willy\", \"email\":\"willy@test.com\", \"telephone\":\"690000000\", \"adresse\":\"Yaounde\"}"


## Structure du Projet

WillBank-Microservices/
├── docker-compose.yml # Configuration de l'infrastructure (DB, RabbitMQ)
├── discovery-service/ # Serveur Eureka
├── gateway-service/ # API Gateway (routeur)
├── client-service/ # Gestion des clients
├── account-service/ # Gestion des comptes (en cours)
└── .github/workflows/ # Pipelines CI/CD


## Choix Techniques & Apprentissage
Ce projet met en œuvre plusieurs patterns de conception :
Architecture en couches : Controller -> Service -> Repository -> Entity.
DTO Pattern : Séparation stricte entre les Entités (BDD) et les objets exposés (API).
OpenFeign : Communication synchrone entre microservices (à venir).
Event-Driven : Communication asynchrone via RabbitMQ (à venir).