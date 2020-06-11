package cn.novalue.blog.service;


import java.util.Map;

public interface MailService {
    /**
     * 发送简单文本邮件
     *
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     */
    void sendSimpleMail(String to, String subject, String content);

    /**
     * 发送模板邮件
     *
     * @param to 收件人
     * @param subject 主题
     * @param content 往模板中传递的参数
     * @param templateFilename 模板文件名
     * @param attachFilename 附件文件名
     */
    void sendTemplateMail(String to, String subject, Map<String, Object> content, String templateFilename, String attachFilename);
}
