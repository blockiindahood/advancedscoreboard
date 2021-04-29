package de.blocki.advancedscoreboard.fb;

import lombok.Getter;
import lombok.Setter;

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
