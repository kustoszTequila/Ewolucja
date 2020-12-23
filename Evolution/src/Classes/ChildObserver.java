package Classes;

import GUI.GenomFrame;
import GUI.PropertiesPanel;
import World.EvolutionWorld;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChildObserver {

    EvolutionWorld map;
    protected Map<Animal,ArrayList<Animal>> animals = new ConcurrentHashMap<Animal,ArrayList<Animal>>();
    private int parents;
    private int children;

    public ChildObserver(EvolutionWorld map)
    {
        this.map = map;
        this.parents = 0;
        this.children = 0;
    }
    public double childrenRatio()
    {
        parents =0;
        children =0;
        parents = animals.values().size();
        for (Map.Entry<Animal,ArrayList<Animal>> entry : animals.entrySet())
        {
            ArrayList<Animal> list = entry.getValue();
            for (int i=0;i<list.size();i++)
            {
                children++;
            }

        }
        if (parents == 0)
            return 0;
        else
            return ((double)children/(double)parents);
    }
    public void removeParent(Animal animal)
    {
        animals.remove(animal);
    }
    public void addChildren(Animal parent,Animal child)
    {
        ArrayList<Animal> list = animals.get(parent);
        if (list == null)
        {
            ArrayList<Animal> tmp = new ArrayList<Animal>();
            tmp.add(child);
            animals.put(parent,tmp );
        }
        else
        {

            list.add(child);

        }
    }

}
