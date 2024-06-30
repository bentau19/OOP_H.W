import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Var implements Expression {
    private String value;
    public Var(String value) {
        this.value = value;
    }
    @Override
public List<String> getVariables(){
        List<String> list = new ArrayList<>();
        list.add(value);
    return list;
}
@Override
    public String toString(){
        return value;
    }
    public Boolean evaluate (Map<String, Boolean> assignments) throws Exception {
        if (!assignments.containsKey(value)) {
            this.evaluate();
        }
        return assignments.get(value);
    }
    public Boolean evaluate () throws Exception{
        throw new Exception("An error occurred because someCondition is true.");
    }
    public Expression assign(String var, Expression expr){
        if (var.equals(value)){
            return expr;
        }
        return this;
    }
    @Override
    public Expression norify() {
        return new Var(this.value);
    }

    @Override
    public Expression nandify() {
        return new Var(this.value);
    }
    @Override
    public Expression simplify() {
        return new Var(this.value);
    }
}
