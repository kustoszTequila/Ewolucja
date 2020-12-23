package GUI;

import Classes.Gene;
import Classes.StatParser;
import World.EvolutionWorld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class PropertiesPanel extends JPanel implements ActionListener {

    private Simulation simulation;
    private EvolutionWorld evWorld;
    private int age;

    private JLabel ageLabel;
    private JLabel animalNumLabel;
    private JLabel grassNumLabel;
    private JLabel genomLabel;
    private JLabel avgEnergyLabel;
    private JLabel avgLifespanLabel;
    private JLabel avgChildrenLabel;

    private int animalNum=0;
    private int grassNum=0;
    private Gene genom;
    private double avgEnergy=0;
    private double avgLifespan=0;
    private double avgChildren=0;

    private JButton saveButton;
    private DecimalFormat df;
    public PropertiesPanel(Simulation simulation, EvolutionWorld evWorld)
    {
        df = new DecimalFormat("###.##");
        this.simulation = simulation;
        this.evWorld = evWorld;
        this.age =1;
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        saveButton = new JButton("Save to txt") ;
        saveButton.addActionListener(this);
        ageLabel = new JLabel("Current age: " + age);
        animalNumLabel = new JLabel("Number of animals: " );
        grassNumLabel = new JLabel ("Number of grass: " );
        avgEnergyLabel = new JLabel ("Average energy: " );
        avgLifespanLabel = new JLabel ("Average lifespan: ");
        avgChildrenLabel = new JLabel ("Average children: ");
        genomLabel = new JLabel ("Dominant genom: ");
        this.add(saveButton);
        this.add(ageLabel);
        this.add(animalNumLabel);
        this.add(grassNumLabel);
        this.add(avgEnergyLabel);
        this.add(genomLabel);
        this.add(avgLifespanLabel);
        this.add(avgChildrenLabel);
    }
    public void updateProperties()
    {
        ageLabel.setText("Current age: " + simulation.age);
        age = simulation.age;

        animalNumLabel.setText("Number of animals: " + evWorld.getAnimalSize());
        animalNum += evWorld.getAnimalSize();

        grassNumLabel.setText("Number of grass: " + evWorld.getGrassSize());
        grassNum += evWorld.getGrassSize();

        avgEnergyLabel.setText("Average energy: " + df.format(evWorld.getAvgEnergy()));
        avgEnergy += evWorld.getAvgEnergy();

        genomLabel.setText("<html>Dominant genom: <br/>"+ evWorld.getMaxGenom() +"</html>");
        genom = evWorld.getMaxGenom();

        avgLifespanLabel.setText("Average lifespan: " + df.format(evWorld.getAvgDeadLife()));
        avgLifespan += evWorld.getAvgDeadLife();

        avgChildrenLabel.setText("Average children: " + df.format(evWorld.childObserver.childrenRatio()));
        avgChildren += evWorld.childObserver.childrenRatio();
    }
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        age = simulation.age;
        if(e.getSource() == saveButton && age != 0)
        {
            StatParser statParser = new StatParser(age,animalNum,grassNum,avgEnergy,avgLifespan,avgChildren,genom);
            statParser.writeToFile();
        }
    }
}
