public class board {
    public int[] space;
    public int[] marbles;

    public board(int nmarbles) {
        this.space = new int[285];
        this.marbles = new int[nmarbles];
    }

    public void initialise(int[] startlocations) {
        this.marbles = startlocations;
        for (int index : startlocations) {
            space[index] = 1;
        } 
    }

}