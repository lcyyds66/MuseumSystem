import entity.*;
import utils.others.Util;
import utils.tables.ConsoleTable;
import utils.tables.table.Cell;

import java.util.*;

/**
 * @Author lh
 * @Date 2023/8/24 15:00
 * @Description UI展示类，定义各个界面的展示
 **/
public class UI {

    /**
     * 系统初始界面,提示用户选择登录身份
     */
    public static void initUI(){
        System.out.println("");
        System.out.print("\t\t\t欢迎来到影院管理系统，请选择登录身份 ⬇️\n");
        System.out.println("******************************************************\n");
        System.out.println("\t[1]管理员\t[2]影院经理\t[3]前台人员\t[4]影院顾客\n");
        System.out.println("******************************************************\n");
        System.out.print("请输入序号：");
    }

    /**
     * 登录界面
     * @return 输入的用户名和密码，用于验证
     */
    public static Map<String, String>loginUI(){
        Scanner input = new Scanner(System.in);
        Map<String, String> loginInfo = new HashMap<>();
        System.out.println("请输入用户名：");
        String username = input.next();
        System.out.println("请输入密码：");
        String password = input.next();
        loginInfo.put("username", username);
        loginInfo.put("password", password);

        return loginInfo;
    }

    /**
     * 用在操作最后，接收用户的下一步操作是返回主页面还是留在当前页面还是退出程序
     *
     * @return 返回主页面返回1 继续当前页面操作返回2 退出系统返回0
     */
    public static int nextOprUI(){
        Scanner input = new Scanner(System.in);
        int nextOpr = 0;
        System.out.println("返回主页面输入1，继续操作输入2，退出系统输入0：");
        nextOpr = input.nextInt();

        return nextOpr;
    }

    /**
     * 管理员主界面
     * @param admin 管理员对象
     */
    public static void adminMainUI(Admin admin){
        System.out.println();
        System.out.println("*************************************");
        System.out.println("|\t管理员: "+ admin.getName()+" 你好！\t\t\t\t|");
        System.out.println("-------------------------------------");
        System.out.println("|\t[1]修改本人密码\t             \t|");
        System.out.println("|\t[2]查看前台信息\t[3]查看经理信息\t|");
        System.out.println("|\t[4]修改前台密码\t[5]修改经理密码\t|");
        System.out.println("|\t[6]删除前台信息\t[7]删除经理信息\t|");
        System.out.println("|\t[8]查询前台信息\t[9]查询经理信息\t|");
        System.out.println("|\t[10]增加前台信息\t[11]增加经理信息\t|");
        System.out.println("|\t[12]更新前台信息\t[13]更新经理信息\t|");
        System.out.println("|\t[14]退出\t\t\t\t\t\t\t|");
        System.out.println("-------------------------------------");
        System.out.println("请输入操作选项：");
        Scanner input = new Scanner(System.in);
        switch (input.nextInt()){
            case 1:
                adminUpdatePasswordUI(admin);
                break;
            case 2:
                adminListUsersInfoUI(admin, 2);
                break;
            case 3:
                adminListUsersInfoUI(admin, 1);
                break;
            case 4:
                adminUpdateUserPassword(admin, 2);
                break;
            case 5:
                adminUpdateUserPassword(admin, 1);
                break;
            case 6:
                adminDeleteUserInfoUI(admin, 2);
                break;
            case 7:
                adminDeleteUserInfoUI(admin, 1);
                break;
            case 8:
                adminQueryUserInfoUI(admin, 2);
                break;
            case 9:
                adminQueryUserInfoUI(admin, 1);
                break;
            case 10:
                adminAddUserInfoUI(admin, 2);
                break;
            case 11:
                adminAddUserInfoUI(admin, 1);
                break;
            case 12:
                adminListUsersInfoUI(admin, 2);
                break;
            case 13:
                adminListUsersInfoUI(admin, 1);
                break;
            case 14:
                break;
        }
    }

    /**
     * 用在操作最后，接收管理员的下一步操作是返回主页面还是留在当前页面还是退出程序
     *
     * @return 返回主页面返回1 继续当前页面操作返回2 退出系统返回0
     */
    public static int adminNextOprUI(){
        Scanner input = new Scanner(System.in);
        int nextOpr = 0;
        System.out.println("返回主页面输入1，继续操作输入2，退出系统输入0：");
        nextOpr = input.nextInt();

        return nextOpr;
    }

