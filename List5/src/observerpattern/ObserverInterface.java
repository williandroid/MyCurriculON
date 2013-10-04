/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package observerpattern;

import empresa.Empresa;

/**
 *
 * @author tassioauad
 */
public interface ObserverInterface {
    
    public void notificacaoQueda(String nomeEmpresa, float porcentagem);
    public void notificacaoAlta(String nomeEmpresa, float porcentagem);
    
}
