package com.gptool.chartgpt.pojo;

import java.util.List;
import java.util.Map;

public class OptionInfo {

    private Map<List<Topic>,List<Information>> optionInfo;

    public Map<List<Topic>,List<Information>> getOptionInfo() {
        return this.optionInfo;
    }

    public void setOptionInfo(Map<List<Topic>,List<Information>> optionInfo) {
        this.optionInfo = optionInfo;
    }
    
}
