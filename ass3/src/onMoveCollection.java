import biuoop.DrawSurface;

import java.util.ArrayList;

public class onMoveCollection {
    private ArrayList<onMovement> moves;
    public onMoveCollection() {
        moves = new ArrayList<>();
    }

    public onMoveCollection(ArrayList<onMovement> moves) {
        this.moves = moves;
    }
    public void addMove(onMovement m) {
        moves.add(m);
    }
}
