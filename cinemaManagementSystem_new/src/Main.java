import entity.*;
import utils.others.OrderTask;
import utils.others.TImeTask;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;

/**
 * @Author ${USER}
 * @Date ${DATE} ${HOUR}:${MINUTE}
 * @Description //TODO
 **/
public class Main {

    // 初始化输入对象
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        Main.start();
    }

    /**
     * 程序主入口
     */
    public static void start(){
        Scanner input = new Scanner(System.in);
        // 初始化数据库对象，用于全局操作
        // 讲一下另外的全局实现方法，设置一个全局类，然后设置静态属性作为全局变量用，但是该操作有风险
        // 全局类初始化全局数据表
        Global.initAllData();

        // 初始化界面
        UI.initUI();

        // 接收输入
        int userType = input.nextInt();
        // 接收输入
        Map<String, String >loginInfo = UI.loginUI();

        // 启动定时任务，定时检查未支付订单，十秒一检查，时间可变
        Timer time = new Timer();
        time.schedule(new OrderTask(), 0, 10*1000);

        // 根据用户选择进行不同对象的初始化（即身份登录）
        switch (userType){
            // 管理员登录
            case 1:
                Admin admin = new Admin();
                // 用户名密码验证
                boolean adminPasswordCheck = admin.login(loginInfo, 1);
                if(adminPasswordCheck){
                    // 更新用户信息
                    // 从数据表中读取出用户信息，
                    // 相当于session保存了用户信息,该对象和数据库对象以及输入对象将传递至各个层，用于操作
                    admin = admin.getAdminByUsername(loginInfo.get("username"));
                    UI.adminMainUI(admin);
                }else {
                    System.out.println("用户名或密码错误!");
                    UI.initUI();
                }
                break;
            case 2:
                Manage manage = new Manage();
                // 用户名密码验证
                boolean passwordCheck = manage.login(loginInfo, 2);
                manage = manage.getManageByUsername(loginInfo.get("username"));
                ManageUI.manageMainUI(manage);
                /*if(passwordCheck){
                    manage = manage.getManageByUsername(loginInfo.get("username"));
                    ManageUI.manageMainUI(manage);
                }else {
                    System.out.println("用户名或密码错误!");
                    UI.initUI();
                }*/
                break;
            case 3:
                Receptionist receptionist = new Receptionist();
                // 用户名密码验证
                boolean receptionistPasswordCheck = receptionist.login(loginInfo, 3);
                receptionist = receptionist.getReceptionistByUsername(loginInfo.get("username"));
                ReceptionistUI.receptionistMainUI(receptionist);
                /*if(receptionistPasswordCheck){
                    receptionist = receptionist.getReceptionistByUsername(loginInfo.get("username"));
                    ReceptionistUI.receptionistMainUI(receptionist);
                }else {
                    System.out.println("用户名或密码错误!");
                    UI.initUI();
                }*/
                break;
            case 4:
                Consumer consumer = new Consumer();
                // 用户名密码验证
                boolean consumerPasswordCheck = consumer.login(loginInfo, 4);
                consumer = consumer.getConsumertByUsername(loginInfo.get("username"));
                ConsumerUI.consumerPayUI(consumer);
                /*if(consumerPasswordCheck){
                    consumer = consumer.getConsumertByUsername(loginInfo.get("username"));
                    ConsumerUI.consumerPayUI(consumer);
                }else {
                    System.out.println("用户名或密码错误!");
                    UI.initUI();
                }*/
                break;
        }
    }
}