package com.csxs.letterbook.widgets.numbtn;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.CommodityListE;
import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;


public class AddWidget extends FrameLayout {

	private View sub;
	private TextView tv_count;
	private long count;
	private AddButton addbutton;
	private boolean sub_anim, circle_anim;

    private  CommodityListE commodityListE;
	public interface OnAddClick {

		void onAddClick(View view, CommodityListE commodityList);

		void onSubClick(CommodityListE commodityListE);
	}

	private OnAddClick onAddClick;

	public AddWidget(@NonNull Context context) {
		super(context);
	}

	public AddWidget(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		inflate(context, R.layout.view_addwidget, this);
		addbutton = findViewById(R.id.addbutton);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AddWidget);
		for (int i = 0; i < a.getIndexCount(); i++) {
			int attr = a.getIndex(i);
			if (attr == R.styleable.AddWidget_circle_anim) {
				circle_anim = a.getBoolean(R.styleable.AddWidget_circle_anim, false);
			} else if (attr == R.styleable.AddWidget_sub_anim) {
				sub_anim = a.getBoolean(R.styleable.AddWidget_sub_anim, false);
			}

		}
		a.recycle();
		sub = findViewById(R.id.iv_sub);
		tv_count = findViewById(R.id.tv_count);

		addbutton.setAnimListner(new AddButton.AnimListner() {
			@Override
			public void onStop() {
				if (count == 0) {
					ViewAnimator.animate(sub)
							.translationX(addbutton.getLeft() - sub.getLeft(), 0)
							.rotation(360)
							.alpha(0, 255)
							.duration(300)
							.interpolator(new DecelerateInterpolator())
							.andAnimate(tv_count)
							.translationX(addbutton.getLeft() - tv_count.getLeft(), 0)
							.rotation(360)
							.alpha(0, 255)
							.interpolator(new DecelerateInterpolator())
							.duration(300)
							.start();
				}
			//	addbutton.setGoodsData(commodityListE.getIsSpec());
				if (onAddClick != null) {
					onAddClick.onAddClick(addbutton, commodityListE);
				}

				count++;
				tv_count.setText(count + "");
				commodityListE.setSelectCount(count);
			}
		});
		sub.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (count == 0) {
					return;
				}
				if (count == 1 && sub_anim) {
					subAnim();
				}
				count--;
				tv_count.setText(count == 0 ? "1" : count + "");
				commodityListE.setSelectCount(count);
				if (onAddClick != null) {
					onAddClick.onSubClick(commodityListE);
				}
			}
		});
	}

	private void subAnim(){
		ViewAnimator.animate(sub)
				.translationX(0, addbutton.getLeft() - sub.getLeft())
				.rotation(-360)
				.alpha(255, 0)
				.duration(300)
				.interpolator(new AccelerateInterpolator())
				.andAnimate(tv_count)
				.onStop(new AnimationListener.Stop() {
					@Override
					public void onStop() {
						if (circle_anim) {
							addbutton.expendAnim();
						}
					}
				})
				.translationX(0, addbutton.getLeft() - tv_count.getLeft())
				.rotation(-360)
				.alpha(255, 0)
				.interpolator(new AccelerateInterpolator())
				.duration(300)
				.start()
		;
	}

	public void setData(OnAddClick onAddClick, CommodityListE commodityLists) {
		this.commodityListE = commodityLists;
		this.onAddClick = onAddClick;
		this.count = commodityLists.getSelectCount();
		if (this.count == 0) {
			sub.setAlpha(0);
			tv_count.setAlpha(0);
		} else {
			sub.setAlpha(1f);
			tv_count.setAlpha(1f);
			tv_count.setText(this.count + "");
		}
	}

	public void setState(int count) {
		addbutton.setState(count > 0);
	}

	public void expendAdd(int count) {
		this.count = count;
		tv_count.setText(count == 0 ? "1" : count + "");
		if (count == 0) {
			subAnim();
		}
	}
}
