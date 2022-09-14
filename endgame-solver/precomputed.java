import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class precomputed {
    
    public static ArrayList<Integer> valid_indices;

    public static int[] directionOffsets = {17, 18, -1, 1, -18, -17}; 
    //        change to indice when moving: UL  UR  L   R   DL   DR
    //                        array indice: 0   1   2   3   4    5

    public static int[][] numSpacesToEdge; // in form [space_index][direction]

    public precomputed() {

        /* builds the array of indices that are actually board positions */
        valid_indices = new ArrayList<Integer>();

        int[] middle_starts = {4, 21, 38, 55, 68, 86, 104, 122, 140, 157, 174, 191, 208, 230, 248, 266, 284};
        int[] middle_ends   = {4, 22, 40, 58, 80, 97, 114, 131, 148, 166, 184, 202, 220, 233, 250, 267, 284};

        for (int i = 0; i < middle_starts.length; i++) {
            IntStream.range(middle_starts[i], middle_ends[i]+1).forEach(n -> valid_indices.add(n));
        }    


        numSpacesToEdge = new int[285][6];

        for (int space : valid_indices) {

            // the x and y co-ordinates of the space
            int x = space%17;
            int y = (int) Math.floor(space/17);
            int d = y - x; // difference between y and x

            /*
             * Calculate distance to edge in all 6 directions, based on x and y
             */

            // UL [0] and DR [5] directions
            if (x < 4 || (9 <= x && x <= 12)) {
                numSpacesToEdge[space][0] = x - y + 4;
                numSpacesToEdge[space][5] = y - 4;     
            } else {
                numSpacesToEdge[space][0] = 12 - y;
                numSpacesToEdge[space][5] = y - x + 4;
            }

            // UR [1] and DL [4] directions
            if (-4 <= d && d <= -1) {
                numSpacesToEdge[space][1] = 12 - y;
                numSpacesToEdge[space][4] = x - 4;
            } else {
                if (0 <= d && d <= 4) {
                    numSpacesToEdge[space][1] = 12 - x;
                    numSpacesToEdge[space][4] = y - 4;
                } else {
                    if (d < -4) {
                        numSpacesToEdge[space][1] = 12 - x;
                        numSpacesToEdge[space][4] = x - 4;
                    } else {
                        numSpacesToEdge[space][1] = 12 - y;
                        numSpacesToEdge[space][4] = y - 4;
                    } 
                }
            }

            // L [2] and R [3] directions
            if (y < 4 || (9 <= y && y <= 12)) {
                numSpacesToEdge[space][2] = x - 4;
                numSpacesToEdge[space][3] = y - x + 4;
            } else {
                numSpacesToEdge[space][2] = x - y + 4;
                numSpacesToEdge[space][3] = 12 - x;
            }            
        }


    }
}
