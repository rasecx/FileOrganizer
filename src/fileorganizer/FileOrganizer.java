/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileorganizer;

import java.io.File;

/**
 *
 * @author jcferreira
 */
public class FileOrganizer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File dirRaiz = new File("D:\\TesteMus");

        for (File f : dirRaiz.listFiles()) {
            if (f.isDirectory()) {
                System.out.println(f.getAbsoluteFile());
                runSubDir(f);
            } else {
                Utils.printConsole(f, true);
            }
        }
    }

    private static void runSubDir(File f) {

        for (File file : f.listFiles()) {

            if (file.isDirectory()) {
                System.out.println(file.getAbsoluteFile());
                runSubDir(file);
            } else {
                try {
                    ShowMediaInfo.ByFileName(file.getAbsolutePath());
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Utils.printConsole(file, false);

            }

        }

    }

}
