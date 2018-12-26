package com.example.zjy.bigheadshrimp.base;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;

/**
 * Created by 仕囵弹 on 2018/11/7.
 * Fragment基类
 */

public abstract class BaseFragment<B extends ViewDataBinding, VM extends BaseViewModel> extends Fragment {

    private BaseActivity activity;
    private boolean first;
    private boolean refresh;
    protected B binding;
    protected VM viewModel;
    protected final String TAG = this.getClass().getSimpleName();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            activity = (BaseActivity) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        first = true;
        viewModel = getViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        init(savedInstanceState);
        return binding.getRoot();
    }

    protected abstract int getLayoutId();

    protected abstract void init(Bundle savedInstanceState);

    protected void start() {
    }

    protected void refresh() {
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && first) {
            first = false;
            start();
        } else if (isVisibleToUser && refresh) {
            refresh = false;
            refresh();
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @SuppressWarnings("unchecked")
    private VM getViewModel() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class<VM> vmClass = (Class<VM>) type.getActualTypeArguments()[1];
        Log.i("viewmodel", "getViewModel: " + vmClass + "/" + BaseViewModel.class);
        return ViewModelProviders.of(getSelfActivity()).get(vmClass);
    }

    public BaseActivity getSelfActivity() {
        return activity;
    }

}
