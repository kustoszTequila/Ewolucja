package Classes;


import Interfaces.IPositionChangeObserver;

import java.awt.*;

public class Grass extends AbstractWorldMapElement {



    public Grass ( Vector2d new_vec)
    {
        this.position = new_vec;

    }
    public Vector2d getPosition()
    {
        return this.position;
    }

    public String toString()
    {
        return "*";
    }

    public void addObserver(IPositionChangeObserver observer) {
        return;
    }

    public  boolean isMovable()
    {
        return false;
    }

    @Override
    public Color getColor()
    {
        return (new Color(0,150,0));
    }
}
