package GUI;

import Classes.Animal;
import Interfaces.IAnimalPanelObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AnimalPanel implements IAnimalPanelObserver {

    private Simulation simulation;
    protected Animal animal;

    private JFrame frame;
    private JPanel panel;

    private JLabel childrenLabel;
    private JLabel deadLabel;
    private JLabel offspringLabel;

    private int childrenNum;
    private int offspringNum;

    public AnimalPanel(Simulation simulation,Animal animal)
    {
        frame = new JFrame();
        frame.setSize(300, 100);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle(animal.getGenes().toString());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        this.simulation = simulation;
        this.animal = animal;
        animal.addAnimalPanel(this);
        this.panel = new JPanel();
        panel.setLayout(new BorderLayout());
        childrenNum = 0;
        offspringNum = 0;
        childrenLabel = new JLabel("children: " + childrenNum);
        deadLabel = new JLabel("Still alive");
        offspringLabel = new JLabel ("offspring: " + offspringNum);
        panel.add(childrenLabel,BorderLayout.NORTH);
        panel.add(offspringLabel,BorderLayout.CENTER);
        panel.add(deadLabel,BorderLayout.SOUTH);
        frame.add(panel);

    }
    public Animal getAnimal()
    {
        return this.animal;
    }
    public void changeStatus (int deadAge)
    {
        deadLabel.setText("Dead age: " + deadAge);
    }
    public void increaseChildren()
    {
        childrenNum += 1;
        childrenLabel.setText("children: " + childrenNum);
    }
    public void increaseOffspring()
    {
        offspringNum += 1;
        offspringLabel.setText("offspring: " + offspringNum);
    }

}

