package com.dat.transitiondemo;

import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Dat on 24-Aug-17.
 */

class DummyGenerator {
    private static String[] urls = {
        "https://pp.userapi.com/c639324/v639324508/3492a/AkGLw45Ivpo.jpg",
        "https://pp.userapi.com/c639324/v639324272/3db55/45JtI1UCUoU.jpg",
        "https://pp.userapi.com/c626616/v626616781/156f7/RC0mX0qa1Z4.jpg",
        "https://pp.userapi.com/c314516/v314516781/235d/P1ocj6ULMlo.jpg",
        "https://pp.userapi.com/c638430/v638430781/2bee8/MbUNRm1y2Ik.jpg",
        "https://pp.userapi.com/c637529/v637529781/2d52b/178X-ub7Qqo.jpg",
        "https://pp.userapi.com/c636631/v636631781/36522/g2qW8Fx3rM0.jpg",
        "https://pp.userapi.com/c628631/v628631781/22af5/SsuuAGoXL8o.jpg",
        "https://pp.userapi.com/c628122/v628122781/4ad25/5YGHkPeliI0.jpg",
        "https://pp.userapi.com/c630226/v630226781/1620c/0xUQniyAP9Y.jpg",
        "https://pp.userapi.com/c633129/v633129781/115e6/rS6JMqOKDvs.jpg",
        "https://pp.userapi.com/c625416/v625416781/54bed/q-tSyW4JimY.jpg"
    };

    @NonNull
    public static List<String> getImages() {
        List<String> res = new ArrayList<>();
        Collections.addAll(res, urls); 
        return res;
    }
}
