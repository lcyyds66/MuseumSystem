package entity;

import entity.User;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author lh
 * @Date 2023/8/24 10:28
 * @Description //TODO
 **/
public class Consumer extends User {

    private String vipLevel;
    private double totalSpending = 0f;
    private int consumptionTimes = 0;

    public String getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(String vipLevel) {
        this.vipLevel = vipLevel;
    }

    public double getTotalSpending() {
        return totalSpending;
    }

    public void setTotalSpending(double totalSpending) {
        this.totalSpending = totalSpending;
    }

    public int getConsumptionTimes() {
        return consumptionTimes;
    }

    public void setConsumptionTimes(int consumptionTimes) {
        this.consumptionTimes = consumptionTimes;
    }

    /**
     * 根据用户名得到消费者信息 将用户信息封装为对象
     * @param username 用户名
     * @return 经理信息
     */
    public Consumer getConsumertByUsername(String username){
        Consumer consumer = null;

        for (Consumer consumer1: Global.consumers) {
            if(consumer1.getUsername().equals(username)){
                consumer = consumer1;
            }
        }

        return consumer;
    }

    /**
     * 购票
     * @param filmName 电影名
     * @param time 放映时间
     * @param consumer 消费者
     * @param row 行
     * @param col 列
     * @return 电影票
     */
    public Map<String, String> buy(String filmName, String time, Consumer consumer, int row, int col){
        // 最终的电影票信息
        Map<String, String > movieTicket = new HashMap<>();

        // 更新场次信息
        for (ArrangeFilm arrangeFilm :
                Global.arrangeFilms) {
            if (arrangeFilm.getFilmName().equals(filmName) && arrangeFilm.getTime().equals(time)){
                float price = arrangeFilm.getPrice();
                // 计算折扣
                String consumerType = consumer.getVipLevel();
                double pay = 0;
                if(consumerType.equals("gold")){
                    pay = price * 0.88;
                } else if (consumerType.equals("silver")) {
                    pay = price * 0.95;
                }else{
                    pay = price;
                }

                // 更新座位信息
                arrangeFilm.getSeats().get(row).set(col, "X");

                // 更新电影票信息
                movieTicket.put("pay", String.valueOf(pay));

                // 生成订单号
                UUID orderId = UUID.randomUUID();
                movieTicket.put("oderId", orderId.toString());
                movieTicket.put("time", arrangeFilm.getTime());
                movieTicket.put("auditoriumId", String.valueOf(arrangeFilm.getAuditoriumId()));

                // 生成订单信息，加入订单列表
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setOrderDate(new Date());
                orderInfo.setOrderId(orderId.toString());
                orderInfo.setFilmName(filmName);
                orderInfo.setTime(time);
                orderInfo.setSeatRow(row);
                orderInfo.setSeatCol(col);
                orderInfo.setAuditoriumId(arrangeFilm.getAuditoriumId());
                Global.orderInfos.add(orderInfo);

            }
        }

        return movieTicket;
    }

    /**
     * 更新消费者消费信息
     * @param consumer
     */
    public void updateConsumerInfo(Consumer consumer){
        for (Consumer con :
                Global.consumers) {
            if (con.getUsername().equals(consumer.getUsername())){
                con.setTotalSpending(consumer.getTotalSpending());
                con.setConsumptionTimes(consumer.getConsumptionTimes());
            }
        }
    }

    /**
     * TODO 若支付不够咋办
     * 支付
     * @param orderId
     * @param consumer
     * @return
     */
    public Map<String, String> pay(String orderId, Consumer consumer, double pay){

        Map<String, String> payResult = new HashMap<>();

        OrderInfo orderInfo = null;

        // 查询订单 如果没有，则说明已经被删除，已经过期
        // 如果存在，则证明可以支付，同时将订单从待支付订单中删除
        ArrayList<OrderInfo> newOrderInfos = new ArrayList<>();
        for (OrderInfo order :
                Global.orderInfos) {
            if(order.getOrderId().equals(orderId)){
                orderInfo = order;
            }else{
                newOrderInfos.add(order);
            }
        }
        // 更新待支付订单
        Global.orderInfos = newOrderInfos;

        // 如果查询为空，则直接返回
        if(orderInfo == null){
            payResult.put("paySuccess", "0");
        }else{
            payResult.put("paySuccess", "1");
            // 更新消费者信息
            consumer.setTotalSpending(consumer.getTotalSpending()+pay);
            consumer.setConsumptionTimes(consumer.getConsumptionTimes()+1);
            updateConsumerInfo(consumer);

            // 更新电影票信息
            payResult.put("pay", String.valueOf(pay));
            // 电影票电子ID
            UUID movieId = UUID.randomUUID();
            payResult.put("movieId", movieId.toString());
            //电影票其他信息
            payResult.put("movieName", orderInfo.getFilmName());
            payResult.put("auditoriumId", String.valueOf(orderInfo.getAuditoriumId()));
            payResult.put("time", orderInfo.getTime());

            //更新消费记录
            PayInfo payInfo = new PayInfo();
            payInfo.setPay(pay);
            Date date = new Date();
            String strDateFormat = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
            payInfo.setPayDate(sdf.format(date));
            payInfo.setFilmName(orderInfo.getFilmName());
            payInfo.setUsername(consumer.getUsername());
            Global.payInfos.add(payInfo);
        }

        return payResult;
    }


}
