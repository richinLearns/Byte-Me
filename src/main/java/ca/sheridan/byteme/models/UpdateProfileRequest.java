package ca.sheridan.byteme.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileRequest {
    private String name;
    private String email;
    private String currentPassword;
    private String newPassword;
    private String confirmNewPassword;
    private String timezone;
}
