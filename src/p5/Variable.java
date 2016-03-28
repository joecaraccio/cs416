package p5;

/**
 * Created by Joe on 3/26/2016.
 */
public class Variable extends Operand {

    String s1;
    public Variable( String s ){
        s1 = s;
    }

    public String printable() {
        String returnstring = "@" +  s1;

        return returnstring;
    }
}
