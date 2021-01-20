
package br.com.hkp.classes.xswing.inputfield;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class ReadServerFile extends JFrame
{
    private JTextField enterField;
    private JEditorPane contentsArea;
    
    public ReadServerFile()
    {
        super("Hugo's Browser");
        
        enterField = new JTextField();
        enterField.addActionListener
                  (
                      new ActionListener()
                      {
                          public void actionPerformed(ActionEvent e)
                          {
                              getThePage(e.getActionCommand());
                          }
                      }//fim da classe anonima
                  );
        
        add(enterField,BorderLayout.NORTH);
        
        contentsArea = new JEditorPane();
        
        contentsArea.setEditable(false);
        
        contentsArea.addHyperlinkListener
                     (
                         new HyperlinkListener()
                         {
                             public void hyperlinkUpdate(HyperlinkEvent e)
                             {
                                 if (e.getEventType() == 
                                     HyperlinkEvent.EventType.ACTIVATED)
                                     getThePage(e.getURL().toString());
                             }
                         }
                     );
        
        add(new JScrollPane(contentsArea),BorderLayout.CENTER);
       
    }//fim do construtor
    
    private void getThePage(String location)
    {
        try
        {
            contentsArea.setPage(location);
            enterField.setText(location);
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog
                       (this,"Erro","ERRO NA URL", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    public static void main(String[] args)
    {
        ReadServerFile app = new ReadServerFile();
        app.setSize(400,300);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
    
}
