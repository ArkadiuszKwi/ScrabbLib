
public class Move
{
    public enum direction {Horizontal, Vertical};
    private final Position pos;
    private final direction dir;
    private final String word;
    
    public Move(Position pos, direction dir, String word) {
        this.pos = pos;
        this.dir = dir;
        this.word = word;
    }
    
    public Position getPos() { return this.pos; }
    public direction getDir() { return this.dir; }
    public String getWord() { return this.word; }
}

