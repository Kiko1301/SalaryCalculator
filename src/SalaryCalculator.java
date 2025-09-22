import java.util.Scanner;

public class SalaryCalculator {
    // Constants for pay rates and standard hours
    private static final double OVERTIME_RATE = 0.35; // 35%
    private static final double NIGHT_RATE = 0.35; // 35%
    private static final double HOLIDAY_RATE = 2.5; // 250%
    private static final int STANDARD_HOURS = 40; // Standard weekly hours
    private static final double MINIMUM_SALARY = 0.0; // Minimum allowed base salary

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user input with validation
        double baseSalary = getValidDoubleInput(scanner, "Enter base monthly salary: ", MINIMUM_SALARY);
        int totalHoursWorked = getValidIntInput(scanner, "Enter total hours worked: ", 0);
        int nightHours = getValidIntInput(scanner, "Enter total night hours worked: ", 0);
        int holidayHours = getValidIntInput(scanner, "Enter total holiday hours worked: ", 0);

        // Calculate different components of salary
        double overtimePay = calculateOvertimePay(baseSalary, totalHoursWorked);
        double nightPay = calculateNightPay(baseSalary, nightHours);
        double holidayPay = calculateHolidayPay(baseSalary, holidayHours);

        // Calculate and display total salary
        double totalSalary = baseSalary + overtimePay + nightPay + holidayPay;
        
        System.out.printf("\nSalary Breakdown:%n");
        System.out.printf("Base Salary: $%.2f%n", baseSalary);
        System.out.printf("Overtime Pay: $%.2f%n", overtimePay);
        System.out.printf("Night Pay: $%.2f%n", nightPay);
        System.out.printf("Holiday Pay: $%.2f%n", holidayPay);
        System.out.printf("Total Salary: $%.2f%n", totalSalary);

        scanner.close();
    }

    /**
     * Calculates overtime pay based on hours worked beyond standard hours
     * @param baseSalary Monthly base salary
     * @param totalHoursWorked Total hours worked in the period
     * @return Calculated overtime pay
     */
    private static double calculateOvertimePay(double baseSalary, int totalHoursWorked) {
        if (totalHoursWorked <= STANDARD_HOURS) {
            return 0.0;
        }
        
        int overtimeHours = totalHoursWorked - STANDARD_HOURS;
        double hourlyRate = baseSalary / STANDARD_HOURS;
        return overtimeHours * hourlyRate * (1 + OVERTIME_RATE);
    }

    /**
     * Calculates additional pay for night hours
     * @param baseSalary Monthly base salary
     * @param nightHours Total night hours worked
     * @return Calculated night pay
     */
    private static double calculateNightPay(double baseSalary, int nightHours) {
        double hourlyRate = baseSalary / STANDARD_HOURS;
        return nightHours * hourlyRate * (1 + NIGHT_RATE);
    }

    /**
     * Calculates additional pay for holiday hours
     * @param baseSalary Monthly base salary
     * @param holidayHours Total holiday hours worked
     * @return Calculated holiday pay
     */
    private static double calculateHolidayPay(double baseSalary, int holidayHours) {
        double hourlyRate = baseSalary / STANDARD_HOURS;
        return holidayHours * hourlyRate * (1 + HOLIDAY_RATE);
    }

    /**
     * Gets valid double input from user with error handling
     * @param scanner Scanner object for input
     * @param prompt Prompt message to display
     * @param minValue Minimum allowed value
     * @return Valid double input
     */
    private static double getValidDoubleInput(Scanner scanner, String prompt, double minValue) {
        while (true) {
            System.out.print(prompt);
            try {
                double input = scanner.nextDouble();
                if (input < minValue) {
                    System.out.println("Error: Value must be at least " + minValue);
                    continue;
                }
                return input;
            } catch (Exception e) {
                System.out.println("Error: Please enter a valid number");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    /**
     * Gets valid integer input from user with error handling
     * @param scanner Scanner object for input
     * @param prompt Prompt message to display
     * @param minValue Minimum allowed value
     * @return Valid integer input
     */
    private static int getValidIntInput(Scanner scanner, String prompt, int minValue) {
        while (true) {
            System.out.print(prompt);
            try {
                int input = scanner.nextInt();
                if (input < minValue) {
                    System.out.println("Error: Value must be at least " + minValue);
                    continue;
                }
                return input;
            } catch (Exception e) {
                System.out.println("Error: Please enter a valid integer");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
}

//This code has been contributed by Kristijan Jordanovski 