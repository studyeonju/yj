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
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String url = "jdbc:oracle:thin:@172.30.1.19:1521:xe";
//		String id = "yeonju";
//		String  password = "sa810500";
//		
//		Connection conn;
//		try {
//			conn = DriverManager.getConnection(url,id,password);
//			String sql = "select count(*) from tb_members where id = ?  ";
//			PreparedStatement psmt = conn.prepareStatement(sql);
//			psmt.setString(1, "byj7140");
//			
//			boolean result = psmt.execute();
//			System.out.println(result);
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
		//db조회 
 		start();
	}
	
	public static void start() { 
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(8888);
			 // 서버 소켓 생성
			while(true) {
				System.out.println("클라이언트 접속 기다리기");
	            socket = serverSocket.accept();
	            System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());
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
