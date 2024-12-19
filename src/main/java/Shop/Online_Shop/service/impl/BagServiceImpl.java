
package Shop.Online_Shop.service.impl;

import Shop.Online_Shop.service.BagService;
import Shop.Online_Shop.model.dto.BagDto;
import Shop.Online_Shop.mapper.BagMapper;
import Shop.Online_Shop.model.Product;
import Shop.Online_Shop.model.Purchase;
import Shop.Online_Shop.model.Bag;
import Shop.Online_Shop.model.User;
import Shop.Online_Shop.repository.ProductRepository;
import Shop.Online_Shop.repository.PurchaseRepository;
import Shop.Online_Shop.repository.BagRepository;
import Shop.Online_Shop.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BagServiceImpl implements BagService {

    @Autowired
    private BagRepository bagRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BagMapper bagMapper;
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public List<BagDto> getAllBagDto() {
        List<Bag> bagList = bagRepository.findAll();
        return bagMapper.bagListToDto(bagList);
    }

    @Override
    public BagDto getBagDto(Long id) {
        Bag bag = bagRepository.findById(id).orElseThrow();
        return bagMapper.bagToDto(bag);
    }

    @Override
    public void addProductInBag(Long productId, Long userId) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new EntityNotFoundException("Product not found with ID: " + productId));
        User user = userRepository.findById(userId).orElseThrow(() ->
                new EntityNotFoundException("User not found with ID: " + userId));

        Bag bag = bagRepository.findByUserIsCartId(userId);

        if (bag == null) {
            bag = new Bag();
            bag.setUserIsCart(user);
            bag.setTotalPrice(0);
            bag.setProductList(new ArrayList<>());
        }

        if (!bag.getProductList().contains(product)) {
            product.setQuantity(1);
            bag.getProductList().add(product);
        } else {
            for (Product p : bag.getProductList()) {
                if (p.getId().equals(productId)) {
                    p.setQuantity(p.getQuantity() + 1);
                    break;
                }
            }
        }

        bagRepository.save(bag);
    }



    @Override
    public void deleteProductInBag(Long productId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new EntityNotFoundException("User not found with ID: " + userId));

        Bag bag = bagRepository.findByUserIsCartId(userId);

        List<Product> productList = bag.getProductList();

        boolean removed = productList.removeIf(product -> product.getId().equals(productId));

        if (!removed) {
            throw new EntityNotFoundException("Product not found in the bag with ID: " + productId);
        }

        bagRepository.save(bag);
    }


    @Override
    public String checkout(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new EntityNotFoundException("User not found with ID: " + userId));

        Bag bag = bagRepository.findByUserIsCartId(userId);

        if (bag == null || bag.getProductList().isEmpty()) {
            return "Ваша корзина пуста!";
        }

        double totalPrice = calculateTotalPrice(bag);

        if (user.getBalance() >= totalPrice) {
            // Proceed with the purchase
            Purchase purchase = new Purchase();
            purchase.setUser(user);
            purchase.setProducts(new ArrayList<>(bag.getProductList())); // Copying the list
            purchase.setPurchaseTime(LocalDateTime.now());
            purchase.setTotalPrice(totalPrice);

            purchaseRepository.save(purchase);

            user.setBalance(user.getBalance() - totalPrice);

            userRepository.save(user);

            bag.getProductList().clear();
            bag.setTotalPrice(0);
            bagRepository.save(bag);

            return "Покупка успешно совершена!";
        } else {
            double requiredAmount = totalPrice - user.getBalance();
            return "Недостаточно средств для покупки! Вам не хватает " + requiredAmount + " KZT.";
        }
    }




    private double calculateTotalPrice(Bag bag) {
        double totalPrice = 0;
        List<Product> productList = bag.getProductList();
        for (Product product : productList) {
            totalPrice += product.getPrice() * product.getQuantity();
        }
        return totalPrice;
    }

    @Override
    public void createBagForUser(User user) {
        Bag bag = new Bag();
        bag.setUserIsCart(user);
        bag.setTotalPrice(0);
        bag.setProductList(new ArrayList<>());

        bagRepository.save(bag);
    }

}
