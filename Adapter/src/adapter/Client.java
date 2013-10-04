/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adapter;

/**
 *
 * @author tassioauad
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Cabo3Pinos cabo3Pinos1 = new AdaptadorCabo2Pinos();
        Cabo3Pinos cabo3Pinos2 = new AdaptadorCabo4Pinos();
        Cabo3Pinos cabo3Pinos3 = new AdaptadorCabo5Pinos();
        
        cabo3Pinos1.plugar3Pinos();
        cabo3Pinos2.plugar3Pinos();
        cabo3Pinos3.plugar3Pinos();
        
    }
}
