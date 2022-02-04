package pedromachakio.com.github.restapiblogapp.service;

import pedromachakio.com.github.restapiblogapp.payload.PostDTO;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDTO);

    List<PostDTO> getAllPosts(int pageNumber, int pageSize);

    PostDTO getPostById(Long id);

    PostDTO updatePost(PostDTO postDTO, Long id);

    void deletePostById(Long id);
}
