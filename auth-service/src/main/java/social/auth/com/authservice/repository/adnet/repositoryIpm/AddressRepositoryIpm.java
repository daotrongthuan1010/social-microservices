package social.auth.com.authservice.repository.adnet.repositoryIpm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import social.auth.com.authservice.repository.adnet.AddressRepository;
import social.auth.com.authservice.repository.adnet.response.CityResponse;
import social.auth.com.authservice.repository.adnet.response.DistrictResponse;
import social.auth.com.authservice.utils.ContentMessageConstant;


@Slf4j
@Repository
@RequiredArgsConstructor
public class AddressRepositoryIpm implements AddressRepository {

    private final RestTemplate restTemplate;

    @Value(value = "${name}")
    private String apiUrl;

    @Override
    public ResponseEntity<DistrictResponse> findAddress(String name) {
        try {

            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiUrl)
                    .queryParam("q", name);

            var districtResponse = restTemplate.getForObject(builder.toUriString(), DistrictResponse.class);

            if (ObjectUtils.isEmpty(districtResponse)) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(districtResponse);
        } catch (RestClientException exception) {
            log.info(ContentMessageConstant.RE_INPUT_ADDRESS);
            return ResponseEntity.status(500).build();
        }
    }
}
