package converter;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void convert(boolean fromDecimal, Scanner scanner) {
        String prompt1;
        String prompt2;
        String resultLabel;
        String input;
        String result = null;
        int base;
        if (fromDecimal) {
            prompt1 = "Enter a number in decimal system: ";
            prompt2 = "Enter the target base: ";
            resultLabel = "Conversion result: ";
        } else {
            prompt1 = "Enter source number: ";
            prompt2 = "Enter source base: ";
            resultLabel = "Conversion to decimal result: ";
        }
        System.out.println(prompt1);
        input = scanner.nextLine();
        System.out.println(prompt2);
        base = scanner.nextInt();
        scanner.nextLine();

        if (!(base == 2 || base == 8 || base == 16 )) {
            return;
        }
        switch (base) {
            case 2 : { result = fromDecimal ? Integer.toBinaryString(Integer.parseInt(input)) : Integer.toString(Integer.parseInt(input, base)); break; }
            case 8 : {  result = fromDecimal ? Integer.toOctalString(Integer.parseInt(input)) : Integer.toString(Integer.parseInt(input, base)); break;}
            case 16 : { result = fromDecimal ? Integer.toHexString(Integer.parseInt(input)) : Integer.toString(Integer.parseInt(input, base)); break;}
        }
        System.out.println(resultLabel + result +"\n");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(java.lang.System.in);
        String prompt = "";
        while (!prompt.equals("/quit")) {

            System.out.println("Do you want to convert /from decimal or /to decimal? (To quit type /exit)");
            prompt = scanner.nextLine();
            switch (prompt) {
                case "/exit":
                    java.lang.System.exit(0);
                    break;
                case "/to":
                    convert(false, scanner);
                    break;
                case "/from":
                    convert(true, scanner);
                    break;
            }
        }
    }
}

