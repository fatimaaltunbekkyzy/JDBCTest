package peaksoft.serviceImpl;

import peaksoft.dao.CommentDao;
import peaksoft.daoImpl.CommentDaoImpl;
import peaksoft.models.Comment;
import peaksoft.service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao = new CommentDaoImpl();
    @Override
    public Boolean CreateCommentTable() {
      return commentDao.CreateCommentTable();

    }

    @Override
    public void saveComment(Comment comment) {
    commentDao.saveComment(comment);
    }

    @Override
    public List<Comment> getAllComment() {
        return commentDao.getAllComment();
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentDao.getCommentById(id);
    }

    @Override
    public void deleteCommentById(Long id) {
    commentDao.deleteCommentById(id);
    }

    @Override
    public void updateComment(Comment comment,Long id) {
    commentDao.updateComment(comment,id);
    }

    @Override
    public List<Comment> findRecentCommentsByPost(Long postId, int limit) {
        return commentDao.findRecentCommentsByPost(postId, limit);
    }
}
