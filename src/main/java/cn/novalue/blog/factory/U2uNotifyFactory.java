package cn.novalue.blog.factory;

import cn.novalue.blog.handler.U2uNotifyHandler;
import cn.novalue.blog.model.enums.U2uNotifyType;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wu Yangjie
 * @date 2020-07-04
 */
@Component
public class U2uNotifyFactory implements ApplicationContextAware {
    private Map<U2uNotifyType, U2uNotifyHandler> u2uNotifyHandlerMap;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, U2uNotifyHandler> beansOfType = applicationContext.getBeansOfType(U2uNotifyHandler.class);
        u2uNotifyHandlerMap = new HashMap<>(beansOfType.size());
        beansOfType.forEach((k, v) -> {
            u2uNotifyHandlerMap.put(v.getHandlerType(), v);
        });
    }
    public U2uNotifyHandler getHandler(U2uNotifyType u2uNotifyType) {
        return u2uNotifyHandlerMap.get(u2uNotifyType);
    }
}
