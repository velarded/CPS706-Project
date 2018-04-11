package client;
import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JEditorPane;
import javax.swing.JTextField;

public class Browser extends JFrame{
    private JTextField urlField;
    private JEditorPane editorPane;

    public Browser(){

        urlField = new JTextField(35);
        editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.setEditable(false);

        getContentPane().setLayout(new BorderLayout());
        add(urlField, BorderLayout.NORTH);
        add(editorPane, BorderLayout.CENTER);
    }

    /*load html to editor pane*/
    public void load(String html){
        editorPane.setText(html);
    }

}