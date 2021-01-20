/*
arquivo JCurrencyField.java criado a partir de 16 de marco de 2019.
*/
package br.com.hkp.classes.xswing.inputfield;

import java.awt.*;
import java.util.Locale;
import java.text.NumberFormat;  
import br.com.hkp.classes.localetools.LocaleTools;
import br.com.hkp.classes.stringtools.StringTools;


/**
 * Um campo para entrada de valores monetarios em alguma moeda especifica.
 * 
 * @author Hugo Kaulino Pereira
 * @version 1.0
 * @since 1.0
 */
public final class JCurrencyField extends JGenericField<Double>
{
    private final char decimalPoint;
    private final Locale locale;
    private final NumberFormat formatter; 
    private final String charsAllowedInGainedFocus;
    private final boolean negativeValues;
   
    
    /**
     * O construtor da classe define para que tipo de moeda o campo serah 
     * configurado, e também se o campo aceita valores monetarios negativos.
     * 
     * @param l O Locale.
     * @param negativeValues Se true o campo aceitara entrada de valores 
     * monetarios negativos.
     */
    /*[00]----------------------------------------------------------------------
    *                       Construtor da classe
    --------------------------------------------------------------------------*/
    public JCurrencyField(Locale l, boolean negativeValues)
    {
        super();
        
        if (LocaleTools.numericTest(l))   
            locale = l;
        else
            locale = Locale.ROOT;  
        
        this.negativeValues = negativeValues;
        
        decimalPoint = LocaleTools.decimalPoint(locale);
        
        charsAllowedInGainedFocus = 
            ((negativeValues) ? "-" : "") + "0123456789" + decimalPoint;
        
        formatter = NumberFormat.getCurrencyInstance(locale); 
        
        regexp[FOCUS_LOST] = "[^\n]*";
        
        String minusSignal = (negativeValues) ? "[-]?" : "";
        if (LocaleTools.hasCents(locale)) 
            regexp[FOCUS_GAINED] = 
                minusSignal + "\\d*[" + decimalPoint + "]?\\d{0,2}";
        else
            regexp[FOCUS_GAINED] = minusSignal + "\\d*";
        
        
    }//fim do construtor JCurrencyField()
    
      
    /*[01]----------------------------------------------------------------------
    *    Este metodo serah executado toda vez que o campo receber o foco.
    --------------------------------------------------------------------------*/
    @Override
    protected void focusGained()
    {
        super.focusGained();
        if (negativeValues) setBackground(Color.WHITE);
    }//fim de focusGained()
    
    /*[02]----------------------------------------------------------------------
    *     Este metodo serah executado toda vez que o campo perder o foco.
    --------------------------------------------------------------------------*/
    @Override
    protected void focusLost()
    {
        super.focusLost();
        if ((negativeValues) && (getValue() < 0)) setBackground(Color.PINK);
    }//fim de focusLost()
    
    /*[03]----------------------------------------------------------------------
    *     Converte a representacao do padrao "plain" para o padrao "lost"
    --------------------------------------------------------------------------*/
    @Override
    protected String plainToLost(String text)
    {
        try
        {
           return formatter.format(Double.parseDouble(text));
        }
        catch(NumberFormatException e)
        {
            return "";
        }
    }//fim de plainToLost()
    
    /*[04]----------------------------------------------------------------------
    *                          "plain" para "gained"
    --------------------------------------------------------------------------*/
    @Override
    protected String plainToGained(String text)
    {
        return text.replace('.',decimalPoint);
    }//fim de plainToGained()
    
    /*[05]----------------------------------------------------------------------
    *                     "lost" para "plain"
    --------------------------------------------------------------------------*/
    @Override
    protected String lostToPlain(String text)
    {
        return StringTools.filterString
                           (
                               text,
                               charsAllowedInGainedFocus,
                               true
                           ).replace(decimalPoint, '.');
    }//fim de lostToPlain()
        
    /*[06]----------------------------------------------------------------------
    *                    "gained" para "plain"
    --------------------------------------------------------------------------*/
    @Override
    protected String gainedToPlain(String text)
    {
        return text.replace(decimalPoint, '.');
    }//fim de gainedToPlain()
   
    /*[07]----------------------------------------------------------------------
    *                Retorna o valor monetario com um double
    --------------------------------------------------------------------------*/
    @Override
    protected Double plainToValue(String text)
    {
        try
        {
            return Double.parseDouble(text);
        }
        catch(NumberFormatException e)
        {
            return Double.NaN;
        }//fim do try/catch
    }//fim de plainToValue()
    
    /*[08]----------------------------------------------------------------------
    *          Converte um valor monetario em double para string
    --------------------------------------------------------------------------*/
    @Override
    protected String valueToPlain(Double value)
    {
        long round = Math.round(value);
        if (value == round)//Evita ponto decimal em numeros inteiros
            return String.valueOf(round);
        else 
            return String.valueOf(value);
    }//fim de valueToPlain()
         
    /*
    Remove todos os caracteres de espaco em branco que terminarem a direita e
    a esquerda de str antes que ela seja processada pelo metodo insertString()
    da classe CustomDocument. Sem este pre-processamento qualquer tentativa de
    colar no campo uma string valida, mas com algum espaco em branco a direita 
    ou a esquerda, seria rejeitada pelo metodo insertString pois uma string com
    espacos em branco nao casa com a expressao regular definida para o padrao
    "gained", que eh quando o usuario pode fazer edicoes digitando ou colando 
    texto no campo.
    */
    /*[09]----------------------------------------------------------------------
    *        Retira caracteres espaco em branco das exremidades de str
    *        antes de que seja tentada uma insercao de str no campo.
    --------------------------------------------------------------------------*/
    @Override
    protected String insertString(String str)
    {
        return str.trim();
    }//fim de insertString()
    
}//fim da classe JCurrencyField

