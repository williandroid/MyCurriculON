/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa;

import dispositivo.Dispositivo;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import observerpattern.SubjectInterface;

/**
 *
 * @author tassioauad
 */
public class Empresa implements SubjectInterface{
    
    private String nome;
    private float porcentagem = 0;
    private List<Dispositivo> dispositivos = new ArrayList<>();
    
    public Empresa(String name) {
        this.nome = name;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPorcentagem() {
        return porcentagem;
    }

    public void setNovaPorcentagem(float novaPorcentagem) {
        if (novaPorcentagem - porcentagem > 0) {
            notificarAlta(novaPorcentagem - porcentagem);
        } else {
            notificarQueda(novaPorcentagem - porcentagem);
        }
        this.porcentagem = novaPorcentagem;
    }

    public List<Dispositivo> getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(List<Dispositivo> dispositivos) {
        this.dispositivos = dispositivos;
    }
    
    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivos.add(dispositivo);
    }

    @Override
    public void notificarQueda(float porcentagem) {
        for(Dispositivo dispositivo : dispositivos) {
            dispositivo.notificacaoQueda(nome, porcentagem);
        }
    }

    @Override
    public void notificarAlta(float porcentagem) {
        for(Dispositivo dispositivo : dispositivos) {
            dispositivo.notificacaoAlta(nome, porcentagem);
        }
    }
    
}
