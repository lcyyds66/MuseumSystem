package utils.others;

import entity.ArrangeFilm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;

// 测试类 可忽略

public class Test {
    public static void main(String[] args) {
        ArrangeFilm arrangeFilm = new ArrangeFilm("aa", 1, 1.1f, "ss");
//        List<ArrangeFilm> arrangeFilms = new ArrayList<>();
        Util.printArrangeFilmInfo(arrangeFilm);

    }

}

