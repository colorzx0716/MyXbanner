package com.bawie.myxbanner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private XBanner banner;
    private List<String> images;
    private List<String> titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        banner = (XBanner) findViewById(R.id.banner);


        initData();//初始化Xbanner中展示的数据

        banner.setData(images,titles);//Xbanner绑定数据
        //Xbanner适配数据
        banner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(MainActivity.this).load(images.get(position)).into((ImageView) view);
            }
        });

        //设置Xbanner默认的页面切换动画及动画时长
        banner.setPageTransformer(Transformer.Default);
        banner.setPageChangeDuration(1000);

        banner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                Toast.makeText(MainActivity.this, "点了第"+(position+1)+"张图片", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initData() {

        images = new ArrayList<>();
        titles = new ArrayList<>();

        images.add("http://lady.southcn.com/6/images/attachement/jpg/site4/20161110/34/5739760734408788890.jpg");
        titles.add("这是一个可爱的阿拉蕾");

        images.add("http://img.mshishang.com/pics/2016/1021/20161021050650376.jpg");
        titles.add("这是一个别扭的阿拉蕾");

        images.add("http://new-img2.ol-img.com/985x695/70/165/liDZEisTULFw.jpg");
        titles.add("这是一个卖萌的阿拉蕾");

        images.add("http://img16.3lian.com/gif2016/q28/91/29.jpg");
        titles.add("这是一个生气的阿拉蕾");

    }

    @Override
    protected void onResume() {
        super.onResume();
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }
}
