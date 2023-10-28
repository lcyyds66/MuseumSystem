package entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @Author mfw_lh
 * @Date 2023/8/30 16:36
 * @Description //TODO
 **/
public class Global {


    // 全局数据表
    public static ArrayList<Admin> admins = new ArrayList<>();
    public static ArrayList<Consumer> consumers = new ArrayList<>();
    public static ArrayList<Manage> manages = new ArrayList<>();
    public static ArrayList<Receptionist> receptionists = new ArrayList<Receptionist>();
    public static ArrayList<Film> films = new ArrayList<>();
    public static ArrayList<ArrangeFilm> arrangeFilms = new ArrayList<>();
    public static ArrayList<PayInfo> payInfos = new ArrayList<>();
    public static ArrayList<OrderInfo> orderInfos = new ArrayList<>();

    /**
     * 初始化管理员数据信息
     */
    private static void initAdmins() {
        Admin admin = new Admin("admin", "123", "管理员小王", "133", "133");
        admins.add(admin);
    }

    private static void initManages() {
        Manage manage = new Manage("manage", "经理", "456", "135983", "274049");
        manages.add(manage);
    }

    private static void initReceptionists() {
        Receptionist receptionist = new Receptionist();
        receptionist.setEmail("11@163.com");
        receptionist.setName("福西西");
        receptionist.setPassword("0616");
        receptionist.setPhone("13131216262");
        receptionist.setUsername("receptionist");
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        receptionist.setRegisterDate(sdf.format(date));
        receptionists.add(receptionist);
    }
    /**
     * 数据库初始化，用于初始化数据表信息，当前仅初始化管理员数据
     */
    public static void initAllData() {
        initAdmins();
        initManages();
        initReceptionists();
    }

    /**
     * 根据用户名得到管理员对象信息
     *
     * @param username
     * @return
     */
    public Admin getAdminByUsername(String username) {
        Admin loginAdmin = null;

        for (Admin admin : Global.admins) {
            if (admin.getUsername().equals(username)) {
                loginAdmin = admin;
            }
        }
        return loginAdmin;
    }

}
