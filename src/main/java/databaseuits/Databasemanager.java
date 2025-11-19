package databaseuits;

import org.example.Base;

import java.io.IOException;
import java.sql.*;

public class Databasemanager extends Base {

    public Connection DbConnection(String DbName) throws IOException, SQLException {
        // "jdbc:mysql://<hostname>:<port>/<databaseName>";
        String Hostname=loadProperty("hostname");
        String db_username=loadProperty("username");
        String db_password=loadProperty("Password");
        String db_port=loadProperty("PortNumber");
        Connection connection=DriverManager.getConnection("jdbc:mysql://"+Hostname+":"+db_port+"/"+DbName,db_username,db_password);

        System.out.println("Database connected successfully");
        return connection;
    }


    public ResultSet execute_query(String query,String db_name) throws SQLException, IOException {

        Connection connection=DbConnection(db_name);
        Statement statement=connection.createStatement();
        ResultSet result=statement.executeQuery(query);
        result.next();
        return result;



    }

}
