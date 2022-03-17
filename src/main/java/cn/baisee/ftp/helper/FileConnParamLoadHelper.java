package cn.baisee.ftp.helper;

import cn.baisee.ftp.model.SSHParamModel;
import lombok.AllArgsConstructor;

/**
 * @author xiaoliu
 * @ClassName FileConnParamLoadHelper
 * @date 2021/12/28 11:29
 */

public class FileConnParamLoadHelper {

    private static SSHParamModel paramModel = null;


    public static SSHParamModel getSshParamModel(){

        return paramModel;
    }


    public FileConnParamLoadHelper(SSHParamModel sshParamModel) {
        FileConnParamLoadHelper.paramModel=sshParamModel;
    }



}
