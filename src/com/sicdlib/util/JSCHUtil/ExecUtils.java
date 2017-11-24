package com.sicdlib.util.JSCHUtil;

/**
 * Created by haoyang on 2017/4/26.
 */
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class ExecUtils {
    private static ExecUtils instance;

    public static ExecUtils getInstance() {
        if (instance == null) {
            instance = new ExecUtils();
        }
        return instance;
    }

    private ExecUtils() {

    }

    private Session getSession(String host, int port, String ueseName)
            throws Exception {
        JSch jsch = new JSch();
        Session session = jsch.getSession(ueseName, host, port);
        return session;
    }

    public Session connect(Map<String, String> sftpDetails) throws Exception {
        String host = sftpDetails.get(JSCHConstants.SFTP_REQ_HOST);
        String s_port = sftpDetails.get(JSCHConstants.SFTP_REQ_PORT);
        String userName = sftpDetails.get(JSCHConstants.SFTP_REQ_USERNAME);
        String password = sftpDetails.get(JSCHConstants.SFTP_REQ_PASSWORD);

        int port = JSCHConstants.SFTP_DEFAULT_PORT;

        if (s_port != null && !s_port.equals("")) {
            port = Integer.valueOf(s_port);
        }

        Session session = getSession(host, port, userName);
        session.setPassword(password);
        Properties config = new Properties();
        config.setProperty("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.connect();
        return session;
    }

    public String execCmd(Session session, String command) throws Exception {
        if (session == null) {
            throw new RuntimeException("Session is null!");
        }
        ChannelExec exec = (ChannelExec) session.openChannel("exec");
//        exec.setPty(true);
        InputStream in = exec.getInputStream();
        byte[] b = new byte[1024];

        exec.setCommand(command);
        exec.connect();
        StringBuffer buffer = new StringBuffer();
        while (in.read(b) > 0) {
            buffer.append(new String(b));
        }
        exec.disconnect();

        return buffer.toString();
    }

    public void clear(Session session) {
        if (session != null && session.isConnected()) {
            session.disconnect();
            session = null;
        }
    }
}
