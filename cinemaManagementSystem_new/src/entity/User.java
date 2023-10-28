package entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author lh
 * @Date 2023/8/24 10:28
 * @Description //用户基类
 **/
public abstract class User {

    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String registerDate;

    /**
     * 无参构造方法
     */
    public User(){}

    /**
     * 有参构造方法
     * @param username 用户名
     * @param password 密码
     * @param name 姓名
     * @param phone 电话
     * @param email 邮箱
     */
    public User(String username, String password, String name, String phone, String email){
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        // 注册时间为默认当天
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        this.registerDate = sdf.format(date);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    /**
     * 根据用户名判断用户是否已存在
     * @param checkUsername 用户名
     * @param userinfos 用户信息集
     * @return 存在返回true，否则返回false
     */
    public boolean userExist(String checkUsername, List<?>userinfos){

        for (Object userinfo :
                userinfos) {
            String username = ((User) userinfo).getUsername();
            if(username.equals(checkUsername)){
                return true;
            }
        }

        return false;
    }


    /**
     * 登录验证
     * @param loginInfo 用户输入信息
     * @param userInfos 对应的数据表
     * @return 验证正确返回true 否则返回false
     */
    public boolean login(Map<String, String>loginInfo, int loginType){
        ArrayList<?> userInfos = null;

        // 根据登录类型确定使用哪个数据表进行验证
        switch (loginType){
            case 1:
                userInfos = Global.admins;
                break;
            case 2:
                userInfos = Global.manages;
                break;
            case 3:
                userInfos = Global.receptionists;
                break;
            case 4:
                userInfos = Global.consumers;
                break;
        }
        for (Object userInfo :
                userInfos) {
            String username = ((User) userInfo).getUsername();
            if(username.equals(loginInfo.get("username"))){
                String password = ((User) userInfo).getPassword();
                if(password.equals(loginInfo.get("password"))){
                    return true;
                }
            }
        }

        return false;
    }




}
