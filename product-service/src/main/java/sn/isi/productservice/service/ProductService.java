package sn.isi.productservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sn.isi.productservice.model.Product;
import sn.isi.productservice.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }


    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> updateProduct(int id, Product productDetails) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setReference(productDetails.getReference());
                    existingProduct.setDesignation(productDetails.getDesignation());
                    existingProduct.setQuantite(productDetails.getQuantite());
                    existingProduct.setPrix(productDetails.getPrix());
                    existingProduct.setCategorie(productDetails.getCategorie());
                    return productRepository.save(existingProduct);
                });
    }

    public boolean deleteProduct(int id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Product> searchProductsByKeyword(String keyword) {
        return productRepository.findByDesignationContainingIgnoreCase(keyword);
    }
    public List<Product> getProductsByCategory(int categoryId) {
        return productRepository.findByCategorieId(categoryId);
    }
}