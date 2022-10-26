import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

class move_generator {

    static int[][] numSpacesToEdge;
    static int[] directionOffsets = precomputed.directionOffsets;
    //public static board b;

    public static ArrayList<move> generateMoves(board b) {

        precomputed precalc = new precomputed();

        numSpacesToEdge = precomputed.numSpacesToEdge;
  
        ArrayList<move> moves = new ArrayList<move>();
        ArrayList<Integer> reachable = new ArrayList<Integer>();
        HashMap<Integer, Integer> predecessors = new HashMap<Integer, Integer>();

        Queue<Integer> queue = new LinkedList<Integer>();

        // for each marble's space index:
        for (int index : b.marbles) {

            // reset these for each marble
            reachable.clear();
            predecessors.clear(); 

            /* do a bfs from position to find all possible ending spaces */
            queue.add(index);
            while(!queue.isEmpty()) {
                int current = queue.remove();


                for (int dir = 0; dir < 6; dir++) { // in each direction, 
                
                        int closest = -1;

                        for (int dist = 1; dist < ((numSpacesToEdge[current][dir])/2)+1; dist++) { // look for the next closest marble that could be a valid jump
                        
                            int check = current + (dist * directionOffsets[dir]); // get index of space to check by adding offset

                            if ((b.space[check] > 0) && (check != index)) {
                                closest = dist; // marble found in this direction
                                break;
                            }
                        }
                        
                        boolean canjump = true;
                        int landing = -1;

                        // if there is one:
                        if (closest != -1) {
                            
                            // check if all spaces from there to the potental landing space are free
                            for (int dist = closest+1; dist < closest*2+1; dist++) {

                                int check = current + (dist * directionOffsets[dir]);
                                landing = check;

                                if (b.space[check] > 0) {
                                    // there is a marble in the space, so cannot do the jump
                                    canjump = false;
                                    break;
                                }
                            }
                        }

                        if (canjump && landing != -1) { // all spaces are clear so jump is possible

                            if (!reachable.contains(landing)) { //prevents 'loop' moves
                                
                                queue.add(landing); // jumps can continue onwards so add to queue to be processed
                                reachable.add(landing);
                  
                                predecessors.put(landing, current); // current -> landing, used to work out the move paths

                                queue.add(landing);
                            }
                            
                        }
                            
                }
            }

            /* determine the path to each reachable space */
            for (int start : reachable) {
                ArrayList<Integer> path = new ArrayList<Integer>();

                int endpointer = predecessors.get(start);
                // work backwards from the end space by getting the predecessor, until at the starting space
                while (endpointer != index) { 
                    path.add(endpointer);
                    endpointer = predecessors.get(endpointer);
                }
                Collections.reverse(path); // as the path was backwards

                move thismove = new move(endpointer, start, path);
                moves.add(thismove);

            }
        }
          return moves;
    }


    public static void main(String[] args) {
        int[] startlocations = {4, 38, 40, 56, 57, 58, 74, 75, 91, 105, 126, 129, 177, 180, 198, 214, 217, 230, 233, 266};
        board b = new board(startlocations.length);
        b.initialise(startlocations);

        System.out.println("---");
        //visit(38);
        ArrayList<move> possiblemoves = generateMoves(b);

        for (move mv : possiblemoves) {
            //System.out.println(move.getPath(mv));
            mv.printMove();
        }
    }
}