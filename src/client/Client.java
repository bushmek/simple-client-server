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
        	System.out.println("������ Ip");
        	String ip = console.readLine();
        	try {
        	socket = new Socket(ip, 12345);
        	}catch(IOException e) {
        		System.out.println("�� ������� ���������� �� �������");
        	}
        	System.out.println("������ ��������� �� �������");
        	try {
        		
             in = new BufferedReader(
                    new InputStreamReader(
                    socket.getInputStream()));
            
             out = new PrintWriter(
                    new BufferedWriter(
                    new OutputStreamWriter(
                    socket.getOutputStream())), true);
             
        	}catch(Exception e) {
        		System.out.println("�� ������� �������� ������ ��� �����/������");
        	}
        	while(!socket.isOutputShutdown()) {
            System.out.println("������ ��������� ������ ( ��� ���������� ������ ������ 0 ): ");
         	int n = Integer.parseInt(console.readLine());
         	if(n==0) {
         		 System.out.println("����� � ��������... ");
         		 break;
         	}
            out.println(n);
            System.out.println("������ �������� ������: ");
            for (int i=0;i<n;i++) {
          	  int r=Integer.parseInt(console.readLine());
          	  out.println(r);
            }
            System.out.println("������ ������� �� �������... ");
            String res = in.readLine();
            String res2 = in.readLine();
            System.out.println("������ ����������� = "+res+" ����������� ��������� = "+res2);
        	}
            socket.close();
            in.close();
            out.close();
            console.readLine();
        
       }
    }
}