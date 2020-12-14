package Classes;


import Interfaces.IPositionChangeObserver;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {

    Comparator<Vector2d> xComp = new XComparator();
    Comparator<Vector2d> yComp = new YComparator();
    SortedSet<Vector2d> xSorted = new TreeSet<Vector2d>(xComp);
    SortedSet<Vector2d> ySorted = new TreeSet<Vector2d>(yComp );

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition,Object animal)
    {
            xSorted.remove(oldPosition);
            ySorted.remove(oldPosition);
            xSorted.add(newPosition);
            ySorted.add(newPosition);
    }

    public void addToX(Vector2d el)
    {
        xSorted.add(el);
    }
    public void addToY(Vector2d el)
    {
        ySorted.add(el);
    }
    public Vector2d getMinBound()
    {
        return (new Vector2d(xSorted.first().x,ySorted.first().y));
    }
    public Vector2d getMaxBound()
    {
        return (new Vector2d(xSorted.last().x,ySorted.last().y));
    }
}
