package skill.android.wl.androidskill.mvp.view.ui;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import skill.android.wl.androidskill.R;
import skill.android.wl.androidskill.widget.ObservableScrollView;
import skill.android.wl.androidskill.widget.TimeLineView;

public class ScrollActivity extends AppCompatActivity {

    private Unbinder bind;


    @BindView(R.id.head_image)
    ImageView headImage;
    @BindView(R.id.timeLineView2)
    TimeLineView mView2;
    @BindView(R.id.ptr)
    PtrFrameLayout ptrFrameLayout;
    private GridLayout gridLayout;
   @BindView(R.id.root_content)
    LinearLayout rootContent;
    private TextView title;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bind = ButterKnife.bind(this);

       // title = new TextView(this);
        //title.setText("12344");
        //title.setGravity(Gravity.CENTER);
        imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.ic_launcher);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        ptrFrameLayout.addPtrUIHandler(new PtrUIHandler() {
            @Override
            public void onUIReset(PtrFrameLayout frame) {

            }

            @Override
            public void onUIRefreshPrepare(PtrFrameLayout frame) {

            }

            @Override
            public void onUIRefreshBegin(PtrFrameLayout frame) {

            }

            @Override
            public void onUIRefreshComplete(PtrFrameLayout frame) {

            }

            @Override
            public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
                int currentPosY = ptrIndicator.getCurrentPosY();
                Log.e("onUIPositionChange", "onUIPositionChange: "+" 0  "+  currentPosY+"  "+ptrIndicator.getCurrentPercent()+" "+ptrIndicator.getHeaderHeight());
                ViewCompat.setScaleY(imageView,ptrIndicator.getCurrentPercent());
                ViewCompat.setScaleX(imageView,ptrIndicator.getCurrentPercent());
                ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
//                layoutParams.height=ptrIndicator.getHeaderHeight();
//                imageView.setLayoutParams(layoutParams);
            }
        });
        ptrFrameLayout.setHeaderView(imageView);
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ptrFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrFrameLayout.refreshComplete();
                    }
                },1000);
            }
        });
       /* ptrFrameLayout.addPtrUIHandler(new PtrUIHandler() {
            @Override
            public void onUIReset(PtrFrameLayout frame) {

            }

            @Override
            public void onUIRefreshPrepare(PtrFrameLayout frame) {

            }

            @Override
            public void onUIRefreshBegin(PtrFrameLayout frame) {

            }

            @Override
            public void onUIRefreshComplete(PtrFrameLayout frame) {

            }

            @Override
            public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

            }
        });*/
//        classic1();
        //ptrFrameLayout.setPinContent(true);
        //PtrClassicDefaultHeader defaultHeader = new PtrClassicDefaultHeader(this);
       /* PtrDefaultHandler ptrHandler = new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrFrameLayout.refreshComplete();
                    }
                }, 1500);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }
        };
        ptrFrameLayout.setPtrHandler(ptrHandler);
        ptrFrameLayout.addPtrUIHandler(new PtrClassicDefaultHeader(this));*/
     /*   ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                Toast.makeText(ScrollActivity.this,"start",Toast.LENGTH_SHORT).show();
            }
        });*/
       /*ptrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
           @Override
           public void onRefreshBegin(PtrFrameLayout frame) {
               Toast.makeText(ScrollActivity.this,"start",Toast.LENGTH_SHORT).show();
           }
       });*/

        List<String> data = new ArrayList<>();
        data.add("等候支付");
        data.add("等候商家接单");
        data.add("等候配送");
        data.add("等候送达");
        mView2.builder().pointStrings(data, 2).load();

        completeA();
        gridLayout = new GridLayout(this);
        gridLayout.setColumnCount(1);
        for (int i = 0; i < 10; i++) {
            View inflate = LayoutInflater.from(this).inflate(R.layout.time_progress_item, null);
            gridLayout.addView(inflate, GridLayout.LayoutParams.MATCH_PARENT, GridLayout.LayoutParams.WRAP_CONTENT);
        }
        rootContent.addView(gridLayout, LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        /*scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
               // scrollView.getParent().getParent().requestDisallowInterceptTouchEvent(true);
                Log.d("TAG", "scrollView  onTouch() called with: v = [" + "], event = [" + event + "]");
                return false;
            }
        });
        root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("TAG", "root  onTouch() called with: v = [" + "], event = [" + event + "]");
                return false;
            }
        });
        root.setScrollViewListener(new ObservableScrollView.OnScrollViewListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                if (headImage.getHeight()==t){
                    scrollView.getParent().getParent().requestDisallowInterceptTouchEvent(true);
                }else{
                    scrollView.getParent().getParent().requestDisallowInterceptTouchEvent(false);
                }
                Log.d("TAG", "onScrollChanged() called with: l"+headImage.getScrollY()+"  "+headImage.getTop()+" " +headImage.getHeight()   +" = [" + l + "], t = [" + t + "], oldl = [" + oldl + "], oldt = [" + oldt + "]");
            }
        });
        scrollView.setScrollViewListener(new ObservableScrollView.OnScrollViewListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                if (headImage.getHeight()==t){
                    scrollView.getParent().getParent().requestDisallowInterceptTouchEvent(true);
                }else{
                    scrollView.getParent().getParent().requestDisallowInterceptTouchEvent(false);
                }
                Log.d("TAG", "onScrollChanged() called with: l"+headImage.getScrollY()+"  "+headImage.getTop()+" " +headImage.getHeight()   +" = [" + l + "], t = [" + t + "], oldl = [" + oldl + "], oldt = [" + oldt + "]");
            }
        });*/
    }

    private void classic1() {
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrFrameLayout.refreshComplete();
                    }
                }, 1500);
            }
        });
        PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(ScrollActivity.this);
        ptrFrameLayout.addPtrUIHandler(header);
        ptrFrameLayout.setHeaderView(header);
    }

    private void completeA() {
        ptrFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ptrFrameLayout.refreshComplete();
                    }
                });
            }
        },1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
