package mobify.app.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Autowired
    public SecurityConfiguration(BCryptPasswordEncoder bCryptPasswordEncoder, DataSource dataSource) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.dataSource = dataSource;
    }

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

    // create two users, admin and user
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

//        JDBC
        auth.jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource);
//                .passwordEncoder(bCryptPasswordEncoder);

//         memory authentication
//        auth.inMemoryAuthentication()
//                .withUser("user").password("123").roles(RoleConfig.USER.role)
//                .and()
//                .withUser("admin").password("123").roles(RoleConfig.ADMIN.role)
//                .and()
//                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    // another way to add url exceptions for authentication
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().antMatchers("/webjars/**");
//        web.ignoring().antMatchers("/css/**", "/fonts/**", "/libs/**");
//    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new MyAuthenticationSuccessHandler();
    }

    @Bean
    public AccessDeniedHandler myAccessDeniedHandler() {
        return new MyAccessDeniedHandler();
    }
}
