import entity.Admin;
import entity.Consumer;
import entity.Manage;
import entity.Receptionist;

import java.util.ArrayList;

/**
 * @Author lh
 * @Date 2023/8/24 14:17
 * @Description 数据库类，用于存放所有数据
 **/
public class DB {


    public ArrayList<Admin> admins = new ArrayList<>();
    public ArrayList<Consumer> consumers = new ArrayList<>();
    public ArrayList<Manage> manages = new ArrayList<>();
    public ArrayList<Receptionist> receptionists = new ArrayList<>();

    /**
     * 初始化管理员数据信息
     */
    private void initAdmins(){
        Admin admin = new Admin("admin", "123", "张三", "133", "133");
        admins.add(admin);
    }

    /**
     * 数据库初始化，用于初始化数据表信息，当前仅初始化管理员数据
     */
    public void initAllData(){
        initAdmins();
    }

    /**
     * 根据用户名得到管理员对象信息
     * @param username
     * @return
     */
    public Admin getAdminByUsername(String username){
        Admin loginAdmin = null;
        for (Admin admin: admins) {
            if(admin.getUsername().equals(username)){
                loginAdmin = admin;
            }
        }

        return loginAdmin;
    }


}
