package DBProxy;

import java.sql.SQLException;

public class DatabaseProxy implements Database {

    Database db = new RealDatabase();

    @Override
    public void authentication(String username, String password) throws Exception {
      db.authentication(username, password);
}

    @Override
    public String returnUserID() {
        return db.returnUserID();
    }

    @Override
    public String returnUserPassword() {
        return  db.returnUserPassword();
    }

    @Override
    public void signUpUser(String username, String Email, String Password) throws SQLException, ClassNotFoundException {

        db.signUpUser(username, Email, Password);

    }

}
