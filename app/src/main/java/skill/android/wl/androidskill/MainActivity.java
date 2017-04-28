package skill.android.wl.androidskill;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import skill.android.wl.androidskill.adapter.ViewPagerFragmentAdapter;
import skill.android.wl.androidskill.mvp.presenter.MainPresenter;
import skill.android.wl.androidskill.mvp.view.ui.BaseActivity;
import skill.android.wl.androidskill.mvp.view.ui.fragment.TypeFragment;

public class MainActivity extends BaseActivity<MainPresenter> {

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    private ViewPagerFragmentAdapter mViewpagerFragmentAdapter;
    private List<String> mTitles;
    private ArrayList<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        initTitles();
        mViewpagerFragmentAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager());
        mViewpagerFragmentAdapter.setmTitles(mTitles);
        mViewPager.setAdapter(mViewpagerFragmentAdapter);
//        mViewpagerFragmentAdapter.addFragments(mFragments);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    private void initTitles() {
        mTitles = new ArrayList<>();

        String[] array = getResources().getStringArray(R.array.types);
        List<String> asList = Arrays.asList(array);
        mTitles=asList;
        mFragments = new ArrayList<>();
        mFragments.add( TypeFragment.newInstance("Type1",-1));
        mFragments.add( TypeFragment.newInstance("Type2",-1));
        mFragments.add( TypeFragment.newInstance("Type3",-1));
        mFragments.add( TypeFragment.newInstance("Type4",-1));
        mFragments.add( TypeFragment.newInstance("Type5",-1));

    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
