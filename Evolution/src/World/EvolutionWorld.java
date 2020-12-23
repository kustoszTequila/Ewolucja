package World;

import Classes.*;
import Enums.MoveDirection;
import Interfaces.IPositionChangeObserver;
import Interfaces.IWorldMap;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class EvolutionWorld implements IWorldMap, IPositionChangeObserver {


    protected Map<Vector2d,ArrayList<Animal>> animals = new ConcurrentHashMap<Vector2d,ArrayList<Animal>>();
    protected Map <Vector2d,Grass> grasses = new HashMap<Vector2d,Grass>();
    protected ArrayList<Animal> animalList =  new ArrayList<Animal>();
    protected ArrayList<Grass> grassList =  new ArrayList<Grass>();
    public LinkedHashMap<Gene,Genom> genomes = new LinkedHashMap<>();
    private static int id =0;
    private  Vector2d lowerLeftJnglV;
    private  Vector2d upperRightJnglV;
    private  Vector2d upperRightV;
    private  Vector2d lowerLeftV;
    public int width;
    public int height;
    private double jungleRatio;
    public int jungleWidth, jungleHeight;
    private int startEnergy,moveEnergy;
    private int minCopEnergy;
    private int genSize ,genRange ;
    private int plantEnergy ;
    private int age;

    private int deadAnimals =0;
    private int avgLifespan=0;
    private double avgDeadLife =0;
    public ChildObserver childObserver;




    public EvolutionWorld(int swidth, int sheight,double jungleRatio,int startEnergy, int minCopEnergy,int moveEnergy, int genRange, int genSize, int planyEnergy)
    {
        // przypisanie wartości atrybutą
        this.width = swidth;
        this.height = sheight;
        this.jungleRatio = jungleRatio;
        this.age =0;
        this.startEnergy= startEnergy;
        this.minCopEnergy = minCopEnergy;
        this.moveEnergy = moveEnergy;
        this.genSize = genSize;
        this.genRange = genRange;
        this.plantEnergy = planyEnergy;
        this.childObserver = new ChildObserver(this);

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

        }
        else if ( e instanceof Grass)
        {
            grasses.put(newVec,(Grass) e);
            grassList.add((Grass) e);

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

        removeFromMap((Animal) animal, oldPosition);
        addToMap((Animal) animal, newPosition);
        ((Animal) animal).changeEnergy(-1*moveEnergy);
    }
    public void addToMap(Animal animal, Vector2d vector)
    {
        ArrayList<Animal> list = animals.get(vector);
        if (list == null)
        {
            ArrayList<Animal> tmp = new ArrayList<Animal>();
            tmp.add(animal);
            animals.put(vector,tmp );
        }
        else
        {

            list.add(animal);

        }
    }
    public void removeFromMap(Animal animal,Vector2d vector) {
        vector = vectorInBorders(vector);
        ArrayList<Animal> list = animals.get(vector);

        if (list == null) {
            return ;

        }
        else if (list.size() == 0)
        {
            animals.remove(vector);
            return ;
        }
        else
        {

            list.remove(animal);

            if (list.size() == 0)
            {
                animals.remove(vector);
            }

        }
    }
    public ArrayList<Animal> getAnimalList()
    {
        return animalList;
    }
    public void placeGrass()
    {
        int jungleSize = jungleHeight * jungleWidth;
        int counter = 0;
        while (counter < jungleSize*2)
        {
            int x = (int) (Math.random() * jungleWidth + lowerLeftJnglV.x);
            int y = (int) (Math.random() * jungleHeight + lowerLeftJnglV.y);
            if (canPlaceGrass(new Vector2d(x,y)))
            {
                place(new Grass(new Vector2d(x,y),plantEnergy));
                break;
            }
            counter++;
        }
        int steppSize = width * height - jungleSize;
        counter = 0;
        while (counter < steppSize*2)
        {
            int x = (int) (Math.random() * width + lowerLeftV.x);
            int y = (int) (Math.random() * height + lowerLeftV.y);
            Vector2d newVec =  new Vector2d(x,y);
            if (canPlaceGrass(newVec) && !(newVec.follows(lowerLeftJnglV) && newVec.precedes(upperRightJnglV)) )
            {
                place(new Grass(new Vector2d(x,y),plantEnergy));
                break;
            }
            counter++;
        }

    }
    public void eatGrass()
    {
        ArrayList<Grass> grassToDelete  = new ArrayList<Grass>();
        for (int i=0;i < grassList.size();i++)
        {
            ArrayList<Animal> anim = animals.get(grassList.get(i).getPosition());
            if (anim != null )
            {
                if (anim.size() != 0)
                {
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
                    int dividedEnergy = (int)(grassList.get(i).getEnergy()/eatingAnimals.size());
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
        age++;
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
                    animalArray.sort(Comparator.comparing(Animal::getEnergy));
                    Animal female = animalArray.get(0);
                    Animal male = animalArray.get(1);
                    if (female.getEnergy() >= minCopEnergy && male.getEnergy() >= minCopEnergy)
                        {
                            Animal child = male.breed(female);
                            female.setParent();
                            childObserver.addChildren(female,child);
                            int counter = 0;
                            while (counter <8 )
                            {
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
                            animalList.add(child);
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
                deadAnimals ++;
                avgLifespan += a.getAge();
                if (a.getAnimalPanel() != null && a.getAnimalPanel().getAnimal().equals(a))
                    a.changeStatus(age);
                if(a.getParent())
                    childObserver.removeParent(a);

                removeFromMap(a, a.getPosition());
                animalList.remove(a);
            }
            else
            {
                a.increaseAge();
            }
        }
        if(deadAnimals != 0)
        {
            this.avgDeadLife =((double)avgLifespan/deadAnimals);

        }
    }
    public void addAnimals(int animalNumber)
    {
        while (animalNumber > 0)
        {
            int x = (int) (Math.random() * width + lowerLeftV.x);
            int y = (int) (Math.random() * height + lowerLeftV.y);
            Vector2d newVec =  new Vector2d(x,y);
            if (isOccupied(newVec) == false)
            {
                place(new Animal(this,newVec,genSize,genRange,startEnergy,minCopEnergy,id));
                this.id++;
                animalNumber --;
            }
        }
    }
    public void addGrass(int grassNumber)
    {
        while (grassNumber > 0)
        {
            int x = (int) (Math.random() * width + lowerLeftV.x);
            int y = (int) (Math.random() * height + lowerLeftV.y);
            Vector2d newVec =  new Vector2d(x,y);
            if ( grasses.get(newVec) == null)
            {
                place(new Grass(newVec,plantEnergy));
                grassNumber --;
            }
        }
    }
    public Vector2d getLowerLeftJnglV()
    {
        return this.lowerLeftJnglV;
    }
    public ArrayList<Grass> getGrassList()
    {
        return this.grassList;
    }
    public int getAnimalSize()
    {
        return animalList.size();
    }
    public int getGrassSize()
    {
        return grassList.size();
    }
    public double getAvgEnergy()
    {
        double eng=0;
        for (int i=0;i<animalList.size();i++)
        {
            eng += animalList.get(i).getEnergy();
        }
        eng = eng/animalList.size();
        return eng;
    }
    public double getAvgDeadLife()
    {
        return avgDeadLife;
    }
    public Map<Vector2d,ArrayList<Animal>> getAnimalMap()
    {
        return animals;
    }

    public void addToGenom(Gene gene)
    {
        Genom genom = genomes.get(gene);
        if (genom == null)
        {
            genomes.put(gene,new Genom (gene));
        }
        else
        {
            genom.increase();
        }
    }
    public Gene getMaxGenom()
    {
        int min = -1;
        Gene gene = null;
        for (Map.Entry<Gene,Genom> entry : genomes.entrySet()) {
            Genom genom = entry.getValue();
            if (genom.getSize() > min)
            {
                min = genom.getSize();
                gene = genom.getGenotype();
            }
        }
        return gene;
    }
    public int getId()
    {
        return id;
    }
}
