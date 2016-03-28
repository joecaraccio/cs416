package p5;

/**
 * Created by Joe on 3/26/2016.
 */
public class Operator extends  EToken {

    String operator;

    public Operator(String s ){
        //System.out.println( "Operator");
        operator = s;

    }

    public String printable(){
        //System.out.println("Printable");
        String printformat = "<" + operator + ">";

        return printformat;
    }


}
