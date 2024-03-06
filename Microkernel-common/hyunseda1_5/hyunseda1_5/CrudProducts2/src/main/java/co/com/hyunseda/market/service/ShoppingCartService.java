/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.hyunseda.market.service;

import co.com.hyunseda.market.infra.Subject;
import co.com.hyunseda.market.market.ItemShoppingCart;
import co.com.hyunseda.market.market.ShoppingCart;

/**
 *
 * @author X1605
 */
public class ShoppingCartService extends Subject{
    private ShoppingCart shoppingCar;

    public ShoppingCartService(ShoppingCart shoppingCar) {
        this.shoppingCar = shoppingCar;
    }
   
   
   public ShoppingCart getShoppingCar(){
       return shoppingCar;
   }
   public boolean addItem(ItemShoppingCart item){
       this.shoppingCar.addItem(item);
        this.notifyAllObserves();
        return true;
    }
   
   public double calculateTotal(){
       double total = 0;
       for(ItemShoppingCart items: shoppingCar.getItems()){
           total = total + items.getProduct().getPrice() * items.getAmount();
       }
       return total;
   }
}
