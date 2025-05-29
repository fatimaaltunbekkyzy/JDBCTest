package peaksoft.daoImpl;

import peaksoft.cogfig.DBConnection;
import peaksoft.dao.CommentDao;
import peaksoft.models.Comment;
import peaksoft.models.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
    private final Connection connection = DBConnection.getConnection();
    @Override
    public Boolean CreateCommentTable() {
        String sql = """
                CREATE TABLE comments(id SERIAL PRIMARY KEY,
text VARCHAR,
user_id INT REFERENCES users(id),
post_id INT REFERENCES posts(id))
                """;
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    @Override
    public void saveComment(Comment comment) {
        String sql = """
        INSERT INTO comments(text,user_id,post_id)
        VALUES(?,?,?)
        """;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, comment.getText());
            statement.setLong(2,   comment.getUserId());
            statement.setLong(3,   comment.getPostId());
            statement.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Comment> getAllComment() {
        List<Comment> comments = new ArrayList<>();
        String sql = "select * from comments";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Comment comment = new Comment();
                comment.setId(resultSet.getLong("id"));
                comment.setText(resultSet.getString("text"));
                comment.setUserId(resultSet.getLong("user_id"));
                comment.setPostId(resultSet.getLong("post_id"));
                comments.add(comment);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return comments;
    }

    @Override
    public Comment getCommentById(Long id) {
        String sql = "select * from comments where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getLong("id"));
                comment.setText(resultSet.getString("text"));
                comment.setUserId(resultSet.getLong("user_id"));
                comment.setPostId(resultSet.getLong("post_id"));
                return comment;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    @Override
    public void deleteCommentById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement("""

                DELETE FROM commens WHERE id = ?;
""") ){
            statement.setLong(1 ,id);
            statement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateComment(Comment comment,Long id) {
        String sql = """
        UPDATE comments SET text = ?, post_id = ?, user_id = ? WHERE id = ?
    """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, comment.getText());
            statement.setLong(2, comment.getPostId());
            statement.setLong(3, comment.getUserId());
            statement.setLong(4, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public List<Comment> findRecentCommentsByPost(Long postId, int limit) {
        List<Comment> comments = new ArrayList<>();
        String sql = """
        SELECT id, text, post_id, user_id
        FROM comments
        WHERE post_id = ?
        ORDER BY id DESC
        LIMIT ?
    """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, postId);
            statement.setInt(2, limit);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getLong("id"));
                comment.setText(rs.getString("text"));
                comment.setPostId(rs.getLong("post_id"));
                comment.setUserId(rs.getLong("user_id"));
                comments.add(comment);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return comments;
    }
}
