package tranlam.viewpagerdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class Tab1 extends Fragment implements View.OnClickListener {

    public static Fragment F_link1 = new Fragment();


    private View mView;
    private LinearLayout link1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.tab_fragment1, container, false);
        link1 = (LinearLayout) mView.findViewById(R.id.rLink1);
        link1.setOnClickListener(this);
        return mView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rLink1: {
                Bundle bundle = new Bundle();
                FragmentTransaction ft = getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction();
                F_link1.setArguments(bundle);
                ft.replace(R.id.pager, F_link1);
                ft.addToBackStack(null);
                ft.commit();
                break;
            }
            default:
                break;
        }

    }

}
