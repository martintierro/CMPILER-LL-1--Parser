import java.util.*;

public class Parser {
    private Stack<String> stack = new Stack<>();

    public String parse(ArrayList<Token> tokens){
        this.stack.clear();
        this.stack.push("$"); //push ending symbol
        this.stack.push("S"); //push starting symbol
        int token_pointer = 0;
        tokens.add(new Token("$"));
        while(!stack.isEmpty() && token_pointer < tokens.size()){
            if(tokens.get(token_pointer).getTokenType().equals("ERROR"))
                return "REJECT";
            else if(ParsingTable.getInstance().isProduction(stack.peek())){
                String production = ParsingTable.getInstance().getProduction(stack.peek(), tokens.get(token_pointer).getTokenType());
                if(production != null)
                    this.expand(production);
                else
                    return "REJECT";
            } else if(stack.peek().equals("")){
                stack.pop();
            } else if(stack.peek().equals("$")){
                if(tokens.get(token_pointer).getLexeme().equals("$")){
                    stack.pop();
                    token_pointer++;
                }
                else{
                    return "REJECT";
                }
            } else{
                if(stack.peek().equals(tokens.get(token_pointer).getTokenType())){
                    stack.pop();
                    token_pointer++;
                } else{
                    return "REJECT";
                }
            }
            System.out.println(stack);
            System.out.println(token_pointer);
        }
        if(stack.isEmpty() && token_pointer >= tokens.size())
            return "ACCEPT";
        else
            return "REJECT";

    }

    public void expand(String production){
        if(stack.isEmpty()){
            return;
        }
        stack.pop();
        String[] temp = production.split(" ");
        for(int i = temp.length-1; i >= 0; i--){
            this.stack.push(temp[i]);
        }
    }
}