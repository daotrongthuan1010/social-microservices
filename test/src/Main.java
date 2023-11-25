import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<PermissionGetResponse> list = List.of();

        boolean a  = list.stream().flatMap(permissionGetResponse -> permissionGetResponse.permissions().stream())
                .anyMatch(permissionResponse -> "ngr_i2a".equals(permissionResponse.permissionCode()) && permissionResponse.isOn());
        System.out.println(a);
    }
}