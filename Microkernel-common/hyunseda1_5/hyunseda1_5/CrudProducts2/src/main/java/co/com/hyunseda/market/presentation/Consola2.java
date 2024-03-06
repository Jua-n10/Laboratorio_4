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
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class Consola2 {
     private ProductService productService;
    private DeliveryService deliveryService;
    IProductRepository ipr = FactoryProduct.getInstance().getRepository("default");
   
    private Scanner scanner;

    public Consola2(){
        ProductService productService = new ProductService(ipr);
        deliveryService = new DeliveryService();
        scanner = new Scanner(System.in);
    }

 

    public void handleDeliveryCostOption(double saldo,String tipoPay,double valorCompra){

        //Creamos el objeto que será pasado a la capa de dominio para que se haga el cálculo.
        Delivery deliveryEntity = new Delivery(saldo, tipoPay, valorCompra);

        try {
            if(deliveryService.calculateDeliveryCost(deliveryEntity)){
                 System.out.println("transaccion correcta !! , el metodo de pago fue " + tipoPay);
                JOptionPane.showMessageDialog(null, "transaccion correcta", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }else{
                 System.out.println("saldo insuficiente");
                 JOptionPane.showMessageDialog(null, "saldo insuficiente", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
           // boolean cost = deliveryService.calculateDeliveryCost(deliveryEntity);
           

        } catch (Exception exception) {
            System.out.println("No fue posible realizar la transaccion . " + exception.getMessage());
        }


    }
}
