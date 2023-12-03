package social.com.postservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import social.com.postservice.model.Post;

@Repository
public interface PostRepository  extends JpaRepository<Post, Long> {

}
