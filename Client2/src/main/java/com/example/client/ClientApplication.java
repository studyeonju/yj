package com.example.client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ClientApplication {
	
	
	
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(ClientApplication.class, args);
		try {
			start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static void start() throws IOException {
		Socket socket = null;
		Scanner scanner;
		InputStream input = null;
		BufferedReader in = null;
		OutputStream output=null;
		PrintWriter writer;
		String response;
		try {
			socket = new Socket("localhost", 8888);
				// TODO Auto-generated catch block
			scanner = new Scanner(System.in);
			 // 소켓에서 데이터를 받아오는 InputStream 객체 생성
            input = socket.getInputStream();
            
            // 소켓으로 데이터를 보내는 OutputStream 객체 생성
            output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
            
            // 서버로 메시지 전송
            ClientThread ct = new ClientThread(socket);
            Thread thread = new Thread(ct);
            thread.start();
            // 서버로부터 받은 메시지 출력
           
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while(true) {
				System.out.println("로그인 id입력해주세여.");
		        String id = scanner.nextLine();
		        writer.println(id);
		        writer.flush();
		        response = in.readLine();
		        
			    System.out.println("Server response: " + response);
			}
			
            
            // 소켓 및 스트림 닫기
            
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			socket.close();
            input.close();
            output.close();
			
		}
	}
		   
	 
}
