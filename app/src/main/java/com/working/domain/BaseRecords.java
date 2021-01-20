package com.working.domain;

import java.util.ArrayList;
import java.util.List;

public class BaseRecords<T> extends RecordsBean {
    private List<T> detailInfo;

    public void setDetailInfo(List<T> data) {
        detailInfo = data;
    }

    public List<T> getDetailInfo() {
        if (detailInfo == null) {

            detailInfo = new ArrayList<>();
        }
        return detailInfo;
    }
}
