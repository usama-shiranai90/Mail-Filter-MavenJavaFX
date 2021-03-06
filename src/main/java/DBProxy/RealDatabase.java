package DBProxy;

import Implementation.NetworkAddress;

import java.sql.*;

public class RealDatabase implements Database {

    private static String usernameStatic = "";
    private static String passwordStatic = "";
    private static String macAddress = null;
    private NetworkAddress networkAddress;

    public String getUser() {
        return usernameStatic;
    }

    public String getPass() {
        return passwordStatic;
    }

    @Override
    public void authentication(String username, String password) throws ClassNotFoundException, SQLException {
        networkAddress = new NetworkAddress();
        String macAddressOfPC = networkAddress.getPCMacAddress();
        Connection connection = DriverManager.getConnection(databaseURL, sqluser, sqlpassword);
        Class.forName(driversql);

        if (connection != null) {
            System.out.println("Connection successful");
        }

        connection.createStatement();

        PreparedStatement preparedStatementForLoginSelection;
        preparedStatementForLoginSelection = connection.prepareStatement("select userid,password ,systemMACAddress from mailfilter");
        boolean flag = true;
        ResultSet resultset = preparedStatementForLoginSelection.executeQuery();

        while ((resultset.next()) && flag) {
            usernameStatic = resultset.getString("userid");

            passwordStatic = resultset.getString("password");
            macAddress = resultset.getString("systemMACAddress");
            System.out.println("macAddress = " + macAddress);

            if (username.hashCode() == usernameStatic.hashCode() && password.hashCode() == passwordStatic.hashCode()) {
                if (macAddress == null) {
                    int userid = Integer.parseInt(usernameStatic);
                    PreparedStatement statement = connection.prepareStatement(String.format("UPDATE mailfilter SET systemMACAddress ='%s' WHERE userid = %d; ", macAddressOfPC, userid));
                    statement.executeUpdate();
                    flag = false;
                } else if (macAddressOfPC.equals(macAddress)) {
                    flag = false;
                } else {
                    flag = true;
                    break;
                }

            }
        }
        System.out.println("usernameStatic = " + usernameStatic);
        System.out.println("passwordStatic = " + passwordStatic);
        System.out.println("macAddress = " + macAddress);
    }

    @Override
    public String returnUserID() {
        return getUser();
    }

    @Override
    public String returnUserPassword() {
        return getPass();
    }


    @Override
    public void signUpUser(String username, String Email, String Password) throws ClassNotFoundException, SQLException {

        Connection connection = DriverManager.getConnection(databaseURL, sqluser, sqlpassword);
        Class.forName(driversql);

        String query1 = "INSERT INTO customer(username,Email,password) values (?,?,?);";

        PreparedStatement preparedStatement = connection.prepareStatement(query1);

        preparedStatement.setString(1, username);
        preparedStatement.setString(2, Email);
        preparedStatement.setString(3, Password);

        preparedStatement.executeUpdate();
        System.out.println("Successfully created a new user account...");

    }

}
