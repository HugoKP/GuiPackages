/*
arquivo JFilterCharField.java criado a partir de 16 de marco de 2019.
 */
package br.com.hkp.classes.xswing.inputfield;

/**
 * Esta classe eh um JGenericField que implementa um campo de entrada que soh
 * permite a digitacao dos caracteres que estiverem tambem presentes na string
 * que eh passada como argumento ao construtor desta classe.
 * 
 * @author Hugo Kaulino Pereira
 * @since 1.0
 * @version 1.0
 */
public class JFilterCharField extends JGenericField<String>
{
     /**
     * Construtor da classe.
     * 
     * @param filter Um JCustomField customizado por um objeto desta classe soh
     * aceitarah a insercao de caracteres no campo que tambem estiverm presentes
     * na String filter
     */
    /*[00]----------------------------------------------------------------------
    *                          Construtor da classe
    --------------------------------------------------------------------------*/
    public JFilterCharField(String filter)
    {
        super();
        regexp[FOCUS_LOST] = "[" + filter + "]*";
        regexp[FOCUS_GAINED] = "[" + filter + "]*";
    }//fim do construtor JFilterCharFIeld()
         
    /*[01]----------------------------------------------------------------------
    *        Retira caracteres espaco em branco das exremidades de str
    *        antes de que seja tentada uma insercao de str no campo.
    --------------------------------------------------------------------------*/
    @Override
    protected String insertString(String str)
    {
        return str.trim();
    }//fim de insertString()
    
}//fim da classe JFilterCharField

