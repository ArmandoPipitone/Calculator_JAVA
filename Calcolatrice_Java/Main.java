import calculator.CalcolatriceGUI;
import java.util.Locale;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        SwingUtilities.invokeLater(() -> new CalcolatriceGUI());
    }
    
    public static void launch(){
        @SuppressWarnings("unused")
        CalcolatriceGUI app = new CalcolatriceGUI();
    }
}