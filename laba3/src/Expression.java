import static java.lang.Math.pow;

public class Expression {
    public double evaluateExpression(String expression){
        Stack operands = new Stack(100,"double");
        Stack operators = new Stack(100,"char");


        for (int i = 0;i<expression.length();i++){
            char c = expression.charAt(i);
            if(Character.isDigit(c)){
                int num = 0;
                int res = 0;
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    num = num * 10 + (expression.charAt(i) - '0');
                    i++;
                }
                i--;
                res += num;
                operands.push(res);
            }
            else if(c=='('){
                operators.push(c);
            }
            else if(c == ')'){
                while (operators.peekOutCharArray()!='('){
                    operands.push(applyOperator(operators.popOutCharArray(),operands.popOutDoubleArray(),operands.popOutDoubleArray()));
                }
                operators.popOutCharArray();
            }
            else if(c == '+' || c == '-' || c == '*' || c == '/'){
                while (!operators.isEmpty() && precedence(c) <= precedence(operators.peekOutCharArray())){
                    operands.push(applyOperator(operators.popOutCharArray(),operands.popOutDoubleArray(),operands.popOutDoubleArray()));
                }
                operators.push(c);
            }
        }
        while (!operators.isEmpty()) {
            operands.push(applyOperator(operators.popOutCharArray(), operands.popOutDoubleArray(), operands.popOutDoubleArray()));
        }

        return operands.popOutDoubleArray();
    }

    public double applyOperator(char operator, double value2, double value1){
        return switch (operator) {
            case '+' -> value1 + value2;
            case '-' -> value1 - value2;
            case '*' -> value1 * value2;
            case '/' -> {
                if (value2 == 0) throw new ArithmeticException("Деление на ноль");
                yield value1 / value2;
            }
            default -> 0;
        };
    }

    public int precedence(char c){
        if(c == '+' || c == '-'){
            return 1;
        }
        else if (c == '*' || c == '/'){
            return 2;
        }
        return 0;
    }

    public boolean isValidExpression(String expression){
        int countParentheses = 0;
        boolean lastWasOperator = true;

        for (char c: expression.toCharArray()) {
            if (c == '(') {
                countParentheses++;
                lastWasOperator = true;
            } else if (c == ')') {
                countParentheses--;
                if (countParentheses < 0 || lastWasOperator) {
                    return false;
                }
                lastWasOperator = false;
            } else if (c == '-' || c == '+' || c == '/' || c == '*') {
                if (lastWasOperator) {
                    return false;
                }
                lastWasOperator = true;
            } else if (!Character.isDigit(c)) {
                return false;
            } else {
                lastWasOperator = false;
            }
        }
        return countParentheses == 0 && !lastWasOperator;
    }

    public double evaluatePolishNotation(String expression){
        Stack stack = new Stack(100, "double");
        char[] tokens = expression.toCharArray();

        for (int i = tokens.length-1;i>=0;i--){
            char token = tokens[i];
            if(Character.isDigit(token)){
                int count = -1;
                for(int j = i;j>=0 && Character.isDigit(expression.charAt(j));j--){
                    count++;
                }
                double num = 0;
                int[] numbers = new int[count+1];
                while(i>=0 && Character.isDigit(expression.charAt(i))){
                    numbers[count] = (expression.charAt(i) - '0');
                    count--;
                    i--;
                }
                i++;
                for(int l = numbers.length-1,step = 0;l>=0;l--){
                    num += numbers[l]*pow(10,step);
                    step++;
                }
                stack.push(num);
            }
            else if(Character.isWhitespace(token)){

            }
            else {
                double operand2 = stack.popOutDoubleArray();
                double operand1 = stack.popOutDoubleArray();
                switch (token){
                    case '+':
                        stack.push(operand1+operand2);
                        break;
                    case '-':
                        stack.push(operand1-operand2);
                        break;
                    case '*':
                        stack.push(operand1*operand2);
                        break;
                    case '/':
                        stack.push(operand1/operand2);
                        break;
                }
            }
        }
        return stack.popOutDoubleArray();
    }

    public double evaluateReveresPolishNotation(String expression){
        Stack stack = new Stack(100, "double");
        char[] tokens = expression.toCharArray();

        for (int i = 0;i < tokens.length;i++){
            char token = tokens[i];
            if(Character.isDigit(token)){
                int count = -1;
                for(int j = i;j<tokens.length && Character.isDigit(expression.charAt(j));j++){
                    count++;
                }
                double num = 0;
                int[] numbers = new int[count+1];
                int index = 0;
                while(i<tokens.length && Character.isDigit(expression.charAt(i))){
                    numbers[index] = (expression.charAt(i) - '0');
                    index++;
                    count--;
                    i++;
                }
                i--;
                for(int l = numbers.length-1,step = 0;l>=0;l--){
                    num += numbers[l]*pow(10,step);
                    step++;
                }
                stack.push(num);
            }
            else if(Character.isWhitespace(token)){}

            else {
                double operand1 = stack.popOutDoubleArray();
                double operand2 = stack.popOutDoubleArray();
                switch (token){
                    case '+':
                        stack.push(operand2+operand1);
                        break;
                    case '-':
                        stack.push(operand2-operand1);
                        break;
                    case '*':
                        stack.push(operand2*operand1);
                        break;
                    case '/':
                        stack.push(operand2/operand1);
                        break;
                }
            }
        }
        return stack.popOutDoubleArray();
    }

    public boolean isValidExpressionInPolishNotation(String expression){
        Stack stack = new Stack(100,"double");
        char[] tokens = expression.toCharArray();

        for (int i = tokens.length-1;i>=0;i--) {
            if (isOperator(tokens[i])) {
                if (stack.size() < 2) {
                    return false;
                }
                stack.popOutDoubleArray();
            } else if (Character.isDigit(tokens[i])) {
                while (i>=0 && Character.isDigit(tokens[i])) {
                    i--;
                }
                i++;
                stack.push((double) 1);
            } else if (Character.isWhitespace(tokens[i])) {

            } else {
                return false;
            }

        }
        return stack.size() == 1;
    }

    public boolean isValidExpressionReversInPolishNotation(String expression){
        Stack stack = new Stack(100,"double");
        char[] tokens = expression.toCharArray();

        for (int i = 0;i< tokens.length;i++){
            if(isOperator(tokens[i])){
                if(stack.size()<2){
                    return false;
                }
                stack.popOutDoubleArray();
            }
            else if(Character.isDigit(tokens[i])){
                while (i<tokens.length && Character.isDigit(tokens[i])){
                    i++;
                }
                i--;
                stack.push((double) 1);
            }
            else if (Character.isWhitespace(tokens[i])){

            }
            else {
                return false;
            }

        }
        return stack.size() == 1;
    }

    public boolean isOperator(char token){
        return token =='+' || token =='-' || token == '/' || token == '*';
    }
}
