package entity;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author lh
 * @Date 2023/8/24 14:42
 * @Description //前台人员
 **/
public class Receptionist extends User{

    // 空构造方法
    public Receptionist(){}

    public Receptionist(String username, String name, String password, String phone, String email){
        super(username, name, password, phone, email);
    }

    /**
     * 根据用户名得到前台信息 将用户信息封装为对象
     * @param username 用户名
     * @return 经理信息
     */
    public Receptionist getReceptionistByUsername(String username){
        Receptionist loginReceptionist = null;

        for (Receptionist receptionist: Global.receptionists) {
            if(receptionist.getUsername().equals(username)){
                loginReceptionist = receptionist;
            }
        }

        return loginReceptionist;
    }

    /**
     * 根据消费者账号查询消费者信息
     * @param username 消费者账号
     * @return 消费者信息
     */
    public Consumer getConsumerInfoByUsername(String username){
        Consumer consumer = null;
        for (Consumer consumerInfo :
                Global.consumers) {
            if (consumerInfo.getUsername().equals(username)){
                consumer = consumerInfo;
            }
        }
        
        return consumer;
    }

    /**
     * 获取所有影片信息
     * @return 影片列表
     */
    public List<Film> getAllFilm(){
        return Global.films;
    }

    /**
     * 根据电影名和放映时间查询场次信息
     * @return 场次信息
     */
    public ArrangeFilm queryArrangeFilm(String queryFilmName, String queryTime){
        ArrangeFilm  queryArrangeFilm = null;

        for (ArrangeFilm arrangeFilm :
                Global.arrangeFilms) {
            if(arrangeFilm.getFilmName().equals(queryFilmName)
                && arrangeFilm.getTime().equals(queryTime)){
                queryArrangeFilm = arrangeFilm;
            }
        }

        return queryArrangeFilm;
    }

    /**
     * 售票
     * @param filmName 电影名
     * @param time 放映时间
     * @param consumerUsername 消费者账号
     * @param row 座位行号
     * @param col 座位列号
     */
    public Map<String, String> sale(String filmName, String time, String consumerUsername, int row, int col){
        // 最终的电影票信息
        Map<String, String > movieTicket = new HashMap<>();
        //拿到消费者信息
        Consumer consumer = getConsumerInfoByUsername(getUsername());

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

                // 更新消费者信息
                consumer.setTotalSpending(consumer.getTotalSpending()+pay);
                consumer.setConsumptionTimes(consumer.getConsumptionTimes()+1);
                updateConsumerInfo(consumer);

                // 更新电影票信息
                movieTicket.put("pay", String.valueOf(pay));
                // 电影票电子ID
                UUID movieId = UUID.randomUUID();
                movieTicket.put("movieId", movieId.toString());
                //电影票其他信息
                movieTicket.put("movieName", arrangeFilm.getFilmName());
                movieTicket.put("auditoriumId", String.valueOf(arrangeFilm.getAuditoriumId()));
                movieTicket.put("time", arrangeFilm.getTime());

                //更新消费记录
                PayInfo payInfo = new PayInfo();
                payInfo.setPay(pay);
                Date date = new Date();
                String strDateFormat = "yyyy-MM-dd HH:mm:ss";
                SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
                payInfo.setPayDate(sdf.format(date));
                payInfo.setFilmName(filmName);
                payInfo.setUsername(consumerUsername);
                Global.payInfos.add(payInfo);
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

}
