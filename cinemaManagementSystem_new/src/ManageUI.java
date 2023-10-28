import entity.*;
import utils.others.Util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * @Author lh
 * @Date 2023/8/24 15:00
 * @Description 管理员UI展示类，定义各个界面的展示
 **/


public class ManageUI {

    public static void manageMainUI(Manage manage){
        System.out.println();
        System.out.println("*************************************");
        System.out.println("|\t经理: "+ manage.getName()+" 你好！\t\t\t\t|");
        System.out.println("-------------------------------------");
        System.out.println("|\t[1]修改本人密码\t             \t|");
        System.out.println("|\t[2]重置用户密码\t             \t|");
        System.out.println("|\t[3]上映影片列表\t[4]添加影片信息\t|");
        System.out.println("|\t[5]修改影片信息\t[6]删除影片信息\t|");
        System.out.println("|\t[7]查询影片信息\t             \t|");
        System.out.println("|\t[8]增加影片场次\t[9]修改影片场次\t|");
        System.out.println("|\t[10]删除影片场次\t[11]影片场次列表\t|");
        System.out.println("|\t[12]消费者列表\t[13]查询消费者信息\t|");
        System.out.println("|\t[14]退出\t\t\t\t\t\t\t|");
        System.out.println("-------------------------------------");
        System.out.println("请输入操作选项：");
        Scanner input = new Scanner(System.in);
        switch (input.nextInt()) {
            case 1:
                manageUpdatePasswordUI(manage);
                break;
            case 2:
                manageResetConsumerPasswordUI(manage);
                break;
            case 3:
                manageListFilm(manage);
                break;
            case 4:
                manageAddFilmUI(manage);
                break;
            case 5:
                manageUpdateFilmUI(manage);
                break;
            case 6:
                manageDeleteFilmUI(manage);
                break;
            case 7:
                manageQueryFilmUI(manage);
                break;
            case 8:
                manageAddArrangeFilmUI(manage);
                break;
            case 9:
                manageUpdateArrangeFilmUI(manage);
                break;
            case 10:
                manageDeleteArrangeFilmUI(manage);
                break;
            case 11:
                manageListArrangeFilmUI(manage);
                break;
            case 12:
                manageListConsumerInfoUI(manage);
                break;
            case 13:
                // TODO
                break;
            case 14:
                break;

        }
    }

    public static void manageUpdatePasswordUI(Manage manage){
        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.println("请输入旧密码:");
        String oldPassword = input.next();
        if(manage.getPassword().equals(oldPassword)){
            System.out.println("请输入新密码:");
            String newPassword = input.next();
            manage.setPassword(newPassword);
            // 修改信息表
            manage.updateManagePassword(manage);
            System.out.println("更新成功！请重新登录");
            UI.loginUI();
        }else{
            System.out.println("密码错误！");
            manageUpdatePasswordUI(manage);
        }
    }


    /**
     * 重置消费者密码
     * @param manage 经理
     */
    public static void manageResetConsumerPasswordUI(Manage manage){
        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.println("请输入消费者账号");
        String consumerUsername = input.next();
        // 输入账号，如果账号不存在则重复让其输入
        boolean userExit = false;
        String updateUsername = "";
        while (!userExit){
            System.out.println("请输入用户账号:");
            updateUsername = input.next();
            userExit = manage.userExist(updateUsername);
            // 如果不存在则提示，重新进入页面
            if(!userExit){
                System.out.println("用户不存在！请重新输入");
            }
        }
        manage.resetConsumerPassword(consumerUsername);

        // 下一步
        int nextOpr = UI.nextOprUI();
        switch (nextOpr){
            case 1:
                manageMainUI(manage);
                break;
            case 2:
                manageResetConsumerPasswordUI(manage);
                break;
        }
    }

    /**
     * 列出所有上映影片的信息
     * @param manage 经理
     */
    public static void manageListFilm(Manage manage){
        System.out.println("");
        System.out.println("*************************************");
        System.out.println("所有上映影片如下：");
        List<Film> films = manage.getAllFilms();
        Util.printFileInfo(films);
        // 下一步
        int nextOpr = UI.nextOprUI();
        switch (nextOpr){
            case 1:
                manageMainUI(manage);
                break;
            case 2:
                manageResetConsumerPasswordUI(manage);
                break;
        }
    }

