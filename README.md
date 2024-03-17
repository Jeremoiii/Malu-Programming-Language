# Malu Programming Language

![malu](https://github.com/GermanWarthog/Malu-Programming-Language/assets/79488475/6132c432-4a83-4639-a29f-18fb47ed508f)

--- 

## Informationen:

Dieses Projekt ist ein Versuch, eine eigene Programmiersprache zu erstellen und dazu einen Server-sided Editor zu erstellen.

Der Editor speichert die erstellten Dateien gesichert in einer Datenbank und kann diese auch wieder abrufen.

## Installation:
- Java 21
- Maven
- XAMPP

## Starten:

1. Starte XAMPP und starte Apache und MySQL
2. Installation der Datenbank und Tabellen [mehr Infos hier](./src/main/java/imports/import.sql)
   1. Datenbank erstellen
   2. Tabellen erstellen
3. `Main.java` starten

## Allgemeine Informationen:
- `/buffer` Alles was mit dem Speichern von großen Strings zutun hat. Wird primär für die Ausgabe der Programmiersprache verwendet.
- `/client` Ausgangspunkt für alle Interaktionen zwischen Client und Server. Alles, was von der Client an den Server muss, wird hier verarbeitet.
- `/editor` Alles was mit dem Editor zutun hat. Login, Registrieren, Listeners, CodeRunner, etc.
- `/frontend` Core der Programmiersprache. Hier befinden sich AST, Lexer und Parser
- `/generic` Alle NRW Klassen.
- `/imports` Alle Imports für die Datenbank.
- `/net` Parser für die NetEvents, da diese nur als String existieren. Unterteilung in Event-Name und Event-Inhalt bzw. Daten.
- `/runtime` Interpreter der Programmiersprache. Hier werden die RuntimeValues erstellt und verwaltet.
- `/scripts` Beispiel Script für die Verwendung der Malu Programmiersprache.
- `/server` Server-Client Kommunikation. Hier wird der Server gestartet und die Verbindung zum Client aufgebaut. Der Server verwaltet auch die Datenbank.
- `/shared` Alle Klassen die von Server und Client verwendet werden.
- `/utils` Alle Utilities die in mehreren Klassen verwendet werden.