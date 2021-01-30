package com.working.interfaces;

import com.working.base.IBaseCallback;
import com.working.domain.StatBean;

public interface IStatCallback extends IBaseCallback {

    void onStatDataLoaded(StatBean data);

    void onStatDataLoadFailed(String msg);
}
