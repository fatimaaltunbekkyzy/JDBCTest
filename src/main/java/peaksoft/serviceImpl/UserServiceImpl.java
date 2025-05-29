package peaksoft.serviceImpl;

import peaksoft.dao.UserDao;
import peaksoft.daoImpl.UserDaoImpl;
import peaksoft.models.User;
import peaksoft.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();
    @Override
    public Boolean CreateUserTable() {
        return userDao.CreateUserTable();
    }

    @Override
    public void saveUser(User user) {
    userDao.saveUser(user);
    }

    @Override
    public List<User> getAllUsers() {
      return userDao.getAllUsers();

    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public void deleteUserById(Long id) {
userDao.deleteUserById(id);
    }

    @Override
    public void updateUser(User user) {
userDao.updateUser(user);
    }

    @Override
    public List<User> searchUserByName(String name) {
        return List.of();
    }
}
