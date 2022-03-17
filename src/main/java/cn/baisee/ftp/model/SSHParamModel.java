package cn.baisee.ftp.model;

import lombok.*;

/**
 * @author xiaoliu
 * @ClassName SSHParamModel
 * @date 2021/12/28 11:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class SSHParamModel {
    private  int port;
    private  String host;
    private  String username;
    private  String password;
    private String bassPath;
    private String directory;

}
