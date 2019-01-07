package tp.ms.cas.security.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;

@Configuration
public class MsAuthenticationManagerConfiguration {


    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Bean
    public AuthenticationManager authenticationManagerBean() {
        return new ProviderManager(
                Arrays.asList(authenticationProvider));
    }
}
