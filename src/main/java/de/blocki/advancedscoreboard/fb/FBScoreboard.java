package de.blocki.advancedscoreboard.fb;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class FBScoreboard {

    private List<?> lines;
    private String title;
    private Integer updateTime;

    public FBScoreboard(){
        lines = null;
        title = null;
        updateTime = 0;
    }

}
