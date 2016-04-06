package p6;

/**
 * Created by Joe on 4/6/2016.
 */
public class TreeNode {

    private float value;
    private String vName;
    private String operator;
    TreeNode right;
    TreeNode left;

    public TreeNode( String s ) throws Exception
    {
        System.out.println("Creating a TreeNode for: " + s );
        vName = null;
        operator = null;

        String token = s;
        float value;
        try
        {
            value = Float.parseFloat( token );

        } catch ( NumberFormatException e ){
            System.out.println("This is not a number");
        }

    }


    public static void main( String[] args ){

    }

}
