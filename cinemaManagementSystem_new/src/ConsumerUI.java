import entity.Consumer;
import entity.Manage;

import java.util.Map;
import java.util.Scanner;

/**
 * 消费者UI
 */
public class ConsumerUI {

    public static void consumerMainUI(Consumer consumer){
        System.out.println();
        System.out.println("*************************************");
        System.out.println("-------------------------------------");
        System.out.println("|\t[1]修改本人密码\t             \t|");
        System.out.println("|\t[2]重置密码   \t             \t|");
        System.out.println("|\t[3]查看电影信息\t             \t|");
        System.out.println("|\t[4]查询场次信息\t             \t|");
        System.out.println("|\t[5]购票      \t             \t|");
        System.out.println("|\t[6]付款      \t             \t|");
        System.out.println("|\t[7]取票      \t             \t|");
        System.out.println("|\t[8]查看购票记录\t             \t|");
        System.out.println("|\t[9]退出\t\t\t\t\t\t\t|");
        System.out.println("-------------------------------------");
        System.out.println("请输入操作选项：");
        Scanner input = new Scanner(System.in);
        switch (input.nextInt()) {
            case 1:
                // TODO

                break;
            case 2:
                // TODO

                break;
            case 3:
                // TODO

                break;
            case 4:
                // TODO

                break;
            case 5:
                consumerBuyUI(consumer);
                break;
            case 6:
                consumerPayUI(consumer);
                break;
            case 7:
                // TODO
                break;
            case 8:
                // TODO
                break;
            case 9:
                break;

        }
    }

    /**
     * TODO 一次买多张 释放
     * 购票
     * @param consumer
     */
    public static void consumerBuyUI(Consumer consumer){
        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.println("*************************************");
        System.out.println("请输入购票信息：");
        System.out.println("请输入电影名称：");
        String filmName = input.next();
        System.out.println("请输入放映时间：");
        String time = input.next();
        System.out.println("请输入座位所在行：");
        int row = input.nextInt();
        System.out.println("请输入座位所在列：");
        int col = input.nextInt();

        Map<String, String> movieTicket = consumer.buy(filmName, time, consumer, row, col);


        System.out.println("出票成功！");
        System.out.println("-----------------");
        System.out.println("|电影名称：" + movieTicket.get("movieName"));
        System.out.println("|放映厅：" + movieTicket.get("auditoriumId"));
        System.out.println("|上映时间：" + movieTicket.get("time"));
        System.out.println("|订单号：" + movieTicket.get("oderId"));
        System.out.println("|收费：" + movieTicket.get("pay"));
        System.out.println("-----------------");

        // 提示缴费
        System.out.println("是否进入缴费页面？（Y/N）");
        String opr = input.next();
        if(opr.equals("Y") || opr.equals("y")){
            consumerPayUI(consumer, movieTicket.get("oderId"));
        }

        // 下一步
        int nextOpr = UI.nextOprUI();
        switch (nextOpr){
            case 1:
                consumerMainUI(consumer);
                break;
            case 2:
                consumerBuyUI(consumer);
                break;
        }
    }

    /**
     * 付账
     * @param consumer
     */
    public static void consumerPayUI(Consumer consumer){
        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.println("++++++缴费页面+++++++");
        System.out.println("请输入订单号：");
        String orderId = input.next();
        System.out.println("请输入缴费金额：");
        double pay = input.nextDouble();

        Map<String, String> payResult = consumer.pay(orderId, consumer, pay);

        System.out.println("查询付款中≥....");
        // 加了一个延时，模拟付款等待时长
        // 该方法必须要有异常检测
        try {
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 支付成功则显示信息
        if(payResult.get("paySuccess").equals("1")){
            System.out.println("出票成功！");
            System.out.println("-----------------");
            System.out.println("|电影名称：" + payResult.get("movieName"));
            System.out.println("|放映厅：" + payResult.get("auditoriumId"));
            System.out.println("|上映时间：" + payResult.get("time"));
            System.out.println("|取票码：" + payResult.get("movieId"));
            System.out.println("|价格：" + payResult.get("pay"));
            System.out.println("-----------------");
        }else{
            System.out.println("订单超时，已失效！");
        }
        // 下一步
        int nextOpr = UI.nextOprUI();
        switch (nextOpr){
            case 1:
                consumerMainUI(consumer);
                break;
            case 2:
                consumerPayUI(consumer);
                break;
        }
    }

    public static void consumerPayUI(Consumer consumer, String orderId){
        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.println("++++++缴费页面+++++++");
        System.out.println("请输入支付金额：");
        double pay = input.nextDouble();
        Map<String, String> payResult = consumer.pay(orderId, consumer, pay);
        // 支付成功则显示信息
        if(payResult.get("paySuccess").equals("1")){
            System.out.println("出票成功！");
            System.out.println("-----------------");
            System.out.println("|电影名称：" + payResult.get("movieName"));
            System.out.println("|放映厅：" + payResult.get("auditoriumId"));
            System.out.println("|上映时间：" + payResult.get("time"));
            System.out.println("|取票码：" + payResult.get("movieId"));
            System.out.println("|价格：" + payResult.get("pay"));
            System.out.println("-----------------");
        }else{
            System.out.println("订单超时，已失效！");
        }
        // 下一步
        int nextOpr = UI.nextOprUI();
        switch (nextOpr){
            case 1:
                consumerMainUI(consumer);
                break;
            case 2:
                consumerPayUI(consumer);
                break;
        }
    }


}
