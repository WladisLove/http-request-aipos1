import View.ViewEntryPoint;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args){

        JFrame myWindow = new ViewEntryPoint().getFrame("HTTP request test");
        myWindow.setSize(1600, 600);
        myWindow.setVisible(true);
        myWindow.setLocationRelativeTo(null);

    }
}
