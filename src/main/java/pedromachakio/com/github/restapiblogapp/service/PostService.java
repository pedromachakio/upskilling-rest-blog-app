package pedromachakio.com.github.restapiblogapp.service;

import pedromachakio.com.github.restapiblogapp.payload.PostDTO;
import pedromachakio.com.github.restapiblogapp.payload.PostResponse;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDTO);

    PostResponse getAllPosts(int pageNumber, int pageSize);

    PostDTO getPostById(Long id);

    PostDTO updatePost(PostDTO postDTO, Long id);

    void deletePostById(Long id);
}
