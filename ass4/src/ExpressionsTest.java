import java.util.HashMap;
import java.util.Map;

/**
 * The ExpressionsTest class contains a main method to test various
 * expressions including variables, logical operations, and their
 * evaluations.
 */
public class ExpressionsTest {
    /**
     * The main method to test the creation, evaluation, and transformation
     * of expressions.
     *
     * @param args command line arguments (not used)
     * @throws Exception if an error occurs during evaluation
     */
    public static void main(String[] args) throws Exception {
        Expression x = new Var("x");
        Expression y = new Var("y");
        Expression z = new Var("z");
        Expression ex = new And(new Or(x, y), new Not(z));
        System.out.println(ex);

        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("x", true);
        map.put("y", false);
        map.put("z", true);

        System.out.println(ex.evaluate(map));
        System.out.println(ex.nandify());
        System.out.println(ex.norify());
        System.out.println(ex.simplify());
    }
}
