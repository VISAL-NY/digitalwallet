package com.bill24.digitalwalletsdk.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bill24.digitalwalletsdk.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class TranDetailBottomSheetFragment extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.transaction_detail_layout,container,false);
    }
}
