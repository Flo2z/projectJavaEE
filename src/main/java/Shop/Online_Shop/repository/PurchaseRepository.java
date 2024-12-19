package Shop.Online_Shop.repository;

import Shop.Online_Shop.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PurchaseRepository extends JpaRepository<Purchase,Long> {
    List<Purchase> findByUserId(Long userId);
}
