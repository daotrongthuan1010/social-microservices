package social.auth.com.authservice.repository.adnet.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DistrictResponse {

    @JsonProperty(namespace = "name_district")
    private final String name;

    @JsonProperty(namespace = "code_district")
    private final int code;

    @JsonProperty(namespace = "code_name")
    private final String codename;

    @JsonProperty("division_type")
    private final String divisionType;

    @JsonProperty(namespace = "province_code")
    private final int provinceCode;

}


