package com.example.zjy.bigheadshrimp.main.matter;


import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.zjy.bigheadshrimp.R;
import com.example.zjy.bigheadshrimp.adapter.SimpleAdapter;
import com.example.zjy.bigheadshrimp.base.BaseFragment;
import com.example.zjy.bigheadshrimp.databinding.FragmentMatterBinding;
import com.example.zjy.bigheadshrimp.databinding.ItemMatterBinding;
import com.example.zjy.bigheadshrimp.entity.MatterEntity;
import com.example.zjy.bigheadshrimp.utils.SizeUtils;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatterFragment extends BaseFragment<FragmentMatterBinding, MatterViewModel> {

    public MatterFragment() {
        // Required empty public constructor
    }

    public static MatterFragment newInstance() {
//        Bundle args = new Bundle();
//        MatterFragment fragment = new MatterFragment();
//        fragment.setArguments(args);
        return new MatterFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_matter;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        final SimpleAdapter<MatterEntity, ItemMatterBinding> adapter = new SimpleAdapter<MatterEntity, ItemMatterBinding>(getSelfActivity()) {
            @Override
            protected int getLayoutId(int viewType) {
                return R.layout.item_matter;
            }

            @Override
            protected void convert(ItemMatterBinding binding, MatterEntity entity, int position) {
                binding.setMatter(entity);
            }
        };
        binding.matterRecycler.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                for (int i = 0; i < parent.getChildCount(); i++) {
                    int size = SizeUtils.dp2px(getSelfActivity(), getResources().getDimension(R.dimen.dp_5));
                    if(i==0){
                        Log.i(TAG, "getItemOffsets: " + size);
                        outRect.top = size;
                    }
                    outRect.left = size;
                    outRect.right = size;
                    outRect.bottom = size;
                }
            }
        });
        binding.matterRecycler.setLayoutManager(new LinearLayoutManager(getSelfActivity()));
        binding.matterRecycler.setAdapter(adapter);
        viewModel.getLiveData().observe(getSelfActivity(), new Observer<List<MatterEntity>>() {
            @Override
            public void onChanged(@Nullable List<MatterEntity> matterEntities) {
                adapter.setList(matterEntities);
            }
        });
        binding.matterFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getSelfActivity(), NewMatterActivity.class));
            }
        });
    }

}
