package com.example.thear.ecampus20.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.thear.ecampus20.R;
import com.example.thear.ecampus20.model.rnp.NpApi;
import com.example.thear.ecampus20.model.rnp.NpModel;
import com.example.thear.ecampus20.presentation.presenter.RnPresenter;
import com.example.thear.ecampus20.presentation.view.RnView;
import com.example.thear.ecampus20.ui.adapter.NpRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RNPFragment extends MvpAppCompatFragment implements RnView {
    public static final String TAG = "RNPFragment";
    List<NpModel> npList = new ArrayList<>();
    RecyclerView recyclerView;
    @InjectPresenter
    RnPresenter mRnPresenter;

    public static RNPFragment newInstance() {
        RNPFragment fragment = new RNPFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rnp, container, false);
        setupNavigation();
        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        npList = new ArrayList<>();
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.np_recycler_view);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        NpRecyclerViewAdapter adapter = new NpRecyclerViewAdapter(npList);
        recyclerView.setAdapter(adapter);

        mRnPresenter.loadData(npList);
    }

    private void setupNavigation() {
        MvpAppCompatActivity activity = (MvpAppCompatActivity) getActivity();
        activity.getSupportActionBar().setTitle(getString(R.string.nav_rnp));
        NavigationView navView = (NavigationView) activity.findViewById(R.id.navView);
        Menu navMenu = navView.getMenu();
        MenuItem menuItem = navMenu.findItem(R.id.navRNP);
        menuItem.setChecked(true);
    }

    void getData(final List<NpModel> list) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api-campus-kpi-ua.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        NpApi api = retrofit.create(NpApi.class);

        api.getNp().enqueue(new Callback<List<NpModel>>() {
            @Override
            public void onResponse(Call<List<NpModel>> call, Response<List<NpModel>> response) {
                list.addAll(response.body());
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<NpModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Sth go wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void updateView() {
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}