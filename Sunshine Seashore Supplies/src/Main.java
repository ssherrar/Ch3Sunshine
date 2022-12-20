import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class Main {
    static Scanner inputDevice;


    public static void main(String[] args) {
        String newDateString = init();
        double rentalTime = inputHours();
        double hours = calcHours(rentalTime);
        double additionalMinutes = calcAdditionalMinutes(rentalTime);
        double rentalCost = calcRentalCost(hours, additionalMinutes);
        String strDate = getDateFromUser();
        String formatDate = formatUserDate(strDate, newDateString);
        output(hours, additionalMinutes, rentalCost, formatDate);

    }

    //************************************************************************************
    private static String init() {

        inputDevice = new Scanner(System.in);
        LocalDate today = LocalDate.now();
        String newDateString = formatDate(String.valueOf(today));
        return newDateString;
    }
    //************************************************************************************
    private static double inputHours() {
        double iMinutes;

        System.out.print("Enter rental time in minutes: ");
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
    private static String formatDate(String enteredDate) {
        String enteredPattern = "YYYY-MM-DD";
        String newPattern = "MMMM dd, yyyy";
        String newDateString = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(enteredPattern);
            Date d = sdf.parse(enteredDate);
            sdf.applyPattern(newPattern);
            newDateString = sdf.format(d);
            System.out.println("************************************************");
            System.out.println("Today's date is: " + newDateString);
            System.out.println("************************************************");
        } catch (ParseException pe) {
            System.out.println("System Error");
        }
        return newDateString;
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
    private static String getDateFromUser() {
        String enteredDate;
        System.out.print("Enter date of rental (MM/DD/YYYY): ");
        enteredDate = inputDevice.nextLine();
        System.out.println("************************************************");
        return enteredDate;
    }
    //************************************************************************************
    private static String formatUserDate(String enteredDate, String todayDateString) {
        String enteredPattern = "MM/DD/YYY";
        String newPattern = "MMM dd, yyyy";

        SimpleDateFormat sdf = new SimpleDateFormat(enteredPattern);
        String newDateString = null;
        try {
            Date d = sdf.parse(enteredDate);
            sdf.applyPattern(newPattern);
            newDateString = sdf.format(d);

        } catch (ParseException pe) {
            System.out.println("\n           ---Invalid date entered---");
            System.out.println("        ---Defaulting to current date---");
            System.out.println("    ---Today's date is: " + todayDateString + "---");
            System.out.println("  ********************************************");

            newDateString = todayDateString;


            String newDateStringPattern= "MMMM dd, yyyy";
            String newestPattern = "MMM dd, yyyy";

            SimpleDateFormat sdf2 = new SimpleDateFormat(newDateStringPattern);

            try {
                Date d = sdf2.parse(newDateString);
                sdf2.applyPattern(newestPattern);
                newDateString = sdf2.format(d);

            }catch (ParseException ppe) {

            }

        }
        return (newDateString);
    }
    //************************************************************************************
    private static void output(double hours, double additionalMinutes, double rentalCost, String formatDate) {

        DecimalFormat df = new DecimalFormat("$###,###.00");

        System.out.println(CompanyMotto.getMotto());
        System.out.println();
        System.out.format("%-15s%6s%17s%10s\n", "Date", "Hours", "Extra Minutes", "Cost");
        System.out.format("%-15s%6.2f%17.2f%10s\n", formatDate, hours, additionalMinutes, df.format(rentalCost));
    }
}



