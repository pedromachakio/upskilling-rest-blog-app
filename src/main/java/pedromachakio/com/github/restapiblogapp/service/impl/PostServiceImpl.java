package pedromachakio.com.github.restapiblogapp.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pedromachakio.com.github.restapiblogapp.exception.ResourceNotFoundException;
import pedromachakio.com.github.restapiblogapp.model.Post;
import pedromachakio.com.github.restapiblogapp.payload.PostDTO;
import pedromachakio.com.github.restapiblogapp.payload.PostResponse;
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
    public PostResponse getAllPosts(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Post> pageListOfPosts = postRepository.findAll(pageable);

        // get content from Page object
        List<Post> listOfPosts = pageListOfPosts.getContent();


      /*  // my own solution
       List<PostDTO> listOfAllPostsDTO = new ArrayList<>();

        for (Post individualPost : listOfAllPosts) {
            PostDTO individualPostDTO = mapToDto(individualPost);

            listOfAllPostsDTO.add(individualPostDTO);
        }
        return listOfAllPostsDTO;*/

        List<PostDTO> listOfPostsDTO = listOfPosts.stream().map(this::mapToDto).collect(Collectors.toList()); // individualPost -> mapToDto(individualPost)

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(listOfPostsDTO);
        postResponse.setPageNumber(pageListOfPosts.getNumber());
        postResponse.setPageSize(pageListOfPosts.getSize());
        postResponse.setTotalElements(pageListOfPosts.getTotalElements());
        postResponse.setTotalPages(pageListOfPosts.getTotalPages());
        postResponse.setTotalPages(pageListOfPosts.getTotalPages());
        postResponse.setLastPage(pageListOfPosts.isLast());

        return postResponse;
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


    @Override
    public PostDTO updatePost(PostDTO postDTO, Long id) {
        Post requestedPost = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        // can't use mapToEntity since it instantiates a new Post and this one already exists com o respetivo ID
        requestedPost.setTitle(postDTO.getTitle());
        requestedPost.setContent(postDTO.getContent());
        requestedPost.setDescription(postDTO.getDescription());

        Post updatedPost = postRepository.save(requestedPost);

        return mapToDto(updatedPost);
    }

    @Override
    public void deletePostById(Long id) {
        Post requestedPost = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(requestedPost);
    }


    // ------------------------- Utility methods ------------------------------

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
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setDescription(postDTO.getDescription());

        return post;
    }
}
