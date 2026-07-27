/***V4***/
package calculator;

import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import javax.swing.*;
import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/*
*   A functional Swing-based calculator with support for keyboard input, basic arithmetic, parentheses, and error handling.
*   KMAP and KMAP_SYMBOLS to map buttons and key bindings.
*   Using JRootPane + InputMap/ActionMap allows global key input without focus on the text field
*
*   MathEngine.eval:
*       parses parentheses recursively.
*       tokenize splits numbers and operations.
*       tokenMergeSign handles unary + and -.
*       TokenDoMul handles * and / first.
*       do algebraic sum
*
* Note: String as X//Y -> X*Y it's wanted: X//Y -> thinked as a fraction X/(1/Y) -> X*Y.
*       the case without is in comment
*/

//try adding a dark/light theme toggle or simple styling tweaks to make it visually appealing
public class CalcolatriceGUI{

    //GUI Element
    private final JFrame     frame;         //Principal Window
    private final JPanel     keyboardPanel;         //Keyboard Panel
    private final JButton    buttons[];  //Single JButtons array to manage all button in a single cicle
    private final JTextField display;    //to see input and result
    private final JPanel displayPanel;
    private final JButton themeToggle;

    //to take keybord input when focus is on frame -> global input
    private final JRootPane root;
    private final InputMap inputMap;
    private final ActionMap actionMap;

    //GUI options
    final static int DIMX = 350;
    final static int DIMY = 500;
    final static Dimension PRINCIPAL_DIM = new Dimension(DIMX, DIMY);
    final static String[] KMAP = {
        "(", ")", "C", "←",
        "1", "2", "3", "+",
        "4", "5", "6", "-",
        "7", "8", "9", "*",
        "0", ".", "=", "/"
        };
    final static String KMAP_SYMBOLS = String.join("", KMAP);
    final static int NBUT = KMAP.length;
    final static int KMAP_ROWS = 5;
    final static int KMAP_COLS = 4;
    final static int HGAP = 15;
    final static int VGAP = 15;
    final static GridLayout KEYS_LAYOUT = new GridLayout(KMAP_ROWS, KMAP_COLS, HGAP, VGAP);
    final static Font B_FONT = new Font("Arial", Font.BOLD, 20);
    final static Font T_FONT = new Font("Arial", Font.BOLD, 30);

    //Costructor GUI
    public CalcolatriceGUI() {

        //GUI's element initialization 
        frame         = new JFrame("Calcolatrice - "+ (Theme.isDark() ? "Dark" : "Light"));
        keyboardPanel = new JPanel(KEYS_LAYOUT);
        display       = new JTextField();
        buttons       = new JButton[NBUT];
        displayPanel  = new JPanel(new BorderLayout());
        themeToggle   = new JButton(Theme.isDark() ? "🌞" : "🌙");

        //Setup
        //display
        display.setFont      (T_FONT);
        display.setFocusable (false);
        display.setEditable  (false);
        display.setMargin    (new Insets(5,5,5,5));

        //displayPanel
        displayPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        displayPanel.add      (display, BorderLayout.CENTER);
        displayPanel.add      (themeToggle, BorderLayout.EAST);

        //Buttons
        themeToggle.setFocusPainted(false);
        themeToggle.addActionListener(e -> {
            Theme.toggleTheme();
            themeToggle.setText(Theme.isDark() ? "🌞" : "🌙");
            applyTheme();
            });
        for(int count=0; count < NBUT; count++){
            buttons[count] = new JButton(KMAP[count]);
            buttons[count].setFont(B_FONT);
            buttons[count].setFocusPainted(false);
            //buttons[count].addActionListener((ActionEvent e) -> {   inputManager(((JButton)e.getSource()).getText());   });
            String txt = KMAP[count];
            buttons[count].addActionListener(e -> inputManager(txt));
            keyboardPanel.add(buttons[count]);
        }

        //Principal windows
        frame.setLayout(new BorderLayout());   //default
        frame.add          (displayPanel, BorderLayout.PAGE_START);
        frame.add          (keyboardPanel, BorderLayout.CENTER);
        frame.setSize      (PRINCIPAL_DIM);
        frame.setResizable (false);
        frame.setVisible   (true);
        frame.setLocationRelativeTo(null);  //center the window
        frame.requestFocusInWindow();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Key bindings (input global keyboard)
        root       = frame.getRootPane();
        inputMap   = root.getInputMap (WHEN_IN_FOCUSED_WINDOW);
        actionMap  = root.getActionMap();
        //adding KMAP_SYMBOLS to InputMap to ignore other input, keys are like K_0, k_1, ...
        //and adding keys to ActionMap to know what to do (normally used for shortcut)
        for (char c : KMAP_SYMBOLS.toCharArray()) {
            String key = "k_" + c;
            inputMap.put   (KeyStroke.getKeyStroke(c), key);
            actionMap.put  (key, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {    inputManager(String.valueOf(c));    }
            });
        }

