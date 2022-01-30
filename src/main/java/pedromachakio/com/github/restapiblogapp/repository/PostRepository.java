package pedromachakio.com.github.restapiblogapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pedromachakio.com.github.restapiblogapp.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    // no need to anotate with @Repository, está subentendido
    // also muitos dos crud métodos such as findAll estão implementados na interface que esta estende por isso tambem nao é precisco implementar
}
