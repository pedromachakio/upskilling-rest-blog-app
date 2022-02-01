package pedromachakio.com.github.restapiblogapp.service;

import pedromachakio.com.github.restapiblogapp.payload.PostDTO;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDTO);

    List<PostDTO> getAllPosts();

    PostDTO getPostById(Long id);
}
