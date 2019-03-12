package com.android.optionmenumvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.optionmenumvvm.commands.CountMenuActionCallback;
import com.android.optionmenumvvm.commands.MainActivityActionCallback;
import com.android.optionmenumvvm.databinding.ActivityMainBinding;
import com.android.optionmenumvvm.databinding.MenuActionCountBinding;
import com.android.optionmenumvvm.viewmodel.CountMenuViewModel;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;
    CountMenuViewModel mCountMenuViewModel;

    CountMenuActionCallback mCountMenuActionCallback = new CountMenuActionCallback() {
        @Override
        public void onCountMenuItemClicked() {
            Toast.makeText(MainActivity.this, "Count clicked!", Toast.LENGTH_SHORT)
                    .show();
        }
    };

    MainActivityActionCallback mActionCallback = new MainActivityActionCallback() {
        @Override
        public void onPlusClicked() {
            mCountMenuViewModel.setCount(mCountMenuViewModel.getCount() + 1);
        }

        @Override
        public void onMinusClicked() {
            mCountMenuViewModel.setCount(mCountMenuViewModel.getCount() - 1);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCountMenuViewModel = new CountMenuViewModel();

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setCallback(mActionCallback);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem menuItemCount = menu.findItem(R.id.action_count);
        MenuActionCountBinding binding = MenuActionCountBinding.inflate(getLayoutInflater());
        binding.setData(mCountMenuViewModel);
        binding.setCallback(mCountMenuActionCallback);

        MenuItemCompat.setActionView(menuItemCount, binding.getRoot());
        MenuItemCompat.setShowAsAction(menuItemCount, MenuItemCompat.SHOW_AS_ACTION_ALWAYS);

        return super.onCreateOptionsMenu(menu);
    }


}
