package net.ddns.sinapouya.userservice.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.ddns.sinapouya.userservice.dto.TokenDto;
import net.ddns.sinapouya.userservice.dto.UserDto;
import net.ddns.sinapouya.userservice.exception.UserException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Getter
@Setter
@Slf4j
public class UserService {
    public static final String TOKEN_URI = "http://keycloak:8080/realms/{realm}/protocol/{protocol}/token";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String FORM_URLENCODED = "application/x-www-form-urlencoded";
    public static final String CLIENT_ID_KEY = "client_id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String GRANT_TYPE = "grant_type";
    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.protocol}")
    private String protocol;

    @Value("${keycloak.client.id}")
    private String clientId;

    public final WebClient webClient;

    public UserDto generateToken(TokenDto tokenDto) {

        try{
            return webClient.post()
                    .uri(TOKEN_URI, realm, protocol)
                    .header(CONTENT_TYPE, FORM_URLENCODED)
                    .body(BodyInserters
                            .fromFormData(CLIENT_ID_KEY, clientId)
                            .with(USERNAME, tokenDto.getUserName())
                            .with(PASSWORD, tokenDto.getPassword())
                            .with(GRANT_TYPE, tokenDto.getGrantType()))
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<UserDto>() {})
                    .blockOptional()
                    .orElseThrow(() -> new UserException("Failed to retrieve token information"));
        }catch (Exception e){
            throw new UserException("Failed to retrieve token information");
        }

    }
}
