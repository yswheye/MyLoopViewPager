package com.uphyca.android.loopviewpager.sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.LoopViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ToxicBakery.viewpager.transforms.ForegroundToBackgroundTransformer;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final LoopViewPager viewPager = (LoopViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        viewPager.setPageTransformer(true, new ForegroundToBackgroundTransformer());
    }

    private static class MyPagerAdapter extends FragmentPagerAdapter {

        MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return DummyFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return String.valueOf(position);
        }
    }

    public static class DummyFragment extends Fragment {

        private static final String ARGS_POSITION = "position";
        private static final int[] COLORS = new int[] { 0xFF33B5E5, 0xFFAA66CC, 0xFF99CC00, 0xFFFFBB33, 0xFFFF4444 };

        @NonNull
        public static DummyFragment newInstance(int position) {
            final DummyFragment f = new DummyFragment();
            Bundle args = new Bundle();
            args.putInt(ARGS_POSITION, position);
            f.setArguments(args);
            return f;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            int pos = getArguments().getInt(ARGS_POSITION);
            final View view = inflater.inflate(R.layout.fragment_dummy, container, false);
            view.setBackgroundColor(COLORS[pos % 5]);
            final TextView textView = (TextView) view.findViewById(R.id.text);
            textView.setText(String.valueOf(pos));
            return view;
        }
    }
}
