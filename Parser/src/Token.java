public class Token {

    private static final int s0 = 0;
    private static final int s1 = 1;
    private static final int s2 = 2;
    private static final int s3 = 3;
    private static final int s4 = 4;
    private static final int s5 = 5;
    private static final int s6 = 6;
    private static final int s7 = 7;
    private static final int s8 = 8;
    private static final int dead = -1;

    private static int state = s0;

    enum TokenType{
        ERROR,
        LPAREN,
        RPAREN,
        QUESTION,
        UNION,
        ALPHANUM,
        PLUS,
        STAR,
        EPSILON,
    }

    private TokenType tokenType;
    private String lexeme;

    public Token(String word){
        this.tokenType = identifyToken(word);
        this.lexeme = word;
    }


    public static boolean checkIfToken(String str){
        for (TokenType type: TokenType.values()) {
            if(type.toString().equals(str))
                return true;
        }
        return false;
    }

    public static void initialize() {
        state = s0;
    }

    private static TokenType identifyToken(String word){
        initialize();
        for (int i = 0; i < word.length(); i++) {
            char input = word.charAt(i);
            state = transition(state, input);
        }
        switch(state){
            case s1: return TokenType.STAR;
            case s2: return TokenType.PLUS;
            case s3: return TokenType.UNION;
            case s4: return TokenType.QUESTION;
            case s5: return TokenType.LPAREN;
            case s6: return TokenType.RPAREN;
            case s7: return TokenType.EPSILON;
            case s8: return TokenType.ALPHANUM;
            default:{
                return TokenType.ERROR;
            }
        }
    }

    static private int transition(int state, char input) {
        switch (state) {
            case s0: switch (input) {
                case '*': return s1;
                case '+': return s2;
                case 'U': return s3;
                case '?': return s4;
                case '(': return s5;
                case ')': return s6;
                case 'E': return s7;
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z': return s8;
                default: return dead;
            }
            default: return dead;
        }
    }

    public String getTokenType() {
        return tokenType.toString();
    }

    public String getLexeme() {
        return lexeme;
    }

    @Override
    public String toString() {
        if(this.tokenType == TokenType.ALPHANUM)
            return this.tokenType.toString();
        else
            return this.lexeme;
    }
}
