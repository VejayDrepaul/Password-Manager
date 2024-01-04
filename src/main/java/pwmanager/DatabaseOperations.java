package pwmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.table.DefaultTableModel;

public class DatabaseOperations {
    public static DatabaseOperations instance = null;
    private Connection dbConn;

    public static DatabaseOperations getInstance() {
        if (instance == null)
            instance = new DatabaseOperations();

        return instance;
    }

    public boolean login(String username, String password) {
        Config conn = new Config();
        String[] dbInfo = conn.getDBInfo();

        try {           
            if (dbInfo[1].equals(username) && dbInfo[2].equals(password)) {
                this.dbConn = DriverManager.getConnection(dbInfo[0], username, password);
                return true;
            }
        }
        catch (Exception e) {
            System.out.println("Connection failed" + e); 
        }

        return false;
    }

    public boolean addAccounts(String account, String username, String password, String accountNotes, String hash) {
        String insertSQL = "INSERT INTO accounts (account, username, password, account_notes, hashed_password) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement prepStatement = dbConn.prepareStatement(insertSQL)) {
            prepStatement.setString(1, account);
            prepStatement.setString(2, username);
            prepStatement.setString(3, password);
            prepStatement.setString(4, accountNotes);
            prepStatement.setString(5, hash);

            int rowsAffected = prepStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            } else {
                System.out.println("Failed to add account.");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }

        return false;
    }

    public boolean deleteAccount(String account) {
        String deleteSQL = "DELETE FROM accounts WHERE account = ?";

        try (PreparedStatement prepStatement = dbConn.prepareStatement(deleteSQL)) {
            prepStatement.setString(1, account);

            int rowsAffected = prepStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            } else {
                System.out.println("Account not found or failed to remove.");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }

        return false;
    }

    public boolean populateTable(DefaultTableModel table) {
        String sqlQuery = "SELECT account, username, password, account_notes FROM accounts";

        try (PreparedStatement statement = dbConn.prepareStatement(sqlQuery)) {
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                String account = result.getString("account");
                String username = result.getString("username");
                String password = result.getString("password");
                String account_notes = result.getString("account_notes");
                table.addRow(new Object[]{account, username, password, account_notes});
            }

            return true;
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }

        return false;
    }
}
