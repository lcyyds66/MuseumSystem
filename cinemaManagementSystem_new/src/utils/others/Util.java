package utils.others;

import entity.*;
import utils.tables.ConsoleTable;
import utils.tables.table.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author mfw_lh
 * @Date 2023/8/31 21:34
 * @Description 工具类，用于查询一些信息
 **/
public class Util {

    /**
     * 根据用户类型拿到对应的数据列表
     * 1：管理员
     * 2：前台
     * @param userType 用户类型
     * @return 对应的数据列表
     */
    public static List<?> getUsersInfo(int userType){
        List<?> usersInfo;
        if(userType == 1){
            usersInfo = Global.manages;
        }else{
            usersInfo = Global.receptionists;
        }

        return usersInfo;
    }

    /**
     * 表格输出用户信息
     * @param users 用户信息
     */
    public static void printUserInfo(List<?>users){
        // 输出表格
        List<Cell> headers = new ArrayList<Cell>(){{
            add(new Cell("用户名"));
            add(new Cell("姓名"));
            add(new Cell("电话"));
            add(new Cell("邮箱"));
            add(new Cell("注册时间"));
        }};
        List<List<Cell>> body = new ArrayList<>();

        // 将数据信息加入到表格中
        for (Object user:
                users) {
            List<Cell> list = new ArrayList<Cell>(){{
                add(new Cell(((User)user).getUsername()));
                add(new Cell(((User)user).getName()));
                add(new Cell(((User)user).getPhone()));
                add(new Cell(((User)user).getEmail()));
                add(new Cell(((User)user).getRegisterDate().toString()));
            }};
            body.add(list);
        }

        // 表格输出，用于控制台输出表格样式
        // 该函数是一个工具类，不需要研究细节
        new ConsoleTable.ConsoleTableBuilder().addHeaders(headers).addRows(body).build().print();
    }

    /**
     * 表格输出所有电影信息
     * @param films 电影列表
     */
    public static void printFileInfo(List<Film> films){
        // 输出表格
        List<Cell> headers = new ArrayList<Cell>(){{
            add(new Cell("片名"));
            add(new Cell("导演"));
            add(new Cell("主演"));
            add(new Cell("影片简介"));
            add(new Cell("时长"));
        }};
        List<List<Cell>> body = new ArrayList<>();

        // 将数据信息加入到表格中
        for (Film film :
                films) {
            List<Cell> list = new ArrayList<Cell>(){{
                add(new Cell(film.getFilmName()));
                add(new Cell(film.getFilmDirector()));
                add(new Cell(film.getStarring()));
                add(new Cell(film.getIntroduction()));
                add(new Cell(String.valueOf(film.getDuration())));
            }};
            body.add(list);
        }

        // 表格输出，用于控制台输出表格样式
        // 该函数是一个工具类，不需要研究细节
        new ConsoleTable.ConsoleTableBuilder().addHeaders(headers).addRows(body).build().print();

    }

    /**
     * 表格输出所有场次信息
     * @param arrangeFilms 电影列表
     */
    public static void printArrangeFilmInfo(List<ArrangeFilm> arrangeFilms){
        // 输出表格
        List<Cell> headers = new ArrayList<Cell>(){{
            add(new Cell("放映厅"));
            add(new Cell("电影名称"));
            add(new Cell("时间段"));
            add(new Cell("价格"));
        }};
        List<List<Cell>> body = new ArrayList<>();

        // 将数据信息加入到表格中
        for (ArrangeFilm arrangeFilm :
                arrangeFilms) {
            List<Cell> list = new ArrayList<Cell>(){{
                add(new Cell(String.valueOf(arrangeFilm.getAuditoriumId())));
                add(new Cell(arrangeFilm.getFilmName()));
                add(new Cell(arrangeFilm.getTime()));
                add(new Cell(String.valueOf(arrangeFilm.getPrice())));
            }};
            body.add(list);
        }

        // 表格输出，用于控制台输出表格样式
        // 该函数是一个工具类，不需要研究细节
        new ConsoleTable.ConsoleTableBuilder().addHeaders(headers).addRows(body).build().print();

    }

    /**
     * 表格输出所有消费者信息
     * @param consumers 用户信息列表
     */
    public static void printConsumerInfo(List<Consumer> consumers){
        // 输出表格
        List<Cell> headers = new ArrayList<Cell>(){{
            add(new Cell("用户ID"));
            add(new Cell("用户名"));
            add(new Cell("用户级别"));
            add(new Cell("用户注册时间"));
            add(new Cell("用户累计消费总额"));
            add(new Cell("用户累计消费次数"));
            add(new Cell("用户手机号"));
            add(new Cell("用户邮箱"));
        }};
        List<List<Cell>> body = new ArrayList<>();

        // 将数据信息加入到表格中
        for (Consumer consumer :
                consumers) {
            List<Cell> list = new ArrayList<Cell>(){{
                add(new Cell(consumer.getUsername()));
                add(new Cell(consumer.getName()));
                add(new Cell(consumer.getVipLevel()));
                add(new Cell(consumer.getRegisterDate()));
                add(new Cell(String.valueOf(consumer.getTotalSpending())));
                add(new Cell(String.valueOf(consumer.getConsumptionTimes())));
                add(new Cell(consumer.getPhone()));
                add(new Cell(consumer.getEmail()));
            }};
            body.add(list);
        }

        // 表格输出，用于控制台输出表格样式
        // 该函数是一个工具类，不需要研究细节
        new ConsoleTable.ConsoleTableBuilder().addHeaders(headers).addRows(body).build().print();

    }

    /**
     * 输出场次信息
     * @param arrangeFilm 场次
     */
    public static void printArrangeFilmInfo(ArrangeFilm arrangeFilm){
        System.out.println("**************************************************");
        System.out.println("电影场次信息如下：");
        System.out.println("总座位数：" + String.valueOf(arrangeFilm.getTotalSeatNum())
                + " 空闲座位数：" + String.valueOf(arrangeFilm.getVacantSearNum()));
        System.out.println("座位信息如下：");
        System.out.println("**************************************************");
        System.out.print("\t");
        for (int i = 1; i < 13; i++) {
            System.out.print(i + " \t");
        }
        System.out.println();
        for (int i = 0; i < arrangeFilm.getSeats().size(); i++) {
            System.out.print(i+"\t");
            List<String> listStatus = arrangeFilm.getSeats().get(i);
            for (String status :
                    listStatus) {
                System.out.print(status+"\t");
            }
            System.out.println();
        }
        System.out.println("**************************************************");
    }

}

