package ff.main.log;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import javax.swing.SwingUtilities;

public class RunGUI implements Runnable {
    protected GUI l;
    private static Logger Logger = org.apache.log4j.Logger.getLogger(RunGUI.class.getName());
    public void run() {
        l = new GUI();
    }
    public static void main(String[] args) {
    	PropertyConfigurator.configure("conf/log4j.properties");
        SwingUtilities.invokeLater(new RunGUI());
    }
}
