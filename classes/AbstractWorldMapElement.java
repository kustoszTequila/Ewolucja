package Classes;


import Interfaces.IMapElement;
import Interfaces.IPositionChangeObserver;

import java.awt.*;
import java.util.ArrayList;

abstract  public class AbstractWorldMapElement implements IMapElement {
    protected Vector2d position;
    protected ArrayList<IPositionChangeObserver> observers = new ArrayList<>();
    public abstract Vector2d getPosition();
    public abstract String toString();
    public abstract boolean isMovable() ;
    public void addObserver(IPositionChangeObserver observer)
    {
        observers.add(observer);
    }
    public Color getColor()
    {
        return (new Color(255,255,255));
    }
}
