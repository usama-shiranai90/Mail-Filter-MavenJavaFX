package DBProxy;

import java.sql.SQLException;

public interface Database {

/*    String databaseURL = "jdbc:mysql://localhost:3306/mailFilterDemoDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String sqluser = "root";
    String sqlpassword = "osama123";
    String driversql = "com.mysql.cj.jdbc.Driver";*/

    String databaseURL = "jdbc:mysql://remotemysql.com:3306/vutdgcLlF5";
    String sqluser = "vutdgcLlF5";
    String sqlpassword = "Va2KcNwwgT";
    String driversql = "com.mysql.cj.jdbc.Driver";

    public boolean authentication(String username, String password) throws Exception;  // login

    public String returnUserID();

    public String returnUserPassword();

    public void signUpUser(String username, String Email, String Password) throws ClassNotFoundException, SQLException;

}
