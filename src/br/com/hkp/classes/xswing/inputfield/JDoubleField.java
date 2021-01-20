/*
arquivo JDoubleField.java criado a partir de 16 de marco de 2019.
*/
package br.com.hkp.classes.xswing.inputfield;

import java.util.Locale;
import br.com.hkp.classes.localetools.LocaleTools;
import br.com.hkp.classes.stringtools.StringTools;


/**
 * Implementa um campo para entrada de texto em uma unica linha customizado
 * para receber strings que representem valores numericos do tipo double.
 * 
 * @author Hugo Kaulino Pereira
 * @version 1.0
 * @since 1.0
 */
public final class JDoubleField extends JGenericField<Double>
{
    private final char decimalPoint;
    private final char separator;
    private final Locale locale;
       
    /**
     * Cria um objeto  para entrada de valores numericos de ponto flutuante. 
     * O valor sera exibido no campo de acordo com o Locale passado pelo
     * argumento l.
     * 
     * @param l O Locale para configuracao do campo. O valor numerico sera 
     * exibido de acordo com as convencoes deste Locale. Ou seja, o caractere de
     * ponto decimal e o caractere de agrupamento de digitos serao determinados
     * por este locale.
     */
    /*[00]----------------------------------------------------------------------
    *                       Construtor da classe
    --------------------------------------------------------------------------*/
    public JDoubleField(Locale l)
    {
        super();
        if (LocaleTools.numericTest(l))   
            locale = l;
        else
            locale = Locale.ROOT;
        
        decimalPoint = LocaleTools.decimalPoint(locale);
        separator = LocaleTools.separator(locale);
        
        regexp[FOCUS_LOST] =  "[-]?((\\d{1,3}){1}([" + 
                              separator +
                              "]\\d{3})*)?([" +
                              decimalPoint +
                              "]{1}\\d*|[" +
                              decimalPoint +
                              "]?)";
        
        regexp[FOCUS_GAINED] = "[-]?\\d*[" + decimalPoint + "]?\\d*";
    
    }//fim do construtor JDoubleField()
          
    /*
    A implementacao deste metodo recebe uma string no formato "plain" e tenta
    converte-la para um double. Se a conversao for bem sucedida este double
    eh convertido para uma string no padrao "lost". Ou seja, com ponto decimal
    definido pelo locale e caracteres separadores tambem definidos pelo locale.
    Alem disso o padrao "lost" exige que qualquer zero a direita da parte 
    decimal seja removido.
    Se a conversao para double nao for bem sucedida eh retornada uma string
    vazia.
    */
    /*[01]----------------------------------------------------------------------
    *              Do formato "plain" para o formato "lost"
    --------------------------------------------------------------------------*/
    @Override
    protected String plainToLost(String text)
    {
        double d;
        try
        {
           d = Double.parseDouble(text);
           if (d == -0.0) d = d * d;// evita que seja inserido "-0" no campo.
        }
        catch(NumberFormatException e)
        {
            return "";
        }
        
        int lastPosition = text.length() - 1;
        int decimalPosition = text.indexOf('.');
        int decimalDigits = 
            (decimalPosition == -1)? 0 : lastPosition - decimalPosition;
        
        /*
        Retira zeros a direita na parte decimal. Se houver.
        */
        if (decimalDigits > 0)
            while (text.charAt(lastPosition) == '0')
            {
                decimalDigits--;
                lastPosition--;
            }//fim do while
        
        return String.format(locale, "%,." + decimalDigits + "f", d);
          
    }//fim de plainToLost()
    
    /*
    Converte para o padrao "gained", que eh a string com ponto decimal 
    definido pelo locale mas sem separadores.
    */
    /*[02]----------------------------------------------------------------------
    *              Do formato "plain" para o "gained"
    --------------------------------------------------------------------------*/
    @Override
    protected String plainToGained(String text)
    {
        return text.replace('.', decimalPoint);
    }//fim de LostToGained()
    
    /*
    Converte do formato "lost" para "plain". O formato "plain" eh definido
    nesta classe como uma representacao de um numero em ponto flutuante que
    nao utiliza separadores e o ponto decimal eh um ponto '.'
    Este metodo remove todos os separadores de text e troca o ponto decimal
    definido pelo locale por um caractere ponto '.'
    */
    /*[03]----------------------------------------------------------------------
    *              Do formato "lost" para o "plain"
    --------------------------------------------------------------------------*/
    @Override
    protected String lostToPlain(String text)
    {
        return StringTools.filterString
                           (
                               text,
                               separator
                           ).replace(decimalPoint, '.');
    }//fim de lostToPlain()
    
     /*
    Converte do formato "gained" para "plain". O formato "plain" eh definido
    nesta classe como uma representacao de um numero em ponto flutuante que
    nao utiliza separadores e o ponto decimal eh um ponto '.'
    Este metodo remove todos os separadores de text e troca o ponto decimal
    definido pelo locale por um caractere ponto '.'
    */
    /*[04]----------------------------------------------------------------------
    *             Do formato "gained" para o "plain"
    --------------------------------------------------------------------------*/
    @Override
    protected String gainedToPlain(String text)
    {
        return text.replace(decimalPoint, '.');
    }//fim de gainedToPlain()
   
    /*
    Converte uma string no padrao "plain" representando um numero double em
    um tipo Double. Se a conversao nao for possivel retorna NaN (Not a Number)
    */
    /*[05]----------------------------------------------------------------------
    *    Recebe uma string no formato "plain" e a converte para Double
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
    
    /*
    Converte um Double para sua representacao "plain", que eh sem separadores e
    com o ponto decimal sendo o caractere ponto '.'
    */
    /*[06]----------------------------------------------------------------------
    *                  De double para "plain"
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
    da classe privada CustomDocument. Sem este pre-processamento qualquer 
    tentativa de colar no campo uma string valida, mas com algum espaco em
    branco a direita ou a esquerda, seria rejeitada pelo metodo insertString 
    pois uma string com espacos em branco nao casa com a expressao regular 
    definida para o padrao "gained", que eh quando o usuario pode fazer edicoes 
    digitando ou colando texto no campo.
    */
    /*[07]----------------------------------------------------------------------
    *        Retira caracteres espaco em branco das exremidades de str
    *        antes de que seja tentada uma insercao de str no campo.
    --------------------------------------------------------------------------*/
    @Override
    protected String insertString(String str)
    {
        return str.trim();
    }//fim de insertString()
    
}//fim da classe JDoubleField