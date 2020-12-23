package Enums;

import Classes.Vector2d;

public enum MapDirections {
    NORTH,
    SOUTH,
    WEST,
    EAST,
    NORTHWEST,
    NORTHEAST,
    SOUTHWEST,
    SOUTHEAST
    ;

    public String toString()
    {
        switch(this)
        {
            case NORTH:
                return "Północ";
            case EAST:
                return "Wschód";
            case WEST:
                return "Zachód";
            case SOUTH:
                return "Południe";
            case NORTHWEST:
                return "↖";
            case SOUTHWEST:
                return "↙";
            case NORTHEAST:
                return "↗";
            case SOUTHEAST:
                return "↘";
            default:
                return "Błąd";
        }
    }
    public MapDirections next()
    {
        switch(this)
        {
            case NORTH:
                return NORTHEAST;
            case NORTHEAST:
                return EAST;
            case NORTHWEST:
                return NORTH;
            case EAST:
                return SOUTHEAST;
            case SOUTHEAST:
                return SOUTH;
            case SOUTHWEST:
                return WEST;
            case SOUTH:
                return SOUTHWEST;
            case WEST:
                return NORTHWEST;
            default:
                return this;
        }
    }
    public MapDirections previous()
    {
        switch(this)
        {
            case NORTH:
                return NORTHWEST;
            case NORTHEAST:
                return NORTH;
            case EAST:
                return NORTHEAST;
            case SOUTHEAST:
                return EAST;
            case WEST:
                return SOUTHWEST;
            case SOUTH:
                return SOUTHEAST;
            case NORTHWEST:
                return WEST;
            case SOUTHWEST:
                return SOUTH;
            default:
                return this;
        }
    }
    public Vector2d toUnitVector()
    {
        switch(this)
        {
            case NORTH:
                return new Vector2d(0,1);
            case EAST:
                return new Vector2d(1,0);
            case WEST:
                return new Vector2d(-1,0);
            case SOUTH:
                return new Vector2d(0,-1);
            case NORTHWEST:
                return new Vector2d(-1, 1);
            case SOUTHWEST:
                return new Vector2d(-1, -1);
            case NORTHEAST:
                return new Vector2d(1, 1);
            case SOUTHEAST:
                return new Vector2d(1, -1);
            default:
                return new Vector2d(0,0);
        }
    }

}
