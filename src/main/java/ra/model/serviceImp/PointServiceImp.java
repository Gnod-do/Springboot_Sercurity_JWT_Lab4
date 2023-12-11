package ra.model.serviceImp;

import org.springframework.stereotype.Service;
import ra.jwt.JwtTokenProvider;
import ra.model.Credentials.PointsCredentials;
import ra.model.entity.Points;
import ra.model.entity.Users;
import ra.model.repository.PointRepository;
import ra.model.repository.UserRepository;
import ra.model.service.PointService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class PointServiceImp implements PointService {

    private final UserRepository userRepository;

    private PointRepository pointRepository;

    private JwtTokenProvider jwtTokenProvider;

    public PointServiceImp(UserRepository userRepository, PointRepository pointRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.pointRepository = pointRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public List<Points> getAllPointsByUser(Users user) {
        return pointRepository.getPointsByUser(user);
    }

    @NotEmpty
    @NotNull
    public Points register(PointsCredentials pointsCredentials) throws IllegalArgumentException {
        Points point = new Points();
        point.setX(Double.parseDouble(pointsCredentials.getX()));
        point.setY(Double.parseDouble(pointsCredentials.getY()));
        point.setR(Double.parseDouble(pointsCredentials.getR()));
        point.setUser(userRepository.getUsersByUserName(jwtTokenProvider.getUserNameFromJWT(pointsCredentials.getToken())));
        if (point.getUser() == null) {
            throw new IllegalArgumentException();
        }
        point.setResult(pointsCredentials.isResult());
        pointRepository.save(point);
        return point;
    }
}
