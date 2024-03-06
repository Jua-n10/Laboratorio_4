/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.com.hyunseda.market.access;

import co.com.hyunseda.market.market.Category;
import java.util.List;

/**
 *
 * @author Felipe Castro
 */
public interface ICategoryRepository {
    //administrador
    boolean add(Category cat);
    boolean edit(Long id, Category category);
    void delete();
    Category findById(Long id);
    List<Category> findAll();
    //visitante
    
    
}
