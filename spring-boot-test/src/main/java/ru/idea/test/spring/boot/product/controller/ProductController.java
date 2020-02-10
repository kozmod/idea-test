package ru.idea.test.spring.boot.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.idea.test.spring.boot.product.entity.Product;
import ru.idea.test.spring.boot.IdRepo;

@RestController
public class ProductController {

    private final IdRepo<Long, Product> repo;

    public ProductController(IdRepo<Long, Product> repo) {
        this.repo = repo;
    }

    @GetMapping(value = "/products")
    public ResponseEntity<Object> getProduct() {
        return new ResponseEntity<>(repo.getAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/products")
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        if (repo.getById(product.getId()).isPresent()) {
            throw new IllegalStateException(
                    String.format("Product with id=%s is already exist: %s", product.getId(), product)
            );
        }
        repo.add(product);
        return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
    }

    @PutMapping(value = "/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        if (id == null || product == null) {
            throw new IllegalArgumentException();
        }
        repo.deleteById(id);
        product.setId(id);
        repo.add(product);
        return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        return repo.deleteById(id)
                .map(p -> new ResponseEntity<Object>("Product is deleted successfully: " + p, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(String.format("Product with id=%s is not exists", id), HttpStatus.OK));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> validationError(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
