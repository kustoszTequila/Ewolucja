package GUI;

import Classes.Animal;
import Enums.AnimationStatus;
import World.EvolutionWorld;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Simulation implements ActionListener {

    public final int deltaT;
    public EvolutionWorld map;
    public int startNumOfAnimals;
    public int grassSpawned;
    private AnimationStatus status;
    protected Animal animalToObserve;
    protected int age;

    public JFrame frame;
    public Timer timer;
    public MapPanel mapPanel;
    public PropertiesPanel propPanel;

    private JButton stopButton;
    private JPanel stopButtonPanel;




    public Simulation(EvolutionWorld map, int deltaT, int startNumOfAnimals,int grassSpawned) {
        this.age =0;
        this.map = map;
        this.deltaT= deltaT;
        this.startNumOfAnimals = startNumOfAnimals;
        this.grassSpawned = grassSpawned;

        timer = new Timer(deltaT, this);

        frame = new JFrame("Simulation");
        frame.setSize(1920, 1080);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        mapPanel = new MapPanel(map, this);
        propPanel = new PropertiesPanel(this,map);

        stopButton = new JButton("Stop");
        stopButton.addActionListener(this);
        stopButtonPanel = new JPanel();
        stopButtonPanel.add(stopButton);

        propPanel.setPreferredSize(new Dimension(200,200));
        mapPanel.setPreferredSize(new Dimension(900,800));
        stopButtonPanel.setPreferredSize(new Dimension(100,100));
        frame.add(stopButtonPanel,BorderLayout.NORTH);
        frame.add(mapPanel,BorderLayout.CENTER);
        frame.add(propPanel,BorderLayout.WEST);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(100,100));
        frame.add(bottomPanel, BorderLayout.SOUTH);
        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(100,100));
        frame.add(rightPanel, BorderLayout.EAST);

    }

    public void startSimulation() {
        status = AnimationStatus.PLAYED;
        map.addAnimals(startNumOfAnimals);
        map.addGrass(grassSpawned);
        age++;
        propPanel.updateProperties();
        timer.start();
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if ( e.getSource() == stopButton)
        {
            if (timer.isRunning())
            {
                timer.stop();
                stopButton.setText("Start");
                status = status.change();

            }
            else
            {
                timer.start();
                stopButton.setText("Stop");
                status = status.change();

            }

        }
        else
        {
            map.everyDay();
            age++;
            mapPanel.repaint();
            propPanel.updateProperties();
            propPanel.repaint();

        }

    }

    public AnimationStatus getStatus()
    {
        return this.status;
    }


}
