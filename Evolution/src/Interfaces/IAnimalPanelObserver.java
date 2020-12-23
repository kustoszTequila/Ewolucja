package Interfaces;

import Classes.Animal;

public interface IAnimalPanelObserver {



    void increaseChildren();
    void changeStatus(int deadAge);
    Animal getAnimal();
}
