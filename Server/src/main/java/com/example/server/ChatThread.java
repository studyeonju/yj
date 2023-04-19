package com.example.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatThread extends Thread {

	static List<PrintWriter> list = Collections.synchronizedList(new ArrayList<PrintWriter>());

	Socket socket = null;
	BufferedReader in = null;
	PrintWriter out = null;

	public ChatThread(Socket socket) {
		this.socket = socket;
		try {
			out = new PrintWriter(socket.getOutputStream());// 소켓으로 데이터를 보내는 OutputStream 객체 생성 ㅋ클라이언트한테 보낸사용
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));// 소켓에서 클라이언트에서 받을려고 사용
																					// InputStreamReader사용
			list.add(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		String id = "";
		try {
//			boolean flag = false;
//			id = in.readLine();
//			String[] arr = {"byj7140"};
//			for(int i=0;i<arr.length;i++) {
//				if(arr[i].equals(id)){
//					flag = true;
//				}
//			}
			id = in.readLine();
			System.out.println("[" + id + " 새연결생성]");
			sendAll(id + "입장");
			while (id != null) {
				String inputMsg = in.readLine();
				if ("quit".equals(inputMsg))
					break;
				sendAll(id + ">>" + inputMsg);
			}

		} catch (IOException e) {
			System.out.println("[" + id + " 접속끊김]");
		} finally {
			sendAll(id + "님이 나가셨습니다.");
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("[" + id + " 연결종료]");
	}

	private void sendAll(String s) {
		for (PrintWriter out : list) {
			out.println(s);
			out.flush();
		}
	}
}
