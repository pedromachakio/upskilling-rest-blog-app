package pedromachakio.com.github.restapiblogapp.service.impl;

import org.springframework.stereotype.Service;
import pedromachakio.com.github.restapiblogapp.exception.ResourceNotFoundException;
import pedromachakio.com.github.restapiblogapp.model.Post;
import pedromachakio.com.github.restapiblogapp.payload.PostDTO;
import pedromachakio.com.github.restapiblogapp.repository.PostRepository;
import pedromachakio.com.github.restapiblogapp.service.PostService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) { // se uma classe tiver apenas um construtor, pode-se omitir o @Autowired na propriedade
        this.postRepository = postRepository;
    }


    @Override
    public List<PostDTO> getAllPosts() {
        List<Post> listOfAllPosts = postRepository.findAll();

      /*  // my own solution
       List<PostDTO> listOfAllPostsDTO = new ArrayList<>();

        for (Post individualPost : listOfAllPosts) {
            PostDTO individualPostDTO = mapToDto(individualPost);

            listOfAllPostsDTO.add(individualPostDTO);
        }
        return listOfAllPostsDTO;*/

        return listOfAllPosts.stream().map(this::mapToDto).collect(Collectors.toList()); // individualPost -> mapToDto(individualPost)
    }

    @Override
    public PostDTO getPostById(Long id) {
        Post requestedPost = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        return mapToDto(requestedPost);
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {

        // convert DTO to entity/repository and save it
        Post savedPost = postRepository.save(mapToEntity(postDTO));

        // convert entity/repository to DTO and return
        return mapToDto(savedPost);
    }


    // convert entity/repository into DTO
    private PostDTO mapToDto(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setDescription(post.getDescription());
        postDTO.setContent(post.getContent());

        return postDTO;
    }

    // convert DTO to entity/repository
    private Post mapToEntity(PostDTO postDTO) {
        Post post = new Post();
        post.setContent(postDTO.getContent());
        post.setDescription(postDTO.getDescription());
        post.setTitle(postDTO.getTitle());

        return post;
    }
}
