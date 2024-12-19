
package Shop.Online_Shop.repository;

import Shop.Online_Shop.model.Bag;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Transactional
@Repository
public interface BagRepository extends JpaRepository<Bag,Long> {
    Bag findByUserIsCartId(Long userId);
}