        // adding ENTER to MathEngine.eval
        inputMap.put (KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
        actionMap.put("enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {    inputManager("=");    }
        });

        // adding BACKSPACE to eraze last added element
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "back");
        actionMap.put("back", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {    inputManager("←");  }
        });

        applyTheme();
    }    //Constructor end

    private void applyTheme(){
        frame.setTitle(("Calcolatrice - "+ (Theme.isDark() ? "Dark" : "Light")));

        //Background
        displayPanel.setBackground (Theme.get(UIElement.BACKGROUND));
        keyboardPanel.setBackground(Theme.get(UIElement.BACKGROUND));

        //Display
        display.setBackground(Theme.get(UIElement.DISPLAY_BG));
        display.setForeground(Theme.get(UIElement.DISPLAY_FG));

        //Buttons
        for (JButton b : buttons){
            b.setBackground(Theme.get(UIElement.BUTTON_BG));
            b.setForeground(Theme.get(UIElement.BUTTON_FG));
        }
        themeToggle.setBackground(Theme.get(UIElement.BUTTON_BG));
        themeToggle.setForeground(Theme.get(UIElement.BUTTON_FG));

        //Refresh
        SwingUtilities.updateComponentTreeUI(frame);
    }

    //update the TextField(ex) with input
    private void inputManager(String inString) {
        String txt = display.getText();
        switch (inString){
            case "C" -> {   // "C" -> Clear  adding  Esc, Del, Canc
                display.setForeground(Theme.get(UIElement.DISPLAY_FG));
                txt = ""; 
            }
            case "=" -> {
                double tmp = MathEngine.eval(txt);
                if(Double.isNaN(tmp)){
                    display.setForeground(Color.RED);
                    txt = "Error";
                }
                else {
                    display.setForeground(Theme.get(UIElement.DISPLAY_FG));
                    txt = String.format(Locale.US, "%.8f", tmp).replaceAll("\\.?0+$", "");
                }
            }
            case "←" -> {   if(!txt.isEmpty())  txt = txt.substring(0, txt.length() -1);    }
            default  -> {
                if(txt.isEmpty()) display.setForeground(Theme.get(UIElement.DISPLAY_FG));
                txt += inString;
            }
        }
        display.setText(txt);
    }
}

enum UIElement {    BACKGROUND, DISPLAY_BG, DISPLAY_FG, BUTTON_BG,  BUTTON_FG   }

class Theme{
    /* Can use an enum themes changing darkMode to int:
    * togleTheme -> darkMode = ++darkMode%themes.size();
    * isDark() -> themeName(){ return themes[darkMode];}
    * get() -> use a switch and improve method for each theme
    */
    private static final Color DARK_BG = new Color(30,30,30);//DARK DARK GRAY
    private static boolean darkMode = true;
    
    public static void toggleTheme() {  darkMode = !darkMode;   }

    public static boolean isDark()  {   return darkMode;    }

    public static Color get(UIElement el) { return darkMode ? dark(el) : light(el); }

    private static Color dark (UIElement el){
        return switch (el) {
            case BACKGROUND -> DARK_BG;
            case DISPLAY_BG -> new Color(20, 20, 20);   //CHINESE BLACK
            case DISPLAY_FG -> Color.WHITE;
            case BUTTON_BG  -> new Color(60, 60, 60);    //MEDIUM DARK GRAY
            case BUTTON_FG  -> Color.WHITE;
        };
    }

    private static Color light (UIElement el){
        return switch (el) {
            case BACKGROUND -> Color.LIGHT_GRAY;
            case DISPLAY_BG -> Color.WHITE;
            case DISPLAY_FG -> Color.BLACK;
            case BUTTON_BG  -> new Color(220, 220, 220); //LIGHT LIGHT GRAY
            case BUTTON_FG  -> Color.BLACK;
        };
    }
}
