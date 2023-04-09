package com.example.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
		try {
			start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 public static void start() throws InterruptedException {
		   try {
	        	  Socket socket = new Socket("localhost", 8888);
	              Scanner scanner = new Scanner(System.in);
	              // 소켓에서 데이터를 받아오는 InputStream 객체 생성
	              InputStream input = socket.getInputStream();
	              BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	              
	              // 소켓으로 데이터를 보내는 OutputStream 객체 생성
	              OutputStream output = socket.getOutputStream();
	              PrintWriter writer = new PrintWriter(output, true);
	              
	              // 서버로 메시지 전송 ->thread 
	              System.out.println("로그인 id입력해주세여.");
	              String id = scanner.nextLine();
	              writer.println(id);
	              
	              // 서버로부터 받은 메시지 출력 ->thread로..
	              String response = reader.readLine();
	              System.out.println("Server response: " + response);
	              
	              // 소켓 및 스트림 닫기
	              socket.close();
	              input.close();
	              output.close();
	                 
	          } catch (Exception e) {
	                 System.out.println("client err:" + e);
	          }
	   
	 }
}
