package Classes;


import Interfaces.IMapElement;
import Interfaces.IPositionChangeObserver;

import java.awt.*;
import java.util.ArrayList;

abstract  public class AbstractWorldMapElement implements IMapElement {
    protected int energy;
    protected Vector2d position;
    public abstract String toString();
    public Vector2d getPosition()
    {
        return this.position;
    }
    public Color getColor()
    {
        return (new Color(255,255,255));
    }
    public int getEnergy() { return energy; }
    public void setEnergy(int energy) { this.energy = energy;}
}
