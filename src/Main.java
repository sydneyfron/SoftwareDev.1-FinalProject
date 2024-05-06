import java.util.*;

public class Main {

    private static void assignHousing(List<Group> groups){
        for (Group g : groups) {
            for (int i = 0; i < g.getPreferences().size(); i++) {
                if (g.endHouse != null) {
                    break;
                }
                int pref = g.getPreferences().get(i);
                House preferredHouse = House.houses.get(pref);
                if (preferredHouse != null && preferredHouse.getNumOfHouse() > 0) {
                    //System.out.println(preferredHouse.getNumOfHouse());
                    preferredHouse.removeHouse();
                    g.endHouse = preferredHouse.getHName();
                    break;
                }
            }
        }
    }
    public static void main(String[] args) {
        //This project simulates housing selection. It reads the information on housing options and
        // creates House objects which groups will be assigned to. It generates an entire class of students,
        // files info on each student in the AllStudents.txt, then a bunch of groups are
        // generated and students r put into the groups, then the list of groups is sorted
        // with the group with the highest priority point avg. first. Each group has an ordered
        // list of where they prefer to live. The list of groups is gone thru and assigned
        // housing and this information is stored in completeGroups.txt

        Scanner scan=new Scanner(System.in);
        House.houses.clear();
        House.houseList();

        Student.clearStuTxt();
        int numStu=0;
        System.out.println("Enter number of students to be simulated: ");
        while(scan.hasNext()){
            String line=scan.nextLine();
            try{
                numStu=Integer.parseInt(line);
                break;
            }
            catch(NumberFormatException e){
                System.out.println("Not a number try again");
            }}
        Student.createStudents(numStu);

        Student.students.clear();
        Student.putStuInList();

        Group.groups.clear();
        Group.createGroups();

        List<Group> groups = Group.getGroups();


        Collections.sort(groups, Comparator.comparingDouble(Group::getPoints).reversed());


        assignHousing(groups);
        Group.clearGroupTxt();
        Group.putGroupsInTXT();

        System.out.println("Simulation successful. Check CompleteGroups.txt file to see group assignments");




    }
}