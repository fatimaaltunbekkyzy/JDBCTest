package peaksoft.dao;

import peaksoft.models.User;

import java.util.List;

public interface UserDao {
Boolean CreateUserTable();
void saveUser(User user);
List<User> getAllUsers();
User getUserById(Long id);
void deleteUserById(Long id);
void updateUser(Long id,User user);
List<User>searchUserByName(String name);
}
