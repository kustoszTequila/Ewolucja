package World;

import Classes.*;
import Enums.MoveDirection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;


public class EvolutionWorld extends AbstractWorldMap {

    public  Vector2d lowerLeftJnglV;
    public  Vector2d upperRightJnglV;
    public  Vector2d upperRightV;
    public  Vector2d lowerLeftV;
    public int width;
    public int height;
    private double jungleRatio;
    public int jungleWidth, jungleHeight;
    private int startEnergy=8,moveEnergy=4;
    private int minCopEnergy;
    private int genSize =32,genRange =7;



    public EvolutionWorld(int swidth, int sheight,double jungleRatio)
    {
        // przypisanie wartości atrybutą
        this.width = swidth;
        this.height = sheight;
        this.jungleRatio = jungleRatio;

        // utworzenie wektora lewego dolnego rogu i prawego górnego
        this.lowerLeftV  = new Vector2d(0,0);
        this.upperRightV = new Vector2d (width-1,height-1);
        jungleWidth = (int)(this.width * jungleRatio);
        jungleHeight = (int)(this.height * jungleRatio);
        lowerLeftJnglV = new Vector2d ((int)(width - jungleWidth)/2,(int)(height-jungleHeight)/2);
        upperRightJnglV = new Vector2d (lowerLeftJnglV.x+jungleWidth,lowerLeftJnglV.y+jungleHeight);


    }

    public Vector2d vectorInBorders(Vector2d vec)
    {
        int x = vec.x;
        int y = vec.y;

        if (x >= lowerLeftV.x)
        {
            x = x % width;
        }
        else
        {
            x = (width - Math.abs((x % width)));
        }
        if ( y >= lowerLeftV.y)
        {
            y = y % height;
        }
        else
        {
            y = (height -  Math.abs((y % height)));
        }
        return new Vector2d(x,y);

    }

    public boolean canMoveTo(Vector2d position)
    {
        return true;
    }
    public boolean canPlaceGrass (Vector2d position)
    {
        position = vectorInBorders(position);
        if ( grasses.get(position) == null)
        {
            return animals.get(position) == null;
        }
        return false;
    }
    public boolean place (AbstractWorldMapElement e)
    {
        Vector2d newVec = vectorInBorders(e.getPosition());

        if (e instanceof Animal)
        {
            addToMap((Animal)e,newVec);
            animalList.add((Animal) e);
            //e.addObserver(this);

        }
        else if ( e instanceof Grass)
        {
            grasses.put(newVec,(Grass) e);
            grassList.add((Grass) e);
            e.addObserver(this);
        }
        return true;

    }
    public boolean isOccupied(Vector2d position)
    {
        return objectAt(position) != null;
    }

