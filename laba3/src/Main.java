import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int flag;
        int num;

        Scanner scanner = new Scanner(System.in);
        do{
            System.out.println("""
                    1 - Преобразование введенного выражения  и его проверка на корректность
                    2 - Вычисления выражения, записанного в прямой польской нотации и проверка его на корректность
                    3 - Вычисления выражения, записанного в обратной польской нотации и проверка его на корректность
                    """);
            System.out.print("Введите число от 1 до 3: ");
            num = scanner.nextInt();
            switch (num){
                case 1:
                    Expression simpeExpression = new Expression();
                    System.out.print("Введите выражение (без пробелов): ");
                    String expression = scanner.next();
                    if(simpeExpression.isValidExpression(expression)){
                        System.out.println("Выражение прошло проверку на корректность");
                        double result = simpeExpression.evaluateExpression(expression);
                        System.out.println("Результат вычисления выражения: " + result);
                    }
                    else{
                        System.out.println("Выражение не прошло проверку на корректность");
                    }
                    break;
                case 2:
                    Expression polishNotationExpression = new Expression();
                    System.out.print("Введите выражение в прямой польской нотации (c пробелами): ");
                    scanner.nextLine();
                    String expressionInPolishNotation = scanner.nextLine();
                    if(polishNotationExpression.isValidExpressionInPolishNotation(expressionInPolishNotation)){
                        System.out.println("Выражение прошло проверку на корректность");
                        double resultInPolishNotation = polishNotationExpression.evaluatePolishNotation(expressionInPolishNotation);
                        System.out.println("Результат вычисления выражения: " + resultInPolishNotation);
                    }
                    else{
                        System.out.println("Выражение не прошло проверку на корректность");
                    }
                    break;
                case 3:
                    Expression reversPolishNotationExpression = new Expression();
                    System.out.print("Введите выражение в обратной польской нотации (с пробелами): ");
                    scanner.nextLine();
                    String expressionInReversPolishNotation = scanner.nextLine();
                    if(reversPolishNotationExpression.isValidExpressionReversInPolishNotation(expressionInReversPolishNotation)) {
                        System.out.println("Выражение прошло проверку на корректность");
                        double resultInReversPolishNotation = reversPolishNotationExpression.evaluateReveresPolishNotation(expressionInReversPolishNotation);
                        System.out.println("Результат вычисления выражения: " + resultInReversPolishNotation);
                    }
                    else{
                        System.out.println("Выражение не прошло проверку на корректность");
                    }
                    break;
            }

            System.out.print("Если хотите продолжить выполнение программы нажмите 0, иначе 1: ");
            flag = scanner.nextInt();
        }while (flag==0);

    }
}