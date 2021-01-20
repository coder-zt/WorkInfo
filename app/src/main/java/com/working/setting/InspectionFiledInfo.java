package com.working.setting;

import java.util.List;

public class InspectionFiledInfo {

    private static final String TAG = "InspectionFiledInfo";
    /**
     * field : diseaseType
     * alias : 病害类型
     * show : 1
     * type : integer
     * input_type : 1
     * value :
     * range : ["局部修理挡土墙","局部修理路肩墙"]
     */

    private String field;
    private String alias;
    private int show;
    private String type;
    private int input_type;
    private String value;
    private List<String> range;
    private int rangeIndex;


    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getShow() {
        return show;
    }

    public void setShow(int show) {
        this.show = show;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getInput_type() {
        return input_type;
    }

    public void setInput_type(int input_type) {
        this.input_type = input_type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<String> getRange() {
        return range;
    }

    public void setRange(List<String> range) {
        this.range = range;
    }

    public int getRangeIndex() {
        return rangeIndex;
    }

    public void setRangeIndex(int rangeIndex) {
        this.rangeIndex = rangeIndex;
    }

    @Override
    public String toString() {
        return "InspectionFiledInfo{" +
                "field='" + field + '\'' +
                ", alias='" + alias + '\'' +
                ", show=" + show +
                ", type='" + type + '\'' +
                ", input_type=" + input_type +
                ", value='" + value + '\'' +
                '}';
    }
}
