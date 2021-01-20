/*
arquivo JLowerCaseField.java criado a partir de 16 de marco de 2019.
*/
package br.com.hkp.classes.xswing.inputfield;

/**
 * A classe permite customizar um  campo de entrada de texto para converte todas
 * as letras maiusculas digitadas nele ( ou coladas ) em minusculas.
 * 
 * @author Hugo Kaulino Pereira
 * @version 1.0
 * @since 1.0
 */
public final class JLowerCaseField extends JGenericField<String>
{
    /*[00]----------------------------------------------------------------------
    *                      Construtor da classe.
    --------------------------------------------------------------------------*/
    public JLowerCaseField()
    {
        regexp[FOCUS_LOST] = "[^\n]*";
        regexp[FOCUS_GAINED] = "[^\n]*";
        
    }//fim do construtor JLowerCaseField()
       
    /*[01]----------------------------------------------------------------------
    *        Retira caracteres espaco em branco das exremidades de str
    *        antes de que seja tentada uma insercao de str no campo.
    --------------------------------------------------------------------------*/
    @Override
    protected String insertString(String str)
    {
        return str.toLowerCase();
    }//fim de insertString()
    
}//fim da classe JLowerCaseField

