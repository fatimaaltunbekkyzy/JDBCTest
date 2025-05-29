package peaksoft.daoImpl;

import peaksoft.cogfig.DBConnection;
import peaksoft.dao.UserDao;
import peaksoft.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class UserDaoImpl implements UserDao {
    private final Connection connection = DBConnection.getConnection();
    @Override
    public Boolean CreateUserTable() {
        String sql = """
      CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY,
userName VARCHAR,
email VARCHAR,
password VARCHAR)
        """;
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public void saveUser(User user) {
String sql = """
        INSERt INTO users(userName, email, password)
        VALUES (?,?,?)
        """;
try(PreparedStatement statement = connection.prepareStatement(sql)){
    statement.setString(1,user.getUserName());
    statement.setString(2,user.getEmail());
    statement.setString(3,user.getPassword());
    statement.executeUpdate();
}catch (SQLException e) {
    System.out.println(e.getMessage());
}
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "select * from users";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
               User user = new User();
               user.setId(resultSet.getLong("id"));
               user.setUserName(resultSet.getString("userName"));
               user.setEmail(resultSet.getString("email"));
               user.setPassword(resultSet.getString("password"));

                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public User getUserById(Long id) {
        String sql = "select * from users where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
              User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUserName(resultSet.getString("userName"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteUserById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement("""

                DELETE FROM users WHERE id = ?;
""") ){
            statement.setLong(1 ,id);
            statement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateUser( Long id,User user) {
        try (PreparedStatement statement = connection.prepareStatement("""
        UPDATE users SET username = ?, email = ? WHERE id = ?
    """)) {
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setLong(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<User> searchUserByName(String name) {
        List<User> users = new ArrayList<>();
        String sql = """
                SELECT * FROM users WHERE userName = ?
                """;
          try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
           preparedStatement.setString(1, name);
              ResultSet resultSet = preparedStatement.executeQuery();
while (resultSet.next()) {
    User user = new User();
    user.setUserName(resultSet.getString("userName"));
    user.setEmail(resultSet.getString("email"));
    user.setPassword(resultSet.getString("password"));
    users.add(user);
}
          }catch (SQLException e) {
              System.out.println(e.getMessage());
          }
        return users;
    }
}
