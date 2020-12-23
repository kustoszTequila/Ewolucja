package GUI;

import javax.swing.*;

public class StartMenu  {
    private JFrame StartFrame;


    public StartMenu()
    {
        StartFrame = new JFrame("Welcome to the simulation");
        StartFrame .setSize(600, 600);
        StartFrame .setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StartFrame .setLocationRelativeTo(null);
    }
    public void StartProgram()
    {
        StartFrame.add(new Settings());
        StartFrame.setVisible(true);
    }
}
