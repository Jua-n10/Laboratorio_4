/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.hyunseda.market.presentation;

import co.com.hyunseda.market.access.FactoryProduct;
import co.com.hyunseda.market.access.IProductRepository;
import co.com.hyunseda.market.service.DeliveryService;
import co.com.hyunseda.market.service.ProductService;
import co.unicauca.microkernel.common.entities.Delivery;
import java.util.Scanner;

/**
 *
 * @author ACER
 */
public class Consola {
      private ProductService productService;
    private DeliveryService deliveryService;
    IProductRepository ipr = FactoryProduct.getInstance().getRepository("default");
   
    private Scanner scanner;

    public Consola(){
        ProductService productService = new ProductService(ipr);
        deliveryService = new DeliveryService();
        scanner = new Scanner(System.in);
    }

    public void start() {

        int option;

        System.out.println("Aplicación de envíos");

        do {

            System.out.println();
            System.out.println("1. Calcular costo de envío.");
            System.out.println("2. Salir.");

            option = scanner.nextInt();

            switch (option) {
                case 1:
                    handleDeliveryCostOption();
                    break;
            }

        } while(option != 2);

        System.out.println("Aplicación terminada");
    }

    private void handleDeliveryCostOption(){

        //Mostrar producto para el cual se calculará el envío.
       // List<Product> products = productService.findAllProducts();

       // System.out.println("Seleccione un producto: ");

        
        //Seleccionar producto para el que se calculará el envío.
        //int productIndex = scanner.nextInt();
        

        System.out.println("ingrese el saldo");
        double saldo = scanner.nextDouble();

        //Leer salto de línea para que pueda pregunta por el código del país. (https://stackoverflow.com/a/13102066/1601530)
        scanner.nextLine();

        System.out.println("Código del medio de pago: ");
        String countryCode = scanner.nextLine();
        
        System.out.println("ingrese el valor producto: ");
        double valor = scanner.nextDouble();
        //Creamos el objeto que será pasado a la capa de dominio para que se haga el cálculo.
        Delivery deliveryEntity = new Delivery(saldo, countryCode, valor);

        try {
            if(deliveryService.calculateDeliveryCost(deliveryEntity)){
                 System.out.println("transaccion correcta !! , el metodo de pago fue " + countryCode);
            }else{
                 System.out.println("saldo insuficiente");
            }
           // boolean cost = deliveryService.calculateDeliveryCost(deliveryEntity);
           

        } catch (Exception exception) {
            System.out.println("No fue posible calcular el costo del envío. " + exception.getMessage());
        }



    }
}
