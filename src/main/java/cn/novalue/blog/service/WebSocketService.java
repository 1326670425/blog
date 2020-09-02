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
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Wu Yangjie
 * @date 2020-09-01
 */
@Slf4j
@Component
@ServerEndpoint(value = "/websocket", configurator = WebSocketConfig.class)
public class WebSocketService {

    private volatile Set<User> onlineSet = ConcurrentHashMap.newKeySet();
    private static final String SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";

    @OnOpen
    public void open(Session session, EndpointConfig config) throws IOException {
        
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        if (!Objects.isNull(httpSession)) {
            SecurityContext context = (SecurityContext)httpSession.getAttribute(SECURITY_CONTEXT);
            onlineSet.add(((MyUserDetails)context.getAuthentication().getPrincipal()).getUser());
        }

    }
    @OnClose
    public void close(Session session, EndpointConfig config) {
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        if (!Objects.isNull(httpSession)) {
            SecurityContext context = (SecurityContext)httpSession.getAttribute(SECURITY_CONTEXT);
            onlineSet.remove(((MyUserDetails)context.getAuthentication().getPrincipal()).getUser());
        }
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

    public Set<User> getOnlineSet() {
        return this.onlineSet;
    }
}
