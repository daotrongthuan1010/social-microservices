package social.com.postservice.repository.repositoryIpm;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import social.com.postservice.repository.ExternalRepository;

@Repository
@RequiredArgsConstructor
public class ExternalRepositoryIpm implements ExternalRepository {

    private final RestTemplate restTemplate;


}
