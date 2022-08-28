import java.util.ArrayList;

class move_gen {
    public int[] directionOffsets = {17, 18, -1, 1, -18, -17}; 
    // change to indice when moving: UL  UR   L  R   DL   DR

    public int[][] numSpacesToEdge;

    public void calcSpacesToEdge() {
        // TODO: spaces to edge calculation
    }

    public ArrayList<move> generateMoves() {
        ArrayList<move> moves = new ArrayList<move>();

        // TODO: generating moves function


        //// outline:
        // for each of the current player's marbles:
          // bfs from position to find all possible ending spaces
          // (in each direction, from 1 space away to <distToEdge//2>, look for the next closest marble, 
          // then check if all spaces from there to the potental landing space are free)
          // if so: add landing space to queue of spaces to be searched from
          // return all such spaces found
    }


}