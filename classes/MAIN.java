package Classes;

import Enums.MoveDirection;
import World.EvolutionWorld;

import java.util.ArrayList;

public class MAIN {
    public static void main(String args[])
    {
        ArrayList<MoveDirection> directions = new OptionsParser().parse(args);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        EvolutionWorld ev = new EvolutionWorld (10,10,0.5);
       // SimulationEngine eng = new SimulationEngine(directions,ev,positions);
        //ev.showMeAnimals();
        //eng.run();
        //ev.showMeAnimals();
        ev.addAnimals(4);
        for (int i=0;i<3;i++)
        {
            ev.everyDay();
        }
        ev.toString();
        ArrayList<Animal> an = ev.getAnimalList();

        for (int i=0;i<an.size();i++)
        {
            Animal zwierze = an.get(i);
            Gene gen = zwierze.getGenes();
            System.out.println(an.size());
            //System.out.println(gen.toString());
            System.out.println(an.get(i).getEnergy());

        }
    }
}
