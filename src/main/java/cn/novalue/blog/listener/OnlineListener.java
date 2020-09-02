package cn.novalue.blog.listener;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author Wu Yangjie
 * @date 2020-08-31
 */
@Component
public class OnlineListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("create: " + se);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("destroyed: " + se);
    }
}
