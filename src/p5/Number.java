package p5;

/**
 * Created by Joe on 3/26/2016.
 */
public class Number extends Operand {

    Float token;

    public Number( float s ){
        System.out.println("Number");
        token = s;
    }

    public String printable() {
        int tokenint =  Math.round( token );
        String returnstring = "@" +  tokenint;

        return returnstring;
    }
}
