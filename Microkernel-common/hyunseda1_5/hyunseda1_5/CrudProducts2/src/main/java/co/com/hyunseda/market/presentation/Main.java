
package co.com.hyunseda.market.presentation;

import co.com.hyunseda.market.access.FactoryCategory;
import co.com.hyunseda.market.access.FactoryProduct;
import co.com.hyunseda.market.access.ICategoryRepository;
import co.com.hyunseda.market.access.IProductRepository;
import co.com.hyunseda.market.presentation.GUIProducts;
import co.com.hyunseda.market.service.CategoryService;
import co.com.hyunseda.market.service.ProductService;
import co.unicauca.microkernel.plugin.manager.DeliveryPluginManager;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Libardo Pantoja
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        IProductRepository ipr = FactoryProduct.getInstance().getRepository("default");
        ICategoryRepository cr = FactoryCategory.getInstance().getRepository("default");
        
        CategoryService categoryService = new CategoryService(cr);
        ProductService productService = new ProductService(ipr);
        
        GUIInicio instanceI = new GUIInicio(categoryService, productService );
        GUIProducts instance = new GUIProducts(categoryService, productService);
        GUICategory instance2 = new GUICategory(categoryService);

        instanceI.setVisible(true);
         String basePath = getBaseFilePath();
        try {
            DeliveryPluginManager.init(basePath);

            Consola presentationObject = new Consola();
            presentationObject.start();

        } catch (Exception ex) {
            Logger.getLogger("Application").log(Level.SEVERE, "Error al ejecutar la aplicación", ex);
        }
        
        
        instanceI.setVisible(true);
    }
    
        private static String getBaseFilePath() {
        try {
            String path = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            path = URLDecoder.decode(path, "UTF-8"); //This should solve the problem with spaces and special characters.
            File pathFile = new File(path);
            if (pathFile.isFile()) {
                path = pathFile.getParent();
                
                if (!path.endsWith(File.separator)) {
                    path += File.separator;
                }
                
            }

            return path;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error al eliminar espacios en la ruta del archivo", ex);
            return null;
        }
    }
    
}
