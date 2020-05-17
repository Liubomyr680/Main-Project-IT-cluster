package forest.detector.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource implements AutoCloseable{
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/users?useTimezone=true&serverTimezone=UTC";

    private Connection connection = null;

    public DataSource(){
        try{
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        try{
            if(connection == null){
                connection = DriverManager.getConnection(DB_URL,"root","1ab35c");
            }
        } catch (SQLException e){
            System.out.println("Error " + e.toString());
        }

        return connection;
    }

    @Override
    public void close() throws Exception {

        try{
            if(connection == null){
                connection.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

    }
}