public class Not extends UnaryExpression {
    public Not(Expression expr) {
        super(expr,'~');
    }
    public boolean calc(boolean a) {
        return !a;
    }

    public Expression simplify() {
        //checks if the expression has a boolean value, if it does, it returns the negation of it.
        Expression a = super.exp.simplify();
        if (a.toString().equals("F")) {
            return new Val(true);
        }
        if (a.toString().equals("T")) {
            return new Val(false);
        }
        return new Not(a);

    }
}
