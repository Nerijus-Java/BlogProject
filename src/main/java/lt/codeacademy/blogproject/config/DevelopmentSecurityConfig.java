package lt.codeacademy.blogproject.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Profile("development")
public class DevelopmentSecurityConfig extends WebSecurityConfigurerAdapter {

    private final String h2ConsolePath;
    private final UserDetailsService userDetailsService;

    public DevelopmentSecurityConfig(@Value("${spring.h2.console.path:}")String h2ConsolePath, UserDetailsService userDetailsService) {
        this.h2ConsolePath = h2ConsolePath;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/img/**","/signIn","/blog/open/**","/blog","/")
                .permitAll()
                .antMatchers("/private/**").authenticated()
                .anyRequest()
                .authenticated()
                .and().formLogin()
                .permitAll()
                .loginPage("/signIn")
                .loginProcessingUrl("/signIn")
                .passwordParameter("loginPassword")
                .usernameParameter("loginName")
                .defaultSuccessUrl("/blog" , true)
                .failureUrl("/signIn?error");
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .antMatchers(h2ConsolePath + "/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    @Bean
    public PasswordEncoder encoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
