/*
 * package app.security;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.context.properties.EnableConfigurationProperties;
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter; import
 * org.springframework.security.config.http.SessionCreationPolicy; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.security.crypto.password.PasswordEncoder;
 * 
 * import app.config.MongoUserDetailsService;
 * 
 * @EnableWebSecurity
 * 
 * @Configuration
 * 
 * @EnableConfigurationProperties public class SecurityConfiguration extends
 * WebSecurityConfigurerAdapter {
 * 
 * @Autowired MongoUserDetailsService userDetailsService;
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception { http
 * .csrf(); http
 * .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
 * http .cors().disable() .authorizeRequests()
 * .antMatchers("/user/**").permitAll() .anyRequest().authenticated()
 * .and().httpBasic() .and().sessionManagement().disable(); }
 * 
 * @Bean public PasswordEncoder passwordEncoder() { return new
 * BCryptPasswordEncoder(); }
 * 
 * @Override public void configure(AuthenticationManagerBuilder builder) throws
 * Exception { builder.userDetailsService(userDetailsService); } }
 */