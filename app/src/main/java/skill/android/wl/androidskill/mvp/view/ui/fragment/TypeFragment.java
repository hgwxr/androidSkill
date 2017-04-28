package skill.android.wl.androidskill.mvp.view.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import skill.android.wl.androidskill.R;


public class TypeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String LAYOUT_TYPE= "layout_type";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private int layoutType=-1;

    private OnFragmentInteractionListener mListener;
    private BaseDeal deal;

    public TypeFragment() {
        // Required empty public constructor
    }


    public static TypeFragment newInstance(String param1, @LayoutRes  int param2) {
        TypeFragment fragment = new TypeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(LAYOUT_TYPE, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            layoutType = getArguments().getInt(LAYOUT_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        if (layoutType!=-1){
             view= inflater.inflate(layoutType, container, false);
        }else{
            view=inflater.inflate(R.layout.fragment_type, container, false);
        }
        return view;
    }

    TextView tv;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        switch (layoutType) {
            case R.layout.fragment_type:
                deal = new LayoutTypeDeal();
                deal.attach(view);
                break;
            case R.layout.fragment_type1:
                deal = new LayoutType1Deal();
                deal.attach(view);
                break;
        }
        deal.initView();
        deal.dealView();
       /* tv=ButterKnife.findById(view,R.id.tv);
        tv.setText(mParam1);*/
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        deal.deAttach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
