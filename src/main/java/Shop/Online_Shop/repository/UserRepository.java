package Shop.Online_Shop.repository;

import Shop.Online_Shop.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Transactional
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    boolean existsById(Long id);
    List<User> findAllByFullNameIgnoreCaseContaining(String fullName);
}
