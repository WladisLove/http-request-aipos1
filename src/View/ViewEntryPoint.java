package View;

import Controller.HttpRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewEntryPoint {
    private JFrame frame = new JFrame();
    private JTextArea requestArea = new JTextArea(20,40);
    private JTextArea statusArea = new JTextArea(5,20);
    private JTextArea responseArea = new JTextArea(20,70);
    private HttpRequest httpRequest;
    private String chosenType = "GET";
    private JTextField uriField = new JTextField("/", 15);
    private JTextField hostField = new JTextField("ttable.bspu.by", 15);
    private JTextArea headersArea = new JTextArea("Connection: close",3,30);
    private JTextArea bodyArea = new JTextArea(6,30);
    private JTextArea formsArea = new JTextArea(6,30);


    public ViewEntryPoint(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        requestArea.setFont(new Font("Dialog", Font.PLAIN, 14));
        requestArea.setTabSize(10);
        requestArea.setLineWrap(true);
        requestArea.setWrapStyleWord(true);

        responseArea.setFont(new Font("Dialog", Font.PLAIN, 14));
        responseArea.setTabSize(10);
        responseArea.setLineWrap(true);
        responseArea.setWrapStyleWord(true);

        JComboBox combo = new JComboBox(new String[] {"GET", "POST", "HEAD"});
        combo.setSelectedIndex(0);
        JPanel panel = new JPanel();
        JButton btn = new JButton("Go");

        panel.add(combo);
        panel.add(new JLabel("URI: "));
        panel.add(uriField);
        panel.add(btn);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                httpRequest = new HttpRequest(chosenType,
                        uriField.getText(),
                        hostField.getText(),
                        headersArea.getText(),
                        bodyArea.getText());

                makeRequest(true);

                if(httpRequest.remakeRequest()){
                    makeRequest(false);
                }
            }
        });
        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                chosenType = (String)cb.getSelectedItem();
            }
        });

        Box box = Box.createHorizontalBox();
        box.add(new JScrollPane(requestArea));
        box.add(Box.createHorizontalStrut(10));
        box.add(new JScrollPane(statusArea));
        box.add(Box.createHorizontalStrut(10));
        box.add(new JScrollPane(responseArea));


        JPanel requestContent = new JPanel();
        Box settingBox = Box.createVerticalBox();
        settingBox.add(new JLabel("Enter Headers:"));
        Box box1 = Box.createVerticalBox();
        box1.add(new JLabel("Host "));
        box1.add(hostField);
        settingBox.add(box1);
        Box box2 = Box.createVerticalBox();
        box2.add(new JLabel("Other Headers ( Param: value; ) "));
        box2.add(headersArea);
        settingBox.add(box2);

        Box settingBox2 = Box.createVerticalBox();
        settingBox2.add(new JLabel("Enter Body:"));
        settingBox2.add(bodyArea);

        Box settingBox3 = Box.createVerticalBox();
        settingBox3.add(new JLabel("Available forms and fields:"));
        settingBox3.add(new JScrollPane(formsArea));

        requestContent.add(settingBox);
        requestContent.add(settingBox2);
        requestContent.add(Box.createVerticalStrut(20));
        requestContent.add(settingBox3);

        JPanel contents = new JPanel();
        contents.add(box);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(requestContent, BorderLayout.CENTER);
        frame.add(contents, BorderLayout.SOUTH);

    }

    private void makeRequest(boolean refreshStatus){
        requestArea.setText(null);
        requestArea.insert(httpRequest.getRequest(),0);
        responseArea.setText(null);
        responseArea.insert(httpRequest.getResponse(),0);
        if(refreshStatus)
            statusArea.setText(null);
        statusArea.insert(httpRequest.getStatusCodeInfo(),0);

        formsArea.setText(null);
        formsArea.insert(httpRequest.response.formToFill(), 0);
    }

    public JFrame getFrame(String name){
        frame.setName(name);
        return frame;
    }
}
