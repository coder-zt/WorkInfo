package com.working.fragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.working.R;
import com.working.adapter.MineListAdapter;
import com.working.base.BaseFragment;
import com.working.databinding.FragmentMineBinding;
import com.working.domain.LoginInfo;
import com.working.setting.MineMenuItem;
import com.working.utils.AppRouter;
import com.working.utils.UserDataMan;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页-我的Fragment
 */
public class MineFragment extends BaseFragment<FragmentMineBinding> {
    private static final String TAG = "MineFragment";
    @Override
    protected void initView(View view) {
        RecyclerView rvMineList = (RecyclerView)view.findViewById(R.id.rv_mine_list);
        rvMineList.setLayoutManager(new LinearLayoutManager(getContext()));
        MineListAdapter adapter = new MineListAdapter();
        rvMineList.setAdapter(adapter);
        List<MineMenuItem> mDatas = new ArrayList<>();
        mDatas.add(new MineMenuItem(getResources().getDrawable(R.mipmap.user_info_icon),
                "个人信息", new MineMenuItem.OnItemClickedListener() {
            @Override
            public void onItemClick() {
                if (UserDataMan.getInstance().isLogin()) {
                    AppRouter.toUserInfoActivity(getActivity());
                }else{
                    AppRouter.toLoginActivity(getActivity());
                }
            }
        }));

        mDatas.add(new MineMenuItem(getResources().getDrawable(R.mipmap.inspection_icon),
                "巡检记录", new MineMenuItem.OnItemClickedListener() {
            @Override
            public void onItemClick() {
                AppRouter.toInspectionActivity(getActivity());
            }
        }));

        mDatas.add(new MineMenuItem(getResources().getDrawable(R.mipmap.purchase_icon),
                "采购清单", new MineMenuItem.OnItemClickedListener() {
            @Override
            public void onItemClick() {
                AppRouter.toListActivity(getActivity(), 2);
            }
        }));

        mDatas.add(new MineMenuItem(getResources().getDrawable(R.mipmap.order_icon),
                "购买记录", new MineMenuItem.OnItemClickedListener() {
            @Override
            public void onItemClick() {
                AppRouter.toListActivity(getActivity(), 3);
            }
        }));
        mDatas.add(new MineMenuItem(getResources().getDrawable(R.mipmap.repertory_icon),
                "库存记录", new MineMenuItem.OnItemClickedListener() {
            @Override
            public void onItemClick() {
                AppRouter.toRepertoryManageActivity(getActivity());
            }
        }));
        adapter.setData(mDatas);
    }

    @Override
    public void onResume() {
        super.onResume();
        LoginInfo loginInfo = UserDataMan.getInstance().getLoginInfo();
        getDataBinding().setLoginInfo(loginInfo);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

}
