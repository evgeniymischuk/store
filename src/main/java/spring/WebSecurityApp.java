package spring;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityApp extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable().authorizeRequests()
                .antMatchers("/js").permitAll()
                .antMatchers("/css").permitAll()
                .antMatchers("/img").permitAll()
                .antMatchers("/svg").permitAll()
                .antMatchers("/admin").hasRole("manager")
                .antMatchers("/admin/orders").hasRole("manager")
                .antMatchers("/admin/refresh").hasRole("manager")
                .antMatchers("/clear-csv").hasRole("manager")
                .and()
                .formLogin();
    }
}
