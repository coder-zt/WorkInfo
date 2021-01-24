package com.working.base;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.working.adapter.InspectionInfoAdapter;
import com.working.domain.ISearchInfo;
import com.working.domain.InspectionList;

import java.util.ArrayList;
import java.util.List;

public abstract class SearchAdapter<T extends RecyclerView.ViewHolder, F extends ISearchInfo> extends RecyclerView.Adapter<T> {


    protected List<F> filterData = new ArrayList<>();
    private String filterStr = "";

    protected int filterData(List<F> data) {
        filterData.clear();
        for (F datum : data) {
            String searchInfo = datum.getSearchInfo();
            if(searchInfo.contains(filterStr)){
                filterData.add(datum);
            }
        }
        return filterData.size();
    }

    public void search(String info) {
        filterStr = info;
        notifyDataSetChanged();
    }

}
