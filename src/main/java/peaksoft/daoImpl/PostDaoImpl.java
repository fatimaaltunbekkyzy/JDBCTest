package peaksoft.daoImpl;

import peaksoft.cogfig.DBConnection;
import peaksoft.dao.PostDao;
import peaksoft.models.Post;
import peaksoft.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDaoImpl implements PostDao {
    private final Connection connection = DBConnection.getConnection();
    @Override
    public Boolean CreatePostTable() {
        String sql = """
                CREATE TABLE posts(id SERIAL PRIMARY KEY,
image VARCHAR,
description VARCHAR,
created_date DATE,
user_id INT REFERENCES users(id))
                """;
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void savePost(Post post) {
String sql = """
        INSERT INTO posts(image,description,created_date,user_id)
        VALUES(?,?,?,?)
        """;
try(PreparedStatement statement = connection.prepareStatement(sql)) {
    statement.setString(1, post.getImage());
    statement.setString(2, post.getDescription());
    statement.setDate(3, Date.valueOf(post.getCreated_date()));
    statement.setLong(4, post.getUserId());
    statement.executeUpdate();
}catch (SQLException e) {
    System.out.println(e.getMessage());
}
    }

    @Override
    public List<Post> getAllPost() {
        List<Post> posts = new ArrayList<>();
        String sql = "select * from posts";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Post post = new Post();
                post.setId(resultSet.getLong("id"));
                post.setImage(resultSet.getString("image"));
                post.setDescription(resultSet.getString("description"));
                post.setCreated_date(resultSet.getDate("created_date").toLocalDate());
                post.setUserId(resultSet.getLong("user_id"));
                posts.add(post);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return posts;

    }

    @Override
    public Post getPostById(Long id) {
        String sql = "select * from posts where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getLong("id"));
                post.setImage(resultSet.getString("image"));
                post.setDescription(resultSet.getString("description"));
                post.setCreated_date(resultSet.getDate("created_date").toLocalDate());
                post.setUserId(resultSet.getLong("user_id"));
                return post;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void deletePostById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement("""

                DELETE FROM posts WHERE id = ?;
""") ){
            statement.setLong(1 ,id);
            statement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updatePost(Long id,Post post) {
        try (PreparedStatement statement = connection.prepareStatement("""
        UPDATE posts SET username = ?, email = ? WHERE id = ?
    """)) {
            statement.setString(1, post.getImage());
            statement.setString(2, post.getDescription());
            statement.setDate(3, Date.valueOf(post.getCreated_date()));
            statement.setLong(4, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int countPostByUser(Long userId) {
        String sql = "SELECT COUNT(*) FROM posts WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error counting posts: " + e.getMessage());
        }
        return 0;
    }
}
