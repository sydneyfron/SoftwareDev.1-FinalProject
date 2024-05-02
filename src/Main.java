import java.util.*;

public class Main {
    public static void main(String[] args) {
        House.houses.clear();
        House.houseList();

        Student.dents.clear();
        Student.putStuInList();

        Group.groups.clear();
        Group.createGroups();

        List<Group> groups = Group.getGroups();

        /*for(Group g:groups){
            for(int i=0;i<g.getPreferences().size();i++){
                if(g.endHouse!=null){
                    break;
                }
                if(House.houses.get((g.getPreferences().get(i))).getNumOfHouse()>0){
                    System.out.println(House.houses.get((g.getPreferences().get(i))).getNumOfHouse());
                    House.houses.get(i).removeHouse();
                    g.endHouse=House.houses.get(i).getHName();


                }
            }
        }*/
        Collections.sort(groups, Comparator.comparingDouble(Group::getPoints).reversed());

        for (Group g : groups) {
            for (int i = 0; i < g.getPreferences().size(); i++) {
                if (g.endHouse != null) {
                    break;
                }
                int pref = g.getPreferences().get(i);
                House preferredHouse = House.houses.get(pref);
                if (preferredHouse != null && preferredHouse.getNumOfHouse() > 0) {
                    System.out.println(preferredHouse.getNumOfHouse());
                    preferredHouse.removeHouse();
                    g.endHouse = preferredHouse.getHName();
                    break;
                }
            }
        }


        Group.clearTxt();
        Group.putGroupsInTXT();


    }
}