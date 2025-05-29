package peaksoft;

import peaksoft.cogfig.DBConnection;
import peaksoft.models.User;
import peaksoft.service.UserService;
import peaksoft.serviceImpl.UserServiceImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
      //  DBConnection.getConnection();
        UserService userService = new UserServiceImpl();
       // userService.CreateUserTable();
//        userService.saveUser(new User("Adilet","a@gmail.com","a123"));
//        userService.saveUser(new User("Elaman","e@gmail.com","e1234"));
//        userService.saveUser(new User("Elnura","el@gmail.com","e12345"));
userService.getAllUsers();
     //   userService.getUserById(1L);
     //  userService.deleteUserById(6L);
    }
}
