package GUI;

import World.EvolutionWorld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JPanel implements ActionListener{

    private final int WIDTH = 800;
    private final int HEIGHT = 800;


    //Labels
    private JLabel widthLabel;
    private JLabel heightLabel;
    private JLabel jungleRatioLabel;
    private JLabel FPSLabel;

    private JLabel startEnergyLabel;
    private JLabel minCopEnergyLabel;
    private JLabel grassEnergyLabel;
    private JLabel dailyEnergyLabel;

    private JLabel startAnimalsLabel;
    private JLabel startGrassLabel;

    private JLabel genSizeLabel;
    private JLabel genRangeLabel;

    // Text
    private JTextField widthText;
    private JTextField heightText;
    private JTextField jungleRatioText;
    private JTextField FPSText;

    private JTextField startEnergyText;
    private JTextField minCopEnergyText;
    private JTextField grassEnergyText;
    private JTextField dailyEnergyText;

    private JTextField startAnimalsText;
    private JTextField startGrassText;

    private JTextField genSizeText;
    private JTextField genRangeText;

    private JButton playButton;
    private JButton doublePlayButton;

    private Integer[] defaultValues = new Integer[]{25,25,1000,30,15,10,1,30,200,32,7,};
    private Double defaultRatio = 0.4;

    public Settings ()
    {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));

        widthLabel = new JLabel("Width of the map:  ");
        heightLabel = new JLabel("Height of the map:    ");
        jungleRatioLabel = new JLabel ( "Ratio of the jungle:   ");
        FPSLabel = new JLabel ("Refreash rate in ms:     ");

        startEnergyLabel = new JLabel ("Start energy of animals:    ");
        minCopEnergyLabel = new JLabel ("Minimal copulation energy of aniamals: ");
        grassEnergyLabel = new JLabel ("Energy of grass:    ");
        dailyEnergyLabel = new JLabel("Energy lost every day:   ");

        startAnimalsLabel = new JLabel("How many animals at start:  ");
        startGrassLabel = new JLabel("Initial grass spawned in stepp and in Jungle");

        genSizeLabel = new JLabel ("Size of genes:  ");
        genRangeLabel = new JLabel("range of jenes from 0 to:   ");

        int numOfColumns = 12;

        widthText = new JTextField();
        widthText.setColumns(numOfColumns);
        heightText = new JTextField();
        heightText.setColumns(numOfColumns);
        jungleRatioText = new JTextField();
        jungleRatioText.setColumns(numOfColumns);
        FPSText = new JTextField();
        FPSText.setColumns(numOfColumns);

        startEnergyText = new JTextField();
        startEnergyText.setColumns(numOfColumns);
        minCopEnergyText = new JTextField();
        minCopEnergyText.setColumns(numOfColumns);
        grassEnergyText = new JTextField();
        grassEnergyText.setColumns(numOfColumns);
        dailyEnergyText = new JTextField();
        dailyEnergyText.setColumns(numOfColumns);

        startAnimalsText = new JTextField();
        startAnimalsText.setColumns(numOfColumns);
        startGrassText = new JTextField();
        startGrassText.setColumns(numOfColumns);

        genSizeText = new JTextField();
        genSizeText.setColumns(numOfColumns);
        genRangeText = new JTextField();
        genRangeText.setColumns(numOfColumns);

        widthLabel.setLabelFor(widthText);
        heightLabel.setLabelFor(heightText);
        jungleRatioLabel.setLabelFor(jungleRatioText);
        FPSLabel.setLabelFor(FPSText);
        startEnergyLabel.setLabelFor(startEnergyText);
        minCopEnergyLabel.setLabelFor(minCopEnergyText);
        grassEnergyLabel.setLabelFor(grassEnergyText);
        dailyEnergyLabel.setLabelFor(dailyEnergyText);
        startAnimalsLabel.setLabelFor(startAnimalsText);
        startGrassLabel.setLabelFor(startGrassText);
        genSizeLabel.setLabelFor(genSizeText);
        genRangeLabel.setLabelFor(genRangeText);

        widthText.setText(defaultValues[0].toString());
        heightText.setText(defaultValues[1].toString());;
        jungleRatioText.setText(defaultRatio.toString());;
        FPSText.setText(defaultValues[2].toString());;

        startEnergyText.setText(defaultValues[3].toString());;
        minCopEnergyText.setText(defaultValues[4].toString());;
        grassEnergyText.setText(defaultValues[5].toString());;
        dailyEnergyText.setText(defaultValues[6].toString());;

        startAnimalsText.setText(defaultValues[7].toString());;
        startGrassText.setText(defaultValues[8].toString());;

        genSizeText.setText(defaultValues[9].toString());;
        genRangeText.setText(defaultValues[10].toString());;
        genRangeText.setText(defaultValues[10].toString());;




        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();
        JPanel p6 = new JPanel();
        JPanel p7 = new JPanel();
        JPanel p8 = new JPanel();
        JPanel p9 = new JPanel();
        JPanel p10 = new JPanel();
        JPanel p11 = new JPanel();
        JPanel p12 = new JPanel();

        p1.add(widthLabel);
        p2.add(heightLabel);
        p3.add(jungleRatioLabel);
        p4.add(FPSLabel);
        p5.add(startEnergyLabel);
        p6.add(minCopEnergyLabel);
        p7.add(grassEnergyLabel);
        p8.add(dailyEnergyLabel);
        p9.add(startAnimalsLabel);
        p10.add(startGrassLabel);
        p11.add( genSizeLabel);
        p12.add(genRangeLabel);

        p1.add(widthText);
        p2.add(heightText);
        p3.add(jungleRatioText);
        p4.add(FPSText);
        p5.add(startEnergyText);
        p6.add(minCopEnergyText);
        p7.add(grassEnergyText);
        p8.add(dailyEnergyText);
        p9.add(startAnimalsText);
        p10.add(startGrassText);
        p11.add( genSizeText);
        p12.add(genRangeText);

        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.add(p4);
        this.add(p5);
        this.add(p6);
        this.add(p7);
        this.add(p8);
        this.add(p9);
        this.add(p10);
        this.add(p11);
        this.add(p12);
        playButton = new JButton("Play Simulation");
        playButton.addActionListener(this);
        doublePlayButton = new JButton("Play two Simulations");
        doublePlayButton.addActionListener(this);

        JPanel playButtonPanel = new JPanel();
        playButtonPanel.add(playButton);
        playButtonPanel.add(doublePlayButton);
        add(playButtonPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playButton)
        {
            check();
            EvolutionWorld ev = new EvolutionWorld(Integer.parseInt(widthText.getText()),
                    Integer.parseInt(heightText.getText()),Double.parseDouble(jungleRatioText.getText()),
                    Integer.parseInt(startEnergyText.getText()),Integer.parseInt(minCopEnergyText.getText())
                    ,Integer.parseInt(dailyEnergyText.getText()),
                    Integer.parseInt(genRangeText.getText()),Integer.parseInt(genSizeText.getText()),
                    Integer.parseInt(grassEnergyText.getText()));


            Simulation sim = new Simulation(ev,Integer.parseInt(FPSText.getText()),
                    Integer.parseInt(startAnimalsText.getText()),
                    Integer.parseInt(startGrassText.getText()));
            sim.startSimulation();
        }
        else if (e.getSource() == doublePlayButton)
        {
            check();
            EvolutionWorld ev = new EvolutionWorld(Integer.parseInt(widthText.getText()),
                    Integer.parseInt(heightText.getText()),Double.parseDouble(jungleRatioText.getText()),
                    Integer.parseInt(startEnergyText.getText()),Integer.parseInt(minCopEnergyText.getText())
                    ,Integer.parseInt(dailyEnergyText.getText()),
                    Integer.parseInt(genRangeText.getText()),Integer.parseInt(genSizeText.getText()),
                    Integer.parseInt(grassEnergyText.getText()));


            Simulation sim = new Simulation(ev,Integer.parseInt(FPSText.getText()),
                    Integer.parseInt(startAnimalsText.getText()),
                    Integer.parseInt(startGrassText.getText()));
            Simulation sim2 = new Simulation(ev,Integer.parseInt(FPSText.getText()),
                    Integer.parseInt(startAnimalsText.getText()),
                    Integer.parseInt(startGrassText.getText()));

            sim.startSimulation();
            sim2.startSimulation();
        }
    }
    public void check() throws IllegalArgumentException{
        if(Integer.parseInt(widthText.getText()) <= 0) { throw new IllegalArgumentException("Invalid map width");}
        if(Integer.parseInt(heightText.getText()) <= 0){ throw new IllegalArgumentException("Invalid map height");}
        if(Integer.parseInt(minCopEnergyText.getText()) <= 0){ throw new IllegalArgumentException("Invalid minimum copulation energy");}
        if(Integer.parseInt(startEnergyText.getText()) < 0){ throw new IllegalArgumentException("Invalid start energy of animals");}
        if(Integer.parseInt(dailyEnergyText.getText()) < 0){ throw new IllegalArgumentException("Invalid daily energy lost by animals");}
        if((Double.parseDouble(jungleRatioText.getText()) < 0 || Double.parseDouble(jungleRatioText.getText()) >1.0))
        { throw new IllegalArgumentException("Jungle ratio must be between 0 and 1");}
        if(Integer.parseInt(startAnimalsText.getText())
                >Integer.parseInt(widthText.getText())*Integer.parseInt(heightText.getText() ))
        { throw new IllegalArgumentException("Too much animals");}
        if(Integer.parseInt(startGrassText.getText())
                >Integer.parseInt(widthText.getText())*Integer.parseInt(heightText.getText() ))
        { throw new IllegalArgumentException("Too much grass");}

    }
}
