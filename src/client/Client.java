package client;

import java.net.*;
import java.io.*;
import java.util.*;

public class Client {
	private static Socket socket;
	private static BufferedReader in;
	private static PrintWriter out;
	private static Console console;
	
	
	public static void main(String[] args) throws IOException {
        console = System.console();
        if(console!=null) {
        	System.out.println("Введіть Ip");
        	String ip = console.readLine();
        	try {
        	socket = new Socket(ip, 12345);
        	}catch(IOException e) {
        		System.out.println("Не вдалося приєднатися до серверу");
        	}
        	System.out.println("Успішно підключено до серверу");
        	try {
        		
             in = new BufferedReader(
                    new InputStreamReader(
                    socket.getInputStream()));
            
             out = new PrintWriter(
                    new BufferedWriter(
                    new OutputStreamWriter(
                    socket.getOutputStream())), true);
             
        	}catch(Exception e) {
        		System.out.println("Не вдалося створити потоки для вводу/виводу");
        	}
        	while(!socket.isOutputShutdown()) {
            System.out.println("Введіть розмірність масиву ( для завершення роботи введіть 0 ): ");
         	int n = Integer.parseInt(console.readLine());
         	if(n==0) {
         		 System.out.println("Вихід з програми... ");
         		 break;
         	}
            out.println(n);
            System.out.println("Введіть елементи масиву: ");
            for (int i=0;i<n;i++) {
          	  int r=Integer.parseInt(console.readLine());
          	  out.println(r);
            }
            System.out.println("Чекаємо відповідь від сервера... ");
            String res = in.readLine();
            String res2 = in.readLine();
            System.out.println("Середнє арифметичне = "+res+" Квадратичне відхилення = "+res2);
        	}
            socket.close();
            in.close();
            out.close();
            console.readLine();
        
       }
    }
}