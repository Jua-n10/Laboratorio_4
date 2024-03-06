/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.hyunseda.market.access;

import co.com.hyunseda.market.market.Category;
import co.com.hyunseda.market.market.Product;
import co.com.hyunseda.market.service.ProductService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe Castro
 */
public class ProductRepository implements IProductRepository {
    private Connection conn;
    
    public ProductRepository() { initDatabase();}
    
    
    @Override
    public boolean save(Product newProduct) {

        try {
            //Validate product
            if (newProduct == null || newProduct.getName().isEmpty()) {
                return false;
            }
            //this.connect();

            String sql = "INSERT INTO products ( name, description, slug, price, stock, keywords, images, characteristics, categories ) "
                    + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? )";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newProduct.getName());
            pstmt.setString(2, newProduct.getDescription());
            pstmt.setString(3, newProduct.getSlug());
            pstmt.setString(4, String.valueOf(newProduct.getPrice()));
            pstmt.setString(5, String.valueOf(newProduct.getStock()));
            pstmt.setString(6, newProduct.getKeywords());
            pstmt.setString(7, newProduct.getImages());
            pstmt.setString(8, newProduct.getCharacteristics());
            pstmt.setString(9, newProduct.getConqCategories());
            
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try {

            String sql = "SELECT * FROM products";
            //this.connect();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Product newProduct = new Product();
                newProduct.setProductId(rs.getLong("productId"));
                newProduct.setName(rs.getString("name"));
                newProduct.setDescription(rs.getString("description"));
                newProduct.setSlug(rs.getString("slug"));
                newProduct.setPrice(Double.parseDouble(rs.getString("price")));
                newProduct.setStock(Integer.parseInt(rs.getString("stock")));
                newProduct.setKeywords(rs.getString("keywords"));
                newProduct.setImages(rs.getString("images"));
                newProduct.setCharacteristics(rs.getString("characteristics"));
                
                String categories = rs.getString("categories");
                String[] cats = categories.split(",");
                
                List<Category> categories2 = new ArrayList<>();
                for(String cat: cats){
                    Category local = new Category();
                    local.setName(cat);
                    categories2.add(local);
                }
                newProduct.setCategories(categories2);
                
                

                products.add(newProduct);
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    private void initDatabase() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS products (\n"
                + "	productId integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	name text NOT NULL,\n"
                + "	description text NULL\n,"
                + "	slug text NULL\n,"
                + "	price text NULL\n,"
                + "	stock text NULL\n,"
                + "	keywords text NULL\n,"
                + "	images text NULL\n,"
                + "	characteristics text NULL\n,"
                + "	categories text NULL\n"   
                + ");";

        try {
            this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void connect() {
        // SQLite connection string
        //String url = "jdbc:sqlite:./myDatabase.db"; //Para Linux/Mac
        //String url = "jdbc:sqlite:C:/sqlite/db/myDatabase.db"; //Para Windows
        String url = "jdbc:sqlite::memory:";

        try {
            conn = DriverManager.getConnection(url);

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public boolean edit(Long id, Product product) {
        boolean c = true;
        try {
            //Validate product
            if (id <= 0 || product == null) {
                c = false;
            }else{
                c = true;
            }
            //this.connect();

            String sql = "UPDATE  products "
                    + "SET name=?, description=?, slug=?, price=?, stock=?, keywords=?, images=?, characteristics=?, categories=?"
                    + "WHERE productId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setString(3, product.getSlug());
            pstmt.setDouble(4, product.getPrice());
            pstmt.setInt(5, product.getStock());
            pstmt.setString(6, product.getKeywords());
            pstmt.setString(7, product.getImages());
            pstmt.setString(8, product.getCharacteristics());
            pstmt.setString(9, product.getConqCategories());
            pstmt.setLong(10, id);
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try {
            //Validate product
            if (id <= 0) {
                return false;
            }
            //this.connect();

            String sql = "DELETE FROM products "
                    + "WHERE productId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Product findById(Long id) {
        try {
            
            String sql = "SELECT * FROM products  "
                    + "WHERE productId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                Product prod = new Product();
                prod.setProductId(res.getLong("productId"));
                prod.setName(res.getString("name"));
                prod.setDescription(res.getString("description"));
                prod.setSlug(res.getString("slug"));
                prod.setPrice(Double.parseDouble(res.getString("price")));
                prod.setStock(Integer.parseInt(res.getString("stock")));
                prod.setKeywords(res.getString("keywords"));
                prod.setImages(res.getString("images"));
                prod.setCharacteristics(res.getString("characteristics"));
                
                String categories = res.getString("categories");
                String[] cats = categories.split(",");
                
                List<Category> categories2 = new ArrayList<>();
                for(String cat: cats){
                    Category local = new Category();
                    local.setName(cat);
                    categories2.add(local);
                }
                prod.setCategories(categories2);
                return prod;
            } else {
                return null;
            }
            //this.disconnect();

        } catch (SQLException ex) {
            System.out.println("entra a la excepcion");
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public List<Product> findByName(String name) {
        List<Product> products = new ArrayList<>();
        try {
            
            String sql = "SELECT * FROM products  "
                    + "WHERE name like ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name+"%");

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Product newProduct = new Product();
                newProduct.setProductId(rs.getLong("productId"));
                newProduct.setName(rs.getString("name"));
                newProduct.setDescription(rs.getString("description"));
                newProduct.setSlug(rs.getString("slug"));
                newProduct.setPrice(Double.parseDouble(rs.getString("price")));
                newProduct.setStock(Integer.parseInt(rs.getString("stock")));
                newProduct.setKeywords(rs.getString("keywords"));
                newProduct.setImages(rs.getString("images"));
                newProduct.setCharacteristics(rs.getString("characteristics"));
                
                String categories = rs.getString("categories");
                String[] cats = categories.split(",");
                
                List<Category> categories2 = new ArrayList<>();
                for(String cat: cats){
                    Category local = new Category();
                    local.setName(cat);
                    categories2.add(local);
                }
                newProduct.setCategories(categories2);
                
                

                
                products.add(newProduct);
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    
    
   public List<Product> findByCat(List<String> catSelect) {
        List<Product> products = new ArrayList<>();
        List<Product> products2 = new ArrayList<>();
        List<Product> lprods = findAll();
        for(String strselected : catSelect){//cat1: prod1 prod2 prod3  cat2: prod1 prod2
            for(Product prod : lprods){
                boolean d = false;
                String cats = prod.getConqCategories();
                System.out.println("cats: "+cats);
                if(!cats.isEmpty()){
                    String[] vcats = cats.split(" ");
                    for(String cat: vcats){
                        System.out.println("Producto: "+cat+"  Seleccionado: "+strselected);
                        if(cat.equals(strselected)){
                            System.out.println("Entra");
                            if(products.isEmpty()){
                                System.out.println("Entra null");
                                products.add(prod);
                            }else{
                                for(Product prodadded : products2){
                                    System.out.println("Entra2");
                                    if(prodadded.equals(prod)){
                                        System.out.println("uno igual");
                                        d = true;
                                        break;
                                    }
                                }
                                if(d == false){
                                    products.add(prod);
                                }
                            }
                            products2.add(prod);
                            
                        }
                    }
                }else{
                    System.out.println("La cadena no tiene nada");
                }
                
            }
        }
        return products;
    } 
    
   
}