import java.util.ArrayList;

public class move {

    public move(int start, int end, ArrayList<Integer> mid){
        this.startSpace = start;
        this.endSpace = end;
        this.midSpaces = mid;
    }
    
    public int startSpace;
    public ArrayList<Integer> midSpaces; // each move can consist of many consequent jumps
    public int endSpace;

    public static ArrayList<Integer> getPath(move m) {
        ArrayList<Integer> path = new ArrayList<Integer>();
        path.add(m.startSpace);
        path.addAll(m.midSpaces);
        path.add(m.endSpace);

        return path;
    }

    public void printMove() {
        String output = Integer.toString(startSpace) + " -> ";

        if (midSpaces.size() > 0) {
            for (int space : midSpaces) {
                output = output + Integer.toString(space) + " -> ";
            }
        }

        output += Integer.toString(endSpace);

        System.out.println(output);
    }
}
