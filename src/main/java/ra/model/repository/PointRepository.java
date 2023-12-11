package ra.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Points;
import ra.model.entity.Users;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Points, Integer> {
    List<Points> getPointsByUser(Users user);

}
