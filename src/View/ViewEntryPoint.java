package View;

import Controller.HttpRequests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ViewEntryPoint {
    private JFrame frame = new JFrame();
    public JTextArea textArea = new JTextArea(20,80);
    public ButtonGroup bg = new ButtonGroup();
    private HttpRequests httpRequests = new HttpRequests();
    private String chosenType = "GET";


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
                textArea.setText(null);
                textArea.insert(httpRequests.makeRequest(chosenType,"https://httpbin.org"),0);
            }
        });
        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                chosenType = (String)cb.getSelectedItem();
                System.out.println(chosenType);
            }
        });

        JPanel contents = new JPanel();
        contents.add(new JScrollPane(textArea));
        
        frame.add(panel, BorderLayout.NORTH);
        frame.add(contents, BorderLayout.CENTER);

    }

    /*private void test(){
        HttpURLConnection con = null;
        String url = "https://requestb.in/sk96ilsk";
        try {

            con = (HttpURLConnection) new URL(url).openConnection();
            // optional default is GET
            con.setRequestMethod("GET");
            //add request header
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //print in String
            System.out.println(response.toString());
            //Read JSON response and print
            System.out.println("result after Reading JSON Response");
            System.out.println(response.toString());
        }
        catch (Throwable cause){
            cause.printStackTrace();
        }
    }*/

    public JFrame getFrame(String name){
        frame.setName(name);
        return frame;
    }
}
