/***V4***/
package calculator;

public class MathEngine {
    public static double eval(String expression) {
        return new ExpressionParser(expression).parse();
    }
}


/*
    //Logical Part -> really in Parser
    private static double evalMathExpress(String expression) {  return (new ExpressionParser(expression).parse());  }
        public static void main(String[] args) {
    //    Locale.setDefault(Locale.US); // int.dec
        try{@SuppressWarnings("unused")
                    Calcolatrice calc = new Calcolatrice();
        }catch (Exception e){   System.out.println(e);        }
    }
}
*/