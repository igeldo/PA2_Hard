# ABS
Dieses Programm dient der Verwaltung eines Antibiotika-Stewardship-Programms.
Es ermöglicht die systematische Erfassung und Überwachung von Antibiotikatherapien,
Infektionen und Behandlungshinweisen in einer zentralen Datenbank.
Die Besonderheit des Programms ist die Integration zweier KI's,
die automatisch die Therapien überprüft und Vorschläge zur Optimierung gibt,
um eine effektive und verantwortungsvolle Anwendung von Antibiotika sicherzustellen.
Alle Daten werden übersichtlich in einer Datenbank gespeichert,
um eine kontinuierliche Kontrolle und Auswertung zu gewährleisten.

## Verwendete Technologien
- Java 21
- Spring Ai 1.0.0-M1
- Postgresql 42.7.3
- lombok 1.18.30
- thymeleaf 3.3.2
- Docker

## Installation
Zunächst ist es erforderlich, das Projekt von GitHub zu klonen. Da das Repositorium privat ist, ist ein Hinzufügen als Co-Autor notwendig.
```bash
git clone https://github.com/KevinFhDortmund/Master-Studienarbeit.git
```
Der Start des Java-Programms erfordert zunächst die Erzeugung und den Start des Docker-Containers,
welcher die Postgres-Datenbank beinhaltet. Der folgende Befehl kann hierfür verwendet werden:
```bash
docker run --name postgres-container -e POSTGRES_USER=test -e POSTGRES_PASSWORD=123 -e POSTGRES_DB=medizin_db -d -p 5433:5432 postgres
```
Der Start der Anwendung kann mittels des folgenden Befehls initiiert werden:
```bash
mvn spring-boot:run
```
Als alternative Möglichkeit kann der Programmstart auch über die Entwicklungsumgebung erfolgen.

## Funktionen
Nach dem Start des Programms können die folgenden Seiten aufgerufen und Datenbankabfragen durchgeführt werden.
```bash
http://localhost:8080/behandlungshinweise/view
```
```bash
http://localhost:8080/erregertypen/view
```
```bash
http://localhost:8080/gesamtuebersicht/view
```
```bash
http://localhost:8080/infektionen/create
```
```bash
http://localhost:8080/infektionen/view
```
```bash
http://localhost:8080/medikamente/view
```
```bash
http://localhost:8080/therapien/view
```
```bash
http://localhost:8080/login
```