import java.util.Objects;


public class Stack {
    private final int maxSize;
    private double[] stackDoubleArray;
    private char[] stackCharArray;
    private int top;

    public Stack(int size,String type){
        this.maxSize = size;
        if(Objects.equals(type, "double")){
            this.stackDoubleArray = new double[maxSize];
        }
        else if(Objects.equals(type,"char")){
            this.stackCharArray = new char[maxSize];
        }
        this.top = -1;
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull(){
        return (top==maxSize-1);
    }

    public void push(double value){
        if (isFull()){
            System.out.println("Стек полон.Невозможно добавить элемент");
        }
        else {
            top++;
            stackDoubleArray[top] = value;
        }
    }

    public void push(char value){
        if (isFull()){
            System.out.println("Стек полон.Невозможно добавить элемент");
        }
        else {
            top++;
            stackCharArray[top] = value;
        }
    }

    public double popOutDoubleArray(){
        if(isEmpty()){
            System.out.println("Стек пуст. Невозможно извлечь элемент из пустого стека");
            return -1;
        }
        else{
            double value = stackDoubleArray[top];
            top--;
            return value;
        }
    }

    public char popOutCharArray(){
        if(isEmpty()){
            System.out.println("Стек пуст. Невозможно извлечь элемент из пустого стека");
            return '0';
        }
        else{
            char value = stackCharArray[top];
            top--;
            return value;
        }
    }


    public void showCharArray(){
        for(int i = 0;i<=top;i++){
            System.out.print(stackCharArray[i] + " ");
        }
    }

    public double peekOutDoubleArray(){
        if(!isEmpty()){
            return stackDoubleArray[top];
        }
        else{
            System.out.println("Стек пуст");
            return -1;
        }
    }

    public char peekOutCharArray(){
        if(!isEmpty()){
            return stackCharArray[top];
        }
        else{
            System.out.println("Стек пуст");
            return '0';
        }
    }

    public int size(){
        return (top+1);
    }

}
