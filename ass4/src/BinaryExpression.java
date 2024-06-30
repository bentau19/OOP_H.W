import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class BinaryExpression extends BaseExpression{
    private Expression left;
    private Expression right;
    private char sign;
    Objects obj;
    public BinaryExpression(Expression left, Expression right, char sign){
        this.left = left;
        this.right = right;
        this.sign = sign;
    }
    public Expression getLeft() {
        return left;
    }
    public Expression getRight() {
        return right;
    }

    // Returns a list of the variables in the expression.
    public List<String> getVariables(){
        List<String> left = this.left.getVariables();
        List<String> right = this.right.getVariables();
        List<String> variables = new ArrayList<>(left);
        variables.removeAll(right);
        variables.addAll(right);
        return variables;
    }

    public Expression assign(String var, Expression expression) {
        try {
            return this.getClass()
                    .getConstructor(Expression.class, Expression.class)
                    .newInstance(left.assign(var, expression), right.assign(var, expression));
        } catch (Exception e) {
            return null;
        }
    }

    public abstract boolean calc(boolean a, boolean b);

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return calc(left.evaluate(assignment), right.evaluate(assignment));
    }



    // Returns a nice string representation of the expression.
    public String toString(){
        return "(" + left.toString() + " " + sign + " " + right.toString() + ")";
    }

}
