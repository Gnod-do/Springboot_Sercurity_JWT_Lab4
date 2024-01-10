package ra.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ra.jwt.JwtTokenProvider;
import ra.model.Credentials.PointsCredentials;
import ra.model.Credentials.validator.PointCredentialsValidator;
import ra.model.entity.Points;
import ra.model.entity.Users;
import ra.model.repository.UserRepository;
import ra.model.serviceImp.PointServiceImp;
import ra.payload.response.MessageResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@Slf4j
@Controller
    @CrossOrigin(origins = "*")
@RequestMapping(path = "${apiPrefix}/auth")
public class PointController {

    private final PointServiceImp pointServiceImp;

    private final PointCredentialsValidator pointCredentialsValidator;

    private final UserRepository userRepository;

    private JwtTokenProvider jwtTokenProvider;


    public PointController(PointServiceImp pointServiceImp, PointCredentialsValidator pointCredentialsValidator, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.pointServiceImp = pointServiceImp;
        this.pointCredentialsValidator = pointCredentialsValidator;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(pointCredentialsValidator);
    }


    // Put and get point of user
    @PostMapping("/point")
    public ResponseEntity<?> setPoint(@RequestBody PointsCredentials point, @RequestHeader(value = "Authorization", required = true) String token, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
        point.setResult(point.getX(), point.getY(), point.getR());
        try {
            pointServiceImp.register(point, token);
        }catch (IllegalArgumentException e){
            log.error("The data sent is not in the correct format");
            Map<String,String> resp = new HashMap<>();
            resp.put("error","The data sent is not in the correct format");
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(resp);
        }
        List<Points> points = pointServiceImp.getAllPointsByUser(userRepository.getUsersByUserName(jwtTokenProvider.getUserNameFromJWT(token)));
        StringJoiner joiner = new StringJoiner(",");
        for (Points point1 : points){
            StringBuilder builder = new StringBuilder();
            builder.append("{\"x\":\"");
            builder.append(String.format("%.2f", point1.getX()));
            builder.append("\", \"y\":\"");
            builder.append(String.format("%.2f", point1.getY()));
            builder.append("\", \"r\":\"");
            builder.append(String.format("%.2f", point1.getR()));
            builder.append("\", \"result\":\"");
            builder.append(point1.getResult());
            builder.append("\"}");
            joiner.add(builder.toString());
        }
        return ResponseEntity.ok("[" + joiner.toString() + "]");
    }

    @PostMapping("/getAllPoint")
    public ResponseEntity<?> getAllPoint(@RequestHeader(value = "Authorization", required = true) String token){
        Users user = userRepository.getUsersByUserName(jwtTokenProvider.getUserNameFromJWT(token));
        if (user == null){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: You do not have access to this resource because you are not logged in"));
        }
        List<Points> points = pointServiceImp.getAllPointsByUser(user);
        StringJoiner joiner = new StringJoiner(",");
        for (Points point1 : points) {
            StringBuilder builder = new StringBuilder();
            builder.append("{\"x\":\"");
            builder.append(String.format("%.2f",point1.getX()));
            builder.append("\", \"y\":\"");
            builder.append(String.format("%.2f",point1.getY()));
            builder.append("\", \"r\":\"");
            builder.append(String.format("%.2f",point1.getR()));
            builder.append("\", \"result\":\"");
            builder.append(point1.getResult());
            builder.append("\"}");
            joiner.add(builder.toString());
        }
        return ResponseEntity.ok("[" + joiner.toString() + "]");
    }

}
