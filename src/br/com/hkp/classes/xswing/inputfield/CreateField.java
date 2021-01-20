/*
 arquivo CreateField.java criado a partir de 14 de marco de 2019.
 */
package br.com.hkp.classes.xswing.inputfield;

import java.util.Locale;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



/**
 * Esta classe apresenta metodos static que retornam objetos JCustomField
 * customizados por objetos JCustomFieldControls implementados neste pacote.
 * 
 * @author Hugo Kaulino Pereira
 * @since 1.0
 * @version 1.0
 */
public final class CreateField
{
    private static Color colorLost; 
    private static Color colorGained;
    private static final double CURRENCY = -1000.0;
    private static final double D = -123456.789;
    private static final long L = -12345678901l;
    private static final int I = 123456;
    private static final String LOWER = "MINÚSCULAS";
    private static final String UPPER = "maiúsculas";
    private static final String FILTER = "abcdbca";
  
    /**
     * Exemplos de uso das classes deste pacote.
     * 
     * @param args Nao utilizado.
     */
    public static void main(String[] args)
    {
        
        JLabel label =  new JLabel("", SwingConstants.RIGHT);
        
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        
                
        colorLost = Color.GRAY; colorGained = Color.BLUE;
      
        JCurrencyField jc = new JCurrencyField(Locale.US, true);
        jc.setValue(CURRENCY);
        jc.setColors(colorLost, colorGained);
        jc.setColumns(30);
        jc.addFocusListener
           (
               new FocusListener()
               {
                   @Override
                   public void focusGained(FocusEvent e)
                   {
                       label.setText("Digite um valor em dólares   ");
                   }
                   @Override
                   public void focusLost(FocusEvent e)
                   {
                      label.setText("");
                   }
               }
           );
                        
        JDoubleField jd = new JDoubleField(Locale.ITALY);
        jd.setValue(D);
        jd.setColors(colorLost, colorGained);
        jd.setColumns(30);
        jd.addFocusListener
           (
               new FocusListener()
               {
                   @Override
                   public void focusGained(FocusEvent e)
                   {
                       label.setText("Digite um double   ");
                   }
                   @Override
                   public void focusLost(FocusEvent e)
                   {
                       label.setText("");
                   }
               }
           );
        
       colorGained = Color.MAGENTA;
        
        JLongField jl = new JLongField(Locale.UK, 10, true);
        jl.setValue(L);
        jl.setColors(colorLost, colorGained);
        jl.setColumns(30);
        jl.addFocusListener
           (
               new FocusListener()
               {
                   @Override
                   public void focusGained(FocusEvent e)
                   {
                       label.setText("Digite um long (até 10 digitos)   ");
                   }
                   @Override
                   public void focusLost(FocusEvent e)
                   {
                       label.setText("");
                   }
               }
           );
        
        JIntField ji = new JIntField(Locale.UK, 5, false);
        ji.setValue(I);
        ji.setColors(colorLost, colorGained);
        ji.setColumns(30);
        ji.addFocusListener
           (
               new FocusListener()
               {
                   @Override
                   public void focusGained(FocusEvent e)
                   {
                       label.setText
                            (
                                "Digite um int positivo (até 5 digitos)   "
                            );
                   }
                   @Override
                   public void focusLost(FocusEvent e)
                   {
                       label.setText("");
                   }
               }
           );
        
        colorGained = Color.DARK_GRAY;
        
        JUpperCaseField juc = new JUpperCaseField();
        juc.setValue(UPPER);
        juc.setColors(colorLost, colorGained);
        juc.setColumns(30);
        juc.addFocusListener
           (
               new FocusListener()
               {
                   @Override
                   public void focusGained(FocusEvent e)
                   {
                       label.setText("Digite QUALQUER TEXTO   ");
                   }
                   @Override
                   public void focusLost(FocusEvent e)
                   {
                       label.setText("");
                   }
               }
           );
        
        JLowerCaseField jlc = new JLowerCaseField();
        jlc.setValue(LOWER);
        jlc.setColors(colorLost, colorGained);
        jlc.setColumns(30);
        jlc.addFocusListener
           (
               new FocusListener()
               {
                   @Override
                   public void focusGained(FocusEvent e)
                   {
                       label.setText("Digite qualquer texto   ");
                   }
                   @Override
                   public void focusLost(FocusEvent e)
                   {
                       label.setText("");
                   }
               }
           );
        
        JFilterCharField jfc = new JFilterCharField("abcd");
        jfc.setValue(FILTER);
        jfc.setColors(colorLost, colorGained);
        jfc.setColumns(30);
        jfc.addFocusListener
           (
               new FocusListener()
               {
                   @Override
                   public void focusGained(FocusEvent e)
                   {
                       label.setText("Só aceita abcd   ");
                   }
                   @Override
                   public void focusLost(FocusEvent e)
                   {
                       label.setText("");
                   }
               }
           );
        
        JButton jb0 = new JButton("Restaurar Valores");
        jb0.addActionListener
           (
               new ActionListener() 
                   {
                       @Override
                       public void actionPerformed(ActionEvent e)
                       {
                             System.out.println(jc.getValue());
                             System.out.println(jd.getValue());
                             System.out.println(jl.getValue());
                             System.out.println(ji.getValue());
                             System.out.println(jlc.getValue());
                             System.out.println(juc.getValue());
                             System.out.println(jfc.getValue());
                             jc.setValue(CURRENCY);
                             jd.setValue(D);
                             jl.setValue(L);
                             ji.setValue(I);
                             jlc.setValue(LOWER);
                             juc.setValue(UPPER);
                             jfc.setValue(FILTER);
                       }//fim de actionPerformed()

                   }//fim da classe anonima que implementa ActionListener
           );
        
        JButton jb1 = new JButton("Saídas Formatadas");
        jb1.addActionListener
           (
               new ActionListener() 
                   {
                       @Override
                       public void actionPerformed(ActionEvent e)
                       {
                      
                             JOptionPane.showMessageDialog
                                         (
                                              null,
                                              ""+jc+
                                              "\n"+jd+
                                              "\n"+jl+
                                              "\n"+ji+
                                              "\n"+jlc+
                                              "\n"+juc+
                                              "\n"+jfc
                                         );
                                        
                       }//fim de actionPerformed()

                   }//fim da classe anonima que implementa ActionListener
           );
        
        JPanel p = new JPanel();
         
        p.setSize(350,265);
        p.setLayout(new FlowLayout());
        p.add(jc);
        p.add(jd);
        p.add(jl);
        p.add(ji);
        p.add(jlc);
        p.add(juc);
        p.add(jfc);
        p.add(jb0); p.add(jb1);
                  
        JFrame frame = new JFrame("Teste dos campos");
     
        frame.setSize(350,280);
        frame.add(p, BorderLayout.CENTER);
        frame.add(label, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
   
}//fim da classe CreateField
