/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adapter;

/**
 *
 * @author tassioauad
 */
public class AdaptadorCabo2Pinos implements Cabo3Pinos {

    Cabo2Pinos cabo = new Cabo2Pinos();
    
    @Override
    public void plugar3Pinos() {
        System.out.println("Adaptando cabo de 2 pinos");
        cabo.plugar2Pinos();
    }
    
}
