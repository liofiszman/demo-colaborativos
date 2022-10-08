package Utils;
import java.sql.*;
public class DBConnection {

    static private Connection connection;

    static public Connection getConnection(){

        if(connection == null){
            LoadDriver();
            connection = CreateConnection();
        }
        return connection;
    }


    private static void LoadDriver(){

        try {
            // The newInstance() call is a work around for some
            // broken Java implementations
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
        }
    }

    private static Connection CreateConnection() {
        String url = "jdbc:mysql://b6dc1d4084a23e:c454969c@us-cdbr-east-06.cleardb.net/heroku_c2b540f14439ad1?reconnect=true:3306";
        String user = "bf8826fcc3ad24";
        String password = "43e25602";

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex){

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        } finally{
            return conn;
        }
    }




}
