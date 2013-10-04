/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adapter2;

import java.util.ArrayList;

/**
 *
 * @author tassioauad
 */
public class PrintableListAdapter extends PrintString{

    public void print(ArrayList<String> arrayList) {
        for(String string : arrayList) {
            printString(string);
        }
    }
    
}
