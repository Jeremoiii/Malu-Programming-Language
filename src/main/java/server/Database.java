package server;

import generic.SQL.DatabaseConnector;
import generic.SQL.QueryResult;
import utils.ObjectPrinter;

public class Database {
    private final DatabaseConnector db;

    public Database() {
        db = new DatabaseConnector("127.0.0.1", 3306, "malu", "root", "");

        // Erstellen der Datenbank und Tabellen, wenn diese noch nicht vorhanden sind.
        db.executeStatement("CREATE DATABASE IF NOT EXISTS malu");
        db.executeStatement("CREATE TABLE user IF NOT EXISTS (id INT NOT NULL AUTO_INCREMENT, user VAR(64), password LONGTEXT,  PRIMARY KEY (id))");
        db.executeStatement("CREATE TABLE projects IF NOT EXISTS (id INT NOT NULL AUTO_INCREMENT, owner VAR(64), src LONGTEXT,  PRIMARY KEY (id))");
    }

    public DatabaseConnector ctx() {
        return db;
    }
}