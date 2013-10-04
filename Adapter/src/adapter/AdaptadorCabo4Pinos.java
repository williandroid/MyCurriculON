/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adapter;

/**
 *
 * @author tassioauad
 */
public class AdaptadorCabo4Pinos implements Cabo3Pinos {

    Cabo4Pinos cabo = new Cabo4Pinos();
    
    @Override
    public void plugar3Pinos() {
        System.out.println("Adaptando cabo de 4 pinos");
        cabo.plugar4Pinos();
    }
    
}
