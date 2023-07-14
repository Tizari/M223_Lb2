# M223_Lb2

Dies ist das Projekt für die Lb2 des Moduls 223. Ziel dieses Projekts ist eine Webanwendung, mit der Benutzer Buchungen und Benutzerprofile verwalten können. Es bietet Funktionen zum Erstellen, Bearbeiten und Löschen von Buchungen sowie zur Verwaltung von Benutzerinformationen. Die Anwendung verwendet Spring Boot für das Backend und Thymeleaf für das Frontend. Die Benutzerauthentifizierung und Autorisierung werden mithilfe von JSON Web Tokens (JWT) für einen sicheren Zugriff implementiert. Das Projekt zielt darauf ab, eine benutzerfreundliche Benutzeroberfläche und robuste Funktionen zur Verwaltung von Buchungen und Benutzerprofilen bereitzustellen.

## Installation

1. Stelle sicher, dass du Java Development Kit (JDK) installiert hast.
2. Lade das Projekt von GitHub herunter oder klon es mit Git:

   git clone <repository-url>

3. Navigiere in das Projektverzeichnis:

   cd project-directory

4. Starte die Anwendung mit Maven:

   ./mvnw spring-boot:run

Die Anwendung wird auf http://localhost:8080 gestartet.

## Verwendung

Um das Projekt starten zu können, sollte man nach dem starten auf http://localhost:8080. hier kann man sich entweder einloggen oder registrieren.

## Konfiguration

- `application.properties`: In dieser Datei kannst du die Datenbankverbindung, den Serverport und andere Anwendungseinstellungen konfigurieren.
