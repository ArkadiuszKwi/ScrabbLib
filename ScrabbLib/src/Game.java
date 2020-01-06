
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game
{
    private final int size = 15;
    private final String language;
    private int turnForPlayer;
    private char[][] board;
    private ArrayList<Move> history;
    private ArrayList<Player> playerInfo;
    private List<Character> setOfTiles;
    
    public Game() { this("EN", 2); }
    public Game(String lang, int players) {
        this.language = lang;
        this.turnForPlayer = 0;
        this.board = createNewBoard();
        this.history = new ArrayList<>();
        this.playerInfo = new ArrayList<>();
        this.setOfTiles = generateTiles();
        for (int i = 0; i < players; i++)
            this.playerInfo.add(new Player(takeTiles("")));
    }
    public Game(Game game) {
        this.language = game.language;
        this.board = game.board;
        this.history = game.history;
        this.playerInfo = game.playerInfo;
        this.setOfTiles = game.setOfTiles;
    }
    
    private int getPlayersCount() { return this.playerInfo.size(); }
    
    public List<Character> extractor(String set) {
        List<Character> tiles = new ArrayList<>();
        Pattern p1 = Pattern.compile("\\d+"), p2 = Pattern.compile("\\D+");
        Matcher m1 = p1.matcher(set), m2 = p2.matcher(set);
        List<Integer> t1 = new ArrayList<>();
        List<Character> t2 = new ArrayList<>();
        while (m1.find()) t1.add(Integer.parseInt(m1.group()));
        while (m2.find()) t2.add(m2.group().charAt(0));
        for (int i = 0; i < t1.size(); i++)
            for (int j = 0; j < t1.get(i); j++)
                tiles.add(t2.get(i));
        return tiles;
    }
    
    public List<Character> generateTiles() {
        return extractor(this.language.equals("EN") ?
            "12E9A9I8O6N6R6T4L4S4U4D3G2B2C2M2P2F2H2V2W2Y1K1J1X1Q1Z" :
            "9A8I7E6O5N5Z4R4W4Y3C3D3K3L3M3P3T2B2G2H2J2Ł2U1Ą1Ę1F1O1S1Z1Ć1Ń1Ź");
    }
    
    public String takeTiles(String rack) {
        int remain = 7 - rack.length();
        for (int i = 0; i < remain; i++)
        {
            if (this.setOfTiles.isEmpty()) return rack;
            int index = (int)(Math.random() * this.setOfTiles.size());
            rack += this.setOfTiles.get(index);
            this.setOfTiles.remove(index);
        }
        return rack;
    }
    
    private char[][] createNewBoard() {
        char[][] newBoard = new char[this.size][this.size];
        for (int i = 0; i < this.size; i++)
            for (int j = 0; j < this.size; j++)
                newBoard[i][j] = '-';
        return newBoard;
    }
    
    // Public
    
    public void displayBoard() {
    }
    
    public void displayPlayersInfo() {
    }
    
    public boolean makeMove(Move move)
    {
        return true;
    }
}

