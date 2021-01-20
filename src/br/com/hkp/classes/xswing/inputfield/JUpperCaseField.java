/*
arquivo JUpperCaseField.java criado a partir de 16 de marco de 2019.
*/
package br.com.hkp.classes.xswing.inputfield;

/**
 * A classe permite customizar um  campo de entrada de texto para converte todas
 * as letras minusculas digitadas nele ( ou coladas ) em maiusculas.
 * 
 * @author Hugo Kaulino Pereira
 * @version 1.0
 * @since 1.0
 */
public final class JUpperCaseField extends JGenericField<String>
{
    /*[00]----------------------------------------------------------------------
    *                      Construtor da classe.
    --------------------------------------------------------------------------*/
    public JUpperCaseField()
    {
        regexp[FOCUS_LOST] = "[^\n]*";
        regexp[FOCUS_GAINED] = "[^\n]*";
        
    }//fim do construtor JUpperCaseField()
       
    /*[01]----------------------------------------------------------------------
    *        Retira caracteres espaco em branco das exremidades de str
    *        antes de que seja tentada uma insercao de str no campo.
    --------------------------------------------------------------------------*/
    @Override
    protected String insertString(String str)
    {
        return str.toUpperCase();
    }//fim de insertString()
    
}//fim da classe JUpperCaseField