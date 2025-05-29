package peaksoft.dao;

import peaksoft.models.Comment;
import peaksoft.models.Post;

import java.util.List;

public interface PostDao {
    Boolean CreatePostTable();
    void savePost(Post post);
    List<Post> getAllPost();
    Post getPostById(Long id);
    void deletePostById(Long id);
    void updatePost(Post post);
    int countPostByUser(Long userId);
}
