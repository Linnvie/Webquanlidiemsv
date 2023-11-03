//package com.ptit.apiquanlidiem.config;
//
//import com.ptit.apiquanlidiem.exception.ResourceNotFoundException;
//import com.ptit.apiquanlidiem.repository.AccountRepository;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import static com.ptit.apiquanlidiem.vo.RoleEnum.*;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    public static final String[] AUTH_WHITELIST = {
//            "/api/v1/auth/login","/account","api/v1/*","api/v1/diem/*", "api/v1/mon","api/v1/mon/search",
//            "/api/v1/lop-tin-chi", "/api/v1/lop-tin-chi/search","/api/v1/lop-tin-chi/one","/api/v1/lop-tin-chi-all",
//            "api/v1/lop-tin-chi/đk-mon","api/v1/diem/*","/api/v1/diem/search/*","/api/v1/lop-tin-chi/nhap-diem",
//            "/api/v1/sinhvien",  "/api/v1/sinhvien/loptinchi", "/api/v1/sinhvien/lop","api/v1/allmon"
//            ,"api/v1/lop/search","api/v1/lop", "api/v1/accountGV",
//    };
//
//    @Autowired
//    private JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    @Autowired
//    private AccountRepository accountRepository;
//
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf().disable()
//                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .httpBasic().disable()
//                .formLogin().disable()
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers(AUTH_WHITELIST).permitAll()
//
//                        .requestMatchers("/api/v1/allgiangvien").hasRole(TEACHER.name())
//                        .requestMatchers("/api/v1/student").hasAnyRole(STUDENT.name())
//
//                        .anyRequest().authenticated()
//                )
//
//                .exceptionHandling()
//                .accessDeniedHandler(accessDeniedHandler())
//                .and()
//
//
//
//                .authenticationProvider(authenticationProvider())
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .build();
//    }
//
//
//    @Bean
//    public AccessDeniedHandler accessDeniedHandler() {
//        return (request, response, accessDeniedException) -> {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.setContentType("application/json");
//
//            StringBuilder errorStatus = new StringBuilder();
//
//            errorStatus.append("{");
//            errorStatus.append("\"status\": \"FORBIDDEN\",\n");
//            errorStatus.append("    \"code\": 403,\n");
//            errorStatus.append("    \"message\": \"Not have access\"");
//            errorStatus.append("}");
//
//            response.getWriter().write(errorStatus.toString());
//        };
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(
//            AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return (username -> accountRepository.findOneByUsername(username)
//                .orElseThrow(() -> new ResourceNotFoundException("Account " + username + " not found.")));
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService());
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//}
