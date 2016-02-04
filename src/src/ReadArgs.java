package src;

/**
 * Created by Joe on 2/3/2016.
 */
public class ReadArgs {
    public ReadArgs() {
    }

    public static int getArg(String[] var0, int var1, int var2) {
        try {
            return Integer.parseInt(var0[var1]);
        } catch (ArrayIndexOutOfBoundsException var4) {
            ;
        } catch (NumberFormatException var5) {
            System.err.println("Error: Bad command line argument: " + var1 + " = " + var0[var1] + ". Should be int!" + " Using default value: " + var2);
        }

        return var2;
    }

    public static float getArg(String[] var0, int var1, float var2) {
        try {
            return Float.parseFloat(var0[var1]);
        } catch (ArrayIndexOutOfBoundsException var4) {
            ;
        } catch (NumberFormatException var5) {
            System.err.println("Error: Bad command line argument: " + var1 + " = " + var0[var1] + ". Should be float!" + " Using default value: " + var2);
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
}

