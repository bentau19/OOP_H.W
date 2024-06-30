
public class Or extends BinaryExpression{
    public Or(Expression left, Expression right){
        super(left, right,'|');
    }
    public Expression nandify() {
        Expression a = super.getLeft().nandify();
        Expression b = super.getRight().nandify();
        Expression aa = new Nand(a, a);
        Expression bb = new Nand(b, b);
        return new Nand(aa, bb);
    }

    @Override
    public Expression norify() {
        Expression a = super.getLeft().norify();
        Expression b = super.getRight().norify();
        Expression c = new Nor(a,b);
        return new Nor(c, c);
    }
    public boolean calc(boolean a, boolean b) {
        return a || b;
    }
    @Override
    public Expression simplify() {
      /*
        * supports
        * • x ∨ 1 = 1
        • x ∨ 0 = x
        • x ∨ x = x
        * */
        Expression a = super.getLeft().simplify();
        Expression b = super.getRight().simplify();
        if (a.toString().equals(b.toString())) {
            return a;
        }
        if (a.toString().equals("T") || b.toString().equals("T")) {
            return new Val(true);
        }
        if (a.toString().equals("F")) {
            return b;
        }

        if (b.toString().equals("F")) {
            return a;
        }
        return new Or(a, b);
    }
}
