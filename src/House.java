import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class House {

    private String hName;
    private int perHouse;
    private int numOfHouse;
    public static List<House> houses = new ArrayList<>();
    //private List<Student> students= new ArrayList<>();




    public House(String hName, int perHouse, int numOfHouse) {
        this.hName = hName;
        this.perHouse = perHouse;
        this.numOfHouse = numOfHouse;

    }

    public String getHName() {
        return hName;
    }

    public void setHName(String hName) {
        this.hName = hName;
    }

    public int getPerHouse() {
        return perHouse;
    }

    public void setPerHouse(int perHouse) {
        this.perHouse = perHouse;
    }

    public int getNumOfHouse() {
        return numOfHouse;
    }

    public void removeHouse() {
        this.numOfHouse --;
    }

    public static List<House> getHouses() {
        return houses;
    }

    public static void houseList() {
        //List<House> houses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("HousingOptions.txt"))) {
            String line;
            String name = null;
            int peoplePerHouse = 0;
            int numberOfHouses = 0;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name: ")) {
                    name = line.substring(6);
                } else if (line.startsWith("People Per House: ")) {
                    peoplePerHouse = Integer.parseInt(line.substring(18));
                } else if (line.startsWith("Number of Houses: ")) {
                    numberOfHouses = Integer.parseInt(line.substring(18));
                    houses.add(new House(name, peoplePerHouse, numberOfHouses));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    public static void main(String[]args){
        houseList();
        // Displaying the houses read from the file
        for (House house : houses) {
            System.out.println("House Name: " + house.getHName());
            System.out.println("People Per House: " + house.getPerHouse());
            System.out.println("Number of Houses: " + house.getNumOfHouse());
            System.out.println();

        }System.out.println(houses.size());
    }


}
