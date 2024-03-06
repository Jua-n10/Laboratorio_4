/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.hyunseda.market.access;

/**
 *
 * @author Felipe Castro
 */
public class FactoryCategory {
    private static FactoryCategory instance;
    
    private FactoryCategory() {
    }

    /**
     * Clase singleton
     *
     * @return
     */
    public static FactoryCategory getInstance() {

        if (instance == null) {
            instance = new FactoryCategory();
        }
        return instance;

    }

    /**
     * Método que crea una instancia concreta de la jerarquia IProductRepository
     *
     * @param type cadena que indica qué tipo de clase hija debe instanciar
     * @return una clase hija de la abstracción IProductRepository
     */
    public ICategoryRepository getRepository(String type) {

        ICategoryRepository result = null;

        switch (type) {
            case "default":
                result = new CategoryRepository();
                break;
        }

        return result;

    }
}
