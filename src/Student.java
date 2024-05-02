import java.io.*;
import java.util.*;

public class Student {
    private int cwid;
    private double points;
    private int groupID;
    public static Queue<Student> dents = new LinkedList<>();
    public Student(int cwid, double points){
        this.cwid=cwid;
        this.points= points;
        //this.groupID=groupID;
    }

    static void createStudents(){
        Random random = new Random();
        for(int i=1;i<=982;i++){
            try{
                PrintWriter writer=new PrintWriter(new FileWriter("AllStudents.txt",true));
                writer.write("CWID: "+(20000000+i));
                writer.write("\n");
                writer.write("Priority points: "+(random.nextInt(17) + 10));
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
    public static Queue<Student> getStudents() {
        return dents;
    }
    public double getpPoints(){
        return points;
    }
    public int getCwid(){
        return cwid;
    }

    public static void putStuInList() {
        //createStudents();
        dents.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("AllStudents.txt"))) {
            String line;
            String name = null;
            int cwid = 0;
            int ppoints = 0;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("CWID: ")) {
                    cwid = Integer.parseInt(line.substring(6));
                } else if (line.startsWith("Priority points: ")) {
                    ppoints = Integer.parseInt(line.substring(17));
                    dents.add(new Student(cwid, ppoints));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

    }

}
