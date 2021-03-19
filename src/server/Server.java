package server;
import java.net.*;
import java.io.*;

public class Server {
	
	private static double avrgArf,sqrDev;
	private static Console console;
	
	public static void main(String[] args)    {
	   console = System.console();
	   if (console!=null) {
       try {
    	   ServerSocket s = new ServerSocket(12345);
    	   System.out.println("Сервер запущено...");
          
           Socket socket = s.accept();
           System.out.println("Клієнт приєднався");
           
           BufferedReader in =
                   new BufferedReader(
                   new InputStreamReader(
                   socket.getInputStream()));

           PrintWriter out =
                   new PrintWriter(
                   new BufferedWriter(
                   new OutputStreamWriter(
                   socket.getOutputStream())), true);
           
           while(!socket.isClosed()){
           int n=Integer.parseInt(in.readLine());
           if(n==0) {
        	   break;
           }
           int arr[]=new int[n];
           for (int i =0;i<n;i++) {
        	   arr[i]=Integer.parseInt(in.readLine());
           }
           System.out.println("Від клієнта принято массив: ");
           for (int i =0;i<n;i++) {
              System.out.print(arr[i]+"   ");
           }
           System.out.println("");
           find(arr);
           out.println(avrgArf);
           out.println(sqrDev);
           }
           in.close();
           out.close();
           socket.close();
           s.close();
           System.out.println("Клієнт відєднався від серверу");
           console.readLine();
      } catch(IOException ex) {
    	  ex.printStackTrace();
	  }
	   }
   }
	
	
	public static void find(int[]arr) {
		double sum=0;
		for (int i=0;i<arr.length;i++) {
			sum+=arr[i];
		}
		avrgArf=(double)sum/arr.length;
		sum=0;
		for (int i=0;i<arr.length;i++) {
			sum=sum+(Math.pow((arr[i]-avrgArf),2));
		}
		sqrDev=Math.sqrt(sum/arr.length);
	}
	
}