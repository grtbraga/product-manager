package com.myproject.product_manager.controller;

import com.myproject.product_manager.model.Category;
import com.myproject.product_manager.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.myproject.product_manager.repository.ProductRepository;
import com.myproject.product_manager.repository.CategoryRepository;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String listProducts(
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
            @RequestParam(value = "categoryPath", required = false) String categoryPath,
            @RequestParam(value = "available", required = false) Boolean available,
            Model model) {
        Sort sort = Sort.by(sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);

        List<Product> products;
        if (categoryPath != null && !categoryPath.isEmpty() && available != null) {
            products = productRepository.findByCategoryPathAndAvailable(categoryPath, available, sort);
        } else if (categoryPath != null && !categoryPath.isEmpty()) {
            products = productRepository.findByCategoryPath(categoryPath, sort);
        } else if (available != null) {
            products = productRepository.findByAvailable(available, sort);
        } else {
            products = productRepository.findAll(sort);
        }

        Product product = new Product();
        product.setCategory(new Category());
        model.addAttribute("products", products);
        model.addAttribute("product", product);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("categoryPath", categoryPath);
        model.addAttribute("available", available);

        List<String> categoryPaths = productRepository.findDistinctCategoryPaths();
        model.addAttribute("categoryPaths", categoryPaths);

        return "products";
    }

    @PostMapping
    public String addProduct(@ModelAttribute Product product) {
        String categoryName = product.getCategory().getName().trim();
        if (categoryName.isEmpty()) {
            product.setCategory(null);
            product.setCategoryPath(null);
        } else {
            Category category = categoryRepository.findByNameIgnoreCase(categoryName)
                    .orElseGet(() -> {
                        Category newCategory = new Category(categoryName);
                        return categoryRepository.save(newCategory);
                    });
            product.setCategory(category);
            product.setCategoryPath(category.getCategoryPath());
        }

        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Product product) {
        Product existingProduct = productRepository.findById(product.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + product.getId()));

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setAvailable(product.getAvailable());

        String categoryName = product.getCategory().getName().trim();
        if (categoryName.isEmpty()) {
            existingProduct.setCategory(null);
            existingProduct.setCategoryPath(null);
        } else {
            Category category = categoryRepository.findByNameIgnoreCase(categoryName)
                    .orElseGet(() -> {
                        Category newCategory = new Category(categoryName);
                        return categoryRepository.save(newCategory);
                    });
            existingProduct.setCategory(category);
            existingProduct.setCategoryPath(category.getCategoryPath());
        }

        productRepository.save(existingProduct);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }
}