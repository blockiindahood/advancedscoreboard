package de.blocki.advancedscoreboard.fb;

import java.util.List;

public class FBScoreboard {

    private List<?> lines;
    private String title;
    private Integer updateTime;

    public FBScoreboard(){
        lines = null;
        title = null;
        updateTime = 0;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public FBScoreboard setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public List<?> getLines() {
        return lines;
    }

    public FBScoreboard setLines(List<?> lines) {
        this.lines = lines;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public FBScoreboard setTitle(String title) {
        this.title = title;
        return this;
    }
}
