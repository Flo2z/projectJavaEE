package Shop.Online_Shop.rest;


import Shop.Online_Shop.service.impl.ProductServiceImpl;
import Shop.Online_Shop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @GetMapping
    public List<Product> getAllProducts(){
        return productServiceImpl.getAllProducts();
    }

    @GetMapping("{id}")
    public Product getProduct(@PathVariable(value = "id") Long id){
        return productServiceImpl.getProductById(id);
    }

    @GetMapping("/findProducts/{name}")
    public List<Product> searchProduct(@PathVariable(value = "name")String name){
        return productServiceImpl.searchProductByName(name);
    }

    @GetMapping("/sortedNameByAcs")
    public List<Product> sortedNameByAcs(){
        return productServiceImpl.sortedNameByAcs();
    }

    @GetMapping("/sortedNameByDesc")
    public List<Product> sortedNameByDesc(){
        return productServiceImpl.sortedNameByDesc();
    }

    @GetMapping("/sortedPriceByAcs")
    public List<Product> sortedPriceByAcs(){
        return productServiceImpl.sortedPriceByAcs();
    }

    @GetMapping("/sortedPriceByDesc")
    public List<Product> sortedProductByDesc(){
        return productServiceImpl.sortedPriceByDesc();
    }

    @GetMapping("/sumMark")
    public double sumMark(){
        return productServiceImpl.countMark();
    }

    @GetMapping("/conversionToRating")
    public String conversionToRating(){
        return productServiceImpl.conversionToRating();
    }


    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return productServiceImpl.addProduct(product);
    }

    @PutMapping("{id}")
    public Product updateProduct(@PathVariable(value = "id") Long id,@RequestBody Product product){
        return productServiceImpl.updateProduct(id,product);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable(value = "id")Long id){
        productServiceImpl.deleteProduct(id);
    }

}
