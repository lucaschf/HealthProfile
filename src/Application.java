import tsi.too.controller.HealthRecords;

public class Application {
    public static void main(String[] args) {
        HealthRecords healthRecords = new HealthRecords();
        healthRecords.readData();
        healthRecords.showData();
        System.exit(0);
    }
}
