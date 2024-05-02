import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

public class Group {
    private List<Integer> preferences;
    public String endHouse;
    private int groupID;
    private double points;
    private List<Student> stus;
    public static List<Group> groups = new ArrayList<>();
    static Queue<Student> dents = Student.getStudents();


    public Group(int gID) {
        int numPpl = 10;
        stus = new ArrayList<>();
        List<Student> stus = new ArrayList<>();
        groupID = gID;
        points = 0;
        List<Integer> pref=new ArrayList<>();
        int d=House.houses.size();
        preferences = new ArrayList<>();
        for (int i = 0; i < House.houses.size(); i++) {
            preferences.add(i);
        }
        Collections.shuffle(preferences);
        endHouse=null;

    }
    public List<Integer> getPreferences() {
        return preferences;
    }
    private void addStudent(Student student) {
        stus.add(student);
    }
    private void calcPpoints(){
        for(Student s: stus){
            points+=s.getpPoints();
        }
        points = Math.round((points / stus.size()) * 100.0)/100.0;
    }
    public double getPoints(){
        return points;

    }
    public static List<Group> getGroups() {
        return groups;
    }
    static void createGroups(){
        for(int i=1;i<=400;i++){
            if(dents.size()>0) {
                Group g=new Group(i);
                groups.add(g);
                for(int s=1;s<=10;s++){
                    if(dents.size()>0){
                        g.addStudent(dents.poll());

                    }


                }
                g.calcPpoints();
            }
        }
    }


    public static void putGroupsInTXT() {
        for(Group g: groups){
            try{
                PrintWriter writer=new PrintWriter(new FileWriter("CompleteGroups.txt",true));
                writer.write("GroupID: "+(g.groupID));
                writer.write("\n");
                writer.write("House in: "+(g.endHouse));
                writer.write("\n");
                writer.close();
            }
            catch(FileNotFoundException e){
                throw new RuntimeException();
            }catch(IOException e){
                throw new RuntimeException(e);
            }
        }

    }
    public static void clearTxt() {
        String filePath = "CompleteGroups.txt";
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
