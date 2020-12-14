package Classes;

import java.util.Objects;

public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString() {
        String str1 = Integer.toString(this.x);
        String str2 = Integer.toString(this.y);

        str1 = "(" + str1 + "," + str2 + ")";
        return str1;
    }

    public boolean precedes(Vector2d other) {
        if (this.x <= other.x && this.y <= other.y) {
            return true;
        } else
            return false;
    }

    public boolean follows(Vector2d other) {
        if (this.x >= other.x && this.y >= other.y) {
            return true;
        } else
            return false;
    }

    public Vector2d upperRight(Vector2d other) {
        int newx, newy;
        newx = Math.max(this.x, other.x);
        newy = Math.max(this.y, other.y);

        return new Vector2d(newx, newy);
    }

    public Vector2d lowerLeft(Vector2d other) {
        int newx, newy;
        newx = Math.min(this.x, other.x);
        newy = Math.min(this.y, other.y);
        return new Vector2d(newx, newy);
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    public Vector2d subtract(Vector2d other) {
        return new Vector2d(this.x - other.x, this.y - other.y);
    }


    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Vector2d)){
            return false;
        }
        Vector2d that = (Vector2d) other;
        if (this.x == that.x && this.y == that.y){
            return true;
        }else{
            return false;
        }
    }
    public Vector2d opposite()
    {
        return new Vector2d(this.x * (-1),this.y * (-1));
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }
}

