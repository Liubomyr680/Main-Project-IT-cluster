package forest.detector.repository;

import forest.detector.entity.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class UserRepository {

    private final DataSource dataSource;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User getUserByEmail(String email) {
        User user = null;

        String users_query = "SELECT email, password, first_name, last_name FROM users " +
                "WHERE email='" + email + "'";

        String role_query = "SELECT email, password, first_name, last_name FROM user_roles " +
                "WHERE email='" + email + "'";
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(users_query);
                ResultSet resultSet2 = statement.executeQuery(role_query);
        ) {
            if (resultSet.next()) {
                user = new User(
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet2.getString("role")
                        );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void setUserInDB(String email, String password, String first_name, String last_name){

        String role = "user";

        try(Connection connection = dataSource.getConnection())
        {
            String users_query = "INSERT INTO users(email, password, first_name, last_name)" +
                    "VALUES ('" + email + "', '" + password + "', '" + first_name + "', '" + last_name + "');";

            String role_query = "INSERT INTO user_roles(email, role_name)" +
                    "VALUES ('" + email + "', '" + role + "');";

            Statement statement = connection.createStatement();
            statement.executeUpdate(users_query);
            statement.executeUpdate(role_query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserRoleInDB(String role,String email){

        try(Connection connection = dataSource.getConnection();)
        {
            String query = "UPDATE user_roles" +
                    " SET role_name='" +  role + "' " +
                    " WHERE email='" + email + "'";

            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String authenticateUser(String mail, String pass){

        String userNameDB = "";
        String roleDB = "";

        try(Connection con = dataSource.getConnection())
        {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select email, role_name from user_roles");

            while(resultSet.next())
            {
                userNameDB = resultSet.getString("email");
                roleDB = resultSet.getString("role_name");

                if(mail.equals(userNameDB) && roleDB.equals("admin"))
                    return "Admin_Role";
                else if(mail.equals(userNameDB) && roleDB.equals("moderator-api"))
                    return "Moderator-api";
                else if(mail.equals(userNameDB) && roleDB.equals("moderator-gui"))
                    return "Moderator-gui";
                else if(mail.equals(userNameDB) && roleDB.equals("user"))
                    return "User";
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return "<h3 style=\"color:#FF0000\";>Invalid user</h3>";
    }

    public List<User> getUsers() {
        try(Connection con = dataSource.getConnection()) {
            // test connection here
            PreparedStatement ps = con.prepareStatement("select * from users");
            ResultSet rs = ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of(new User());
    }

    public boolean checkUser(String username, String passwd) {
        boolean st = false;
        try (Connection con = dataSource.getConnection()) {

            PreparedStatement ps = con.prepareStatement("select * from users where user_name=? and user_passwd=?");
            ps.setString(1, username);
            ps.setString(2, passwd);
            ResultSet rs = ps.executeQuery();
            st = rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return st;
    }

    public void deleteUser(String email){
        try(Connection connection = dataSource.getConnection();)
        {
            String user_query = "DELETE FROM users" +
                    " WHERE email='" + email + "'";

            String role_query = "DELETE FROM user_roles" +
                    " WHERE email='" + email + "'";

            Statement statement = connection.createStatement();
            statement.executeUpdate(user_query);
            statement.executeUpdate(role_query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}