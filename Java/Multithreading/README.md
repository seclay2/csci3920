# Multithreading Examples using JAVA

## Simple Thread Class   
Example to demonstrate a single thread class. 
The thread reads an input from the user while the main class computes squares. 
Extracted from Java, How to Program book by Deitel-Deitel. 

## Print Task
An example using Executor and Runnable objects.
The thread displays a initial message and sleeps for a random time and finally shows a message when done.
Extracted from Java, How to Program book by Deitel-Deitel. 

## Library Server and Client - Multithread
Contains a multi-threaded server that controls the library. Also contains the JavaFX Client. This extends the solution form homework 4 to allow several clients connected at the same time.

It should be take into consideration at what level to make the methods or block ```synchronized```. In this example, the ```processClientMessage``` method is ```synchronized```.
