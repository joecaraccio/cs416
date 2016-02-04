package src;

/**
 * Created by Joe on 2/3/2016.
 */

import java.awt.Component;
import java.io.File;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Utilities {
    private static int frameTime = 100;
    private static JFileChooser chooser = null;

    public Utilities() {
    }

    public static void frame() {
        sleep(frameTime);
    }

    public static void sleep(int var0) {
        try {
            Thread.sleep((long)var0);
        } catch (InterruptedException var2) {
            ;
        }

    }

    public static int getArg(String[] var0, int var1, int var2) {
        try {
            return Integer.parseInt(var0[var1]);
        } catch (ArrayIndexOutOfBoundsException var4) {
            ;
        } catch (NumberFormatException var5) {
            System.err.println("Error: improper command line argument " + var1 + " = " + var0[var1] + ".  It should be an integer; using default value: " + var2);
        }

        return var2;
    }

    public static float getArg(String[] var0, int var1, float var2) {
        try {
            return Float.parseFloat(var0[var1]);
        } catch (ArrayIndexOutOfBoundsException var4) {
            ;
        } catch (NumberFormatException var5) {
            System.err.println("Error: improper command line argument " + var1 + " = " + var0[var1] + ".  It should be an float; using default value: " + var2);
        }

        return var2;
    }

    public static String getArg(String[] var0, int var1, String var2) {
        try {
            return var0[var1];
        } catch (ArrayIndexOutOfBoundsException var4) {
            return var2;
        }
    }

    public static String getFileName() {
        return getFileName("Choose a file. ");
    }

    public static String getFileName(String var0) {
        String var1 = null;
        if(chooser == null) {
            chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));
        }

        chooser.setDialogTitle(var0);
        int var2 = chooser.showOpenDialog((Component)null);

        while(var1 == null && var2 != 1) {
            if(var2 == 0) {
                File var3 = chooser.getSelectedFile();
                if(var3.isFile()) {
                    var1 = var3.getPath();
                } else {
                    var2 = chooser.showOpenDialog((Component)null);
                }
            }
        }

        return var1;
    }

    public static int readInt(String var0, int var1) {
        String var2 = JOptionPane.showInputDialog((Component)null, var0);
        return parseInt(var2, var1);
    }

    private static int parseInt(String var0, int var1) {
        if(var0 == null) {
            return var1;
        } else {
            Scanner var2 = new Scanner(var0);
            if(var2.hasNextInt()) {
                var1 = var2.nextInt();
            } else {
                System.out.println("Integer parse error: " + var0);
            }

            return var1;
        }
    }
}