    public Object objectAt(Vector2d vector)
    {
        Vector2d vector2 = vectorInBorders(vector);
        ArrayList<Animal> l = animals.get(vector2);
        if (l == null) return grasses.get(vector2);
        else if (l.size() == 0) return grasses.get(vector2);
        else return l.get(0);
    }
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition,Object animal)
    {
        oldPosition = vectorInBorders(oldPosition);
        newPosition = vectorInBorders(newPosition);

        if (canMoveTo(newPosition))
        {

            removeFromMap((Animal) animal, oldPosition);
            addToMap((Animal) animal, newPosition);
            ((Animal) animal).changeEnergy(-1);

        }

    }
    @Override
    public String toString()
    {
        MapVisualizer vis = new MapVisualizer(this);
        System.out.println(vis.draw(lowerLeftV, upperRightV));
        return null;
    }
    public boolean addToMap(Animal animal, Vector2d vector)
    {
        //System.out.println("zaczynam dodawnie");
        ArrayList<Animal> list = animals.get(vector);
       // System.out.println("teraz dodaje: " + animal + vector);
        if (list == null)
        {

            ArrayList<Animal> tmp = new ArrayList<Animal>();
            tmp.add(animal);
           //System.out.println("tablica wejściowa: " + tmp);
            animals.put(vector,tmp );
        }

        else if (list != null)
        {
            //System.out.println(list);
            list.add(animal);
           // System.out.println(list);
        }
       // System.out.println("kończe dodawnie");
        return true;
    }
    public boolean removeFromMap(Animal animal,Vector2d vector) {
        ArrayList<Animal> list = animals.get(vector);

        if (list == null) {
            return false;

        }
        else if (list.size() == 0)
        {
            return false;
        }
        else
        {
            list.remove(animal);
            if (list.size() == 0)
            {
                animals.remove(vector);
            }
            return true;
        }
    }
    public ArrayList<Animal> getAnimalList()
    {
        return animalList;
    }
    public void placeGrass()
    {
        System.out.println("zaczynam kłaść trawe");

        int jungleSize = jungleHeight * jungleWidth;
        int counter = 0;
        while (counter < jungleSize)
        {
            int x = (int) (Math.random() * jungleWidth + lowerLeftJnglV.x);
            int y = (int) (Math.random() * jungleHeight + lowerLeftJnglV.y);
            if (canPlaceGrass(new Vector2d(x,y)))
            {
                place(new Grass(new Vector2d(x,y)));
                break;
            }
            counter++;
        }

        int steppSize = width * height - jungleSize;
        counter = 0;
        while (counter < steppSize)
        {
            int x = (int) (Math.random() * width + lowerLeftV.x);
            int y = (int) (Math.random() * height + lowerLeftV.y);
            Vector2d newVec =  new Vector2d(x,y);
            if (canPlaceGrass(newVec) && !newVec.follows(lowerLeftJnglV) && newVec.precedes(upperRightJnglV) )
            {
                place(new Grass(new Vector2d(x,y)));
                break;
            }
            counter++;
        }
        System.out.println("Trawa połozóna");
    }
    public void eatGrass()
    {
        ArrayList<Grass> grassToDelete  = new ArrayList<Grass>();
        System.out.println("rozmiar trawy: " + grassList.size());
        for (int i=0;i < grassList.size();i++)
        {
            ArrayList<Animal> anim = animals.get(grassList.get(i).getPosition());
            System.out.println("tak się prezentuje tablica: " + anim);


            if (anim != null )
            {

                if (anim.size() != 0)
                {
                    System.out.println("zjedanie trawy");
                    grassToDelete.add(grassList.get(i));
                    int maks = getMaxEnergy(anim);
                    ArrayList<Animal> eatingAnimals = new ArrayList<Animal>();
                    for (int j=0;j<anim.size();j++)
                    {
                        if (anim.get(j).getEnergy() == maks)
                        {
                            eatingAnimals.add(anim.get(j));
                        }

                    }
                    System.out.println(plantEnergy);
                    int dividedEnergy = (int)(plantEnergy/eatingAnimals.size());
                    for (int j =0;j<eatingAnimals.size();j++)
                    {
                        eatingAnimals.get(j).changeEnergy(dividedEnergy);
                    }
                    for (int j=0;j<grassToDelete.size();j++)
                    {
                        grassList.remove(grassToDelete.get(j));
                        grasses.remove(grassToDelete.get(j).getPosition());
                    }
                }
            }
        }
    }

    public int getMaxEnergy(ArrayList<Animal> animal)
    {
        int maks = 0;
        for (int i=0; i<animal.size();i++)
        {
            if (animal.get(i).getEnergy() > maks)
            {
                maks = animal.get(i).getEnergy();
            }
        }
        return maks;
    }
    public void everyDay()
    {
        removeDeadAnimals();
        rotateAnimals();
        moveAnimals();
        eatGrass();
        breeding();
        placeGrass();
    }

    public void breeding() {

        for (Map.Entry<Vector2d,ArrayList<Animal>> entry : animals.entrySet()) {
            ArrayList<Animal> animalArray = entry.getValue();
            if (animalArray != null) {
                if (animalArray.size() == 2) {
                    Animal female = animalArray.get(0);
                    Animal male = animalArray.get(1);
                    if (female.getEnergy() >= minCopEnergy && male.getEnergy() >= minCopEnergy)
                        {
                            Animal child = male.breed(female);
                            int counter = 0;
                            while (counter <8 )
                            {
                                Vector2d prev = child.getPosition();
                                Vector2d mov_vec = child.getOrientation().toUnitVector();
                                Vector2d new_vec = child.getPosition().add(mov_vec);
                                if (!isOccupied(new_vec))
                                {
                                    child.move(MoveDirection.FORWARD);
                                    break;
                                }
                                else
                                {
                                    child.move(MoveDirection.RIGHT);
                                    counter++;
                                }
                            }
                            place(child);
                        }
                }
            }
        }
    }
    public void rotateAnimals()
    {
        for (int i=0;i<animalList.size();i++)
        {
            animalList.get(i).rotate();
        }
    }
    public void moveAnimals()
    {
        for (int i=0;i<animalList.size();i++)
        {
            animalList.get(i).move(MoveDirection.FORWARD);

        }
    }
    public void removeDeadAnimals() {
        for (int i = 0; i < animalList.size(); i++) {
            Animal a = animalList.get(i);
            if (a.getEnergy() <= 0) {
                removeFromMap(a, a.getPosition());
                a.removeObserver(this);
                animalList.remove(a);
            }
        }
    }
    public void addAnimals(int animalNumber)
    {
        int mapSize = height * width;
        while (animalNumber > 0)
        {
            int x = (int) (Math.random() * width + lowerLeftV.x);
            int y = (int) (Math.random() * height + lowerLeftV.y);
            Vector2d newVec =  new Vector2d(x,y);
            if (isOccupied(newVec) == false)
            {
                place(new Animal(this,newVec,genSize,genRange,startEnergy,minCopEnergy));
                animalNumber --;
            }

        }

    }

    public void showMeAnimals()
    {
        for (Map.Entry<Vector2d,ArrayList<Animal>> entry : animals.entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue());
            ArrayList<Animal> an = entry.getValue();
            for (int i =0;i<an.size();i++)
            {
                System.out.println(an.get(i).getPosition());
            }
        }
    }

    public double getJungleRatio()
    {
        return this.jungleRatio;
    }
    public Vector2d getLowerLeftJnglV()
    {
        return this.lowerLeftJnglV;
    }
    public ArrayList<Grass> getGrassList()
    {
        return this.grassList;
    }




}
