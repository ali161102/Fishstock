import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class move_generator {

    static int[][] numSpacesToEdge;
    static int[] directionOffsets = precomputed.directionOffsets;

    public static ArrayList<Integer> generateMoves(board b) {

        precomputed precalc = new precomputed();

        numSpacesToEdge = precomputed.numSpacesToEdge;
        //int[] directionOffsets = precomputed.directionOffsets;


        //System.out.println(Arrays.toString(precomputed.numSpacesToEdge[67]));

        ArrayList<move> moves = new ArrayList<move>();

        ArrayList<int[]> endpoints = new ArrayList<int[]>();

        ArrayList<Integer> reachable = new ArrayList<Integer>();

        Queue<Integer> queue = new LinkedList<Integer>();

        
        // for each marble's space index:
        for (int index : b.marbles) {

            queue.add(index);
            System.out.println("queue = " + queue);
            int original = index;

            while(!queue.isEmpty()) {

                int current = queue.remove();
                System.out.println("");
                System.out.println("current set to " + current);

                System.out.println("current's spacestoedge " + Arrays.toString(numSpacesToEdge[current]));

                /* do a bfs from position to find all possible ending spaces */
                for (int dir = 0; dir < 6; dir++) {
                // in each direction, 
                        
                        int closest = -1;

                        System.out.println("direction = " + dir);

                        for (int dist = 1; dist < ((numSpacesToEdge[current][dir])/2)+1; dist++) {
                        // from 1 space away to <distToEdge//2>, look for the next closest marble

                            int check = current + (dist * directionOffsets[dir]);
                            System.out.println("dist = " + dist + ", so check = " + check);
                            
                            if (b.space[check] > 0) {
                                // there is a marble in the space
                                closest = dist;
                                System.out.println("the closest marble is in space " + check);

                                break;


                            }
                        }
                        
                        boolean canjump = true;
                        int landing = -1;

                        // if there is one:
                        if (closest != -1) {
                            
                            System.out.println("check all spaces to landing space");
                            // check if all spaces from there to the potental landing space are free

                            for (int dist = closest+1; dist < closest*2+1; dist++) {

                                int check = current + (dist * directionOffsets[dir]);
                                landing = check;
                                System.out.println("checking jump " + current + " -- " + (current + closest*directionOffsets[dir]) + " --> " + landing);

                                if (b.space[check] > 0) {
                                    System.out.println("there is a marble in " + check + ", so cannot do the jump");
                                    // there is a marble in the space, so cannot do the jump
                                    canjump = false;
                                    break;
                                }
                            }
                        }

                        if (canjump && landing != -1) {
                            System.out.println("!! adding the jump " + current + " ----> " + landing);

                            if (!reachable.contains(landing)) {
                                // prevents 'loop' moves

                                queue.add(landing);
                                reachable.add(landing);
                                endpoints.add(new int[]{original, current, landing});
                                System.out.println("added " + landing + " from " + current + " possibly from " + original);

                            }
                            //queue.add(landing);
                            
                            
                            
                        }
                            
                            // if all spaces are clear:
                                // add landing space to queue to be processed
                }
            }

          // return all such spaces found
        }
          System.out.println(reachable);

          for (int[] move : endpoints) {
            System.out.println(Arrays.toString(move));
          }
          //System.out.println(endpoints);
          return reachable;
    }


    public static void main(String[] args) {

        int[] startlocations = {4, 21, 22, 38, 39, 40, 72, 56, 57, 58}; 
        board b = new board(startlocations.length);
        b.initialise(startlocations);

        generateMoves(b);

        
    }
}