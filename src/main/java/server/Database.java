package server;

import generic.SQL.DatabaseConnector;
import generic.SQL.QueryResult;
import utils.ObjectPrinter;

public class Database {
    private final DatabaseConnector db;

    public Database() {
        db = new DatabaseConnector("127.0.0.1", 3306, "malu", "root", "");
    }

    public DatabaseConnector ctx() {
        return db;
    }

    public QueryResult execute(String query) {
        db.executeStatement(query);

        if (db.getErrorMessage() != null) {
            System.out.println("Error: " + db.getErrorMessage());
            return null;
        }

        QueryResult result = db.getCurrentQueryResult();

        return result;
    }
}