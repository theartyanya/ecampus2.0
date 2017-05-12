package com.example.thear.ecampus20.ui.fragment.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.thear.ecampus20.R;
import com.example.thear.ecampus20.presentation.presenter.main.BulletinsPresenter;
import com.example.thear.ecampus20.presentation.view.main.BulletinsView;

public class BulletinsFragment extends MvpAppCompatFragment implements BulletinsView {
    public static final String TAG = "BulletinsFragment";
    @InjectPresenter
    BulletinsPresenter mBulletinsPresenter;

    public static BulletinsFragment newInstance() {
        BulletinsFragment fragment = new BulletinsFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bulletins, container, false);
        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
