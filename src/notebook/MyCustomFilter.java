/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package notebook;

import java.io.File;

/**
 *
 * @author AYO
 */
public class MyCustomFilter extends javax.swing.filechooser.FileFilter {

    @Override
    public boolean accept(File f) {
        return f.isDirectory()|| f.getAbsolutePath().endsWith(".asm");
    }

    @Override
    public String getDescription() {
       return "Assembly Language Source File (*.asm)";
    }
  

}
