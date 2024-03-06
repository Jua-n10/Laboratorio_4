/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.microkernel.plugins.paysimulated;

import co.unicauca.microkernel.common.entities.Delivery;
import co.unicauca.microkernel.common.interfaces.IDeliveryPlugin;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class PaysimulatedDeliveryPlugin implements IDeliveryPlugin{

    @Override
    public boolean calculateCost(Delivery delivery) {
       
        if(delivery.getAmount() > delivery.getValor()){
            delivery.setAmount(delivery.getAmount()- delivery.getValor());
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "Saldo Insuficiente", "Advertencia", JOptionPane.WARNING_MESSAGE);
             return false;
        }
     
    }
    
    
}
