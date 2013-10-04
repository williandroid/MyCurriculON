/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dispositivo;

import observerpattern.ObserverInterface;

/**
 *
 * @author tassioauad
 */
public class Dispositivo implements ObserverInterface {

    private float minimoPorcentagemQueda;
    private float maximoPorcentagemAlta;
    private String nome;

    public Dispositivo(String nome) {
        this.nome = nome;
    }

    public float getMinimoPorcentagemQueda() {
        return minimoPorcentagemQueda;
    }

    public void setMinimoPorcentagemQueda(float minimoPorcentagemQueda) {
        this.minimoPorcentagemQueda = minimoPorcentagemQueda;
    }

    public float getMaximoPorcentagemAlta() {
        return maximoPorcentagemAlta;
    }

    public void setMaximoPorcentagemAlta(float maximoPorcentagemAlta) {
        this.maximoPorcentagemAlta = maximoPorcentagemAlta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public void notificacaoQueda(String nomeEmpresa, float porcentagem) {
        if (porcentagem >= minimoPorcentagemQueda) {
            System.out.println(nome + ": Houve uma queda de " + porcentagem + " na empresa " + nomeEmpresa);
        }
    }

    @Override
    public void notificacaoAlta(String nomeEmpresa, float porcentagem) {
        if (porcentagem <= maximoPorcentagemAlta) {
            System.out.println(nome + ": Houve uma alta de " + porcentagem + " na empresa " + nomeEmpresa);
        }
    }

    
    
    
}
