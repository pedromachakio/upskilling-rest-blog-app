package pedromachakio.com.github.restapiblogapp.service;

import pedromachakio.com.github.restapiblogapp.payload.PostDTO;

public interface PostService {

    PostDTO createPost(PostDTO postDTO);
}
