package Classes;


import Interfaces.IPositionChangeObserver;

import java.awt.*;

public class Grass extends AbstractWorldMapElement {

    public Grass ( Vector2d new_vec,int energy)
    {
        this.position = new_vec;
        this.setEnergy(energy);

    }

    @Override
    public String toString()
    {
        return "*";
    }

    @Override
    public Color getColor()
    {
        return (new Color(0,150,0));
    }

}
