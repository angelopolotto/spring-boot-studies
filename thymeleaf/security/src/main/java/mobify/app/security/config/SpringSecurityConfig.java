package mobify.app.security.config;

import mobify.app.security.user.Roles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    // roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/home",
                        "/about",
                        "/js/**",
                        "/css/**",
                        "/fonts/**",
                        "/libs/**",
                        "/img/**",
                        "/favicon.ico",
                        "/webjars/**").permitAll()
                .antMatchers(Roles.ADMIN.authorizedUrl).hasAnyRole(Roles.ADMIN.role)
                .antMatchers(Roles.USER.authorizedUrl).hasAnyRole(Roles.USER.role)
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(myAuthenticationSuccessHandler())
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(myAccessDeniedHandler());
    }

    // create two users, admin and user
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("123").roles(Roles.USER.role)
                .and()
                .withUser("admin").password("123").roles(Roles.ADMIN.role)
                .and()
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().antMatchers("/webjars/**");
//        web.ignoring().antMatchers("/css/**", "/fonts/**", "/libs/**");
//    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new MySimpleUrlAuthenticationSuccessHandler();
    }

    @Bean
    public AccessDeniedHandler myAccessDeniedHandler() {
        return new MyAccessDeniedHandler();
    }
}
