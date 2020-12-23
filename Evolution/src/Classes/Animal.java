package Classes;



import Enums.MapDirections;
import Enums.MoveDirection;
import GUI.AnimalPanel;
import World.EvolutionWorld;

import java.awt.*;
import java.util.Objects;

public class Animal extends AbstractWorldMapElement {


    private final int startEnergy;
    private final int minCopEnergy;
    private int age;
    private MapDirections orientation = MapDirections.NORTH;
    private EvolutionWorld map;
    private Gene gene;
    public int id;
    private AnimalPanel animalPanel;
    private ChildObserver childObserver;
    private boolean isParent;

    public Animal(EvolutionWorld map, Vector2d initialPosition, int genSize, int genRange, int startEnergy, int minCopEnergy,int id)
    {
        this.age =0;
        this.map = map;
        this.position = initialPosition;
        gene = new Gene(genSize,genRange);
        map.addToGenom(gene);
        this.startEnergy = startEnergy;
        this.minCopEnergy = minCopEnergy;
        setEnergy(startEnergy);
        this.id =id;
    }
    public Animal (EvolutionWorld map, Vector2d initialPosition, int startEnergy, int minCopEnergy,int id)
    {
        this.map = map;
        this.position = initialPosition;
        this.startEnergy = startEnergy;
        this.minCopEnergy = minCopEnergy;
        setEnergy(startEnergy);
        this.id =id;
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
                map.positionChanged(prev1,new_vec,this);

                break;
            case BACKWARD:
                Vector2d prev2 = this.position;
                Vector2d mov2_vec = this.orientation.toUnitVector().opposite();
                Vector2d new2_vec = this.position.add(mov2_vec);
                new2_vec = map.vectorInBorders(new2_vec);
                this.position = new2_vec;
                map.positionChanged(prev2,new2_vec,this);
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
    public Gene getGenes()
    {
        return this.gene;
    }
    public void changeEnergy(int energy)
    {
        this.energy += energy;
        if (this.energy < 0)
        {
            this.energy = 0;
        }
    }
    public int getAge()
    {
        return this.age;
    }
    public void increaseAge()
    {
        this.age += 1;
    }

    public Animal breed(Animal female) {

        int childEnergy = (int)  (this.energy * 0.25) + (int) (0.25 * female.energy)  ;

        female.changeEnergy((int) -(0.25 * female.energy));

        this.changeEnergy((int) -(this.energy * 0.25));


        Animal child = new Animal(map, this.getPosition(), childEnergy + 1,this.minCopEnergy,map.getId());

        child.gene = new Gene(this.gene, female.gene);
        map.addToGenom(child.gene);
        if (animalPanel != null )
        {

            if(animalPanel.getAnimal().equals(this))
            {

                animalPanel.increaseChildren();
                // animalPanel.addOffspring(child);
            }
            else
            {
                //System.out.println(animalPanel.getAnimal().getGenes().toString());
                animalPanel.increaseOffspring();
            }
            child.addAnimalPanel(this.animalPanel);
        }
        else if(female.getAnimalPanel() != null )
        {
            if(female.getAnimalPanel().getAnimal().equals(female))
            {
                female.getAnimalPanel().increaseChildren();
                // female.getAnimalPanel().addOffspring(child);
            }
            else
            {
                //System.out.println(female.getAnimalPanel().getAnimal().getGenes().toString());
                female.getAnimalPanel().increaseOffspring();
            }
            child.addAnimalPanel(female.getAnimalPanel());
        }
        return child;
    }
    public MapDirections getOrientation()
    {
        return this.orientation;
    }
    @Override
    public Color getColor()
    {
        Color color;
        if (this.energy >= this.startEnergy)
        {
            color = new Color(18,255,10);
        }
        else if (this.startEnergy > this.energy && this.energy >= 0.5*startEnergy)
        {
            color = new Color(230,240,50);
        }
        else if (this.startEnergy*0.5 > this.energy && this.energy >= 0.25*startEnergy)
        {
            color = new Color(230 ,150,30);
        }
        else
            color = new Color(255,0,0);
        return color;
    }
    public void addAnimalPanel(AnimalPanel animalPanel)
    {
        this.animalPanel = animalPanel;
    }
    public void deleteAnimalPanel (){this.animalPanel = null;}
    public AnimalPanel getAnimalPanel()
    {
        return this.animalPanel;
    }
    public void changeStatus(int deadAge)
    {
        this.animalPanel.changeStatus(deadAge);
    }
    @Override
    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Vector2d)){
            return false;
        }
        Animal that = (Animal) other;
        if (this.id == that.id && this.id == that.id){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    public void setParent()
    {
        this.isParent = true;
    }
    public boolean getParent()
    {
        return this.isParent;
    }

}
