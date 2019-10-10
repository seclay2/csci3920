// Fig. 23.3: PrintTask.java
// PrintTask class sleeps for a random time from 0 to 5 seconds
import java.security.SecureRandom;

public class PrintTask implements Runnable {
   private static final SecureRandom generator = new SecureRandom();
   private final int sleepTime; // random sleep time for thread
   private final String taskName;

   public PrintTask(String taskName) {// constructor
      this.taskName = taskName;
        
      // pick random sleep time between 0 and 5 seconds
      sleepTime = generator.nextInt(5000);
   } 

   @Override
   public void run() { //contains the code that a thread will execute
      try { // put thread to sleep for sleepTime amount of time
         System.out.printf("%s going to sleep for %d milliseconds.%n", 
            taskName, sleepTime);
         Thread.sleep(sleepTime); // put thread to sleep

         System.out.printf("%s done sleeping%n", taskName);// print task name
      }
      catch (InterruptedException exception) {
         exception.printStackTrace();
         Thread.currentThread().interrupt(); // re-interrupt the thread
         System.out.printf("%s interrupted%n", taskName);
      }
   }
} 


