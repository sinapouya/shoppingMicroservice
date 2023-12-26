package net.ddns.sinapouya.userservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenDto {
    @NotEmpty(message = "userName cannot be empty")
    @NotNull(message = "userName cannot be null")
    private String userName;
    @NotEmpty(message = "password cannot be empty")
    @NotNull(message = "password cannot be null")
    private String password;
    @NotEmpty(message = "clientId cannot be empty")
    @NotNull(message = "clientId cannot be null")
    private String clientId;
    @NotEmpty(message = "grantType cannot be empty")
    @NotNull(message = "grantType cannot be null")
    private String grantType;
}
