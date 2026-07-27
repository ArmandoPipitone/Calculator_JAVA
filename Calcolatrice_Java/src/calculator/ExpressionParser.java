/***V4***/
package calculator;
/*
* A Specific String Parser -> MathString
*   Specific MathString Parser elements
*   Grammar:
*       expression = term (('+' | '-') term)*
*       term       = factor (('*' | '/') factor)*
*       factor     = number | '(' expression ')' | ('+'|'-') factor
*
*/
class ExpressionParser extends StringParser {

    public ExpressionParser(String input){  super(input);   }
    
    //start Parsing
    @SuppressWarnings("unused")
    double parse() {
        double result = parseExpression();
        //if (pos < input.length()){
        if (!isAtEnd())
        {   MyErrorManager.showErr(MyErrorManager.BLANKEXPRERR);  return Double.NaN;    }
        return result;
    }

    private double parseExpression() {
        //  expression = term (('+' | '-') term)*
        double x = parseTerm();
        while (true) {
            if      (eat('+'))  x += parseTerm();
            else if (eat('-'))  x -= parseTerm();
            else return x;  //exit
        }
    }
    
    private double parseTerm() {
        //  term       = factor (('*' | '/') factor)*
        double x = parseFactor();
        while (true) {
            if (eat('*')) x *= parseFactor();
            else if (eat('/')) {
                double d = parseFactor();
                if (d == 0) {   MyErrorManager.showErr(MyErrorManager.DIV0ERR); return Double.NaN;    }
                x /= d;
            }
            else if (ch == '(' || (ch >= '0' && ch <= '9') || ch == '.')    x *= parseFactor();     // Gestione moltiplicazione implicita
            else  return x;
        }
    }
    
    private double parseFactor() {
        //  factor = number | '(' expression ')' | ('+'|'-') factor
        //  factor = ('+'|'-') factor -> unary '+' '-'
        if (eat('+')) return  parseFactor();
        if (eat('-')) return -parseFactor();

        double x;
        if (eat('(')) {
        //  factor = '(' expression ')'
            x = parseExpression();
            if (!eat(')')) {    MyErrorManager.showErr(MyErrorManager.BRACKETERR);  return Double.NaN;    }
        }
        //  factor = number
        else if ((ch >= '0' && ch <= '9') || ch == '.') {
            StringBuilder sb = new StringBuilder();
            int dots = 0;
            while ((ch >= '0' && ch <= '9') || ch == '.') {
                if (ch == '.') dots++;
                if (dots > 1) { MyErrorManager.showErr(MyErrorManager.NUMFORMERR);  return Double.NaN;    }
                sb.append(peek());
                nextChar();
            }
            if(sb.length() == 0) {  MyErrorManager.showErr(MyErrorManager.MISSNUM);  return Double.NaN; }
            x = Double.parseDouble(sb.toString());
        }
        else {  MyErrorManager.showErr(0);  return Double.NaN;    }
        return x;
    }
}
