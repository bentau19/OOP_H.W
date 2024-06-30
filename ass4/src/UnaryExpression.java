import java.util.List;
import java.util.Map;

public abstract class UnaryExpression extends BaseExpression {
    Expression exp;
    char sign;
    public UnaryExpression(Expression exp ,char sign) {
        this.exp = exp;
        this.sign = sign;
    }
    @Override
    public Expression assign(String var, Expression exp) {
        try {
            return this.getClass()
                    .getConstructor(Expression.class)
                    .newInstance(exp.assign(var, exp));
        } catch (Exception e) {
            return null;
        }
    }
    public List<String> getVariables() {
        return exp.getVariables();
    }
    public abstract boolean calc(boolean a);
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return calc(exp.evaluate(assignment));
    }

    public Expression nandify() {
        Expression curexp = exp.nandify();
        return new Nand(curexp, curexp);
    }

    @Override
    public Expression norify() {
        Expression curexp = exp.norify();
        return new Nor(curexp, curexp);
    }
    public String toString() {
        return sign+"("+exp.toString()+")";
    }
}
