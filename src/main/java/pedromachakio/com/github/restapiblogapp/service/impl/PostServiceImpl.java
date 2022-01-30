package pedromachakio.com.github.restapiblogapp.service.impl;

import org.springframework.stereotype.Service;
import pedromachakio.com.github.restapiblogapp.model.Post;
import pedromachakio.com.github.restapiblogapp.payload.PostDTO;
import pedromachakio.com.github.restapiblogapp.repository.PostRepository;
import pedromachakio.com.github.restapiblogapp.service.PostService;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) { // se uma classe tiver apenas um construtor, pode-se omitir o @Autowired na propriedade
        this.postRepository = postRepository;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {

        // convert DTO to entity/repository
        Post postObject = new Post();
        postObject.setContent(postDTO.getContent());
        postObject.setDescription(postObject.getDescription());
        postObject.setTitle(postObject.getTitle());

        Post newPost = postRepository.save(postObject);

        // convert entity/repository to DTO
        PostDTO postResponse = new PostDTO();
        postResponse.setId(newPost.getId());
        postResponse.setTitle(newPost.getTitle());
        postResponse.setDescription(newPost.getDescription());
        postResponse.setContent(newPost.getContent());

        return postResponse;
    }
}
