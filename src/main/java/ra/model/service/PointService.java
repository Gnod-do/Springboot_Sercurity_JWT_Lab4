package ra.model.service;

import ra.model.entity.Points;
import ra.model.entity.Users;

import java.util.List;

public interface PointService {
    List<Points> getAllPointsByUser(Users user);
}
