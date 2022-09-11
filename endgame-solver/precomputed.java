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


    public int[] directionOffsets = {17, 18, -1, 1, -18, -17}; 
    // change to indice when moving: UL  UR   L  R   DL   DR

    public int[][] numSpacesToEdge;

    public void calcSpacesToEdge() {
        numSpacesToEdge = new int[284][6];

        for (int space : valid_indices) {

            // the x and y co-ordinates of the space
            int x = space%17;
            int y = (int) Math.floor(space/17);
            
            /*
             * Logic for distance to edge in all 6 directions, based on x and y
             */

            /* UL */
            // if 4 <= x <= 8 || x > 12:  return (12 - y)
            // if x < 4 || 9 <= x <= 12: return (x - y + 4)

            /* UR */
            // if 0 <= y-x <= 4 || y-x < -4: return 12 - x
            // if -4 <= y-x <= -1 || y-x > 4 : return 12 - y

            /* L */
            // if y < 4 || 8 <= y <= 12: return (x - 4)
            // if 4 <= y <= 7 || y > 12: return (x - y + 4)

            /* R */
            // if 4 <= y <= 8 || y > 12: return (12 - x)
            // if y < 4 || 9 <= y <= 12: return (y - x + 4)

            /* DL */
            // if -4 <= y-x <= 0 || y-x < -4: return (x - 4)
            // if 1 <= y-x <= 4 || y-x > 4: return (y - 4)

            /* DR */
            // if 4 <= x <= 8 || x > 12: return (y - x + 4)
            // if x < 4 || 9 <= x <= 12: return (y - 4)
        }
        
    }

}
