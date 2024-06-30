import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Val implements Expression {
    private boolean value;
    public Val(boolean value) {
        this.value = value;
    }
//    public boolean getValue() {
//        return value;
//    }
//    public void setValue(boolean value) {
//        this.value = value;
//    }
    public Boolean evaluate (Map<String, Boolean> assignments) throws Exception {
        return this.evaluate();
    }
    public Boolean evaluate () throws Exception{
        return value;
    }
    public List<String> getVariables(){
        return new ArrayList<>();
    }
    public String toString(){
        if (this.value){
            return "T";
        }
        return "F";
    }
    public Expression assign(String var, Expression expr){
        return this;
    }
    @Override
    public Expression norify() {
        return new Val(this.value);
    }

    @Override
    public Expression nandify() {
        return new Val(this.value);
    }
    @Override
    public Expression simplify() {
        return new Val(this.value);
    }
}