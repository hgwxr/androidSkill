package skill.android.wl.androidskill.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import skill.android.wl.androidskill.R;
import skill.android.wl.androidskill.mvp.view.ui.fragment.TypeFragment;

/**
 * @author wl
 * @version :
 * @date 2017/4/28
 * @描述
 */

public class ViewPagerFragmentAdapter extends FragmentStatePagerAdapter {
    private List<String> mTitles;
    private List<Fragment> mFragments;
    private FragmentManager manager;
    public ViewPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
        manager=fm;
        mTitles=new ArrayList<>();
        mFragments=new ArrayList<>();
    }
    public void setmTitles(List<String> mTitles) {
        this.mTitles = mTitles;
        //notifyDataSetChanged();
    }

    public void addFragments(List<Fragment> fragments){
        mFragments.addAll(fragments);
        notifyDataSetChanged();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles!=null?mTitles.get(position):"";
    }
    @Override
    public Fragment getItem(int position) {
//        return TypeFragment.newInstance("","");
        if (position==mFragments.size()){
            TypeFragment typeFragment=null;
            if ("待开发".equals(mTitles.get(position))) {
               typeFragment = TypeFragment.newInstance(mTitles.get(position), R.layout.fragment_type1);
            }else if ("弹窗".equals(mTitles.get(position))){
                typeFragment = TypeFragment.newInstance(mTitles.get(position), R.layout.fragment_type_alert);
            }else{
                typeFragment = TypeFragment.newInstance(mTitles.get(position), R.layout.fragment_type);
            }
            mFragments.add(typeFragment);
        }
        return mFragments.get(position);
    }
    @Override
    public int getCount() {
        return mTitles!=null?mTitles.size():0;
    }
}
