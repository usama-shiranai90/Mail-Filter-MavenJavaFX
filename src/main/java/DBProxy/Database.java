package DBProxy;

import java.sql.SQLException;

public interface Database {

    String databaseURL = "jdbc:mysql://localhost:3306/mailFilterDemoDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String sqluser = "root";
    String sqlpassword = "osama123";
    String driversql = "com.mysql.cj.jdbc.Driver";


    public void authentication(String username, String password) throws Exception;  // login

    public String returnUserID();

    public String returnUserPassword();

    public void signUpUser(String username, String Email, String Password) throws ClassNotFoundException, SQLException;

}
