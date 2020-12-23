package GUI;

import Classes.Animal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenomFrame implements ActionListener {

    private Simulation simulation;
    private MapPanel mapPanel;
    private JPanel panel = new JPanel();
    private JLabel genom = new JLabel("Genom: ");
    private JFrame frame = new JFrame ("Genom");
    private JButton button = new JButton("Observe");
    private Animal animal;
    private AnimalPanel animalPanel;

    public GenomFrame (MapPanel mapPanel, Animal animal,Simulation simulation)
    {
        this.animal = animal;
        this.simulation = simulation;
        this.mapPanel = mapPanel;
        this.button.addActionListener(this);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(400,100);
        frame.setResizable(false);
        frame.setLayout(null);
        panel.setBounds(0,0,400,400);
        genom.setBounds(0,0,frame.getWidth(),frame.getHeight());
        genom.setText(animal.getGenes().toString());
        genom.setHorizontalTextPosition(JLabel.CENTER);
        genom.setVerticalTextPosition(JLabel.CENTER);
        genom.setVerticalAlignment(JLabel.CENTER);
        genom.setHorizontalAlignment(JLabel.CENTER);
        panel.add(genom);
        panel.add(button);
        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (animalPanel == null)
        {
            animalPanel = new AnimalPanel(simulation,animal);
        }
    }
}
