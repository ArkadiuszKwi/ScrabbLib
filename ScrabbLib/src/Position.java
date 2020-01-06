
import java.util.ArrayList;

public class Position
{
    private final int x;
    private final int y;
    
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() { return this.x; }
    public int getY() { return this.y; }
    
    @Override
    public Position clone() {
        return new Position(this.x, this.y);
    }
    
    public static ArrayList<Position> getPositions(Move move) {
        ArrayList<Position> positions = new ArrayList<>();
        Position start = move.getPos();
        positions.add(start.clone());
        Move.direction dir = move.getDir();
        for (int i = 1; i < move.getWord().length(); i++)
            positions.add(new Position(start.x + (dir == Move.direction.Horizontal ?
                i : 0), start.y + (dir == Move.direction.Vertical ? i : 0)));
        return positions;
    }
}

