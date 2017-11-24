package com.sicdlib.util.JSCHUtil; /**
 * Created by haoyang on 2017/4/19.
 */
import com.jcraft.jsch.*;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.Properties;


public class SFTPChannel extends ComChannel{
    Session session = null;
    Channel channel = null;

    private static final Logger LOG = Logger.getLogger(SFTPChannel.class.getName());

    public Channel getChannel(Map<String, String> sftpDetails, int timeout) throws JSchException {
        String ftpHost = sftpDetails.get(JSCHConstants.SFTP_REQ_HOST);
        String port = sftpDetails.get(JSCHConstants.SFTP_REQ_PORT);
        String ftpUserName = sftpDetails.get(JSCHConstants.SFTP_REQ_USERNAME);
        String ftpPassword = sftpDetails.get(JSCHConstants.SFTP_REQ_PASSWORD);

        int ftpPort = JSCHConstants.SFTP_DEFAULT_PORT;

        if (port != null && !port.equals("")) {
            ftpPort = Integer.valueOf(port);
        }

        JSch jsch = new JSch(); // 创建JSch对象
        session = jsch.getSession(ftpUserName, ftpHost, ftpPort); // 根据用户名，主机ip，端口获取一个Session对象
        LOG.debug("Session created.");

        if (ftpPassword != null) {
            session.setPassword(ftpPassword); // 设置密码
        }

        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");

        session.setConfig(config); // 为Session对象设置properties
        session.setTimeout(timeout); // 设置timeout时间
        session.connect(); // 通过Session建立链接

        LOG.debug("Session connected.");
        LOG.debug("Opening Channel.");

        channel = session.openChannel(JSCHConstants.SFTP_TYPE); // 打开SFTP通道
        channel.connect(); // 建立SFTP通道的连接

        LOG.debug("Connected successfully to Host = " + ftpHost + ",as UserName = " + ftpUserName
                + ", returning: " + channel);

        return channel;
    }

    public void closeChannel() throws Exception {
        if (channel != null) {
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
    }
}
