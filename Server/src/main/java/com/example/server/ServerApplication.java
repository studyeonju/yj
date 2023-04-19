package com.example.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.example.server"})
public class ServerApplication{
	
	@Autowired
	ServerService serverService;
	
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
 		start();
	}
	
	public static void start() { 
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(8888);
			System.out.println("서버실행");
			 // 서버 소켓 생성
			while(true) {
	            socket = serverSocket.accept();
	            System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());
	            //클라이언트가 접속할때마다 스레드 생성 
	            ChatThread thread = new ChatThread(socket);
				thread.start();
			}
            
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(serverSocket!=null) {
				try {
					serverSocket.close();
					System.out.println("서버종료");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
