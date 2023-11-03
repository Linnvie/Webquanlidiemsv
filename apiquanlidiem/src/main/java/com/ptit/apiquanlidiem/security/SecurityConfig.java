
package com.ptit.apiquanlidiem.security;


import com.ptit.apiquanlidiem.CustomAuthenticationEntryPoint;
import com.ptit.apiquanlidiem.security.jwt.AuthTokenFilter;
import com.ptit.apiquanlidiem.security.service.impl.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.ptit.apiquanlidiem.vo.RoleEnum.ADMIN;
import static com.ptit.apiquanlidiem.vo.RoleEnum.TEACHER;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private AuthTokenFilter tokenFilter;

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    public static final String[] AUTH_WHITELIST = {
            "/api/v1/auth/login","/api/v1/accountforgetPw","/api/v1/bang-diem/excel"
//            "/account","api/v1/*", "api/v1/mon","api/v1/mon/search",
//            "/api/v1/lop-tin-chi", "/api/v1/lop-tin-chi/search","/api/v1/lop-tin-chi/one","/api/v1/lop-tin-chi-all",
//            "api/v1/lop-tin-chi/đk-mon","/api/v1/diem/search/*","/api/v1/lop-tin-chi/nhap-diem",
//              "/api/v1/sinhvien/loptinchi", "/api/v1/sinhvien/lop" ,"api/v1/allmon"
//            ,"api/v1/lop/search","api/v1/lop", "api/v1/accountGV", "/api/v1/giangvien/search", "/api/v1/giangvien/filter",
//            "/api/v1/diem/loptinchi",  "/api/v1/giangvien", "/api/v1/lop-tin-chi/giangvien", "/api/v1/quantrivien","/api/v1/allgiangvien",
//            "/api/v1/allnganh","/api/v1/alllop"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic().disable()
                .formLogin().disable()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(AUTH_WHITELIST).permitAll()

                        .requestMatchers("/api/v1/allgiangvien").hasAnyRole(ADMIN.getRole())
                      //  .requestMatchers(GET, "/api/v1/order1/**").hasAnyAuthority(ADMIN_CREATE.getPermission())

                        .anyRequest().authenticated()
                )


                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                .and()


                .authenticationProvider(authenticationProvider())
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                .and()
                .build();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");

            StringBuilder errorStatus = new StringBuilder();

            errorStatus.append("{");
            errorStatus.append("\"status\": \"FORBIDDEN\",\n");
            errorStatus.append("    \"code\": 403,\n");
            errorStatus.append("    \"message\": \"Not have access\"");
            errorStatus.append("}");

            response.getWriter().write(errorStatus.toString());
        };
    }


    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
