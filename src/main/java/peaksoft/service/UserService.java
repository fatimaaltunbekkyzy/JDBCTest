package peaksoft.service;

import peaksoft.models.User;

import java.util.List;

public interface UserService {
    Boolean CreateUserTable();
    void saveUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    void deleteUserById(Long id);
    void updateUser(Long id,User user);
    List<User>searchUserByName(String name);
}
