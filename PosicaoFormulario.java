/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author ribega
 */
public class PosicaoFormulario {
    
    // Abri formulário centralizado na tela
    
    public void abrirFormulario(JInternalFrame janela, JDesktopPane desktop){
        int lDesk = desktop.getWidth();
        int aDesk = desktop.getHeight();
        int lIframe = janela.getWidth();
        int aIframe = janela.getHeight();
        janela.setLocation(lDesk / 2 - lIframe / 2, aDesk / 2 - aIframe / 2);
        desktop.add(janela);
        janela.setVisible(true);
    }
    
}
