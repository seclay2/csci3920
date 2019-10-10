import java.util.Scanner;

public class SimpleThreadTest {

    private static class InputReader extends Thread{
        private String text ="";
        @Override
        public void run(){
            Scanner input = new Scanner(System.in);
            System.out.print("Input Text:");
            text = input.nextLine();
        }

        private String getText(){    return text;    }
    }


    public static void main(String[] args){

        long count = 11;
        long result = 1;

        InputReader thread = new InputReader();
        thread.start();

        while (thread.isAlive()) {
            result = count * count;
            count++;
        }
        System.out.printf(  "calculated squares up to %2d"+
                            " * %2d = %2d%n",count,count, result);
        System.out.println("while you typed: "+thread.getText());
    }
}