    /**
     * TODO 是否需要 不需要则删掉
     * 接收用户名
     * 在查找用户信息或者删除用户信息时用于接收输入
     * 如果输入账号不存在则一直循环接收输入，知道正确为止
     * @param admin 管理员
     * @return 管理员输入的账号
     */
    public static String adminInputUsername(Admin admin, int userType){
        // 根据用户类别加载用户数据列表
        List<?> usersInfo = Util.getUsersInfo(userType);
        Scanner input = new Scanner(System.in);
        boolean userExit = false;
        String updateUsername = "";
        while (!userExit){
            System.out.println("请输入用户账号:");
            updateUsername = input.next();
            userExit = admin.userExist(updateUsername, usersInfo);
            // 如果不存在则提示，重新进入页面
            if(!userExit){
                System.out.println("用户不存在！请重新输入");
            }
        }

        return updateUsername;
    }

    /**
     * 更新密码页面
     * @param admin 管理员信息
     */
    public static void adminUpdatePasswordUI(Admin admin){
        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.println("请输入旧密码:");
        String oldPassword = input.next();
        if(admin.getPassword().equals(oldPassword)){
            System.out.println("请输入新密码:");
            String newPassword = input.next();
            admin.setPassword(newPassword);
            for (Admin adminTmp:
                 Global.admins) {
                if(adminTmp.getUsername().equals(admin.getPassword())){
                    adminTmp.setPassword(admin.getPassword());
                }
            }
            System.out.println("更新成功！请重新登录");
            loginUI();
        }else{
            System.out.println("密码错误！");
            adminUpdatePasswordUI(admin);
        }
    }

    /**
     * 展示前台或者经理信息
     * @param admin 管理员信息
     * @param userType 身份
     */
    public static void adminListUsersInfoUI(Admin admin, int userType){
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println("*************************************");
        System.out.println("\t人员信息如下: ");
        // 根据身份信息 加载不同的数据列表
        List<?> usersInfo;
        if(userType == 1){
            usersInfo = Global.manages;
        }else{
            usersInfo = Global.receptionists;
        }
        // 表格输出
        Util.printUserInfo(usersInfo);
        int nextOpr = adminNextOprUI();
        switch (nextOpr){
            case 1:
                adminMainUI(admin);
                break;
            case 2:
                adminListUsersInfoUI(admin, userType);
                break;
        }

    }

    /**
     * 管理员更新前台或者经理密码
     * @param admin 管理员信息
     * @param userType 用户类别
     */
    public static void adminUpdateUserPassword(Admin admin, int userType){
        Scanner input = new Scanner(System.in);

        System.out.println("");
        System.out.println("*************************************");

        // 接收输入的账号
        List<?> usersInfo = Util.getUsersInfo(userType);

        // 输入账号，如果账号不存在则重复让其输入
        boolean userExit = false;
        String updateUsername = "";
        while (!userExit){
            System.out.println("请输入用户账号:");
            updateUsername = input.next();
            userExit = admin.userExist(updateUsername, usersInfo);
            // 如果不存在则提示，重新进入页面
            if(!userExit){
                System.out.println("用户不存在！请重新输入");
            }
        }

        // 开始修改密码
        System.out.println("请输入新密码:");
        String newPassword = input.next();
        admin.setPassword(newPassword);
        for (Object userinfo :
                usersInfo) {
            String username = ((User)userinfo).getUsername();
            if(username.equals(updateUsername)){
                ((User)userinfo).setPassword(newPassword);
                System.out.println("密码修改成功！");
            }
        }
        int nextOpr = adminNextOprUI();
        switch (nextOpr){
            case 1:
                adminMainUI(admin);
                break;
            case 2:
                adminUpdateUserPassword(admin, userType);
                break;
        }
    }

    /**
     * 删除用户信息
     * 根据用户类型删除对应的信息
     * @param admin 管理员
     * @param userType 用户类型
     */
    public static void adminDeleteUserInfoUI(Admin admin, int userType){
        Scanner input = new Scanner(System.in);
        // 接收输入的账号
        List<?> usersInfo = Util.getUsersInfo(userType);

        // 输入账号，如果账号不存在则重复让其输入
        boolean userExit = false;
        String deleteUsername = "";
        while (!userExit){
            System.out.println("请输入用户账号:");
            deleteUsername = input.next();
            userExit = admin.userExist(deleteUsername, usersInfo);
            // 如果不存在则提示，重新进入页面
            if(!userExit){
                System.out.println("用户不存在！请重新输入");
            }
        }

        System.out.println("确认删除用户：" + deleteUsername + " ?(Y/N)");
        String check = input.next();
        // 如果确认则删除，否则返回上一级，继续输入账号进行删除
        if(check.equals("Y") || check.equals("y")){
            // java数组是固定长度，无法直接删除，使用临时变量存储删除后的数组，然后再复制给全局数据表
            if(userType == 1){
                ArrayList<Manage> newManageList = new ArrayList<>();
                for (Object user: usersInfo) {
                    if(!((Manage)user).getUsername().equals(deleteUsername)){
                        newManageList.add(((Manage)user));
                    }
                }
                Global.manages = newManageList;
            }else{
                ArrayList<Receptionist> newReceptionistList = new ArrayList<>();
                for (Object user: usersInfo) {
                    if(!((Receptionist)user).getUsername().equals(deleteUsername)){
                        newReceptionistList.add(((Receptionist)user));
                    }
                }
                Global.receptionists = newReceptionistList;
            }
            System.out.println("已删除！");
            int nextOpr = adminNextOprUI();
            switch (nextOpr){
                case 1:
                    adminMainUI(admin);
                    break;
                case 2:
                    adminDeleteUserInfoUI(admin, userType);
                    break;
            }
        }else{
            adminDeleteUserInfoUI(admin, userType);
        }

    }

