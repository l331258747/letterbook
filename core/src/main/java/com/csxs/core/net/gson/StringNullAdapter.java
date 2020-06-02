package com.csxs.core.net.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by sins
 * Date : 2018/12/7
 * Desc :
 */
public class StringNullAdapter extends TypeAdapter<String> {
    @Override
    public String read(JsonReader reader) throws IOException {
//         || "null".equals(reader.peek().toString().toLowerCase())
        if (JsonToken.NULL.equals(reader.peek())) {
            reader.nextNull();
            return "";
        }
        return reader.nextString();
    }
    @Override
    public void write(JsonWriter writer, String value) throws IOException {
        if (value == null) {
            writer.value("");
            return;
        }
        writer.value(value);
    }

    public static void main(String[] args) {
//        GsonBuilder gsonBuilder = new GsonBuilder();
//
//        //注册自定义String的适配器
//        gsonBuilder.registerTypeAdapter(String.class, new StringNullAdapter());
//
//        Gson gson = gsonBuilder.create();
//        User userBean = new User();
//        userBean.setAge("19");
//        userBean.setName(null);
//        String s=gson.toJson(userBean);
//        System.out.println(gson.toJson(userBean));
//        User json=gson.fromJson(s,User.class);
//        System.out.println(json.toString());
        //Result<List<String>> listResult=new Result<>();
        // 在类的外部这样获取
//        Type type = ((ParameterizedType)listResult.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//        System.out.println(type);
        // 在类的内部这样获取
        //System.out.println(foo.getTClass());


        //System.out.println(((ParameterizedType)listResult.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

}
