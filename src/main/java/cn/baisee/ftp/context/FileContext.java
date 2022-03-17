package cn.baisee.ftp.context;

import cn.baisee.ftp.model.SSHParamModel;
import com.jcraft.jsch.*;
import lombok.Data;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiaoliu
 * @ClassName FileContext
 * @date 2021/12/28 11:31
 */
@Data
public class FileContext {
    /* 连接 Session */
    private static Session session;
    /* 连接通道 */
    private static ChannelSftp sftp;
    private static ConcurrentHashMap<SSHParamModel, Session> sshSessionPool = new ConcurrentHashMap<>();

    public FileContext(SSHParamModel sshParamModel) {
        getSession(sshParamModel);
        sshSessionPool.put(sshParamModel, session);
    }

    public static void getCurrentSession(SSHParamModel sshParamModel) {
        synchronized (FileContext.class) {


            Session session = sshSessionPool.get(sshParamModel);
            if (session == null || session.isConnected()) {
                getSession(sshParamModel);
                sshSessionPool.put(sshParamModel, session);
            }
        }

    }

    public static void getSession(SSHParamModel sshParamModel) {
        JSch jSch = new JSch();
        try {
            session = jSch.getSession(sshParamModel.getUsername(), sshParamModel.getHost(), sshParamModel.getPort());
            if (sshParamModel.getPassword() != null) {
                session.setPassword(sshParamModel.getPassword());
            }
            Properties properties = new Properties();
            properties.put("StrictHostKeyChecking", "on");
            session.setConfig(properties);
            session.connect();
            Channel csftp = session.openChannel("sftp");
            csftp.connect();
            sftp = (ChannelSftp) csftp;
        } catch (JSchException e) {
            e.printStackTrace();
        }


    }


    public static void upload(SSHParamModel sshParamModel, String filename, InputStream io) throws SftpException {
        String bassPath = sshParamModel.getBassPath();
        String director = sshParamModel.getDirectory();
        try {
            sftp.cd(bassPath);

            sftp.cd(director);
        } catch (SftpException e) {
            //目录不存在，则创建文件夹
            String[] dirs = director.split("/");
            String tempPath = bassPath;
            for (String dir : dirs) {
                if (null == dir || "".equals(dir)) continue;
                tempPath += "/" + dir;
                try {
                    sftp.cd(tempPath);
                } catch (SftpException ex) {
                    try {
                        sftp.mkdir(tempPath);
                        sftp.cd(tempPath);
                    } catch (SftpException sftpException) {
                        sftpException.printStackTrace();
                    }

                }
            }


        }
        sftp.put(io, filename);

    }

}
