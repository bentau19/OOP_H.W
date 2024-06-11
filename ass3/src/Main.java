public class Main {
    public static void main(String[] args) {
        Rectangle a = new Rectangle(new Point(10,10),100,100);
        Point b = new Point(10,5);
        Point c = new Point(110,5);
        Line d = new Line(c, b);
        System.out.println(d.closestIntersectionToStartOfLine(a));
    }
}
