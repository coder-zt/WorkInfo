package com.working.base;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.working.R;
import com.working.adapter.IndexPagerAdapter;
import com.working.databinding.ActivityRepertoryInLayoutBinding;
import com.working.other.MessageEvent;
import com.working.utils.TimeUtil;
import com.working.utils.ToastUtil;
import com.working.utils.UserDataMan;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 清单页面的基类
 */
public abstract class ZTBaseListActivity extends BaseActivity {

    private static final String TAG = "BaseListActivity";
    private ActivityRepertoryInLayoutBinding mBinding;
    protected ViewPager mVpFragmentContainer;
    protected ListFragment mCommittedFragment;
    protected ListFragment mUnCommitFragment;
    protected int mActivityCode;
    private boolean mIsSearch;
    private String mStartTime;
    private String mEndTime;
    //    private EditText mEtSearchInput;
    private long end,start;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentData();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_repertory_in_layout);
        mBinding.setActivity(this);
        mBinding.setTitle(getActivityTitle());
        mBinding.setIsCreate(getIsCreate());
        initView();
        initListener();
    }

    protected abstract Boolean getIsCreate();

    private void getIntentData() {
        mActivityCode = getIntent().getIntExtra("data", -1);
        if (mActivityCode == -1) {
            ToastUtil.showMessage("参数错误！");
            finish();
        }
    }

    /**
     * 该Activity的顶部标题
     *
     * @return
     */
    protected abstract String getActivityTitle();

    private void initView() {
        mVpFragmentContainer = (ViewPager) findViewById(R.id.viewpager);
        List<Fragment> fragments = new ArrayList<>();

        UserDataMan instance = UserDataMan.getInstance();
        if (instance.checkSecondApprovalGrant() || instance.checkFirstApprovalGrant()) {
            mBinding.setIsApproval(true);
            mVpFragmentContainer.setCurrentItem(0);
        } else {
            mUnCommitFragment = getListFragment(false);
            fragments.add(mUnCommitFragment);
        }
        mCommittedFragment = getListFragment(true);
        if (mCommittedFragment != null) {
            fragments.add(mCommittedFragment);
        }else{
            mBinding.bottomNavigationView.setVisibility(View.GONE);
            mBinding.llSearch.setVisibility(View.GONE);
        }
        mVpFragmentContainer.setAdapter(new IndexPagerAdapter(fragments, getSupportFragmentManager()));
//        mEtSearchInput = findViewById(R.id.et_search);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        if (messageEvent.getMessage().equals(MessageEvent.upload_file_success)) {
            mVpFragmentContainer.setCurrentItem(1);
        }
    }

    /**
     * 子类提供Fragment
     *
     * @param iusCommit
     * @return
     */
    protected abstract ListFragment getListFragment(boolean iusCommit);

    private void initListener() {
        mVpFragmentContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mBinding.setIsCommit(position == 1);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        mEtSearchInput.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                Log.d(TAG, "onTextChanged: " +s.toString());
//                searchWithFragments(s.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                Log.d(TAG, "afterTextChanged: "+s.toString());
//            }
//        });
    }

    protected void searchWithFragments(String startTime, String endTime) {
        if (mCommittedFragment != null) {
            mCommittedFragment.search(startTime, endTime);
        }
        if (mUnCommitFragment != null) {
            mUnCommitFragment.search(startTime, endTime);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

    }


    /**
     * 搜索功能
     */
    public void onSearch() {
        mIsSearch = true;
        mBinding.setIsSearch(true);
//        mEtSearchInput.setText("");
    }

    public void onSelectTime(boolean isEnd) {
        //获取日历的一个对象
        Calendar calendar = Calendar.getInstance();
        //获取年月日时分的信息
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if (isEnd) {
                    end = TimeUtil.getTimeInMills(year, month,dayOfMonth);
                    mEndTime = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth);
                    mBinding.endTime.setText(mEndTime);
                    if (mStartTime != null) {
                        if (start > end){
                            ToastUtil.showMessage("开始时间不得大于结束时间");
                            return;
                        }
                        searchWithFragments(mStartTime, mEndTime);
                    }
                } else {
                    start = TimeUtil.getTimeInMills(year, month,dayOfMonth);
                    mStartTime = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth);
                    mBinding.startTime.setText(mStartTime);
                    if (mEndTime != null) {
                        if (start > end){
                            ToastUtil.showMessage("开始时间不得大于结束时间");
                            return;
                        }
                        searchWithFragments(mStartTime, mEndTime);
                    }
                }
            }
        }, year, month, day).show();
    }

    /**
     * 设置当前ViewPager中的Item
     *
     * @param position
     */
    public void onSelectItem(int position) {
        mVpFragmentContainer.setCurrentItem(position);
    }

    //相关控件的可见性设置
    public abstract void create();
}
