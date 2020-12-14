package Classes;


import java.util.Comparator;

public class YComparator implements Comparator<Vector2d> {

    @Override
    public int compare(Vector2d first, Vector2d second)
    {

        if (first.y > second.y)
            return 1;
        else if (second.y > first.y)
            return -1;
        else
        {
            if (first.x > second.x)
                return 1;
            else if (second.x > first.x)
                return -1;
            else
                return 0;

        }
    }
}
