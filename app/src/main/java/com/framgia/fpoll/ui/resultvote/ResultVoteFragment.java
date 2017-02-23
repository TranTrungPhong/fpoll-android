package com.framgia.fpoll.ui.resultvote;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.ResultItem;
import com.framgia.fpoll.databinding.FragmentResultVoteBinding;
import com.framgia.fpoll.util.DataBindingUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tran.trung.phong on 23/02/2017.
 */
public class ResultVoteFragment extends Fragment {
    private FragmentResultVoteBinding mBinding;
    private LayoutInflater mInflater;
    private List<ResultItem> mResultItems = new ArrayList<>();
    private ObservableField<ResultVoteAdapter> mAdapter = new ObservableField<>();

    public static ResultVoteFragment getInstance() {
        return new ResultVoteFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (mInflater == null) mInflater = LayoutInflater.from(container.getContext());
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_result_vote, container,
            false);
        mBinding.setFragment(this);
        mAdapter.set(new ResultVoteAdapter(mResultItems));
        return mBinding.getRoot();
    }

    public ObservableField<ResultVoteAdapter> getAdapter() {
        return mAdapter;
    }
}
