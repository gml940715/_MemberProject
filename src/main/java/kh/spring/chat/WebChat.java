package kh.spring.chat;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/webchat", configurator=HttpSessionSetter.class)
public class WebChat {
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	
	String id = null;
	
	@OnOpen
	public void onOpen(Session session, EndpointConfig ec) {
		HttpSession hsession = (HttpSession)ec.getUserProperties().get("httpSession");// Map에서 꺼낸거 :sec.getUserProperties().put("httpSession", hsession);
		id = (String)hsession.getAttribute("loginId");
		
		clients.add(session); // 클라이언트가 접속하면 input outputStream등이 담아줌 set 에
		System.out.println("접속자 발생");
	}
	
	@OnMessage
	public void onMessage(String message, Session session)throws Exception{
		synchronized (clients) {
			for(Session each : clients) {
				if(each != session) {
					each.getBasicRemote().sendText("<div class='you'>"+id + ": " + message +"</div>");
				}else if(each == session) {
					each.getBasicRemote().sendText("<div class='me'>나: " + message +"</div>");
				}
			}
		}
	}
	
	@OnClose
	public void onClose(Session session) {
		clients.remove(session);
	}
}
