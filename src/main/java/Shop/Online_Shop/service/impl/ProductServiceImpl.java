package Shop.Online_Shop.service.impl;

import Shop.Online_Shop.service.ProductService;

import Shop.Online_Shop.model.Product;
import Shop.Online_Shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Random;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Product> searchProductByName(String name) {
        return productRepository.findAllByNameIgnoreCaseContaining(name);
    }

    @Override
    public List<Product> sortedNameByAcs() {
        return productRepository.findAllByOrderByNameAsc();
    }

    @Override
    public List<Product> sortedNameByDesc() {
        return productRepository.findAllByOrderByNameDesc();
    }

    @Override
    public List<Product> sortedPriceByAcs() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    @Override
    public List<Product> sortedPriceByDesc() {
        return productRepository.findAllByOrderByPriceDesc();
    }

    @Override
    public double countMark() {
        double sum = 0;
        Random randomNumber = new Random();
        int randomNum = randomNumber.nextInt(51) + 10;

        for (int i = 10; i <= randomNum; i++) {
            Random mark = new Random();
            int randomMark = mark.nextInt(5) + 2;
            sum += randomMark;
        }
        return sum / randomNum;
    }

    @Override
    public String conversionToRating() {
        double result=countMark();
        if (0.1<result && result<=0.4){
            return "0.5";
        }
        else if(0.4<result && result<=1.0){
            return "1";
        }
        else if(1.0<result && result<=1.4){
            return "1.5";
        }
        else if(1.4<result && result<=2.0){
            return "2";
        }
        else if(2.0<result && result<=2.4){
            return "2.4";
        }
        else if(2.4<result && result<=3.0){
            return "3";
        }
        else if(3.0<result && result<=3.4){
            return "3.4";
        }
        else if(3.4<result && result<=4.0){
            return "4";
        }
        else if(4.0<result && result<=4.4){
            return "4.4";
        }
        else if(4.4<result && result<=5){
            return "5";
        }
        else if(result<0.1){
            return "0";
        }
        return null;
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            return productRepository.save(product);
        } else {
            throw new RuntimeException("Такого продукта не существует по данному id: " + id);
        }
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
