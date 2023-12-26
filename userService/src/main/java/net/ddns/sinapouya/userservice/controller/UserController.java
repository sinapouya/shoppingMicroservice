package net.ddns.sinapouya.userservice.controller;

import lombok.RequiredArgsConstructor;
import net.ddns.sinapouya.userservice.dto.Response;
import net.ddns.sinapouya.userservice.dto.TokenDto;
import net.ddns.sinapouya.userservice.dto.UserDto;
import net.ddns.sinapouya.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Response<UserDto>> generateToken(@RequestBody TokenDto tokenDto){
        UserDto userDto = userService.generateToken(tokenDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response<UserDto>(userDto));
    }

}
