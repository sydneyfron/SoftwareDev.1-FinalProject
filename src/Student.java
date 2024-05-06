import java.io.*;
import java.util.*;

public class Student {
    private int cwid;
    private double points;

    public static Queue<Student> students = new LinkedList<>();
    public Student(int cwid, double points){
        this.cwid=cwid;
        this.points= points;
    }

    static void createStudents(int numStu){
        Random random = new Random();
        for(int i=1;i<=numStu;i++){
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
        return students;
    }
    public double getpPoints(){
        return points;
    }
    public int getCwid(){
        return cwid;
    }

    public static void putStuInList() {
        //createStudents();
        students.clear();
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
                    students.add(new Student(cwid, ppoints));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

    }
    public static void clearStuTxt() {
        String filePath = "AllStudents.txt";
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
