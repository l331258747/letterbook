package com.csxs.letterbook.social.fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.csxs.core.base.BaseDiffActivity;
import com.csxs.core.base.LazyDiffFragment;
import com.csxs.core.utils.MmkvUtlis;
import com.csxs.letterbook.R;
import com.csxs.letterbook.dynamic.adapter.NearbyUserAdapter;
import com.csxs.letterbook.entity.PersonalDynamicE;
import com.csxs.letterbook.social.adapter.PersonalDynamicAdapter;
import com.csxs.letterbook.social.mvp.presenter.PersonalDynamicPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: yeliu
 * created on 2020/6/2
 * description:
 */
public class PersonalDynamicFragment extends LazyDiffFragment<PersonalDynamicPresenter> {

    @Inject
    MmkvUtlis mmkvUtlis;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private PersonalDynamicAdapter personalDynamicAdapter;

    private List<PersonalDynamicE> datas;



    @Override
    protected void fragmentHidden() {

    }

    @Override
    public void initParam() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal_dynamic;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        personalDynamicAdapter = new PersonalDynamicAdapter(activity);
        recyclerView.setAdapter(personalDynamicAdapter);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    protected void lazyInitData(boolean isFirstVisible) {
        if (isFirstVisible) {
            requestNetData();

        }
    }

    private void requestNetData() {
//        mPresenter.getDynamicList();
        datas = new ArrayList<>();
        PersonalDynamicE item = new PersonalDynamicE();
        item.setHeadImg("https://c-ssl.duitang.com/uploads/item/201812/7/2018127203650_KvXLM.jpeg");
        item.setNickName("信书");
        item.setCheck(true);
        item.setAge(40);
        item.setLocation("长沙");
        item.setContent("内容");
        List<Uri> imgs = new ArrayList<>();
        Uri uri = Uri.parse("http://www.uchuanbo.com/ueditor/php/upload/image/20200529/1590734626862764.png");
        imgs.add(uri);
        imgs.add(uri);
        imgs.add(uri);
        imgs.add(uri);
        item.setImgs(imgs);
        item.setRange("500m");
        item.setTime("6分钟");
        datas.add(item);
        datas.add(item);
        datas.add(item);

        personalDynamicAdapter.setNewData(datas);


    }


    public static Fragment newInstance() {
        PersonalDynamicFragment fragment = new PersonalDynamicFragment();
        return fragment;
    }
}
