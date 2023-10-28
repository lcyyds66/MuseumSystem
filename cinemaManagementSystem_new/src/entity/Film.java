package entity;

public class Film {

    public Film(){}

    public Film(String filmName, String filmDirector, String starring, String introduction, int duration){
        this.filmName = filmName;
        this.filmDirector = filmDirector;
        this.starring = starring;
        this.introduction = introduction;
        this.duration = duration;
    }

    private String filmName;
    private String filmDirector;
    private String starring;
    private String introduction;
    private int duration;


    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getFilmDirector() {
        return filmDirector;
    }

    public void setFilmDirector(String fileDirector) {
        this.filmDirector = fileDirector;
    }

    public String getStarring() {
        return starring;
    }

    public void setStarring(String starring) {
        this.starring = starring;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


}
