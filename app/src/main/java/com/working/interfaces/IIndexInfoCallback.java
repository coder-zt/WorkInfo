package com.working.interfaces;

import com.working.base.IBaseCallback;
import com.working.domain.IndexNotice;

import java.util.List;

public interface IIndexInfoCallback extends IBaseCallback {

    void onIndexNoticeLoaded(List<IndexNotice.DataBean.RecordsBean> data);

    void onLoadFail(String msg);

    void onIndexNoticeLoadedMore(List<IndexNotice.DataBean.RecordsBean> data);

    void onLoadMoreFail(String msg);
}
