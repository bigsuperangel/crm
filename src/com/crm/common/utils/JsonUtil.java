package com.crm.common.utils;

import java.lang.reflect.Field;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
	private String[] filter; //过滤属性
	private String[][] field;  //替换属性名称
	private static Gson gson = new Gson();
	
	public void setFilter(String[] filter){
		this.filter = filter;
	}
	
	public void setField(String[][] field) {//{{"name"，"text"}}
		this.field = field;
	}

	public String getJson(Object obj){
		Gson gson = new GsonBuilder()
				 .setFieldNamingStrategy(new MyFieldNamingStrategy())//替换属性
		         .setExclusionStrategies(new MyExclusionStrategy())//过滤属性
				.create();
		return gson.toJson(obj);
	}
	
	public static String toJson(Object obj){
		return gson.toJson(obj);
	}
	
	private class MyFieldNamingStrategy implements FieldNamingStrategy {
        public String translateName(Field f) {
        	if(field == null){
        		return f.getName();
        	}
        	for(int i = 0;i<field.length;i++){
        		if (field[i][0].equals(f.getName())) {
                    return field[i][1];
                }
        	}
            return f.getName();
        }
    }
	
	private class MyExclusionStrategy implements ExclusionStrategy{
        
        public boolean shouldSkipField(FieldAttributes filterAtt) {
        	if(filter == null){
        		return false;
        	}
        	for(int i = 0; i<filter.length; i++){
        		if(filter[i].equals(filterAtt.getName())){
        			return true;
        		}
        	}
        	return false;
        }

        public boolean shouldSkipClass(Class<?> clazz) {             
            return false;
        }      
    }
}
