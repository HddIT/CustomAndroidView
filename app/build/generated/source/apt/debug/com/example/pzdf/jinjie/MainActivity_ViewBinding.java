// Generated code from Butter Knife. Do not modify!
package com.example.pzdf.jinjie;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view7f07006a;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'recyclerView'", RecyclerView.class);
    target.checkBox = Utils.findRequiredViewAsType(source, R.id.checkbox, "field 'checkBox'", CheckBox.class);
    view = Utils.findRequiredView(source, R.id.retrofit_btn, "field 'retrofit_btn' and method 'toRetrofit'");
    target.retrofit_btn = Utils.castView(view, R.id.retrofit_btn, "field 'retrofit_btn'", Button.class);
    view7f07006a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.toRetrofit();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
    target.checkBox = null;
    target.retrofit_btn = null;

    view7f07006a.setOnClickListener(null);
    view7f07006a = null;
  }
}
