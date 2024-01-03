package pwmanager;

import pwmanager.Config;
import java.sql.*;

public class DatabaseOperations {
    private Connection dbConn;

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

    public boolean addAccounts(String account, String username, String password, String accountNotes) {
        String insertSQL = "INSERT INTO accounts (account, username, password, account_notes) VALUES (?, ?, ?, ?)";

        try (PreparedStatement prepStatement = dbConn.prepareStatement(insertSQL)) {
            prepStatement.setString(1, account);
            prepStatement.setString(2, username);
            prepStatement.setString(3, password);
            prepStatement.setString(4, accountNotes);

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
}
