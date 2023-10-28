package entity;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * 电影排片
 */
public class ArrangeFilm {

    public ArrangeFilm(){}

    public ArrangeFilm(String filmName, int auditoriumId, float price, String time){
        this.filmName = filmName;
        this.auditoriumId = auditoriumId;
        this.price = price;
        this.time = time;

        // 初始化座位状态，空座为O 有人为X
        List<List<String>> seats = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < 12; j++) {
                list.add("O");
            }
            seats.add(list);
        }
        this.seats = seats;
        this.totalSeatNum = 7 * 12;
        this.vacantSearNum = 0;

    }

    private String filmName;
    private int auditoriumId;
    private float price;
    private String time;

    private int totalSeatNum;
    private int vacantSearNum;

    public int getTotalSeatNum() {
        return totalSeatNum;
    }

    public void setTotalSeatNum(int totalSeatNum) {
        this.totalSeatNum = totalSeatNum;
    }

    public int getVacantSearNum() {
        return vacantSearNum;
    }

    public void setVacantSearNum(int vacantSearNum) {
        this.vacantSearNum = vacantSearNum;
    }




    // 座位状态
    private List<List<String>> seats;

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public int getAuditoriumId() {
        return auditoriumId;
    }

    public void setAuditoriumId(int auditoriumId) {
        this.auditoriumId = auditoriumId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<List<String>> getSeats() {
        return seats;
    }

    public void setSeats(List<List<String>> seats) {
        this.seats = seats;
    }


}
