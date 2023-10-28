package utils.others;

import entity.Global;
import entity.OrderInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

/**
 * 订单定时任务
 * 定时查询用户订单是否付款，若超过两分钟未付款，则将订单信息取消，释放座位
 */
public class OrderTask extends TimerTask {

    @Override
    public void run() {
        ArrayList<OrderInfo> newOrderInfos = new ArrayList<>();
        for (OrderInfo orderInfo:
                Global.orderInfos) {
            Date orderTime = orderInfo.getOrderDate();
            Date now = new Date();
            long timeInterval = now.getTime() - orderTime.getTime();
            long day=timeInterval/(24*60*60*1000);
            long hour=(timeInterval/(60*60*1000)-day*24);
            long min=((timeInterval/(60*1000))-day*24*60-hour*60);

            // 如果订单时间距离现在小于2分钟，则保留，否则不保留
            if(min < 2){
                newOrderInfos.add(orderInfo);
            }
        }

        // 更新待支付订单列表
        Global.orderInfos = newOrderInfos;
    }
}
