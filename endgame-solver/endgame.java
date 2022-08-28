import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class endgame {
    
    public static ArrayList<Integer> valid_indices;
    
    public static void setValidSpaces() {
        /* builds the array of indices that are actually board positions */
        valid_indices = new ArrayList<Integer>();

        int[] middle_starts = {4, 21, 38, 55, 68, 86, 104, 122, 140, 157, 174, 191, 208, 230, 248, 266, 284};
        int[] middle_ends   = {4, 22, 40, 58, 80, 97, 114, 131, 148, 166, 184, 202, 220, 233, 250, 267, 284};

        for (int i = 0; i < middle_starts.length; i++) {
            IntStream.range(middle_starts[i], middle_ends[i]+1).forEach(n -> valid_indices.add(n));
        }      
    }

    public static int distance(int space) {
        int x = space%17;
        int y = (int) Math.floor(space/17);
        // the x and y co-ordinates of the space

        if (x < 4) { // in the bottom-left set of starting spaces
            return (y + (4 - x));
        } else {
            if (x - y > 4) { // in the bottom-right set of starting spaces
                return (x - 4);
            } else {
                return y; // in the main area where just vertical distance can be used
            }
        }

    }

    public static int total_distance(int[] marbles) {
        int total = -20; // minimum possible distance is 20
        for (int marble : marbles) {
            total += distance(marble);
        }
        return total;
    }

    public static void main(String[] args) {
        setValidSpaces();

        int[] testmarbles = {4, 21, 22, 38, 39, 55, 57, 58, 90, 92};
        
        System.out.println(valid_indices);       
        System.out.println(valid_indices.size()); 

    }

}
