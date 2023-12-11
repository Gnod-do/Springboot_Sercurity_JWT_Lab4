package ra.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ ")
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


}
