package com.elvis.mzmanager.network;

import com.elvis.mzmanager.entity.CardEntity;
import com.elvis.mzmanager.entity.CardEntity.RowsBean.CardLifeBean;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

public class CardLifeBeanAdapter implements JsonDeserializer<CardLifeBean> {
    @Override
    public CardLifeBean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        try {
            if (json.getAsString().equals("") || json.getAsString().equals("null")) {
                return null;
            } else
                return new CardLifeBean();
        }catch (UnsupportedOperationException e){

         return new Gson().fromJson(json.getAsJsonObject(),CardLifeBean.class) ;
        }
    }

//    @Override
//    public JsonElement serialize(CardLifeBean src, Type typeOfSrc, JsonSerializationContext context) {
//        return new JsonPrimitive(src);
//    }
}