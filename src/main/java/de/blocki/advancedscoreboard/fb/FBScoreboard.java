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

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public List<?> getLines() {
        return lines;
    }

    public void setLines(List<?> lines) {
        this.lines = lines;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
