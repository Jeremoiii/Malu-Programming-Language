package server;

import generic.SQL.DatabaseConnector;
import generic.SQL.QueryResult;
import utils.ObjectPrinter;

public class Database {
    public static void main(String[] args) {
        DatabaseConnector db = new DatabaseConnector("127.0.0.1", 3306, "malu", "root", "");

        String error = db.getErrorMessage();
        if (error != null) {
            System.out.println("Error: " + ObjectPrinter.deserializeObjectToString(error));
            System.exit(1);
        }

        System.out.println("Connected to database");

        db.executeStatement("SELECT * FROM projects");
        QueryResult result = db.getCurrentQueryResult();

        System.out.println(ObjectPrinter.deserializeObjectToString(result.getData()));

       db.close();
    }
}