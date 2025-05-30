package peaksoft.dao;

import peaksoft.models.Comment;
import peaksoft.models.User;

import java.util.List;

public interface CommentDao {
    Boolean CreateCommentTable();
    void saveComment(Comment comment);
    List<Comment> getAllComment();
    Comment getCommentById(Long id);
    void deleteCommentById(Long id);
    void updateComment(Comment comment,Long id);
    List<Comment>findRecentCommentsByPost(Long postId,int limit);

}
