package com.example.zzan.user.controller;


import com.example.zzan.global.security.UserDetailsImpl;
import com.example.zzan.global.util.JwtUtil;
import com.example.zzan.user.dto.*;
import com.example.zzan.user.service.KakaoService;
import com.example.zzan.user.service.MailService;
import com.example.zzan.user.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final KakaoService kakaoService;
    private final MailService mailService;

    @GetMapping("/login/oauth2/code/kakao")
    public String kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        String createToken = kakaoService.kakaoLogin(code, response);

        Cookie cookie = new Cookie(JwtUtil.AUTHORIZATION_HEADER, createToken.substring(7));
        cookie.setPath("/");
        response.addCookie(cookie);

        return "index";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody UserRequestDto requestDto){
        return userService.signup(requestDto);
    }

    @PostMapping("/signup/mailconfirm")
    @ResponseBody
    public MailResponseDto mailConfirm(@RequestBody MailRequestDto mailRequestDto) throws Exception {
        String code = mailService.sendSimpleMessage(mailRequestDto.getEmail());
        return new MailResponseDto(code);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto requestDto, HttpServletResponse response) {
        return userService.login(requestDto, response);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(@AuthenticationPrincipal UserDetailsImpl userDetails) {

        return userService.logout(userDetails.getUser());
    }
}