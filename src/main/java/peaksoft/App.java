package peaksoft;
import peaksoft.cogfig.DBConnection;
import peaksoft.models.Comment;
import peaksoft.models.Post;
import peaksoft.models.User;
import peaksoft.service.CommentService;
import peaksoft.service.PostService;
import peaksoft.service.UserService;
import peaksoft.serviceImpl.CommentServiceImpl;
import peaksoft.serviceImpl.PostServiceImpl;
import peaksoft.serviceImpl.UserServiceImpl;

import java.time.LocalDate;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
      //  DBConnection.getConnection();
        UserService userService = new UserServiceImpl();
        userService.CreateUserTable();
        userService.saveUser(new User("Adilet","a@gmail.com","a123"));
        userService.saveUser(new User("Elaman","e@gmail.com","e1234"));
        userService.saveUser(new User("Elnura","el@gmail.com","e12345"));
        System.out.println(userService.getAllUsers());
        System.out.println(userService.getUserById(1L));
        userService.deleteUserById(3L);
        userService.updateUser(1L,new User("Sakina","saki@gmqil.com","s123456"));
        System.out.println(userService.searchUserByName("Sakina"));

System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        PostService postService = new PostServiceImpl();
        postService.CreatePostTable();
postService.savePost(new Post("https://example.com/image.jpg","This is a sample post", LocalDate.of (2002,2,2),1L));
postService.savePost(new Post("https://example.com/images/nature.jpg","Тоо этегиндеги кооз пейзаж.", LocalDate.of (2025, 5, 1),2L));
postService.savePost(new Post("https://example.com/image.jpg","Шаар түн ичинде.", LocalDate.of (2025, 5, 10),1L));
postService.savePost(new Post("https://example.com/image.jpg","Таңкы кофе жана жумуш башталышы.", LocalDate.of (2024,2,2),2L));
System.out.println(postService.getAllPost());
System.out.println(postService.getPostById(1L));
postService.deletePostById(4L);
postService.updatePost(2L,new Post("https://example.com/image.jpg","Таңкы кофе жана жумуш башталышы.", LocalDate.of (2024,2,2),2L));
System.out.println(postService.countPostByUser(1L));

System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        CommentService commentService = new CommentServiceImpl();
        commentService.CreateCommentTable();
        commentService.saveComment(new Comment("Бул биринчи комментарий",1L, 1L));
        commentService.saveComment(new Comment("Экинчи комментарий",1L, 2L));
        commentService.saveComment(new Comment("Үчүнчү комментарий",2L, 1L));
        commentService.saveComment(new Comment("Төртүнчү комментарий",2L, 2L));
        commentService.saveComment(new Comment("Бешинчи комментарий",1L, 1L));

        System.out.println(commentService.getAllComment());
        System.out.println(commentService.getCommentById(2L));
        System.out.println(commentService.findRecentCommentsByPost(1L, 1));
        commentService.deleteCommentById(2L);
    }}
