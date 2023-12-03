package social.com.postservice.repository.repositoryIpm;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import social.com.postservice.model.Post;
import social.com.postservice.repository.PostCustomRepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostCustomRepositoryIpm implements PostCustomRepository {

    private final EntityManager entityManager;


    @Override
    public List<Post> findListPostByName(String name) {
        String nativeQuery = "";

        Query query = entityManager.createNativeQuery(nativeQuery, Post.class);
        query.setParameter("name", name);

        @SuppressWarnings("unchecked")
        List<Post> posts = query.getResultList();

        return posts;
    }

    @Override
    public Optional<Post> findPostByName(String name) {
        try {
            entityManager.getTransaction().begin();

            String nativeQuery = "";

            Query query = entityManager.createNativeQuery(nativeQuery, Post.class);
            query.setParameter("name", name);

            Post post = (Post) query.getSingleResult();
            entityManager.getTransaction().commit();

            return Optional.ofNullable(post);
        } catch (NoResultException e) {
            entityManager.getTransaction().rollback();
            return Optional.empty();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}
