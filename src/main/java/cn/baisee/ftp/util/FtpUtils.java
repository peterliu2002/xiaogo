package cn.baisee.ftp.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author xiaoliu
 * @ClassName FtpUtils
 * @date 2021/12/11 18:50
 */

public class FtpUtils {

    /* ftp服务器ip地址 */
    //192.168.197.128
    //192.168.43.79
    private static final String FTP_ADDRESS = "192.168.197.128";
    /* ftp服务器端口号 */
    private static final int FTP_PORT = 21;
    /* ftp服务器用户名 */
    private static final String FTP_USERNAME = "hyxd";
    /* ftp服务器密码 */
    private static final String FTP_PASSWORD = "113231083240";
    /* 附件路径 */
    private static final String FTP_BASEPATH = "/home/hyxd/files";

    /**
     * @param fileName 上传的文件的名称
     * @param input    文件以输入流形式
     * @return
     */
    public static boolean uploadFile(String fileName, InputStream input) {
        // success 记录上传成功与否
        boolean success = false;
        // 创建一个ftp客户端90
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            // 连接 FTP 服务器
            ftp.connect(FTP_ADDRESS, FTP_PORT);
            // 登录
            ftp.login(FTP_USERNAME, FTP_PASSWORD);
            reply = ftp.getReplyCode();
            // 如果没连接上，就断开服务器
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return false;
            }
            // 设置文件类型 二进制的文件
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            // 创建文件夹
            ftp.makeDirectory(FTP_BASEPATH);
            // 设置上传目录
            ftp.changeWorkingDirectory(FTP_BASEPATH);
            // ftp server可能每次开启不同的端口来传输数据，
            // 但是在linux上，由于安全限制，可能某些端口没有开启，所以就出现阻塞
            // 所以每次数据连接之前，ftp client告诉ftp server开通一个端口来传输数据
            ftp.enterLocalPassiveMode();
            // 存文件，返回值为true，则文件上传成功
            boolean b = ftp.storeFile(fileName, input);
            System.out.println(b);
            // 获取响应状态
            System.out.println(ftp.getReplyString());
            // 存完之后输入流关闭，ftp客户端注销
            input.close();
            ftp.logout();
            // 上传状态改为 true 上传成功
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        return success;
    }


}
