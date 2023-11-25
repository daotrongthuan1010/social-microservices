import java.util.List;

public record PermissionGetResponse (String capMemberId, List<PermissionResponse> permissions ){
}
