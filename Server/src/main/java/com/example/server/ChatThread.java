package com.example.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;


public class ChatThread extends Thread{
	
	ServerServiceImpl serverService = new  ServerServiceImpl();
	
	Socket socket = null;
	BufferedReader in = null;
	PrintWriter out = null;
	
			
	public ChatThread (Socket socket) {
		this.socket = socket;
		try {
			out = new PrintWriter(socket.getOutputStream());//소켓으로 데이터를 보내는 OutputStream 객체 생성 ㅋ클라이언트한테 보낸사용
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));// 소켓에서 클라이언트에서 받을려고 사용 InputStreamReader사용
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	@Override
	public void run() {
		String id = "";
		try {
			id = in.readLine();
			
			int cnt;
			try {
				cnt = serverService.selectId(id);
				if(cnt>0) {
					System.out.println("[" + id + " 새연결생성]");
				}else {
					System.out.println("아이디가 없습니다.");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//db
			
//			
//			while (in != null) {
//				String inputMsg = in.readLine();
//			}
		} catch (IOException e) {
			System.out.println("[" + id + " 접속끊김]");
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("[" + id + " 연결종료]");
	}
	
}
