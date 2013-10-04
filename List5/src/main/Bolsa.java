/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dispositivo.Dispositivo;
import empresa.Empresa;

/**
 *
 * @author tassioauad
 */
public class Bolsa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Empresa vale = new Empresa("Vale do Rio Doce");
        Empresa petrobras = new Empresa("Petrobrás");
        Empresa eletrobras = new Empresa("Eletrobrás");
        
        Dispositivo dispositivoTassio = new Dispositivo("Dispositivo do Tássio");
        dispositivoTassio.setMaximoPorcentagemAlta(20);
        dispositivoTassio.setMinimoPorcentagemQueda(0);
        
        Dispositivo dispositivoLuiz = new Dispositivo("Dispositivo do Luiz");
        dispositivoLuiz.setMaximoPorcentagemAlta(15);
        dispositivoLuiz.setMinimoPorcentagemQueda(5);
        
        vale.setDispositivo(dispositivoLuiz);
        eletrobras.setDispositivo(dispositivoLuiz);
        vale.setDispositivo(dispositivoTassio);
        petrobras.setDispositivo(dispositivoTassio);
        
        vale.setNovaPorcentagem(20);
        vale.setNovaPorcentagem(10);
        vale.setNovaPorcentagem(5);
    }
}
