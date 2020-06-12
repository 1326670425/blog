package cn.novalue.blog.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * JSON工具类
 *
 * @author Wu Yangjie
 * @date 2020-03-03
 */
public class JSONUtils {
    private static final ObjectMapper DEFAULT_MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());
    public static String objectToJSON(Object obj) throws JsonProcessingException {
        return DEFAULT_MAPPER.writeValueAsString(obj);
    }
}