    /**
     * 经理添加电影
     * @param manage
     */
    public static void manageAddFilmUI(Manage manage){
        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.println("*************************************");

        // 输入影片名判断影片是否存在
        boolean filmExit = true;
        String filmName = "";
        while (filmExit){
            System.out.println("输入影片名称：");
            filmName = input.next();
            filmExit = manage.filmExist(filmName);
            if(filmExit){
                System.out.println("影片已存在");
            }
        }
        System.out.println("请输入影片导演");
        String filmDirector = input.next();
        System.out.println("请输入影片主演");
        String starring = input.next();
        System.out.println("请输入影片剧情简介");
        String introduction = input.next();
        System.out.println("请输入影片时长");
        int duration = input.nextInt();

        manage.addFilm(new Film(
                filmName,
                filmDirector,
                starring,
                introduction,
                duration
        ));

        System.out.println("影片添加成功！");

        // 下一步
        int nextOpr = UI.nextOprUI();
        switch (nextOpr){
            case 1:
                manageMainUI(manage);
                break;
            case 2:
                manageAddFilmUI(manage);
                break;
        }
    }

    /**
     * 经理更新电影信息
     * @param manage 经理对象
     */
    public static void manageUpdateFilmUI(Manage manage){
        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.println("*************************************");

        // 输入影片名判断影片是否存在
        boolean filmExit = false;
        String filmName = "";
        while (!filmExit){
            System.out.println("输入影片名称：");
            filmName = input.next();
            filmExit = manage.filmExist(filmName);
            if(!filmExit){
                System.out.println("影片不存在!");
            }
        }

        // 先将电影信息查询出
        Film film = manage.getFilmByName(filmName);

        // 将信息更新
        System.out.println("请输入影片导演");
        String filmDirector = input.next();
        film.setFilmDirector(filmDirector);
        System.out.println("请输入影片主演");
        String starring = input.next();
        film.setStarring(starring);
        System.out.println("请输入影片剧情简介");
        String introduction = input.next();
        film.setIntroduction(introduction);
        System.out.println("请输入影片时长");
        int duration = input.nextInt();
        film.setDuration(duration);

        // 将更新好的影片信息更新到电影列表里
        manage.updateFilm(film);

        // 下一步
        int nextOpr = UI.nextOprUI();
        switch (nextOpr){
            case 1:
                manageMainUI(manage);
                break;
            case 2:
                manageUpdatePasswordUI(manage);
                break;
        }
    }

    /**
     * 删除影片
     * @param manage
     */
    public static void manageDeleteFilmUI(Manage manage){
        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.println("*************************************");

        // 输入影片名判断影片是否存在
        boolean filmExit = false;
        String filmName = "";
        while (!filmExit){
            System.out.println("输入影片名称：");
            filmName = input.next();
            filmExit = manage.filmExist(filmName);
            if(!filmExit){
                System.out.println("影片不存在!");
            }
        }

        System.out.println("确认删除影片：" + filmName + "?(Y/N)");
        String check = input.next();

        if(check.equals("Y") || check.equals("y")){
            manage.deleteFilm(filmName);
            System.out.println("已删除！");
            // 下一步
            int nextOpr = UI.nextOprUI();
            switch (nextOpr){
                case 1:
                    manageMainUI(manage);
                    break;
                case 2:
                    manageDeleteFilmUI(manage);
                    break;
            }
        }else{
            manageDeleteFilmUI(manage);
        }

    }

    /**
     * 查询影片
     * @param manage
     */
    public static void manageQueryFilmUI(Manage manage){
        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.println("*************************************");

        // 创建影片对象，对查询信息进行封装
        Film queryFilm = new Film();
        System.out.println("请输入影片名称（空则直接回车即可）：");
        String filmName = input.next();
        queryFilm.setFilmName(filmName);
        // 将信息更新
        System.out.println("请输入影片导演（空则直接回车即可）：");
        String filmDirector = input.next();
        queryFilm.setFilmDirector(filmDirector);
        System.out.println("请输入影片主演（空则直接回车即可）：");
        String starring = input.next();
        queryFilm.setStarring(starring);

        List<Film> films = manage.queryFilm(queryFilm);
        if(films.size()==0){
            System.out.println("查询为空！");
        }else{
            Util.printFileInfo(films);
        }

        // 下一步
        int nextOpr = UI.nextOprUI();
        switch (nextOpr){
            case 1:
                manageMainUI(manage);
                break;
            case 2:
                manageQueryFilmUI(manage);
                break;
        }

    }

