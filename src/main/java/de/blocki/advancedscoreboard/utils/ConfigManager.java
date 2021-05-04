package de.blocki.advancedscoreboard.utils;

import de.blocki.advancedscoreboard.main.AdvancedScoreboard;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConfigManager {

    private static File file = new File(AdvancedScoreboard.instance.getDataFolder(), "config.yml");
    private static YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);


    public static void setDef(String path, Object value){
        if(!isSet(path)){
            yamlConfiguration.set(path, value);
            save();
        }
    }

    public static boolean isSet(String path){
        return yamlConfiguration.isSet(path);
    }

    public static String get(String path){
        return yamlConfiguration.getString(path);
    }
    public static List<?> getList(String path){ return yamlConfiguration.getList(path); }
    public static String getString(String path){
        if(yamlConfiguration.isSet(path)) {
            return yamlConfiguration.getString(path);
        }else {
            return "Not Found: " + path;
        }
    }
    public static Object getObj(String path){
        if(yamlConfiguration.isSet(path)) {
            return yamlConfiguration.get(path);
        }else {
            return "Not Found: " + path;
        }
    }

    public static boolean getBool(String path){ return yamlConfiguration.getBoolean(path); }


    public static void save(){
        try{
            yamlConfiguration.save(file);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
