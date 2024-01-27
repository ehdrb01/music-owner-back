package kr.co.strato.wrp.util;


import com.google.gson.Gson;

public class JsonUtils {

    public static <T> T parse(String str, Class<T> clazz){

    	Gson gson = new Gson();
    	T obj = gson.fromJson(str, clazz);

        return obj;
    }
}
