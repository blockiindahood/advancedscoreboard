package de.blocki.advancedscoreboard.utils;

import de.blocki.advancedscoreboard.main.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConfigManager {

    private static File file = new File(Main.instance.getDataFolder(), "config.yml");
    private static YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);


    public static void set(String path, Object value){
        yamlConfiguration.set(path, value);
        save();
    }

    public static String get(String path){
        return yamlConfiguration.getString(path);
    }
    public static List<?> getList(String path){ return yamlConfiguration.getList(path); }


    public static void save(){
        try{
            yamlConfiguration.save(file);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
