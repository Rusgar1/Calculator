import java.util.*;

public class Main {

    public static void main(String ... args) throws Exception {
        while (true){

            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите выражение в формате: a + b, a - b, a * b, a / b.");
            String equation = scanner.nextLine();
            String[] expression = equation.split("\\s+");

            if (expression.length != 3){
                scanner.close();
                throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }

            if (expression[1].length() != 1 || !isValidIdentifier(expression[1].toCharArray()[0])){
                scanner.close();
                throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }

            if (isArabianDigit(expression[0]) & isArabianDigit(expression[2]) & !isRomanDigit(expression[0]) & !isRomanDigit(expression[2])){
                int a = Integer.parseInt(expression[0]);
                int b = Integer.parseInt(expression[2]);
                char operator = expression[1].toCharArray()[0];
                if (((Double.parseDouble(expression[0]) < 1.0) | (Double.parseDouble(expression[0]) > 10.0)
                        | (Double.parseDouble(expression[2]) < 1.0) | (Double.parseDouble(expression[2]) > 10.0))) {
                    scanner.close();
                    throw new Exception("Используются только числа от 1 до 10");
                }

                System.out.println(Calculator.calculated(a, b, operator));
            } else if (isRomanDigit(expression[0]) & isRomanDigit(expression[2])){
                int a = Converter.romanToArabic(expression[0]);
                int b = Converter.romanToArabic(expression[2]);
                char operator = expression[1].toCharArray()[0];
                if (a < 1 | a > 10 | b < 1 | b > 10) {
                    scanner.close();
                    throw new Exception("Используются только числа от 1 до 10");
                }

                int result = (Calculator.calculated(a, b, operator));
                if (result < 1){
                    scanner.close();
                    throw new Exception("т.к. в римской системе нет отрицательных чисел");
                }

                String result1 = (Converter.arabicToRoman(Calculator.calculated(a, b, operator)));
                System.out.println(result1);
            } else if (!expression[0].matches(".") | !expression[2].matches(".")){
                scanner.close();
                throw new Exception("Используются только ЦЕЛЫЕ числа от 1 до 10");
            } else {
                scanner.close();
                throw new Exception("т.к. используются одновременно разные системы счисления");
            }
        }
    }

    static boolean isArabianDigit(String chunk){
        if (!chunk.matches("\\d+")){
            return false;
        }
        int value = Integer.parseInt(chunk);
        return value > 0 && value <= 10 ? true : false;
    }

    static boolean isRomanDigit(String chunk){
        return Converter.romanToArabic(chunk) != -1 ? true : false;
    }

    static boolean isValidIdentifier(char identifier){
        return identifier == '+' ||
                identifier == '-' ||
                identifier == '*' ||
                identifier == '/';
    }
}
