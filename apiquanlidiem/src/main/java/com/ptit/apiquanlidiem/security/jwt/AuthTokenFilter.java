package com.ptit.apiquanlidiem.security.jwt;


import com.ptit.apiquanlidiem.exception.AuthorizeException;
import com.ptit.apiquanlidiem.security.service.impl.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public static final String[] AUTH_WHITELIST = {
            "/api/v1/auth/login","/api/v1/accountforgetPw","/api/v1/bang-diem/excel"
//            "/account","api/v1/*", "api/v1/mon","api/v1/mon/search",
//            "/api/v1/lop-tin-chi", "/api/v1/lop-tin-chi/search","/api/v1/lop-tin-chi/one","/api/v1/lop-tin-chi-all",
//            "api/v1/lop-tin-chi/đk-mon","/api/v1/diem/search/*","/api/v1/lop-tin-chi/nhap-diem",
//              "/api/v1/sinhvien/loptinchi", "/api/v1/sinhvien/lop" ,"api/v1/allmon"
//            ,"api/v1/lop/search","api/v1/lop", "api/v1/accountGV", "/api/v1/giangvien/search", "/api/v1/giangvien/filter",
//            "/api/v1/diem/loptinchi",  "/api/v1/giangvien", "/api/v1/lop-tin-chi/giangvien", "/api/v1/quantrivien",
//            "/api/v1/allgiangvien","/api/v1/allnganh" ,"/api/v1/alllop"
    };

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String authHeader = request.getHeader("Authorization");
            String token = null;
            String username = null;
            boolean check = false;

            check = Arrays.stream(AUTH_WHITELIST)
                    .anyMatch(request.getRequestURI()::contains);

            if (authHeader != null && authHeader.startsWith("Bearer ") && !check) {
                token = authHeader.substring(7);
                username = jwtUtils.extractUserName(token);
            }


            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null && !check) {

                if (!jwtUtils.isTokenInvalidated(token)) {

                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    if (jwtUtils.validateToken(token, userDetails)) {
                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(userDetails,
                                        null,
                                        userDetails.getAuthorities());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                        check = true;
                    }
                }
            }

            if(!check) {
                throw new AuthorizeException("UNAUTHORIZED");
            }

        } catch (AuthorizeException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");

            StringBuilder errorStatus = new StringBuilder();

            errorStatus.append("{");
            errorStatus.append("\"status\": \"FORBIDDEN\",\n");
            errorStatus.append("    \"code\": 403,\n");
            errorStatus.append("    \"message\": \"Unauthorized\"");
            errorStatus.append("}");

            response.getWriter().write(errorStatus.toString());
            return;
        }

        filterChain.doFilter(request, response);
    }
}
