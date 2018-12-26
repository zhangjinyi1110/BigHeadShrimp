package com.example.zjy.bigheadshrimp.main.matter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zjy.bigheadshrimp.R;
import com.example.zjy.bigheadshrimp.base.BaseFragment;
import com.example.zjy.bigheadshrimp.databinding.FragmentNewMatterBinding;
import com.example.zjy.bigheadshrimp.entity.MatterEntity;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewMatterFragment extends BaseFragment<FragmentNewMatterBinding, MatterViewModel> {


    public NewMatterFragment() {
        // Required empty public constructor
    }

    public static NewMatterFragment newInstance() {
        return new NewMatterFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new_matter;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        final MatterEntity entity = new MatterEntity();
        binding.setMatter(entity);
        binding.insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entity.createTime = System.currentTimeMillis();
                viewModel.insert(entity);
                getSelfActivity().finish();
            }
        });
    }

}
