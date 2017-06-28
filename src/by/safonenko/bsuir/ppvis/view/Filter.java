package by.safonenko.bsuir.ppvis.view;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by Admin on 17.04.2017.
 */
public class Filter extends FileFilter {
    private String ex;

    public Filter(String ex) {
        this.ex = ex;
    }

    public boolean accept(File file) {
        if (file.isDirectory()) return true;
        return (file.getName().endsWith(ex));
    }

    public String getDescription() {
        return "*" + ex;
    }
}
