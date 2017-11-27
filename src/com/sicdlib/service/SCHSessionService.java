package com.sicdlib.service;

import com.jcraft.jsch.Session;

import com.sicdlib.entity.Constant;
import com.sicdlib.util.JSCHUtil.ExecUtils;
import com.sicdlib.util.JSCHUtil.JSCHConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YH on 2017/5/9.
 */
@Service("schSessionService")
public class SCHSessionService  {
    @Value("${sftp.port}")
    private String port;

    @Value("${sftp.username}")
    private String userName;

    @Value("${sftp.passwd}")
    private String password;

    @Value("${sftp.host}")
    private String host;

    @Value("${sftp.vm01_host}")
    private String vm01_host;

    @Value("${sftp.vm02_host}")
    private String vm02_host;

    @Value("${sftp.vm03_host}")
    private String vm03_host;

    @Value("${sftp.vm04_host}")
    private String vm04_host;

    @Value("${sftp.vm05_host}")
    private String vm05_host;

    @Value("${sftp.vm06_host}")
    private String vm06_host;

    @Value("${sftp.vm07_host}")
    private String vm07_host;

    @Value("${sftp.vm08_host}")
    private String vm08_host;

    @Value("${sftp.timeout}")
    private int timeout;


    public Session getSession() throws Exception {
        Map<String, String> sftpDetails = new HashMap<String, String>();
        // 设置主机ip，端口，用户名，密码
        sftpDetails.put(JSCHConstants.SFTP_REQ_HOST, host);
        sftpDetails.put(JSCHConstants.SFTP_REQ_USERNAME, userName);
        sftpDetails.put(JSCHConstants.SFTP_REQ_PASSWORD, password);
        sftpDetails.put(JSCHConstants.SFTP_REQ_PORT, port);

        Session execSession = ExecUtils.getInstance().connect(sftpDetails);

        return execSession;
    }

    public Session getSession(Constant.VM vm) throws Exception {
        Map<String, String> sftpDetails = new HashMap<String, String>();
        // 设置主机ip，端口，用户名，密码
        sftpDetails.put(JSCHConstants.SFTP_REQ_USERNAME, userName);
        sftpDetails.put(JSCHConstants.SFTP_REQ_PASSWORD, password);
        sftpDetails.put(JSCHConstants.SFTP_REQ_PORT, port);

        switch (vm) {
            case VM00:
                sftpDetails.put(JSCHConstants.SFTP_REQ_HOST, host);
                break;
            case VM01:
                sftpDetails.put(JSCHConstants.SFTP_REQ_HOST, vm01_host);
                break;
            case VM02:
                sftpDetails.put(JSCHConstants.SFTP_REQ_HOST, vm02_host);
                break;
            case VM03:
                sftpDetails.put(JSCHConstants.SFTP_REQ_HOST, vm03_host);
                break;
            case VM04:
                sftpDetails.put(JSCHConstants.SFTP_REQ_HOST, vm04_host);
                break;
            case VM05:
                sftpDetails.put(JSCHConstants.SFTP_REQ_HOST, vm05_host);
                break;
            case VM06:
                sftpDetails.put(JSCHConstants.SFTP_REQ_HOST, vm06_host);
                break;
            case VM07:
                sftpDetails.put(JSCHConstants.SFTP_REQ_HOST, vm07_host);
                break;
            case VM08:
                sftpDetails.put(JSCHConstants.SFTP_REQ_HOST, vm08_host);
                break;
        }

        Session execSession = ExecUtils.getInstance().connect(sftpDetails);

        return execSession;
    }


    public int getTimeout() {
        return timeout;
    }


    public Map<String, String> getSftpDetail() {
        Map<String, String> sftpDetails = new HashMap<String, String>();
        // 设置主机ip，端口，用户名，密码
        sftpDetails.put(JSCHConstants.SFTP_REQ_HOST, host);
        sftpDetails.put(JSCHConstants.SFTP_REQ_USERNAME, userName);
        sftpDetails.put(JSCHConstants.SFTP_REQ_PASSWORD, password);
        sftpDetails.put(JSCHConstants.SFTP_REQ_PORT, port);

        return sftpDetails;
    }


}
