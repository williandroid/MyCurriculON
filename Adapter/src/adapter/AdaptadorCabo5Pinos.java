/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adapter;

/**
 *
 * @author tassioauad
 */
public class AdaptadorCabo5Pinos implements Cabo3Pinos {

    Cabo5Pinos cabo = new Cabo5Pinos();
    
    @Override
    public void plugar3Pinos() {
        System.out.println("Adaptando cabo de 5 pinos");
        cabo.plugar5Pinos();
    }
    
}
