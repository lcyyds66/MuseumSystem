package entity;

import entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lh
 * @Date 2023/8/24 10:28
 * @Description //TODO
 **/
public class Admin extends User {

    /**
     * 空构造方法
     */
    public Admin(){}

    /**
     * 有参构造方法
     * @param username 用户名
     * @param password 密码
     * @param name 姓名
     * @param phone 电话
     * @param email 邮箱
     */
    public Admin(String username, String password, String name, String phone, String email){
        super(username, password, name, phone, email);
    }

    private void go(){
        System.out.println("go");
    }

    /**
     * 更新用户密码
     * @param updateUsername 输入的用户名
     * @param updatePassword 输入的密码
     * @param userInfos 传入的用户信息集（使用了泛型）
     * @return 成功返回true 失败返回false
     */
    public boolean changePassword(String updateUsername, String updatePassword, ArrayList<?> userInfos){
        for (Object userinfo :
                userInfos) {
            String username = ((User)userinfo).getUsername();
            if(username.equals(updateUsername)){
                ((User)userinfo).setPassword(updatePassword);
                System.out.println("密码修改成功！");
                return true;
            }
        }

        return false;
    }

    /**
     * 根据username查询用户信息
     * @param queryUsername 查询用户名
     * @param usersInfo 用户信息集
     * @return 查询到的对象，如果没有则返回控对象
     */
    public User queryUserInfoByUsername(String queryUsername, List<?> usersInfo){
        User queryUser = null;
        for (Object userInfo :
                usersInfo) {
         String username = ((User) userInfo).getUsername();
         if(username.equals(queryUsername)){
             queryUser = (User) userInfo;
         }
        }

        return queryUser;
    }

    /**
     * 根据姓名查询用户信息
     * @param queryName 查询的姓名
     * @param usersInfo 对应的用户数据集
     * @return 可能存在重名问题，因此返回与查询名相符合的数组
     */
    public List<User> queryUserInfoByName(String queryName, List<?>usersInfo){
        List<User> queryUsers = new ArrayList<>();

        for (Object userInfo :
                usersInfo) {
            String name = ((User) userInfo).getName();
            if(name.equals(queryName)){
                queryUsers.add((User) userInfo);
            }
        }

        return queryUsers;
    }

    /**
     * 根据用户名得到管理员对象信息
     * @param username
     * @return
     */
    public Admin getAdminByUsername(String username){
        Admin loginAdmin = null;

        for (Admin admin: Global.admins) {
            if(admin.getUsername().equals(username)){
                loginAdmin = admin;
            }
        }

        return loginAdmin;
    }


}

