package DBProxy;

import java.sql.SQLException;

public interface Database {

/*    String databaseURL = "jdbc:mysql://localhost:3306/mailFilterDemoDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String sqluser = "root";
    String sqlpassword = "osama123";
    String driversql = "com.mysql.cj.jdbc.Driver";*/

    String databaseURL = "jdbc:mysql://sql6.freesqldatabase.com:3306/sql6397818";
    String sqluser = "sql6397818";
    String sqlpassword = "NCbqaPB1pc";
    String driversql = "com.mysql.cj.jdbc.Driver";

    public boolean authentication(String username, String password) throws Exception;  // login

    public String returnUserID();

    public String returnUserPassword();

    public void signUpUser(String username, String Email, String Password) throws ClassNotFoundException, SQLException;

}
