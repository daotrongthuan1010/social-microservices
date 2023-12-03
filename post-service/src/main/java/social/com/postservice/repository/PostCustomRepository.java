package social.com.postservice.repository;

import social.com.postservice.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostCustomRepository {

    List<Post> findListPostByName(String name);

    Optional<Post> findPostByName(String name);
}
