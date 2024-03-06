/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.com.hyunseda.market.access;

import co.com.hyunseda.market.market.Product;
import java.util.List;

/**
 *
 * @author Felipe Castro
 */
public interface IProductRepository {
    boolean save(Product newProduct);
    List<Product> findAll();
    boolean edit(Long id, Product product);
    public boolean delete(Long id);
    Product findById(Long id);
    List<Product> findByName(String name);
    List<Product> findByCat(List<String> catSelect);
}
