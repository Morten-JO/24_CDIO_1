package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.crypto.Data;

public class DataAccessMySQL{

    public static void testDBConn(){

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://localhost:3306/Users?autoReconnect=true&useSSL=false";
        String user = "root";
        String password = "suck it";

        try {
  
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT VERSION()");

            if (rs.next()) {
                System.out.println(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DataAccessMySQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(DataAccessMySQL.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }
    
    public static void executeSQL(String sqlString){
    Connection con = null;
    PreparedStatement pst = null;

    String url = "jdbc:mysql://localhost:3306/Users?autoReconnect=true&useSSL=false";
    String user = "root";
    String password = "Evolver123!";

    try {

        String name = "Test1";
        con = DriverManager.getConnection(url, user, password);
        pst = con.prepareStatement(sqlString);//"INSERT INTO Authors(Name) VALUES(?)");
        pst.setString(1, name);
        pst.executeUpdate();

    } catch (SQLException ex) {
        Logger lgr = Logger.getLogger(DataAccessMySQL.class.getName());
        lgr.log(Level.SEVERE, ex.getMessage(), ex);

    } finally {

        try {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DataAccessMySQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
    
}
