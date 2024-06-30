import java.util.HashMap;

public abstract class BaseExpression implements Expression{

    public Boolean evaluate() throws Exception {
        return evaluate(new HashMap<>());
    }
}
