package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author lh
 * @Date 2023/8/24 14:38
 * @Description 经理类
 **/
public class Manage extends User {

    // 空构造方法
    public Manage(){}

    public Manage(String username, String name, String password, String phone, String email){
        super(username, name, password, phone, email);
    }

    /**
     * 根据用户名得到经理信息 将用户信息封装为对象
     * @param username 用户名
     * @return 经理信息
     */
    public Manage getManageByUsername(String username){
        Manage loginManage = null;

        for (Manage manage: Global.manages) {
            if(manage.getUsername().equals(username)){
                loginManage = manage;
            }
        }

        return loginManage;
    }


    /**
     * 更新经理密码
     * @param manage 经理信息
     */
    public void updateManagePassword(Manage manage){
        for (Manage manageTmp:
                Global.manages) {
            if(manageTmp.getUsername().equals(manage.getPassword())){
                manageTmp.setPassword(manage.getPassword());
            }
        }
    }

    /**
     * 重置消费者密码
     * 将消费者密码重置为默认密码
     * @param consumerUsername 消费者账户
     */
    public void resetConsumerPassword(String consumerUsername){
        for (Consumer consumer: Global.consumers){
            if(consumer.getUsername().equals(consumerUsername)){
                consumer.setPassword("123456");
            }
        }
    }

    /**
     * 根据用户名判断消费者用户是否已存在
     * @param checkUsername 用户名
     * @return 存在返回true，否则返回false
     */
    public boolean userExist(String checkUsername){

        for (Consumer consumer: Global.consumers){
            String username = consumer.getUsername();
            if(username.equals(checkUsername)){
                return true;
            }
        }

        return false;
    }

    /**
     * 获取到所有上映影片
     * @return 所有上映影片
     */
    public List<Film> getAllFilms(){
        return Global.films;
    }

    /**
     * 判断电影是否一存在
     * @param filmName 电影名
     * @return 是否存在
     */
    public boolean filmExist(String filmName){
        for (Film film :
                Global.films) {
            if (film.getFilmName().equals(filmName)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 增加电影
     * @param film 电影信息
     */
    public void addFilm(Film film){
        Global.films.add(film);
    }

    /**
     * 根据电影名称查询电影信息
     * @param fileName 电影名
     * @return 电影信息封装成的对象
     */
    public Film getFilmByName(String fileName){
        Film queryFilm = null;
        for (Film film :
                Global.films) {
            if(film.getFilmName().equals(fileName)){
                queryFilm = film;
            }
        }

        return queryFilm;
    }

    /**
     * 将影片信息更新到影片列表里
     * @param updateFilm 影片信息
     */
    public void updateFilm(Film updateFilm){
        for (Film film:
             Global.films) {
            if(film.getFilmName().equals(updateFilm.getFilmName())){
                film.setFilmDirector(updateFilm.getFilmDirector());
                film.setDuration(updateFilm.getDuration());
                film.setIntroduction(updateFilm.getIntroduction());
                film.setStarring(updateFilm.getStarring());
                film.setDuration(updateFilm.getDuration());
            }
        }
    }

    /**
     * 删除影片
     * @param filmName 影片名称
     */
    public void deleteFilm(String filmName){
        ArrayList<Film> newFilms = new ArrayList<>();

        for (Film film :
                Global.films) {
            if (!film.getFilmName().equals(filmName)) {
                newFilms.add(film);
            }
        }
        Global.films = newFilms;
    }

    /**
     * 根据多个字段查询电影
     * @param queryFilm 查询信息封装成的对象
     * @return 返回符合要求的所有影片
     */
    public List<Film> queryFilm(Film queryFilm){
        List<Film> films = new ArrayList<>();

        for (Film film:
             Global.films) {
            // 判断名称
            boolean nameRight = true;
            // 去空格
            if(queryFilm.getFilmName()!=null || queryFilm.getFilmName().trim() != ""){
                nameRight = queryFilm.getFilmName().equals(film.getFilmName());
            }

            // 判断导演
            boolean directorRight = true;
            if(queryFilm.getFilmDirector()!=null || queryFilm.getFilmDirector().trim()!=""){
                directorRight = queryFilm.getFilmDirector().equals(film.getFilmDirector());
            }

            // 判断主演
            boolean starringRight = true;
            if(queryFilm.getStarring()!=null || queryFilm.getStarring().trim()!=""){
                starringRight = queryFilm.getStarring().equals(film.getStarring());
            }

            if(nameRight && directorRight && starringRight){
                films.add(film);
            }
        }

        return films;

    }

    /**
     * 添加电影场次
     * @param arrangeFilm 电影场次
     */
    public void addArrangeFilm(ArrangeFilm arrangeFilm){
        Global.arrangeFilms.add(arrangeFilm);
    }

    /**
     * 更新电影场次
     * @param updateArrangeFilm 要更新的场次信息
     */
    public void updateArrangeFilm(ArrangeFilm updateArrangeFilm){
        // 根据放映厅id和时间段查询场次，然后进行更新
        for (ArrangeFilm arrangeFilm :
                Global.arrangeFilms) {
            if(arrangeFilm.getAuditoriumId()==updateArrangeFilm.getAuditoriumId()
                && Objects.equals(arrangeFilm.getTime(), updateArrangeFilm.getTime())){
                arrangeFilm.setFilmName(updateArrangeFilm.getFilmName());
                arrangeFilm.setPrice(updateArrangeFilm.getPrice());
            }
        }
    }

    /**
     * 删除影片场次
     * @param deleteArrangeFilm
     */
    public void deleteArrangeFilm(ArrangeFilm deleteArrangeFilm){
        ArrayList<ArrangeFilm> newArrangeFilm = new ArrayList<>();
        for (ArrangeFilm arrangeFilm :
                Global.arrangeFilms) {
            if (!(arrangeFilm.getTime().equals(deleteArrangeFilm.getTime())
                    && arrangeFilm.getAuditoriumId()==deleteArrangeFilm.getAuditoriumId())){
                newArrangeFilm.add(arrangeFilm);
            }
        }

        Global.arrangeFilms = newArrangeFilm;
    }

    /**
     * 获取所有的影片场次
     * @return 所有场次信息
     */
    public List<ArrangeFilm> getAllArrangeFilm(){
        return Global.arrangeFilms;
    }

    /**
     * 获取所有消费者信息
     * @return
     */
    public List<Consumer> getConsumerInfo(){
        return Global.consumers;
    }

    /**
     * TODO 查询消费者信息
     * @param queryConsumer
     * @return
     */
    public List<Consumer> queryConsumerInfo(Consumer queryConsumer){
        List<Consumer> consumers = new ArrayList<>();

        return consumers;
    }


}
