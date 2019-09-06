package mobify.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

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
                        "/h2-console/**",
                        "/console/**",
                        "/webjars/**").permitAll()
                .antMatchers(RoleConfig.ADMIN.authorizedUrl).hasAnyRole(RoleConfig.ADMIN.role)
                .antMatchers(RoleConfig.USER.authorizedUrl).hasAnyRole(RoleConfig.USER.role)
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

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new MyAuthenticationSuccessHandler();
    }

    @Bean
    public AccessDeniedHandler myAccessDeniedHandler() {
        return new MyAccessDeniedHandler();
    }

    // create two users, admin and user
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {

////        JDBC
//        auth.jdbcAuthentication()
//                .usersByUsernameQuery(usersQuery)
//                .authoritiesByUsernameQuery(rolesQuery)
//                .dataSource(dataSource);
////                .passwordEncoder(bCryptPasswordEncoder);

//         memory authentication
//        auth.inMemoryAuthentication()
//                .withUser("user").password("123").roles(RoleConfig.USER.role)
//                .and()
//                .withUser("admin").password("123").roles(RoleConfig.ADMIN.role)
//                .and()
//                .passwordEncoder(NoOpPasswordEncoder.getInstance());
//    }

    // another way to add url exceptions for authentication
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().antMatchers("/webjars/**");
//        web.ignoring().antMatchers("/css/**", "/fonts/**", "/libs/**");
//    }
}
