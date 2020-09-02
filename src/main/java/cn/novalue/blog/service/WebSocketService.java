package cn.novalue.blog.service;

import cn.novalue.blog.config.WebSocketConfig;
import cn.novalue.blog.model.entity.User;
import cn.novalue.blog.security.entity.MyUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Wu Yangjie
 * @date 2020-09-01
 */
@Slf4j
@Component
@ServerEndpoint(value = "/websocket", configurator = WebSocketConfig.class)
public class WebSocketService {

    private static volatile Map<String, User> onlineUsers = new ConcurrentHashMap<>();
    private static final String SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";

    @OnOpen
    public void open(Session session, EndpointConfig config) throws IOException {
        System.out.println("open: " + session.getId());
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        if (!Objects.isNull(httpSession)) {
            SecurityContext context = (SecurityContext)httpSession.getAttribute(SECURITY_CONTEXT);
            onlineUsers.put(session.getId(), ((MyUserDetails)context.getAuthentication().getPrincipal()).getUser());
        }
        session.getBasicRemote().sendText("hello! I'm server");
    }
    @OnClose
    public void close(Session session) {
        System.out.println("close: " + session.getId());
        onlineUsers.remove(session.getId());
    }
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("收到：" + message);
    }
    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("错误");
        throwable.printStackTrace();
    }

    public Collection<User> getOnlineSet() {
        return onlineUsers.values();
    }
}
