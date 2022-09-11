import java.util.ArrayList;
import java.util.stream.IntStream;

public class precomputed {

    public static ArrayList<Integer> valid_indices;
    
    public static ArrayList<Integer> setValidSpaces() {
        /* builds the array of indices that are actually board positions */
        valid_indices = new ArrayList<Integer>();

        int[] middle_starts = {4, 21, 38, 55, 68, 86, 104, 122, 140, 157, 174, 191, 208, 230, 248, 266, 284};
        int[] middle_ends   = {4, 22, 40, 58, 80, 97, 114, 131, 148, 166, 184, 202, 220, 233, 250, 267, 284};

        for (int i = 0; i < middle_starts.length; i++) {
            IntStream.range(middle_starts[i], middle_ends[i]+1).forEach(n -> valid_indices.add(n));
        }      

        return valid_indices;
    }
    
}
