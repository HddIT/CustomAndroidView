// Generated code from Butter Knife. Do not modify!
package com.example.pzdf.jinjie.retrofit;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.pzdf.jinjie.R;
import com.example.pzdf.jinjie.view.PieChart;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RetrofitActivity_ViewBinding implements Unbinder {
  private RetrofitActivity target;

  private View view7f070090;

  @UiThread
  public RetrofitActivity_ViewBinding(RetrofitActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RetrofitActivity_ViewBinding(final RetrofitActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.text, "field 'textView' and method 'onRequest'");
    target.textView = Utils.castView(view, R.id.text, "field 'textView'", TextView.class);
    view7f070090 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onRequest();
      }
    });
    target.btnpart = Utils.findRequiredViewAsType(source, R.id.btn1, "field 'btnpart'", Button.class);
    target.btncount = Utils.findRequiredViewAsType(source, R.id.btn2, "field 'btncount'", Button.class);
    target.pieChart = Utils.findRequiredViewAsType(source, R.id.pieChart, "field 'pieChart'", PieChart.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RetrofitActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.textView = null;
    target.btnpart = null;
    target.btncount = null;
    target.pieChart = null;

    view7f070090.setOnClickListener(null);
    view7f070090 = null;
  }
}
