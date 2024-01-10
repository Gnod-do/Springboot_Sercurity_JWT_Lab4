package ra.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ra.model.Credentials.PointsCredentials;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "${apiPrefix}/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess(){
        return "This is public content";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess(){
        return "This is user content";
    }


    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public String moderatorAccess(){
        return "This is mod content";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess(){
        return "This is admin content";
    }

//    @PostMapping("/header")
//    public String sendByHeader(@RequestHeader("token") String token, @RequestBody String point){
//        StringBuilder stringBuilder = new StringBuilder(token + point);
//        return stringBuilder.toString();
//    }





}
