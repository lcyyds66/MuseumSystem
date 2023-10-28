import entity.ArrangeFilm;
import entity.Film;
import entity.Receptionist;
import utils.others.Util;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 前台页面
 */
public class ReceptionistUI {

    public static void receptionistMainUI(Receptionist receptionist){
        System.out.println();
        System.out.println("*************************************");
        System.out.println("|\t前台: "+ receptionist.getName()+" 你好！\t\t\t\t|");
        System.out.println("-------------------------------------");
        System.out.println("|\t[1]上映影片列表\t              \t|");
        System.out.println("|\t[2]影片场次列表\t              \t|");
        System.out.println("|\t[3]查询场次信息\t              \t|");
        System.out.println("|\t[4]售票       \t              \t|");
        System.out.println("|\t[5]退出\t\t\t\t\t\t\t|");
        System.out.println("-------------------------------------");
        System.out.println("请输入操作选项：");
        Scanner input = new Scanner(System.in);
        switch (input.nextInt()) {
            case 1:
                receptionistListFilmUI(receptionist);
                break;
            case 2:
                receptionistListArrangeFilmInfoUI(receptionist);
                break;
            case 3:
                receptionistQueryArrangeFilmInfoUI(receptionist);
                break;
            case 4:
                receptionistSaleUI(receptionist);
                break;
            case 5:
                break;

        }
    }

    /**
     * 列出所有影片信息
     * @param receptionist
     */
    public static void receptionistListFilmUI(Receptionist receptionist){
        System.out.println("");
        System.out.println("*************************************");
        System.out.println("所有上映影片如下：");
        List<Film> films = receptionist.getAllFilm();
        Util.printFileInfo(films);
        // 下一步
        int nextOpr = UI.nextOprUI();
        switch (nextOpr){
            case 1:
                receptionistMainUI(receptionist);
                break;
            case 2:
                receptionistListFilmUI(receptionist);
                break;
        }
    }

    /**
     * TODO
     * 列出所有场次信息
     * @param receptionist
     */
    public static void receptionistListArrangeFilmInfoUI(Receptionist receptionist){

    }

    /**
     * TODO 需要优化
     * 根据电影和场次信息（放映时间）展示信息
     * @param receptionist
     */
    public static void receptionistQueryArrangeFilmInfoUI(Receptionist receptionist){
        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.println("*************************************");
        System.out.println("请输入电影名：");
        String filmName = input.next();
        System.out.println("请输入放映时间：");
        String time = input.next();

        ArrangeFilm arrangeFilm = receptionist.queryArrangeFilm(filmName, time);
        if(arrangeFilm == null){
            System.out.println("查询信息不存在！");
        }else{
            Util.printArrangeFilmInfo(arrangeFilm);
        }

        // 下一步
        int nextOpr = UI.nextOprUI();
        switch (nextOpr){
            case 1:
                receptionistMainUI(receptionist);
                break;
            case 2:
                receptionistQueryArrangeFilmInfoUI(receptionist);
                break;
        }
    }

    /**
     * 售票
     * @param receptionist
     */
    public static void receptionistSaleUI(Receptionist receptionist){
        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.println("*************************************");
        System.out.println("请输入购票信息：");
        System.out.println("请输入电影名称：");
        String filmName = input.next();
        System.out.println("请输入放映时间：");
        String time = input.next();
        System.out.println("请输入消费者账号：");
        String consumerUsername = input.next();
        System.out.println("请输入座位所在行：");
        int row = input.nextInt();
        System.out.println("请输入座位所在列：");
        int col = input.nextInt();

        Map<String, String> movieTicket = receptionist.sale(filmName, time, consumerUsername, row, col);

        System.out.println("出票成功！");
        System.out.println("-----------------");
        System.out.println("|电影名称：" + movieTicket.get("movieName"));
        System.out.println("|放映厅：" + movieTicket.get("auditoriumId"));
        System.out.println("|上映时间：" + movieTicket.get("time"));
        System.out.println("|取票码：" + movieTicket.get("movieId"));
        System.out.println("|收费：" + movieTicket.get("pay"));
        System.out.println("-----------------");

        // 下一步
        int nextOpr = UI.nextOprUI();
        switch (nextOpr){
            case 1:
                receptionistMainUI(receptionist);
                break;
            case 2:
                receptionistSaleUI(receptionist);
                break;
        }
    }
}
