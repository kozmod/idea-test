package ru.idea.test.spring.boot.product.repo;

import org.springframework.stereotype.Repository;
import ru.idea.test.spring.boot.IdRepo;
import ru.idea.test.spring.boot.product.entity.Product;
import ru.idea.test.spring.boot.product.entity.ProductIdNotValidException;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;

@Repository
public class ProductRepo implements IdRepo<Long, Product> {

    private static final Map<Long, Product> productRepo = new ConcurrentSkipListMap<>();

    @PostConstruct
    private static void init() {
        Product honey = new Product()
                .setId(1L)
                .setName("Test_Product_1");
        productRepo.put(honey.getId(), honey);

        Product almond = new Product()
                .setId(2L)
                .setName("Test_Product_2");
        productRepo.put(almond.getId(), almond);
    }

    @Override
    public Collection<Product> getAll() {
        return productRepo.values();
    }

    @Override
    public Optional<Product> getById(Long id) {
        return Optional.ofNullable(productRepo.get(id));

    }

    @Override
    public Optional<Product> add(Product entity) {
        if (entity == null || entity.getId() == null) {
            throw new ProductIdNotValidException("Data to add not valid: " + entity);
        }
        return Optional.ofNullable(productRepo.put(entity.getId(), entity));
    }

    @Override
    public Product delete(Product entity) {
        if (entity == null || entity.getId() == null) {
            throw new ProductIdNotValidException("Data to delete not valid: " + entity);
        }
        return productRepo.remove(entity.getId());
    }

    @Override
    public Optional<Product> deleteById(Long id) {
        return Optional.ofNullable(productRepo.remove(id));
    }
}
