package Enums;

public enum AnimationStatus {
    PLAYED,
    PAUSE;

    public AnimationStatus change()
    {
        if (this == PLAYED)
        {
            return PAUSE;
        }
        else
            return PLAYED;
    }
}