    /**
     * 管理员查看用户信息
     * 使用用户名或者姓名进行查询
     * @param admin 管理员
     * @param userType 用户类型
     */
    public static void adminQueryUserInfoUI(Admin admin, int userType) {
        Scanner input = new Scanner(System.in);
        List<?> usersInfo = Util.getUsersInfo(userType);

        System.out.println("");
        System.out.println("*************************************");
        System.out.println("请输入查询方式：[1]使用id查询 [2]使用姓名查询");
        int queryType = input.nextInt();

        // 根据用户id查询
        if (queryType == 1) {
            System.out.println("请输入用户id");
            String queryUsername = input.next();
            User user = admin.queryUserInfoByUsername(queryUsername, usersInfo);
            if (user == null) {
                System.out.println("查询用户不存在！");

            } else {
                List<User> users = new ArrayList<>();
                users.add(user);
                Util.printUserInfo(usersInfo);
            }
        }else{
            // 根据用户名查询
            System.out.println("请输入用户的姓名：");
            String queryName = input.next();
            List<User> users = admin.queryUserInfoByName(queryName, usersInfo);
            if(users.size() == 0){
                System.out.println("查询用户不存在");
            }else {
                Util.printUserInfo(usersInfo);
            }
        }

        //进行下一步提示
        int nextOpr = adminNextOprUI();
        switch (nextOpr) {
            case 1:
                adminMainUI(admin);
                break;
            case 2:
                adminQueryUserInfoUI(admin, userType);
                break;
        }

    }

    /**
     * 管理员增加用户信息
     * @param admin 管理员
     * @param userType 用户类别
     */
    public static void adminAddUserInfoUI(Admin admin, int userType){
        Scanner input = new Scanner(System.in);
        // 接收输入的账号
        List<?> usersInfo = Util.getUsersInfo(userType);

        // 输入账号，如果账号不存在则重复让其输入
        boolean userExit = true;
        String addUsername = "";
        while (userExit){
            System.out.println("请输入用户账号:");
            addUsername = input.next();
            userExit = admin.userExist(addUsername, usersInfo);
            // 如果不存在则提示，重新进入页面
            if(userExit){
                System.out.println("账号已存在！请重新输入");
            }
        }
        // TODO 以下需要进行输入验证 判空 和非法字符判定
        System.out.println("请输入姓名：");
        String addName = input.next();
        System.out.println("请输入密码：");
        String addPassword = input.next();
        System.out.println("请输入电话：");
        String addPhone = input.next();
        System.out.println("请输入姓邮箱：");
        String addEmail = input.next();

        // 将新用户加入到对应的数据集中
        if(userType == 1){
            Manage manage = new Manage(addUsername, addName, addPassword, addPhone, addEmail);
            Global.manages.add(manage);
        }else{
            Receptionist receptionist = new Receptionist(addUsername, addName, addPassword, addPhone, addEmail);
            Global.receptionists.add(receptionist);
        }
        System.out.println("添加成功");
        int nextOpr = adminNextOprUI();
        switch (nextOpr){
            case 1:
                adminMainUI(admin);
                break;
            case 2:
                adminAddUserInfoUI(admin, userType);
                break;
        }

    }

    /**
     * TODO 管理员更新用户信息
     * @param admin 管理员对象
     * @param userType 用户类别
     */
    public static void adminUpdateUserInfo(Admin admin, int userType) {
        Scanner input = new Scanner(System.in);
        // 接收输入的账号
        List<?> usersInfo = Util.getUsersInfo(userType);

        // 输入账号，如果账号不存在则重复让其输入
        boolean userExit = false;
        String updateUsername = "";
        while (!userExit) {
            System.out.println("请输入用户账号:");
            updateUsername = input.next();
            userExit = admin.userExist(updateUsername, usersInfo);
            // 如果不存在则提示，重新进入页面
            if (!userExit) {
                System.out.println("用户不存在！请重新输入");
            }
        }


        // java数组是固定长度，无法直接删除，使用临时变量存储删除后的数组，然后再复制给全局数据表
        if (userType == 1) {
            // 用户类型为1 则是将经理转起前台
            // 先将该用户加入到前台数据中

            // 再删除前台数据中

        }else{

        }

    }

}
