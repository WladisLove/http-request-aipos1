package View;

import Controller.HttpRequests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewEntryPoint {
    private JFrame frame = new JFrame();
    public JTextArea textArea = new JTextArea(20,80);
    public ButtonGroup bg = new ButtonGroup();
    private HttpRequests httpRequests = new HttpRequests();


    public ViewEntryPoint(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textArea.setFont(new Font("Dialog", Font.PLAIN, 14));
        textArea.setTabSize(10);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JComboBox combo = new JComboBox(new String[] {"GET", "POST", "HEAD"});
        combo.setSelectedIndex(0);
        JPanel panel = new JPanel();
        JButton btn = new JButton("Go");
        panel.add(combo);
        panel.add(btn);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.insert(httpRequests.sendGetRequest("http://4pda.ru"),0);
            }
        });
        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                String petName = (String)cb.getSelectedItem();
                System.out.println(petName);
            }
        });

        JPanel contents = new JPanel();
        contents.add(new JScrollPane(textArea));
        
        frame.add(panel, BorderLayout.NORTH);
        frame.add(contents, BorderLayout.CENTER);

    }

    public JFrame getFrame(String name){
        frame.setName(name);
        return frame;
    }
}
