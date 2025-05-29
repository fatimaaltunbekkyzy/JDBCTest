package peaksoft.dao;

import peaksoft.models.Comment;
import peaksoft.models.User;

import java.util.List;

public interface CommentDao {
    Boolean CreateCommentTable();
    void saveComment();
    List<Comment> getAllComment();
    Comment getCommentById(Long id);
    void deleteCommentById(Long id);
    void updateComment(Comment comment);
    List<Comment>findRecentCommentsByPost(Long postId,int limit);
}
