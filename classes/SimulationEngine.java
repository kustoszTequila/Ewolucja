package Classes;


import Enums.MoveDirection;
import Interfaces.IEngine;

import java.util.ArrayList;

public class SimulationEngine implements IEngine {

    private ArrayList<MoveDirection> movDir;
    private AbstractWorldMap map;
    private Vector2d[] vectors;
    public SimulationEngine(ArrayList<MoveDirection> movDir, AbstractWorldMap map, Vector2d[] vectors)
    {
        this.movDir = movDir;
        this.map = map;
        this.vectors = vectors;
        // od razu umieszczam zwierzęta na mapie
        for (int i=0; i<vectors.length;i++)
        {
            vectors[i] = map.vectorInBorders(vectors[i]);
        }
        for (int i = 0; i < vectors.length; i++)
        {

            map.place(new Animal(this.map, new Vector2d(vectors[i].x,vectors[i].y),32,7,8,4));
        }

    }

    public void run() {
        for (int i = 0; i < movDir.size();)
        {
            for (int j =0 ; j<map.animalList.size();j++)
            {
                map.animalList.get(j).rotate();
                map.animalList.get(j).move(movDir.get(i));

                i++;
            }
            map.placeGrass();
           // map.showMeAnimals();
            map.eatGrass();
        }

    }
}
