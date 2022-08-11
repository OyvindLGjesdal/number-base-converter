package converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(java.lang.System.in);
        int sourceBase;
        int targetBase;
        String prompt = "";
        while (!prompt.equals("/exit")) {

            System.out.println("Enter two numbers in format: {source base} {target base} (To quit type /exit");
            prompt = scanner.nextLine();
            switch (prompt) {
                case "/exit":
                    java.lang.System.exit(0);
                    break;
                default:
                    sourceBase = Integer.valueOf(prompt.split(" ")[0]);
                    targetBase = Integer.valueOf(prompt.split(" ")[1]);
                    convert(sourceBase, targetBase, scanner);
                    // convert(false, scanner);
                    break;
            }
        }
    }

    public static void convert(int sourceBase, int targetBase, Scanner scanner) {
        if (37 < sourceBase || sourceBase < 2 || targetBase < 2 || targetBase > 37) {
            return;
        }
        String input = "";
        while (!input.equals("/back")) {
            System.out.printf("Enter number in base %d to convert to base %d (To go back type /back) ", sourceBase, targetBase);
            input = scanner.nextLine();
            if (input.equals("/back")) {
                return;
            }
            String[] inputArray = input.split("\\.");
            BigInteger bigIntegerPart = new BigInteger(inputArray[0], sourceBase);

            String fractionsPart = inputArray.length > 1 ? convertFraction(inputArray[1], sourceBase, targetBase) : "";

            System.out.println("Conversion result: " + addIntegerAndFraction(bigIntegerPart.toString(targetBase), fractionsPart));
        }
    }

    public static String addIntegerAndFraction(String integer, String fraction) {
        if (fraction.length() == 0) {
            return integer;
        }
        return integer + "." + fraction.charAt(0) + fraction.charAt(1) + fraction.charAt(2) + fraction.charAt(3) + fraction.charAt(4);
    }

    public static String convertFraction(String sourceFraction, int sourceBase, int targetBase) {

        double decimalValue = 0.0;
        char c;
        for (int i = 0; i < sourceFraction.length(); i++) {
            c = sourceFraction.charAt(i);
            decimalValue += Character.digit(c, sourceBase) / Math.pow(sourceBase, i + 1);
        }

        String decimalInNewBase = decimalFractionalToBase(new BigDecimal(decimalValue), targetBase, 0);
        return decimalInNewBase;
    }

    public static String decimalFractionalToBase(BigDecimal fractionalDecimal, int base, int depth) {
        BigDecimal bigBase = BigDecimal.valueOf(base);
        BigDecimal decimal = fractionalDecimal.multiply(bigBase);
        BigDecimal result = decimal.setScale(0, RoundingMode.FLOOR);
        BigDecimal rest = decimal.subtract(result);

        String resultInTarget = (result.unscaledValue().toString(base));

        return decimalFractionalToBase(rest, base, new StringBuilder().append(resultInTarget), depth, bigBase);
    }

    public static String decimalFractionalToBase(BigDecimal fractionalDecimal, int base, StringBuilder result, int depth, BigDecimal bigBase) {

        BigDecimal decimal = fractionalDecimal.multiply(bigBase);
        BigDecimal result2 = decimal.setScale(0, RoundingMode.FLOOR);
        BigDecimal fractionRest = decimal.subtract(result2);

        if (depth > 7) {
            return result.append(result2.unscaledValue().toString(base)).toString();
        }
        if (result2.stripTrailingZeros().equals(BigDecimal.ZERO) && fractionRest.stripTrailingZeros().equals(BigDecimal.ZERO)) {
            return decimalFractionalToBase(fractionRest, base, result.append("0"), depth + 1, bigBase);
        }
        return decimalFractionalToBase(fractionRest, base, result.append(result2.unscaledValue().toString(base)), depth + 1, bigBase);
    }
}


