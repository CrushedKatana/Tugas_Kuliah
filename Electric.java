import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ElectricVehicleParking {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // VARIABLES
        double parkingPrice, chargingRate;
        String name, platenumber;
        LocalDateTime timeIn = null, timeOut = null, startChargingTime = null, stopChargingTime = null;

        System.out.println("Input name user");
        name = input.next();
        System.out.println("Do you have a membership? (yes/no):");
        String hasMembership = input.next();

        System.out.println("Choose a vehicle: ");
        System.out.println("1. Electric Car");
        System.out.println("2. Electric Motorcycle");
        int choicetype = input.nextInt();

        switch (choicetype) {
            case 1:
                System.out.println("You chose an Electric Car.");
                System.out.println("Input plate number");
                platenumber = input.next();
                parkingPrice = 5000.00; // Adjust parking price for electric cars
                chargingRate = 1000.00; // Adjust charging rate for electric cars
                break;

            case 2:
                System.out.println("You chose an Electric Motorcycle.");
                System.out.println("Input plate number");
                platenumber = input.next();
                parkingPrice = 2000.00; // Adjust parking price for electric motorcycles
                chargingRate = 500.00; // Adjust charging rate for electric motorcycles
                break;

            default:
                System.out.println("Invalid choice. Please choose 1 for Electric Car or 2 for Electric Motorcycle.");
                return;
        }

        while (true) {
            System.out.println("Press '1' to record time in, '2' to record time out, '3' to start charging, '4' to stop charging, '5' to calculate cost, or '0' to exit.");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    timeIn = LocalDateTime.now();
                    System.out.println("Time in recorded: " + formatDateTime(timeIn));
                    break;
                case 2:
                    if (timeIn == null) {
                        System.out.println("Please record time in first.");
                    } else {
                        timeOut = LocalDateTime.now();
                        System.out.println("Time out recorded: " + formatDateTime(timeOut));
                    }
                    break;
                case 3:
                    if (timeIn == null) {
                        timeIn = LocalDateTime.now();
                        System.out.println("Time in recorded: " + formatDateTime(timeIn));
                    }
                    startChargingTime = LocalDateTime.now();
                    System.out.println("Charging started at: " + formatDateTime(startChargingTime));
                    break;
                case 4:
                    if (startChargingTime != null) {
                        stopChargingTime = LocalDateTime.now ();
                        System.out.println("Charging stopped at: " + formatDateTime(stopChargingTime));
                    } else {
                        System.out.println("Please start charging first.");
                    }
                    break;
                case 5:
                   if (timeIn != null && timeOut != null) {
                        long parkingDurationMinutes = calculateDurationMinutes(timeIn, timeOut);
                        double parkingCost = calculateCost(parkingDurationMinutes, parkingPrice);

                        double totalCost = parkingCost;

                        if (startChargingTime != null && stopChargingTime != null) {
                            long chargingDurationMinutes = calculateDurationMinutes(startChargingTime, stopChargingTime);
                            double chargingCost = calculateCost(chargingDurationMinutes, chargingRate);
                            totalCost += chargingCost;
                        }

                        if (hasMembership.equalsIgnoreCase("yes")) {
                            double discount = totalCost * 0.10; // 10% discount
                            totalCost -= discount;
                        }

                         System.out.println("========================================================");
                        System.out.println("Parking Duration: " + parkingDurationMinutes + " minutes");
                        System.out.println("Parking Cost: Rp" + parkingCost);
                        System.out.println("Charging Duration: " + chargingDurationMinutes + " minutes");
                        System.out.println("Charging Cost: Rp" + chargingCost);
                        System.out.println("========================================================");

                        }
                        System.out.println("Total Cost: Rp" + totalCost);
                        System.out.println("========================================================");
                    } else {
                        System.out.println("Please record time in and time out first.");
                    }
                    break;
                case 0:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter '1', '2', '3', '4', '5', or '0'.");
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
