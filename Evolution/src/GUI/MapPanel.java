package GUI;

import Classes.Animal;
import Classes.Gene;
import Classes.Grass;
import Classes.Vector2d;
import Enums.AnimationStatus;
import World.EvolutionWorld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Map;

public class MapPanel extends JPanel implements MouseListener, ActionListener {

    private EvolutionWorld map;
    private Simulation simulation;
    private int wRatio;
    private int hRatio;

    private JButton genomButton = new JButton("show genom");
    private boolean showGenom = false;

    public MapPanel(EvolutionWorld map, Simulation simulation)
    {
        this.map = map;
        this.simulation = simulation;
        this.add(genomButton);
        genomButton.addActionListener(this);
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        int width = this.getWidth();
        int height = this.getHeight();
        wRatio = (int)(width/map.width);
        hRatio = (int)(height/map.height);


        g.setColor(new Color(200, 190, 100));
        g.fillRect(0, 0, width, height);
        g.setColor(new Color(20, 100, 20));
        g.fillRect(map.getLowerLeftJnglV().x * wRatio,
                map.getLowerLeftJnglV().y * hRatio,
                map.jungleWidth * wRatio,
                map.jungleHeight * hRatio);

        ArrayList<Grass> grass = map.getGrassList();

        for (int i=0;i<grass.size();i++)
        {
            g.setColor(grass.get(i).getColor());
            int x = map.vectorInBorders(grass.get(i).getPosition()).x * wRatio;
            int y = map.vectorInBorders(grass.get(i).getPosition()).y * hRatio;
            g.fillRect(x,y,wRatio,hRatio);
        }
        Map<Vector2d,ArrayList<Animal>>  animals = map.getAnimalMap();
        for (Map.Entry<Vector2d,ArrayList<Animal>> entry : animals.entrySet()) {
            ArrayList<Animal> an = entry.getValue();
            if (an != null && an.size() > 0)
            {
                    Animal animal = highestEnergy(an);
                    g.setColor(animal.getColor());
                    int x = map.vectorInBorders(animal.getPosition()).x * wRatio;
                    int y = map.vectorInBorders(animal.getPosition()).y * hRatio;
                    g.fillOval(x,y,wRatio,hRatio);

            }

        }
        if (showGenom)
        {
            Gene genom = map.getMaxGenom();
            for (Map.Entry<Vector2d,ArrayList<Animal>> entry : animals.entrySet()) {
                ArrayList<Animal> an = entry.getValue();
                if (an != null && an.size() > 0)
                {
                    for (int i=0;i<an.size();i++)
                    {
                        Animal anima = an.get(i);
                        if (anima.getGenes()==genom)
                        {
                            g.setColor(Color.BLACK);
                            int x = map.vectorInBorders(anima.getPosition()).x * wRatio;
                            int y = map.vectorInBorders(anima.getPosition()).y * hRatio;
                            g.fillOval(x,y,wRatio,hRatio);
                            break;
                        }
                    }


                }

            }
        }


    }
    @Override
    public void mouseClicked (MouseEvent e)
    {
        if (simulation.getStatus() == AnimationStatus.PAUSE)
        {
            int x=e.getX();
            int y=e.getY();
            Map<Vector2d,ArrayList<Animal>>  animals = map.getAnimalMap();
            for (Map.Entry<Vector2d,ArrayList<Animal>> entry : animals.entrySet()) {
                Vector2d anVec = entry.getKey();
                Vector2d newVec = new Vector2d((anVec.x* wRatio),(anVec.y*hRatio));
                Vector2d newVec2 = new Vector2d((anVec.x* wRatio+wRatio),(anVec.y*hRatio+hRatio));
                Vector2d mapVec = new Vector2d(x,y);
                if (mapVec.follows(newVec) && mapVec.precedes(newVec2))
                {
                    ArrayList<Animal> an = entry.getValue();
                    if (an != null && an.size() != 0)
                    {
                        Animal animal = highestEnergy(an);
                        GenomFrame genomFrame = new GenomFrame(this,animal,simulation);
                    }
                }
            }
        }

    }
    @Override
    public void mousePressed (MouseEvent e)
    {

    }
    @Override
    public void mouseReleased (MouseEvent e)
    {

    }
    @Override
    public void mouseEntered(MouseEvent e)
    {

    }
    @Override
    public void mouseExited (MouseEvent e)
    {

    }
    public Animal highestEnergy(ArrayList<Animal> animals)
    {

        Animal animal = animals.get(0);
        int energy = animal.getEnergy();
        for (int i=1; i<animals.size();i++)
        {
            if (energy < animals.get(i).getEnergy())
            {
                animal = animals.get(i);
                energy = animal.getEnergy();
            }
        }
        return animal;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == genomButton)
        {
            if(showGenom == false)
                showGenom = true;
            else
                showGenom = false;
        }
    }
}
