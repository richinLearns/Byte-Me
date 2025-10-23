package ca.sheridan.byteme.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationForm {
  @NotBlank
  private String name;

  @NotBlank @Email
  private String email;

  @NotBlank @Size(min = 8)
  private String password;

  @NotBlank
  private String confirmPassword;
}
