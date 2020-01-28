import org.junit.jupiter.api.Test;

public class FirstHomeTask {
    @Test
    public void firstHomework() {
        firstTask("Дима",33);
        secondTask(333,83);
        thirdTask(3);
        fourthTask(2,4,6,8);
    }
    private void firstTask(String name, int age) {

        System.out.println("Привет, " + name + ", " + "тебе увы аж " + age);
    }
    private void secondTask(double km, double fuel){

        System.out.println("Fuel consumption is: " + Math.round((fuel/ km) * 100));
    }
    private void thirdTask(double radius){

        System.out.println("Length of circle is: " + Math.round(2 * Math.PI * radius) +
                " ; " + "Circle revolutions(1000km): " + Math.round(100000000 / (radius * 2 * Math.PI)));
    }
    private void fourthTask(double x, double x1, double y, double y1){
        double distance = Math.sqrt ( Math.pow (( x1 - x ), 2 ) + Math.pow (( y1 - y ), 2 ));
        System.out.println("Distance is: " + Math.round(distance));
    }
}
