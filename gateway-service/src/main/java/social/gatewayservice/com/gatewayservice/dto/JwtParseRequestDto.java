package social.gatewayservice.com.gatewayservice.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtParseRequestDto {

    private final String token;


}
