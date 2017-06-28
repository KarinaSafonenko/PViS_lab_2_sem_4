package by.safonenko.bsuir.ppvis;
import  javax.swing.SwingUtilities;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import by.safonenko.bsuir.ppvis.view.MainWindow;

/**
 * Created by Admin on 30.03.2017.
 */
public class AppStarter {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

              new MainWindow();

            }

        });
    }
}
