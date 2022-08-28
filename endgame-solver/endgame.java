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

    public static int distance(int square) {
        ArrayList<Integer> special = new ArrayList<>(Arrays.asList(68, 69, 70, 71, 86, 87, 88, 77, 78, 79, 80, 95, 96, 97, 113, 114, 131));
        ArrayList<Integer> sevens = new ArrayList<>(Arrays.asList(69, 87, 105, 79, 96, 113));

        if (!special.contains(square)) {
            return (int) (Math.floor(square/17));
        } else {
            if (square == 71 || square == 77) {
                return 5;
            } else {
                if (square == 70 || square == 88 || square == 78 || square == 95) {
                    return 6;
                } else {
                    if (sevens.contains(square)) {
                        return 7;
                    } else {
                        return 8;
                    }
                }
            }
        }
    }

    public static int total_distance(int[] marbles) {
        int total = -20;
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

        // for (int space : valid_indices) {
        //     System.out.println(space + " " + distance(space));
        // }

        System.out.println(total_distance(testmarbles));


    }

}
