package Classes;



import Enums.MapDirections;
import Enums.MoveDirection;
import Interfaces.IPositionChangeObserver;

import java.awt.*;
import java.util.ArrayList;

public class Animal extends AbstractWorldMapElement {

    private int energy;
    private final int startEnergy;
    private final int minCopEnergy;

    private MapDirections orientation = MapDirections.NORTH;
    private AbstractWorldMap map;
    private Gene gene;

    public Animal(AbstractWorldMap map, Vector2d initialPosition,int genSize,int genRange,int startEnergy, int minCopEnergy)
    {
        this.map = map;
        addObserver(map);
        if (map.canMoveTo(initialPosition))
            this.position = initialPosition;
        gene = new Gene(genSize,genRange);
        this.startEnergy = startEnergy;
        this.minCopEnergy = minCopEnergy;
        setEnergy(startEnergy);


    }
    public Animal (AbstractWorldMap map, Vector2d initialPosition,int startEnergy, int minCopEnergy)
    {
        this.map = map;
        addObserver(map);
        if (map.canMoveTo(initialPosition))
            this.position = initialPosition;
        this.startEnergy = startEnergy;
        this.minCopEnergy = minCopEnergy;
        setEnergy(startEnergy);
    }
    public String toString()
    {
        switch(orientation)
        {
            case NORTH:
                return "N";
            case EAST:
                return "E";
            case WEST:
                return "W";
            case SOUTH:
                return "S";
            case NORTHWEST:
                return "NW";
            case SOUTHWEST:
                return "SW";
            case NORTHEAST:
                return "NE";
            case SOUTHEAST:
                return "SE";
            default:
                return "Błąd";
        }
    }
    public void move(MoveDirection direction)
    {
        switch(direction) {
            case FORWARD:
                Vector2d prev1 = this.position;
                Vector2d mov1_vec = this.orientation.toUnitVector();
                Vector2d new_vec = this.position.add(mov1_vec);
                new_vec = map.vectorInBorders(new_vec);
                this.position = new_vec;
                for (int i = 0; i < observers.size(); i++)
                {
                    observers.get(i).positionChanged(prev1,new_vec,this);
                }
                break;
            case BACKWARD:
                Vector2d prev2 = this.position;
                Vector2d mov2_vec = this.orientation.toUnitVector().opposite();
                Vector2d new2_vec = this.position.add(mov2_vec);
                //System.out.println(new2_vec.x + "    " + new2_vec.y);
                new2_vec = map.vectorInBorders(new2_vec);
                this.position = new2_vec;
                //System.out.println(new2_vec.x + "    " + new2_vec.y);
                for (int i = 0 ; i < observers.size();i++)
                    observers.get(i).positionChanged(prev2,new2_vec,this);
                break;
            case RIGHT:
                this.orientation = this.orientation.next();
                break;
            case LEFT:
                this.orientation = this.orientation.previous();
                break;
            default:
                break;
        }

    }
    public void rotate() {

        int rotation = gene.getRandomGene();
        for (int i = 0; i < rotation; i++) {
            this.move(MoveDirection.RIGHT);
        }
    }
    public Vector2d getPosition()
    {
        return this.position;
    }

    public void removeObserver(IPositionChangeObserver observer)
    {

        observers.remove(observer);
        return;

    }

    public void positionChanged(Vector2d oldPos, Vector2d newPos)
    {
        map.positionChanged(oldPos,newPos,this);
    }

    public  boolean isMovable()
    {
        return true;
    }

    public Gene getGenes()
    {
        return this.gene;
    }

    public int getEnergy() { return energy; }
    public int getStartEnergy() { return startEnergy; }
    public int getMinCopEnergy() {return minCopEnergy;}

    public void setEnergy(int energy) { this.energy = energy;}
    public void changeEnergy(int energy)
    {
        this.energy += energy;
        if (this.energy < 0)
        {
            this.energy = 0;
        }
    }


    public Animal breed(Animal female) {

        int childEnergy = (int)  (this.energy * 0.25) + (int) (0.25 * female.energy)  ;

        female.changeEnergy((int) -(0.25 * female.energy));

        this.changeEnergy((int) -(this.energy * 0.25));

        Animal child = new Animal(map, this.getPosition(), childEnergy + 1,this.minCopEnergy);

        child.gene = new Gene(this.gene, female.gene);

        return child;
    }
    public MapDirections getOrientation()
    {
        return this.orientation;
    }
    @Override
    public Color getColor()
    {
        return (new Color(100,100,100));
    }
}
