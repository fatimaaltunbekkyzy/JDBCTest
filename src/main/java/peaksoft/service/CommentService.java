package peaksoft.service;

import peaksoft.models.Comment;

import java.util.List;

public interface CommentService {
    Boolean CreateCommentTable();
    void saveComment(Comment comment);
    List<Comment> getAllComment();
    Comment getCommentById(Long id);
    void deleteCommentById(Long id);
    void updateComment(Comment comment,Long id);
    List<Comment>findRecentCommentsByPost(Long postId,int limit);
}
