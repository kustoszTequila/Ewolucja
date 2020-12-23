package Classes;

public class Genom {


    private Gene genotype;
    private int number;


    public Genom(Gene genotype)
    {
        this.genotype = genotype;
        this.number =1;
    }
    public void increase()
    {
        this.number ++;
    }
    public int getSize()
    {
        return number;
    }
    public Gene getGenotype()
    {
        return this.genotype;
    }
}
