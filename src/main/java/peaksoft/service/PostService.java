package peaksoft.service;

import peaksoft.models.Post;

import java.util.List;

public interface PostService {
    Boolean CreatePostTable();
    void savePost(Post post);
    List<Post> getAllPost();
    Post getPostById(Long id);
    void deletePostById(Long id);
    void updatePost(Long id,Post post);
    int countPostByUser(Long userId);
}
