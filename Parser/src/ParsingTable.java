import java.util.HashMap;

public class ParsingTable {
    private HashMap<String, HashMap> table;
    private static ParsingTable instance = null;

    public static ParsingTable getInstance() {
        if (instance == null)
            instance = new ParsingTable();
        return instance;
    }

    private ParsingTable(){
        this.table = new HashMap<>();
        this.initialize();
    }

    private void initialize() {
        HashMap<String, String> S = new HashMap<>();
        S.put("EPSILON", "C N");
        S.put("LPAREN", "C N");
        S.put("ALPHANUM", "C N");

        HashMap<String, String> C = new HashMap<>();
        C.put("EPSILON", "EPSILON");
        C.put("LPAREN", "A");
        C.put("ALPHANUM", "A");

        HashMap<String, String> N = new HashMap<>();
        N.put("UNION", "UNION C N");
        N.put("LPAREN", "A N");
        N.put("RPAREN", "");
        N.put("ALPHANUM", "A N");
        N.put("END", "");

        HashMap<String, String> A = new HashMap<>();
        A.put("LPAREN", "P B");
        A.put("ALPHANUM", "P B");

        HashMap<String, String> P = new HashMap<>();
        P.put("LPAREN", "LPAREN S RPAREN");
        P.put("ALPHANUM", "ALPHANUM");

        HashMap<String, String> B = new HashMap<>();
        B.put("UNION", "");
        B.put("LPAREN", "");
        B.put("RPAREN", "");
        B.put("ALPHANUM", "");
        B.put("STAR", "STAR");
        B.put("PLUS", "PLUS");
        B.put("QUESTION", "QUESTION");
        B.put("END", "");

        table.put("S", S);
        table.put("C", C);
        table.put("N", N);
        table.put("A", A);
        table.put("P", P);
        table.put("B", B);
    }

    public String getProduction(String rule, String terminal){
        HashMap nonTerm = table.get(rule);
        return (String) nonTerm.get(terminal);
    }

    public boolean isProduction(String rule){
        HashMap temp = table.get(rule);
        return temp != null;
    }

}
