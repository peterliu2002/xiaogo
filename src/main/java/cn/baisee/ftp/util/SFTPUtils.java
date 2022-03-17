package cn.baisee.ftp.util;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.*;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Properties;
import java.util.Vector;


/**
 * @author xiaoliu
 * @ClassName SFTPUtils
 * @date 2021/12/25 16:34
 */

public class SFTPUtils {
    /* 连接通道 */
    private ChannelSftp sftp;
    /* 连接 Session */
    private Session session;
    /* 用户名 */
    private String username;
    /* 密码 */
    private String password;

    /* ip地址 */
    private String host;
    /* 端口 */
    private int port;

    /**
     * 构造基于密码认证的sftp对象
     *
     * @param username
     * @param password
     * @param host
     * @param port
     */
    public SFTPUtils(String username, String password, String host, int port) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
    }



    /**
     * 无参构造
     */
    public SFTPUtils() {
    }

    /**
     * 连接sftp服务器
     */
    public void login() {
        try {
            JSch jsch = new JSch();
//            if (privateKey != null) {
//                jsch.addIdentity(privateKey);// 设置私钥
//            }
            session = jsch.getSession(username, host, port);
            if (password != null) {
                session.setPassword(password);
            }
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接 server
     */
    public void logout() {
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.disconnect();
            }
        }
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
            }
        }
    }

    /**
     * 将输入流的数据上传到sftp作为文件。文件完整路径=basePath+directory
     *
     * @param basePath     服务器的基础路径
     * @param directory    上传到该目录
     * @param sftpFileName sftp端文件名
     * @param input        输入流
     */
    public void upload(String basePath, String directory, String sftpFileName, InputStream input) throws SftpException {
        try {
            sftp.cd(basePath);
            sftp.cd(directory);
        } catch (SftpException e) {
            //目录不存在，则创建文件夹
            String[] dirs = directory.split("/");
            String tempPath = basePath;
            for (String dir : dirs) {
                if (null == dir || "".equals(dir)) continue;
                tempPath += "/" + dir;
                try {
                    sftp.cd(tempPath);
                } catch (SftpException ex) {
                    sftp.mkdir(tempPath);
                    sftp.cd(tempPath);
                }
            }
        }
        sftp.put(input, sftpFileName);  //上传文件
    }




    /**
     * 简单测试
     *
     * @param args
     * @throws SftpException
     * @throws IOException
     */
    public static void main(String[] args) throws SftpException, IOException {
        SFTPUtils sftp = new SFTPUtils("hyxd", "113231083240", "192.168.197.128", 22);
        sftp.login();
        //上传文件测试
        File file = new File("D:\\comprehensive\\zp\\图片\\头像\\20211217142329.jpg");
        InputStream is = new FileInputStream(file);
        sftp.upload("/sftp/hyxd/", "upload/a", "多喝热水", is);
        sftp.logout();
    }






}
