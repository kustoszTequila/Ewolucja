package Classes;


import Enums.MoveDirection;

import java.util.ArrayList;

public class OptionsParser {

    public ArrayList<MoveDirection> parse(String[] directions)
    {
        ArrayList<MoveDirection> ret_directions = new ArrayList<MoveDirection>();
        for (String direction : directions) {
            switch (direction) {
                case "f":
                case "forward":
                    ret_directions.add(MoveDirection.FORWARD);
                    break;
                case "b":
                case "backward":
                    ret_directions.add(MoveDirection.BACKWARD);
                    break;
                case "l":
                case "left":
                    ret_directions.add(MoveDirection.LEFT);
                    break;
                case "r":
                case "right":
                    ret_directions.add(MoveDirection.RIGHT);
                    break;
                default:
                    throw new IllegalArgumentException(direction + " is not legal move specification");
            }
        }
        return ret_directions;

    }
}
