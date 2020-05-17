package forest.detector.repository;

import forest.detector.entity.User;

import java.sql.*;

public class UserRepository {

    public User getUserByEmail(String email) {
        User user = null;
        DataSource dataSource = new DataSource();
        String query = "SELECT id, email, password, first_name, last_name, role FROM users " +
                "WHERE email='" + email + "'";
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            if (resultSet.next()) {
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("role")

                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    public User getUserByEmailByPassword(String email, String password) {
        User user = null;
        DataSource dataSource = new DataSource();
        String query = "SELECT id, email, password, first_name, last_name FROM users " +
                "WHERE email='" + email + "' AND password='" + password + "'";
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            if (resultSet.next()) {
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("role")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void setUserInDB(String email, String password, String first_name, String last_name){

        String role = "User";

        DataSource dataSource = new DataSource();
        try{

            Connection connection = dataSource.getConnection();
            String query = "INSERT INTO users(email, password, first_name, last_name, role)" +
                    "VALUES ('" + email + "', '" + password + "', '" + first_name + "', '" + last_name + "', '" + role + "');";

            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserRoleInDB(String role,String email){
        DataSource dataSource = new DataSource();
        try{

            Connection connection = dataSource.getConnection();
            String query = "UPDATE users" +
                    " SET role='" +  role + "' " +
                    " WHERE email='" + email + "'";

            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String authenticateUser(String mail, String pass){
        String email = mail;
        String password = pass;
        DataSource dataSource = new DataSource();

        String userNameDB = "";
        String passwordDB = "";
        String roleDB = "";

        try
        {
            Connection con = dataSource.getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select email,password,role from users");

            while(resultSet.next())
            {
                userNameDB = resultSet.getString("email");
                passwordDB = resultSet.getString("password");
                roleDB = resultSet.getString("role");

                if(email.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("Admin"))
                    return "Admin_Role";
                else if(email.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("Editor"))
                    return "Editor_Role";
                else if(email.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("User"))
                    return "User_Role";
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return "<h3 style=\"color:#FF0000\";>Invalid user</h3>";
    }

}
