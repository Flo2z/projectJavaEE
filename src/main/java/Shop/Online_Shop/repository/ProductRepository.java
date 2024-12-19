package Shop.Online_Shop.repository;

import Shop.Online_Shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByNameIgnoreCaseContaining(String name);

    List<Product> findAllByOrderByPriceAsc();

    List<Product> findAllByOrderByPriceDesc();

    List<Product> findAllByOrderByNameAsc();

    List<Product> findAllByOrderByNameDesc();

    boolean existsById(Long id);

}
