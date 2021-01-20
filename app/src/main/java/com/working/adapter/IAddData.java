package com.working.adapter;

import java.util.List;

interface IAddData<T> {

    void setCollectData(List<T> data);

    void addCollectData(List<T> data);
}
