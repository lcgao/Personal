// Generated code from Butter Knife. Do not modify!
package com.lcgao.music_module;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view2131230861;

  private View view2131230828;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    view = Utils.findRequiredView(source, R.id.ll_layout_play_bar, "field 'mLlPlayBar' and method 'onClickPlayBar'");
    target.mLlPlayBar = Utils.castView(view, R.id.ll_layout_play_bar, "field 'mLlPlayBar'", LinearLayout.class);
    view2131230861 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickPlayBar();
      }
    });
    target.mTvTitle = Utils.findRequiredViewAsType(source, R.id.tv_layout_play_bar_title, "field 'mTvTitle'", TextView.class);
    target.mTvArtist = Utils.findRequiredViewAsType(source, R.id.tv_layout_play_bar_artist, "field 'mTvArtist'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ib_layout_play_bar_play_or_pause, "field 'mIbPlayOrPause' and method 'onClickPlayOrPause'");
    target.mIbPlayOrPause = Utils.castView(view, R.id.ib_layout_play_bar_play_or_pause, "field 'mIbPlayOrPause'", ImageButton.class);
    view2131230828 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickPlayOrPause();
      }
    });
    target.mIbPlayList = Utils.findRequiredViewAsType(source, R.id.ib_layout_play_bar_playlist, "field 'mIbPlayList'", ImageButton.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.mLlPlayBar = null;
    target.mTvTitle = null;
    target.mTvArtist = null;
    target.mIbPlayOrPause = null;
    target.mIbPlayList = null;

    view2131230861.setOnClickListener(null);
    view2131230861 = null;
    view2131230828.setOnClickListener(null);
    view2131230828 = null;
  }
}
