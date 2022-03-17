package cn.baisee.ftp.controller;

import cn.baisee.ftp.context.FileContext;
import cn.baisee.ftp.helper.FileConnParamLoadHelper;
import cn.baisee.ftp.model.SSHParamModel;
import cn.baisee.ftp.util.FtpUtils;
import com.jcraft.jsch.SftpException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * @Description: 用户控制器描述
 * @CreateDate: 2021/2/24 14:31
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Controller
public class FtpController {

    /**
     * 添加用户，含文件上传
     *
     * @param user 用户名，模拟添加用户
     * @param file headPic 必须唯一，不能与 user 中的属性重复
     * @return
     */
    @PostMapping("/userup")
    public String addUser(String user, @RequestParam("headPic") MultipartFile file) throws IOException {
        // 获取上传的文件流
        InputStream input = file.getInputStream();
        // 获取上传的文件名
        String fileName = file.getOriginalFilename();
        // 截取后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        // 使用 UUID 拼接后缀，定义一个不重复的文件名
        String finalName = UUID.randomUUID() + suffix;
        System.out.println(finalName);
        // 调用自定义的 FTP 工具类上传文件
        boolean flag = FtpUtils.uploadFile(finalName, input);
        System.out.println(user + "上传文件：" + flag);
        return "success";
    }
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping
    public String add(String account){


        return "login";
    }
    /**
     * 文件上传测试
     *
     * @return
     * @throws FileNotFoundException
     * @throws SftpException
     */
    @PostMapping("/upload")
    public String upload(@RequestParam("headPic") MultipartFile file) throws IOException, SftpException {
        InputStream inputStream1 = file.getInputStream();
        // 获取上传的文件名
        String fileName = file.getOriginalFilename();
        // 截取后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        // 使用 UUID 拼接后缀，定义一个不重复的文件名
        String finalName = UUID.randomUUID() + suffix;
        System.out.println(finalName);



        SSHParamModel paramModel = FileConnParamLoadHelper.getSshParamModel();
// 根据连接 model 去获取 session
        FileContext.getCurrentSession(paramModel);
// 上传
        FileContext.upload(paramModel, finalName,inputStream1 );

        return "success";
    }

}