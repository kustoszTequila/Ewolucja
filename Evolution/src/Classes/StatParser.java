package Classes;
import java.io.FileWriter;
import java.io.IOException;
public class StatParser {

    private int age;
    private double animalNum;
    private double grassNum;
    private double avgEnergy;
    private double avgLifespan;
    private double avgChildren;
    private Gene genom;

    public StatParser(int age,int animalNum,int grassNum,double avgEnergy,double avgLifespan,double avgChildren, Gene genom)
    {
        this.age =age;
        this.animalNum = (animalNum/age);
        this.grassNum = (grassNum/age);
        this.avgEnergy = (avgEnergy/age);
        this.avgLifespan = (avgLifespan/age);
        this.avgChildren = (avgChildren/age);
        this.genom = genom;
    }
    public void writeToFile()
    {
        try {
            FileWriter myWriter = new FileWriter("statistics" + age + ".txt");
            myWriter.write("age: " + age + '\n');
            myWriter.write("Number of animals: " + animalNum+ '\n');
            myWriter.write("Number of grass: " + grassNum+ '\n');
            myWriter.write("Average energy: " + avgEnergy+ '\n');
            myWriter.write("Average Lifespan: " + avgLifespan+  '\n');
            myWriter.write("Average Children: " + avgChildren + '\n');
            myWriter.write("Dominant Genom: " + genom + '\n');
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
