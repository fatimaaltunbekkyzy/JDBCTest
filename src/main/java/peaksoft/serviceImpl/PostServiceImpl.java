package peaksoft.serviceImpl;

import peaksoft.dao.PostDao;
import peaksoft.daoImpl.PostDaoImpl;
import peaksoft.models.Post;
import peaksoft.service.PostService;

import java.util.List;

public class PostServiceImpl implements PostService {
    private final PostDao postDao = new PostDaoImpl();
    @Override
    public Boolean CreatePostTable() {
        return postDao.CreatePostTable();
    }

    @Override
    public void savePost(Post post) {
postDao.savePost(post);
    }

    @Override
    public List<Post> getAllPost() {
        return postDao.getAllPost();
    }

    @Override
    public Post getPostById(Long id) {
        return postDao.getPostById(id);
    }

    @Override
    public void deletePostById(Long id) {
postDao.deletePostById(id);
    }

    @Override
    public void updatePost(Long id, Post post) {
postDao.updatePost(id, post);
    }

    @Override
    public int countPostByUser(Long userId) {
        return postDao.countPostByUser(userId);
    }
}
