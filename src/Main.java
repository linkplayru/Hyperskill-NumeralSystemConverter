import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        String[] input = new String[3];

        //check for error
        for (int i = 0; i < 3; i++) {
            if (s.hasNext()) {
                input[i] = s.next();
            } else {
                System.out.println("error!");
                return;
            }
        }
        if (!input[0].matches("^(([3][0-6])|([1-2][0-9])|([1-9]))") || !input[1].matches("[\\da-z]+(\\.[\\da-z]+)?") || !input[2].matches("^(([3][0-6])|([1-2][0-9])|([1-9]))")) {
            System.out.println("error!");
            return;
        }

        int fromBase = Integer.parseInt(input[0]);
        String num = input[1];
        int toBase = Integer.parseInt(input[2]);

        String[] parts = num.split("\\.");

        //convert integer to decimal
        int integerDec = 0;
        if (fromBase == 1) {
            integerDec = parts[0].length();
        } else {
            integerDec = Integer.parseInt(parts[0], fromBase);
        }

        //convert integer to new base
        String integerNewBase = "";
        if (toBase == 1) {
            integerNewBase = "1".repeat(integerDec);
        } else {
            integerNewBase = Integer.toString(integerDec, toBase);
        }

        StringBuilder sb = new StringBuilder(integerNewBase);
        if (parts.length == 2) {
            //convert fractional to decimal
            double fractionalDec = 0;
            char[] fractionalArr = parts[1].toCharArray();
            for (int i = 0; i < fractionalArr.length; i++) {
                char ch = fractionalArr[i];
                double val = Character.getNumericValue(ch);
                fractionalDec += val/Math.pow(fromBase, i+1);
            }

            //convert fractional to new base
            StringBuilder sbf = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                fractionalDec = fractionalDec * toBase;
                int integer = (int) fractionalDec;
                sbf.append(Character.forDigit(integer, toBase));
                fractionalDec = fractionalDec % integer;
            }
            sb.append(".");
            sb.append(sbf);
        }

        System.out.println(sb);

    }
}
