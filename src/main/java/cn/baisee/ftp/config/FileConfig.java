package cn.baisee.ftp.config;

import cn.baisee.ftp.context.FileContext;
import cn.baisee.ftp.helper.FileConnParamLoadHelper;
import cn.baisee.ftp.model.SSHParamModel;
import com.jcraft.jsch.SftpException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author xiaoliu
 * @ClassName FileConfig
 * @date 2021/12/28 11:37
 */
@Configuration
public class FileConfig {


    /**
     * 初始化 SSHParamModel 对象到容器中
     *
     * @return
     */
    @Bean
    @ConfigurationProperties("file.connect.sftp.param")
    public SSHParamModel getSSHParamModel() {
        return new SSHParamModel();
    }

    /**
     * 初始化 FileConnParamLoadHelper 对象到容器中
     *
     * @return
     */
    @Bean
    public FileConnParamLoadHelper getFileConnParamLoadHelperInstance() {
        return new FileConnParamLoadHelper(getSSHParamModel());
    }

    /**
     * 初始化 FileContext 对象到容器中
     *
     * @return
     */
    @Bean
    public FileContext getFileContextInstance() {
        return new FileContext(getSSHParamModel());
    }

}
