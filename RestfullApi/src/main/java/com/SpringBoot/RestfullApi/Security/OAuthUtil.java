package com.SpringBoot.RestfullApi.Security;


import ch.qos.logback.core.util.StringUtil;
import com.SpringBoot.RestfullApi.Dto.Enum.AuthProviderType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OAuthUtil {

    @Bean
    public AuthProviderType getProviderTypeFromRegistrationId(String registrationId) {
        if (registrationId == null) return AuthProviderType.LOCAL;

        return switch (registrationId.toLowerCase()) {
            case "google" -> AuthProviderType.GOOGLE;
            case "github" -> AuthProviderType.GITHUB;
            case "facebook" -> AuthProviderType.FACEBOOK;
            default -> AuthProviderType.LOCAL; // fallback for unknown providers
        };
    }


    @Bean
    public String determineProviderTypeFormOAuth2User(OAuth2User oAuth2User,String registrationId){
        if (oAuth2User == null) return null;

        return switch (registrationId.toLowerCase()) {
            case "google" -> oAuth2User.getAttribute("sub"); // Google unique user ID
            case "github" -> String.valueOf(oAuth2User.getAttribute("id"));
            case "facebook" -> String.valueOf(oAuth2User.getAttribute("id"));
            default -> {
                log.error("unsoupported oauyth2 provider :"+ registrationId);
                throw new IllegalArgumentException("unsoupported oauyth2 provider :"+ registrationId);
            }
        };
    }


    public String determineUsernameFromOAuth2User(OAuth2User oAuth2User, String registrationId, String providerId){
        String email =oAuth2User.getAttribute("email");
        if(email != null && !email.isBlank()){
            return email;
        }
        return switch (registrationId.toLowerCase()) {
            case "google" -> oAuth2User.getAttribute("sub"); // Google unique user ID
            case "github" -> String.valueOf(oAuth2User.getAttribute("login"));
            case "facebook" -> String.valueOf(oAuth2User.getAttribute("id"));
            default -> providerId;
        };
    }

}
