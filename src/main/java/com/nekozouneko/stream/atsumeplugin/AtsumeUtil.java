package com.nekozouneko.stream.atsumeplugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AtsumeUtil {

    public static List<String> toUpperList(List<String> list) {
        List<String> arr = new ArrayList<>();

        for (String cont : list) {
            arr.add(cont.toUpperCase(Locale.ENGLISH));
        }

        return arr;
    }

    public static Object checkNull(Object a, Object b) {
        if (a == null) return b;
        else return a;
    }

}
