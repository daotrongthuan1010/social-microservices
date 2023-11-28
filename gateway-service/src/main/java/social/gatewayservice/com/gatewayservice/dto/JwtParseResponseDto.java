package social.gatewayservice.com.gatewayservice.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class JwtParseResponseDto {

    private final String username;

    private final List<String> authorities;

}
