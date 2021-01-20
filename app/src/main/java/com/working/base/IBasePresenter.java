package com.working.base;


public interface IBasePresenter{

    void registerCallback(IBaseCallback callback);

    void unregisterCallback();
}
