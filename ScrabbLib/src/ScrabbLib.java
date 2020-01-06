
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScrabbLib
{
    private String language;
    public enum sortMode {Score, Length};
    private List<String> dictionary = new ArrayList<>();

    private final Map<Character, Integer> engValues = new HashMap<Character, Integer>() {{
        put('A', 1); put('B', 3); put('C', 3); put('D', 2); put('E', 1);
        put('F', 4); put('G', 2); put('H', 4); put('I', 1); put('J', 8);
        put('K', 5); put('L', 1); put('M', 3); put('N', 1); put('O', 1);
        put('P', 3); put('Q', 10); put('R', 1); put('S', 1); put('T', 1);
        put('U', 1); put('V', 4); put('W', 4); put('X', 8); put('Y', 4);
        put('Z', 10);
    }};
    private final Map<Character, Integer> plValues = new HashMap<Character, Integer>() {{
        put('A', 1); put('Ą', 5); put('B', 3); put('C', 2); put('Ć', 6);
        put('D', 2); put('E', 1); put('Ę', 5); put('F', 5); put('G', 3);
        put('H', 3); put('I', 8); put('J', 3); put('K', 2); put('L', 2);
        put('Ł', 3); put('M', 2); put('N', 1); put('Ń', 7); put('O', 1);
        put('Ó', 5); put('P', 2); put('R', 1); put('S', 1); put('Ś', 5);
        put('T', 2); put('U', 3); put('W', 1); put('Y', 2); put('Z', 1);
        put('Ź', 9); put('Ż', 5);
    }};
    private final Map<Position, String> bonuses = new HashMap<Position, String>() {{
        put(new Position(0, 0), "TW"); put(new Position(3, 0), "DL"); put(new Position(7, 0), "TW");
        put(new Position(11, 0), "DL"); put(new Position(14, 0), "TW"); put(new Position(1, 1), "DW");
        put(new Position(5, 1), "TL"); put(new Position(9, 1), "TL"); put(new Position(13, 1), "DW");
        put(new Position(2, 2), "DW"); put(new Position(6, 2), "TL"); put(new Position(8, 2), "TL");
        put(new Position(12, 2), "DW"); put(new Position(0, 3), "DL"); //tbc
    }};
    
    public ScrabbLib() { this("EN"); }
    public ScrabbLib(String lang) { this.language = lang; }
    
    // Public
    
    public void initDictionary() throws Exception {
        try {
            File f = new File("dictionaries/" + (this.language.equals("EN") ? "en.txt" : "pl.txt"));
            dictionary = Files.readAllLines(f.toPath(), StandardCharsets.UTF_8);
        } catch (Exception ex) {
            throw new Exception("The dictionary file could not be read!");
        }
    }

    public List<String> generateWords(String letters)
    {
        return generateWords(letters, ScrabbLib.sortMode.Score);
    }
    
    public List<String> generateWords(String letters, sortMode sort)
    {
        int blanks = (int)letters.chars().filter(c -> c == '-').count();
        List<String> results = dictionary.parallelStream().filter(word -> word.length() <= letters.length()
            && Utils.passWord(word, letters, blanks, 0)).collect(Collectors.toList());
        Collections.sort(results, (String a, String b) -> sort == sortMode.Length
            ? b.length() - a.length() : Integer.compare(score(b), score(a)));
        return results;
    }
}

