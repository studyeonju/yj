package com.example.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientThread extends Thread {
	Socket socket = null;
	BufferedReader br = null;
	PrintWriter out = null;
	Scanner scanner = new Scanner(System.in);
	public ClientThread(Socket socket){
		this.socket = socket;
//		try {
//		br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//		out = new PrintWriter(socket.getOutputStream());//소켓으로 데이터를 보내는 OutputStream 객체 생성 ㅋ클라이언트한테 보낸사용
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	@Override
	public void run() {
		// 최초1회는 client의 name을 서버에 전송
				PrintStream out;
				try {
					out = new PrintStream(socket.getOutputStream());
					out.println();
					out.flush();
					
				while (true) {
					String outputMsg = scanner.nextLine();
					out.println(outputMsg);
					out.flush();
					if("quit".equals(outputMsg)) break;
				}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
	}



}
