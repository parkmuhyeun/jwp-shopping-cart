package cart.service;

import cart.dao.ProductDao;
import cart.dto.AddProductRequest;
import cart.dto.UpdateProductRequest;
import cart.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductDao productDao;

    public ProductService(final ProductDao productDao) {
        this.productDao = productDao;
    }

    public void addProduct(final AddProductRequest addProductRequest) {
        final Product product = new Product(
                addProductRequest.getName(),
                addProductRequest.getImageUrl(),
                addProductRequest.getPrice());
        productDao.save(product);
    }

    public void updateProduct(final UpdateProductRequest updateProductRequest) {
        final Product product = new Product(
                updateProductRequest.getId(),
                updateProductRequest.getName(),
                updateProductRequest.getImageUrl(),
                updateProductRequest.getPrice());
        productDao.update(product);
    }
}
