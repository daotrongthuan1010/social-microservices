package social.auth.com.authservice.repository.adnet.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CityResponse {

    @JsonProperty(namespace = "name_provide")
    private final  String name;

    @JsonProperty(namespace = "code_provide")
    private int code;

    @JsonProperty(namespace = "division_type")
    private String divisionType;

    @JsonProperty(namespace = "phone_code")
    private int phoneCode;

    @JsonProperty(namespace = "code_name")
    private String codename;

    @JsonProperty(namespace = "districts")
    private List<DistrictResponse> districts;

}