    /**
     * 添加电影场次
     * @param manage
     */
    public static void manageAddArrangeFilmUI(Manage manage){
        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.println("*************************************");
        System.out.println("请输入电影名称：");
        String filmName = input.next();
        System.out.println("请输入放映厅序号：");
        int auditoriumId = input.nextInt();
        System.out.println("请输入价格：");
        float price = input.nextFloat();
        System.out.println("请输入时间段：");
        String time = input.next();
        ArrangeFilm addArrangeFilm = new ArrangeFilm(filmName, auditoriumId, price, time);
        manage.addArrangeFilm(addArrangeFilm);
        System.out.println("添加成功！");

        // 下一步
        int nextOpr = UI.nextOprUI();
        switch (nextOpr){
            case 1:
                manageMainUI(manage);
                break;
            case 2:
                manageAddArrangeFilmUI(manage);
                break;
        }
    }

    /**
     * 更新电影场次
     * @param manage
     */
    public static void manageUpdateArrangeFilmUI(Manage manage){
        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.println("*************************************");
        // 根据放映厅和时间进行查询，更新电影名称和价格
        System.out.println("请输入放映厅序号：");
        int auditoriumId = input.nextInt();
        System.out.println("请输入时间段：");
        String time = input.next();
        System.out.println("请输入电影名称：");
        String filmName = input.next();
        System.out.println("请输入价格：");
        float price = input.nextFloat();
        ArrangeFilm updateArrangeFilm = new ArrangeFilm(filmName, auditoriumId, price, time);
        manage.updateArrangeFilm(updateArrangeFilm);
        System.out.println("更新成功！");

        // 下一步
        int nextOpr = UI.nextOprUI();
        switch (nextOpr){
            case 1:
                manageMainUI(manage);
                break;
            case 2:
                manageUpdateArrangeFilmUI(manage);
                break;
        }
    }

    /**
     * 删除电影场次
     * @param manage
     */
    public static void manageDeleteArrangeFilmUI(Manage manage){
        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.println("*************************************");
        // 根据放映厅和时间进行查询，
        System.out.println("请输入放映厅序号：");
        int auditoriumId = input.nextInt();
        System.out.println("请输入时间段：");
        String time = input.next();
        ArrangeFilm deleteArrangeFilm = new ArrangeFilm();
        deleteArrangeFilm.setTime(time);
        deleteArrangeFilm.setAuditoriumId(auditoriumId);
        manage.deleteArrangeFilm(deleteArrangeFilm);
        System.out.println("删除成功！");
        // 下一步
        int nextOpr = UI.nextOprUI();
        switch (nextOpr){
            case 1:
                manageMainUI(manage);
                break;
            case 2:
                manageDeleteArrangeFilmUI(manage);
                break;
        }
    }

    /**
     * 列出所有场次信息
     * @param manage
     */
    public static void manageListArrangeFilmUI(Manage manage){
        System.out.println("");
        System.out.println("*************************************");
        System.out.println("所有场次信息如下：");
        List<ArrangeFilm> arrangeFilms = manage.getAllArrangeFilm();
        Util.printArrangeFilmInfo(arrangeFilms);

        // 下一步
        int nextOpr = UI.nextOprUI();
        switch (nextOpr){
            case 1:
                manageMainUI(manage);
                break;
            case 2:
                manageListArrangeFilmUI(manage);
                break;
        }
    }

    /**
     * 列出所有用户信息
     * @param manage
     */
    public static void manageListConsumerInfoUI(Manage manage){
        System.out.println("");
        System.out.println("*************************************");
        System.out.println("所有消费者信息如下：");
        List<Consumer> consumers = manage.getConsumerInfo();
        Util.printConsumerInfo(consumers);
        // 下一步
        int nextOpr = UI.nextOprUI();
        switch (nextOpr){
            case 1:
                manageMainUI(manage);
                break;
            case 2:
                manageListConsumerInfoUI(manage);
                break;
        }
    }

    /**
     * TODO
     * 查询消费者信息
     * @param manage
     */
    public static void manageQueryConsumerInfoUI(Manage manage){
        // TODO
    }

}
