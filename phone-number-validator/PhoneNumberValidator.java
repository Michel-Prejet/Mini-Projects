
/**
 * PhoneNumberValidator.java
 * 
 * A simple program that prompts the user for a phone number and checks
 * that it follows the one of the following formats: 
 * - 123-123-1234 (includes area code)
 * - 123-1234     (no area code)
 * 
 * Author: Michel Préjet
 * Date: July 9th, 2025
 */

import java.util.Scanner;

public class PhoneNumberValidator {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String phoneNum = "";
        boolean valid = false;
        boolean firstIteration = true;

        while (!valid) {
            // Prompt user
            if (firstIteration) {
                System.out.print("Enter a phone number of the form 123-123-1234 or 123-1234: ");
                phoneNum = scan.nextLine().trim();
            } else {
                System.out.print("Re-enter a phone number of the form 123-123-1234 or 123-1234: ");
                phoneNum = scan.nextLine().trim();
            }

            // Perform all validation checks
            boolean containsAreaCode = containsAreaCode(phoneNum);
            boolean digits = containsOnlyDigits(phoneNum) && correctNumDigits(phoneNum, containsAreaCode);
            boolean structure = digits && hasCorrectStructure(phoneNum, containsAreaCode);

            valid = structure;
            firstIteration = false;
        }

        System.out.println(phoneNum + " is a valid phone number.");
        System.out.println(" *** PROGRAM TERMINATED SUCCESSFULLY *** ");
        scan.close();
    }

    /**
     * Counts the number of dashes in a given phone number
     * 
     * @param phoneNum the phone number to search through
     * @return the number of dashes counted in phoneNum
     */
    public static int getNumDashes(String phoneNum) {
        int numDashes = 0;
        for (int i = 0; i < phoneNum.length(); i++) {
            if (phoneNum.charAt(i) == '-') {
                numDashes++;
            }
        }
        return numDashes;
    }

    /**
     * Checks whether a given phone number contains only digits (excluding dashes)
     * 
     * @param phoneNum the phone number to search through
     * @return true if all characters (except for dashes) are digits; false
     *         otherwise
     */
    public static boolean containsOnlyDigits(String phoneNum) {
        phoneNum = phoneNum.replaceAll("-", "");
        for (int i = 0; i < phoneNum.length(); i++) {
            if (!Character.isDigit(phoneNum.charAt(i))) {
                System.out.println("[Error] Phone number can only contain digits and dashes.");
                return false;
            }
        }
        return true;
    }

    /**
     * Checks whether a given phone number contains an area code based on the
     * number of dashes
     * 
     * @param phoneNum the phone number to search through
     * @return true if phoneNum contains more than one dash; false otherwise
     */
    public static boolean containsAreaCode(String phoneNum) {
        int numDashes = getNumDashes(phoneNum);
        return numDashes >= 2;
    }

    /**
     * Checks whether a given phone number contains the correct number of digits,
     * taking into account the presence or absence of an area code
     * 
     * @param phoneNum         the phone number to search through
     * @param containsAreaCode whether the phone number contains an area code
     * @return true if phoneNum contains 7 digits (without area code) or 10
     *         digits (with area code); false otherwise
     */
    public static boolean correctNumDigits(String phoneNum, boolean containsAreaCode) {
        phoneNum = phoneNum.replaceAll("-", "");
        if (containsAreaCode) {
            if (phoneNum.length() == 10) {
                return true;
            } else {
                System.out.println("[Error] Phone number must contain 10 digits (with area code).");
                return false;
            }
        } else {
            if (phoneNum.length() == 7) {
                return true;
            } else {
                System.out.println("[Error] Phone number must contain 7 digits (without area code).");
                return false;
            }
        }
    }

    /**
     * Checks whether a given phone number has dashes in the right place, taking
     * into account the presence or absence of an area code
     * 
     * @param phoneNum         the phone number to validate
     * @param containsAreaCode whether the phone number contains an area code
     * @return true if there is one dash at index 3 (no area code) or two dashes
     *         at indexes 3 and 7 (with area code); false otherwise
     */
    public static boolean hasCorrectStructure(String phoneNum, boolean containsAreaCode) {
        int firstDash, secondDash;

        if (containsAreaCode) {
            // Check that both dashes are in the right place
            firstDash = phoneNum.indexOf("-");
            secondDash = phoneNum.lastIndexOf("-");
            if (firstDash == 3 && secondDash == 7) {
                return true;
            } else {
                System.out.println(
                        "[Error] Phone number is incorrectly formatted. Check that dashes are in the right place.");
                return false;
            }
        } else {
            // Check that there is exactly 1 dash
            int numDashes = getNumDashes(phoneNum);
            if (numDashes != 1) {
                System.out.println("[Error] Phone number must contain one or two dashes.");
                return false;
            }

            // Check that the dash is in the right place
            firstDash = phoneNum.indexOf("-");
            if (firstDash == 3) {
                return true;
            } else {
                System.out.println(
                        "[Error] Phone number is incorrectly formatted. Check that the dash is in the right place.");
                return false;
            }
        }
    }
}
