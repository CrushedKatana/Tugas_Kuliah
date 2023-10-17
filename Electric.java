import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class electric {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // VARIABLES
        double parkingPrice, chargingRate;
        String name, cartype, motorcycletype, platenumber, vehicletype;
        LocalDateTime timeIn = null, timeOut = null, startChargingTime = null, stopChargingTime = null;
        boolean isCharging = false;

        System.out.println("Input name user");
        name = input.next();
        System.out.println("Do you have a membership? (yes/no):");
        String hasMembership = input.next();

        System.out.println("Choose a vehicle: ");
        System.out.println("1. Electric Car");
        System.out.println("2. Electric Motorcycle");
        int choicetype = input.nextInt();

        switch (choicetype) {
            // ... (same as before)
        }

        while (true) {
            System.out.println("Press '1' to record time in, '2' to record time out, '3' to start charging, '4' to stop charging, '5' to calculate cost, '6' to park only, or '0' to exit.");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    if (isCharging) {
                        System.out.println("Stop charging before recording time in.");
                        break;
                    }
                    timeIn = LocalDateTime.now();
                    System.out.println("Time in recorded: " + formatDateTime(timeIn));
                    break;
                case 2:
                    if (isCharging) {
                        System.out.println("Stop charging before recording time out.");
                        break;
                    }
                    timeOut = LocalDateTime.now();
                    System.out.println("Time out recorded: " + formatDateTime(timeOut));
                    break;
                case 3:
                    if (isCharging) {
                        System.out.println("Charging is already in progress.");
                    } else {
                        startChargingTime = LocalDateTime.now();
                        System.out.println("Charging started at: " + formatDateTime(startChargingTime));
                        isCharging = true;
                    }
                    break;
                case 4:
                    if (!isCharging) {
                        System.out.println("Charging is not in progress.");
                    } else {
                        stopChargingTime = LocalDateTime.now();
                        System.out.println("Charging stopped at: " + formatDateTime(stopChargingTime));
                        isCharging = false;
                    }
                    break;
                case 5:
                    // ... (same as before)
                case 6:
                    // ... (same as before)
                case 0:
                    if (isCharging) {
                        stopChargingTime = LocalDateTime.now();
                        System.out.println("Charging stopped at: " + formatDateTime(stopChargingTime));
                        isCharging = false;
                    }
                    System.out.println("Exiting the program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter '1', '2', '3', '4', '5', '6', or '0'.");
                    break;
            }
        }
    }

    // Helper method to calculate the duration in minutes
    private static long calculateDurationMinutes(LocalDateTime start, LocalDateTime stop) {
        return Duration.between(start, stop).toMinutes();
    }

    // Helper method to calculate the cost based on duration and cost per minute
    private static double calculateCost(long durationMinutes, double costPerMinute) {
        return (double) durationMinutes * costPerMinute / 60.0; // Convert minutes to hours
    }

    // Helper method to format LocalDateTime as a string
    private static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
}