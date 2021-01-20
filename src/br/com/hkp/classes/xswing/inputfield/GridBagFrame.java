
package br.com.hkp.classes.xswing.inputfield;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;



public class GridBagFrame extends JFrame
{
    
    private GridBagLayout layout;
    private GridBagConstraints constraints;
    
    /*[00]----------------------------------------------------------------------
    *                             CONSTRUTOR
    --------------------------------------------------------------------------*/
    public GridBagFrame()
    {
        super("Layout");
        
        layout = new GridBagLayout(); setLayout(layout);
        
        constraints = new GridBagConstraints();
        
        JTextArea textArea1 = new JTextArea("Area1",5,10);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 2000;
        constraints.weighty = 1000;
        addComponent(textArea1,0,0,1,3);
        
        JTextArea textArea2 = new JTextArea("Area2",2,2);
        addComponent(textArea2,2,3,1,1);
        
        String names[] = {"Ferro","Aço","Bronze"};        
        JComboBox<String> comboBox = new JComboBox<>(names);
        addComponent(comboBox,1,2,1,1);
        
        JTextField textField = new JTextField("text");
        addComponent(textField,0,3,2,1);
       
        JButton button1 = new JButton("Button 1");
        //constraints.fill = GridBagConstraints.HORIZONTAL;
        addComponent(button1,1,0,2,1);
        
        JButton button2 = new JButton("Button 2");
        //constraints.weightx = 1;
        //constraints.weighty = 0;
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(button2,1,1,1,1);
        
        JButton button3 = new JButton("Button 3");
        constraints.weightx = 0;
        constraints.weighty = 0;
        addComponent(button3,2,1,1,2);
        
    
    }//fim do construtor
    
    /*[00]----------------------------------------------------------------------
    *                              METODO
    --------------------------------------------------------------------------*/
    private void addComponent
             (
                 Component component, int c, int r, int w, int h
             )
    {
        constraints.gridx = c;
        constraints.gridy = r;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        layout.setConstraints(component, constraints);
        add(component);

    }//fim de addComponent()
    
    
    
}//fim da classe
