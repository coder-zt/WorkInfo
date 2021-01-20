package com.working.presenter.impl;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.working.base.BasePresenterImpl;
import com.working.domain.IndexNotice;
import com.working.interfaces.IIndexInfoCallback;
import com.working.interfaces.IInspectCallback;
import com.working.models.AppModels;
import com.working.presenter.IIndexInfoPresenter;

import java.util.List;

/**
 * 主页-首页banner/公告信息Presenter
 */
public class IndexInfoPresenterImpl extends BasePresenterImpl implements IIndexInfoPresenter {

    private final int pageSize = 20;
    private int page = 1;

    @Override
    public void loadNoticeData() {
        page = 1;
        AppModels.getInstance().getIndexNotice(page, pageSize, new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                IndexNotice notice = null;
                if (msg.obj instanceof IndexNotice) {
                    notice = (IndexNotice) msg.obj;
                }
                if (msg.what == -1 || notice == null || notice.getData() == null) {
                    ((IIndexInfoCallback)mCallback).onLoadFail("首页公告数据加载失败");
                    return false;
                }
                List<IndexNotice.DataBean.RecordsBean> records = notice.getData().getRecords();
                if (records == null) {
                    ((IIndexInfoCallback)mCallback).onLoadFail("首页公告数据加载失败");
                    return false;
                }
                ((IIndexInfoCallback)mCallback).onIndexNoticeLoaded(records);
                return false;
            }
        });
    }

    @Override
    public void loadMoreNoticeData() {
        page++;
        AppModels.getInstance().getIndexNotice(page, pageSize, new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                IndexNotice notice = (IndexNotice) msg.obj;
                if (msg.what == -1 || notice == null || notice.getData() == null) {
                    ((IIndexInfoCallback)mCallback).onLoadMoreFail("首页公告数据加载更多失败");
                    page--;
                    return false;
                }

                List<IndexNotice.DataBean.RecordsBean> records = notice.getData().getRecords();
                if (records == null) {
                    ((IIndexInfoCallback)mCallback).onLoadMoreFail("首页公告数据加载更多失败");
                    page--;
                    return false;
                }
                ((IIndexInfoCallback)mCallback).onIndexNoticeLoadedMore(records);
                return false;
            }
        });
    }
}
