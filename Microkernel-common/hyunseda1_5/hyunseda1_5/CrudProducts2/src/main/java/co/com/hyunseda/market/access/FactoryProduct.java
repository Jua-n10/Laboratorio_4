/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.hyunseda.market.access;

/**
 *
 * @author Felipe Castro
 */
public class FactoryProduct {
    private static FactoryProduct instance;

    private FactoryProduct() {
    }

    /**
     * Clase singleton
     *
     * @return
     */
    public static FactoryProduct getInstance() {

        if (instance == null) {
            instance = new FactoryProduct();
        }
        return instance;

    }

    /**
     * Método que crea una instancia concreta de la jerarquia IProductRepository
     *
     * @param type cadena que indica qué tipo de clase hija debe instanciar
     * @return una clase hija de la abstracción IProductRepository
     */
    public IProductRepository getRepository(String type) {

        IProductRepository result = null;

        switch (type) {
            case "default":
                result = new ProductRepository();
                break;
        }

        return result;

    }
}
