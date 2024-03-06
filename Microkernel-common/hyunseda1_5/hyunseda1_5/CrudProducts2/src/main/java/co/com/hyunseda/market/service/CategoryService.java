/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.hyunseda.market.service;

import co.com.hyunseda.market.access.ICategoryRepository;
import co.com.hyunseda.market.access.IProductRepository;
import co.com.hyunseda.market.market.Category;
import co.com.hyunseda.market.market.Product;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe Castro
 */
public class CategoryService {
    private ICategoryRepository repository;
    //private IProductRepository repository;
    //private ICategoryRepository repositoryCategory;
    /**
     * Constructor que inicia la base de datos
     * @author Libardo, Julio
     */
    public CategoryService(ICategoryRepository categoryRepository) {
        this.repository = categoryRepository;
    }
    
    public boolean addCategory(String nombre){
        Category newCat = new Category();//Es necesario aplicar la regla de inversion de dependencias?
        
        newCat.setName(nombre);
        
        //Validate product
        if (newCat.getName().isEmpty() ) {
            return false;
        }
        repository.add(newCat);
        return true;
        
    }
    
    public boolean editCategory(Long categoryID, Category category) {
        
        //Validate product
        if (category == null || category.getName().isEmpty() ) {
            return false;
        }
        return repository.edit(categoryID, category);
    }
    
    public Category findCategoryById(Long id){
        return repository.findById(id);
    }
    
    public List<Product> findAllProductsByCategory() {
        List<Product> products = new ArrayList<>();
        //products = repository.findAll();

        return products;
    }
    
    public List<Category> findAllCategories() {
        List<Category> categories = new ArrayList<>();
        categories = repository.findAll();

        return categories;
    }
}
