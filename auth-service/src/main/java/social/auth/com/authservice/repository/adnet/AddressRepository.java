package social.auth.com.authservice.repository.adnet;

import org.springframework.http.ResponseEntity;
import social.auth.com.authservice.repository.adnet.response.DistrictResponse;


public interface AddressRepository {

    ResponseEntity<DistrictResponse> findAddress(String name);

}
