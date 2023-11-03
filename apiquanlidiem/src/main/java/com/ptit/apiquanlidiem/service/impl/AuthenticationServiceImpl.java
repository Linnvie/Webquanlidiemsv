package com.ptit.apiquanlidiem.service.impl;

import com.ptit.apiquanlidiem.dto.AccountDtoReq;
import com.ptit.apiquanlidiem.dto.RequestLoginDto;
import com.ptit.apiquanlidiem.dto.ResponseLoginDto;
import com.ptit.apiquanlidiem.entity.AccountEntity;
import com.ptit.apiquanlidiem.exception.NotActivatedExceptionHandler;
import com.ptit.apiquanlidiem.exception.ResourceNotFoundException;
import com.ptit.apiquanlidiem.repository.AccountRepository;
import com.ptit.apiquanlidiem.security.jwt.JwtUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;


@Service
public class AuthenticationServiceImpl {

    @Autowired
    private AccountRepository accRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    private JwtTokenUtil jwt;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseLoginDto authenticate(RequestLoginDto request) throws UnsupportedEncodingException {
        request.setUsername(request.getUsername().toUpperCase());
//        AccountEntity user = accRepo.findOneByUsername(request.getUsername().toUpperCase()).orElse(null);
//
//        if(user!=null && user.getStatus()) {
//            Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername().toUpperCase(), request.getPassword()));
//            Collection<SimpleGrantedAuthority> authorities= new ArrayList<>();
//            authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
//            //Collections.unmodifiableList(Arrays.asList(new SimpleGrantedAuthority(authorities.toString())));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            String accessToken = jwt.generateAccessToken(modelMapper.map(user, AccountDtoReq.class));
//            String refreshToken = jwt.generateRefreshToken(modelMapper.map(user, AccountDtoReq.class));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            return new ResponseLoginDto(request.getUsername(),accessToken, refreshToken, user.getRole());
//        }
//        return null;

        AccountEntity user = this.accRepo
                .findOneByUsername(request.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User with email " + request.getUsername() + " not found!"));
        if (!user.getStatus()) {
            throw new NotActivatedExceptionHandler("Your account is not activated!");
        }

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Sai username hoặc mật khẩu!");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername() ,
                        request.getPassword()));

        String accessToken = jwtUtils.generateTokenFromUsername(request.getUsername());

        String refreshToken =jwtUtils.generateTokenFromUsername("linh");

        return new ResponseLoginDto(accessToken,refreshToken, user.getRole().getId());

    }
}
