package de.blocki.advancedscoreboard.utils;

import de.blocki.advancedscoreboard.AdvancedScoreboard;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConfigManager {

    private final File file;
    private final YamlConfiguration yamlConfiguration;


    public ConfigManager(String filename){
        file = new File(AdvancedScoreboard.getPlugin().getDataFolder(), filename);
        yamlConfiguration = YamlConfiguration.loadConfiguration(file);
    }


    public void setDef(String path, Object value){
        if(!isSet(path)){
            yamlConfiguration.set(path, value);
            save();
        }
    }

    public boolean isSet(String path){
        return yamlConfiguration.isSet(path);
    }

    public String get(String path){
        return yamlConfiguration.getString(path);
    }
    public List<?> getList(String path){ return yamlConfiguration.getList(path); }

    public String getString(String path, boolean translateColorCodes){
        if(yamlConfiguration.isSet(path)) {
            return (translateColorCodes ? ChatColor.translateAlternateColorCodes('&', yamlConfiguration.getString(path)) : yamlConfiguration.getString(path));
        }else {
            return "Not Found: " + path;
        }
    }
    public Object getObj(String path){
        if(yamlConfiguration.isSet(path)) {
            return yamlConfiguration.get(path);
        }else {
            return "Not Found: " + path;
        }
    }

    public boolean getBool(String path){ return yamlConfiguration.getBoolean(path); }


    public void save(){
        try{
            yamlConfiguration.save(file);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
