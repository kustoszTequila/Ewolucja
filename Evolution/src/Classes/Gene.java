package Classes;

import java.util.Arrays;

public class Gene {

    private int size;
    private int[] genes;
    private int range;

    public Gene(int size,int range)
    {
        this.size = size;
        this.range = range;
        genes = new int[size];
        fillRandom();
        repairGenes();
    }
    public Gene (Gene g1, Gene g2)
    {
        this(g1.getSize(),g1.getRange());

        int firstDiv = (int)(Math.random() * (this.range + 1));
        int secondDiv = firstDiv;
        while (firstDiv == secondDiv)
        {
            secondDiv = (int)(Math.random() * (this.range + 1));
        }
        if (firstDiv > secondDiv)
        {
            int tmp = firstDiv;
            firstDiv = secondDiv;
            secondDiv = tmp;
        }

        for (int i =firstDiv;i < secondDiv;i++)
        {
            genes[i] = g1.getGenes(i);
        }
        for (int i = secondDiv; i <this.size;i++)
        {
            genes[i] = g2.getGenes(i);
        }

        repairGenes();
    }
    public void fillRandom()
    {
        for (int i=0;i<size;i++)
        {
            genes[i] = (int)(Math.random() * (range + 1));
        }

    }
    public int getRandomGene()
    {
        return genes[(int)(Math.random() *(size))];
    }

    public void repairGenes()
    {
        //System.out.println("zaczynam naprawe genów");
        boolean inOrder = true;
        while (inOrder) {
            inOrder = false;
            boolean[] isPresent = new boolean[range + 1];
            for (int i = 0; i < range + 1; i++) {
                isPresent[i] = false;
            }

            for (int i = 0; i < size; i++) {
                isPresent[genes[i]] = true;
            }

            for (int i = 0; i < range + 1; i++) {
                if (isPresent[i] == false) {
                    inOrder = true;
                    break;
                }
            }
            if (inOrder == true) {
                for (int i = 0; i < range + 1; i++) {
                    if (isPresent[i] == false) {
                        genes[(int) (Math.random() * (size ))] = i;
                    }
                }
            }
        }
        Arrays.sort(genes);
        //System.out.println("kończe naprawe genów");
    }
    public int getGenes(int i)
    {
        return genes[i];
    }
    public int[] getGenesArray()
    {
        return genes;
    }
    public int getSize()
    {
        return size;
    }
    public int getRange()
    {
        return range;
    }
    @Override
    public String toString()
    {
        String toReturn = "";
        for (int i=0; i<size;i++)
        {
            toReturn += genes[i] + " ";
        }
        return toReturn;
    }
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Gene)
        {
            if (this.getRange() == ((Gene) obj).getRange())
            {
                if (Arrays.equals(genes,((Gene) obj).getGenesArray()))
                {
                    return true;
                }
                return false;
            }
            else
                return false;
        }
        else
            return false;
    }

    @Override
    public int hashCode()
    {

        return Arrays.hashCode(genes);
    }
 }
