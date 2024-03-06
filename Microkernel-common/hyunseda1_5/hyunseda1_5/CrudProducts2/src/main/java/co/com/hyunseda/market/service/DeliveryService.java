/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.hyunseda.market.service;

import co.unicauca.microkernel.common.entities.Delivery;
import co.unicauca.microkernel.common.interfaces.IDeliveryPlugin;
import co.unicauca.microkernel.plugin.manager.DeliveryPluginManager;

/**
 *
 * @author ACER
 */
public class DeliveryService {
    public boolean calculateDeliveryCost(Delivery deliveryData) throws Exception {

        String PaymentMethod = deliveryData.getCountryCode();
        DeliveryPluginManager manager = DeliveryPluginManager.getInstance();
       IDeliveryPlugin plugin = manager.getDeliveryPlugin(PaymentMethod);

        if (plugin == null) {
            throw new Exception("No hay un plugin disponible para la forma de pago indicado: " + PaymentMethod);
        }

        return plugin.calculateCost(deliveryData);

    }
}
