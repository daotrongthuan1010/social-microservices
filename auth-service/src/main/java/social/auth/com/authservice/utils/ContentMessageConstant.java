package social.auth.com.authservice.utils;


import lombok.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:message.properties")
public class ContentMessageConstant {

    public static final String CREATE_USER_SUCCESS = "Tạo tài khoản thành công.";

    public static final String RE_INPUT_ADDRESS = "Địa chỉ đang bảo trì, vui lòng nhập tay.";
}
