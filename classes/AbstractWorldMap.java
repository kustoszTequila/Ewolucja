package Classes;


import Interfaces.IPositionChangeObserver;
import Interfaces.IWorldMap;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

abstract public class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected Map<Vector2d,ArrayList<Animal>> animals = new ConcurrentHashMap<Vector2d,ArrayList<Animal>>();
    protected Map <Vector2d,Grass> grasses = new HashMap<Vector2d,Grass>();
    protected ArrayList<Animal> animalList =  new ArrayList<Animal>();
    protected ArrayList<Grass> grassList =  new ArrayList<Grass>();

    protected int plantEnergy = 2;
    public abstract String toString();

    //IWorldMap
    public abstract boolean canMoveTo(Vector2d position);
    public abstract boolean place(AbstractWorldMapElement animal);
    public abstract boolean isOccupied(Vector2d position);
    public abstract Object objectAt(Vector2d position);
    public abstract void placeGrass();
    //IChangeObserver
    public abstract void positionChanged(Vector2d oldPosition, Vector2d newPosition, Object animal);
    public abstract Vector2d vectorInBorders(Vector2d vec);
    public abstract void eatGrass();
    public abstract void showMeAnimals();


}
