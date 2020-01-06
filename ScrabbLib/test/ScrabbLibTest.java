
import java.io.PrintStream;
import java.util.List;
import org.junit.Test;

public class ScrabbLibTest {
    
    public ScrabbLibTest() {}
    
    @Test
    public void testScrabbLib() throws Exception
    {
        ScrabbLib s = new ScrabbLib("PL");
        try {
            s.initDictionary();
        } catch (Exception ex) {
            throw new Exception("Could not load dictionary!");
        }
        long start = System.currentTimeMillis();
        List<String> list = s.generateWords("E-ŚRAIN").subList(0, 10);
        long timeElapsed = System.currentTimeMillis() - start;
        System.out.println("Generated words in: " + timeElapsed + " ms");
        PrintStream out = new PrintStream(System.out, true, "UTF-8");
        list.stream().forEach((word) -> { out.println(word); });
        
        Game g = new Game();
        g.displayBoard();
        g.displayPlayersInfo();
        Move m = new Move(new Position(7, 7), Move.direction.Horizontal, "TEST");
        g.makeMove(m);
        g.displayBoard();
        g.displayPlayersInfo();
    }
}

