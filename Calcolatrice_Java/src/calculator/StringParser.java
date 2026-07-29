/***V4***/
package calculator;
/*
* A common Recursive Descent String Parser
*/
class StringParser {
    //Attributes
    private final String input;
    private int pos = -1;
    protected char ch;
    
    //Costructor
    @SuppressWarnings("unused")
    StringParser(String input) {    this.input = input; nextChar(); }
    protected void nextChar() {   ch = (++pos < input.length()) ? input.charAt(pos) : '\0';   }
    protected char peek(){  return ch;  }
    protected boolean isAtEnd(){    return ch == '\0';  }
    
    //Check if the pointed char is c (jumping space " ") -> true: point next char
    protected boolean eat(char c) {
        //it shouldn't be needed
        //while (ch == ' ') nextChar(); //too specific
        //while (ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r') nextChar(); //not universal
        while (Character.isWhitespace(ch)) nextChar();  //the most generic
        if (ch == c) {  nextChar(); return true;    }
        return false;
    }
}
