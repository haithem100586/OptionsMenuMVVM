package com.android.optionmenumvvm.viewmodel;

import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.android.optionmenumvvm.BR;
import com.android.optionmenumvvm.R;

/**
 * Created by macbook on 12/03/2019.
 */

public class CountMenuViewModel extends BaseObservable {
    @Bindable
    int count;

    public CountMenuViewModel() {}

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        if (this.count < 0) {
            this.count = 0;
        }
        notifyPropertyChanged(BR.count);
    }

    @Bindable({"count"})
    public @DrawableRes
    int getBackground() {
        if (count > 0) {
            return R.drawable.ic_menu_blue_square;
        }
        return R.drawable.ic_menu_red_square;
    }

    @Bindable({"count"})
    public String getCountText() {
        if (count > 0) {
            return String.valueOf(count);
        }
        return null;
    }

    @BindingAdapter({"android:src"})
    public static void setSrc(ImageView view, @DrawableRes int resId) {
        try {
            view.setImageDrawable(ContextCompat.getDrawable(view.getContext(), resId));
        } catch (Resources.NotFoundException e) {
        }
    }
}