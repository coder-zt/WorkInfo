package com.working.base;


/**
 * presenter基类
 */
public class BasePresenterImpl implements IBasePresenter{

    protected IBaseCallback mCallback;
    protected int page_1 = 1;//上报页数
    protected int page_0 = 1;//未上报页数
    protected final int pageSize = 20;//当前页数

    @Override
    public void registerCallback(IBaseCallback callback) {
        mCallback = callback;
    }

    @Override
    public void unregisterCallback() {
        mCallback = null;
    }


    /**
     * 获取加载数据的页数
     * @param isCommit
     * @param isLoadMore
     * @return
     */
    protected int getPage(boolean isCommit, boolean isLoadMore) {
        if (isLoadMore) {
            if(isCommit){
                return ++page_1;
            }else{
                return ++page_0;
            }
        }else{
            if(isCommit){
                return page_1 = 1;
            }else{
                return page_0 = 1;
            }
        }

    }

    /**
     * 修改页数当请求数据更多时
     * @param isCommit
     */
    protected void setPageOnError(boolean isCommit) {

        if(isCommit){
            page_1--;
        }else{
            page_0--;
        }
    }
}
