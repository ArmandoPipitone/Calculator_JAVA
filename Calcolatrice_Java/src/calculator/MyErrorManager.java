/***V4***/
package calculator;

import static javax.swing.JOptionPane.showMessageDialog;

/*
* Show a popup dialog windows
*/

//Error Manager
public class MyErrorManager {
    static final int OPSEQERR       = 1;
    static final int DIV0ERR        = 2;
    static final int BRACKETERR     = 3;
    static final int NUMFORMERR     = 4;
    static final int BLANKEXPRERR   = 5;
    static final int MISSNUM        = 6;

    static void showErr(int code){
        String message = switch (code){
            case OPSEQERR -> "Operator sequence '-*', '-/', '/*' unsupported";
            case DIV0ERR -> "Division by 0";
            case BRACKETERR -> "Bracket Error";
            case NUMFORMERR -> "Invalid Number";
            case BLANKEXPRERR -> "Invalid empty expression";
            case MISSNUM -> "Missing a number";
            default -> "Unknown";
        };
        showMessageDialog(null, "Error:\n" + message);
    }
}
