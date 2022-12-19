import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    static Scanner inputDevice;

    public static void main(String[] args) {
        init();
        double rentalTime = inputHours();
        double hours = calcHours(rentalTime);
        double additionalMinutes = calcAdditionalMinutes(rentalTime);
        double rentalCost = calcRentalCost(hours, additionalMinutes);
        output(hours, additionalMinutes, rentalCost);

    }

//************************************************************************************
    private static void init() {

        inputDevice = new Scanner(System.in);
    }
//************************************************************************************
    private static double inputHours() {
        double iMinutes;
        System.out.println("Enter rental time in minutes: ");
        String iData = inputDevice.nextLine();
        try{
            iMinutes = Double.parseDouble(iData);
        }
        catch (Exception e){
            System.out.println("Invalid - must be numeric, defaulted to 1 hour\n");
            iMinutes = 60;
        }
        return iMinutes;
    }

//************************************************************************************
    private static double calcHours(double rentalTime) {
        return (int) (rentalTime / 60);
    }
//************************************************************************************
    private static double calcAdditionalMinutes(double rentalTime) {
        return rentalTime % 60;
    }

//************************************************************************************
    private static double calcRentalCost(double rentalTime, double additionalMinutes) {
        return rentalTime * 40.00 + additionalMinutes;
    }
//************************************************************************************
    private static void output(double hours, double additionalMinutes, double rentalCost) {

        DecimalFormat df = new DecimalFormat("$###,###.00");

        System.out.println(CompanyMotto.getMotto());
        System.out.println();
        System.out.format("%6s%25s%15s\n", "Hours", "Additional Minutes", "Rental Cost");
        System.out.format("%6.2f%25.2f     %10s\n", hours, additionalMinutes, df.format(rentalCost));
    }
}



