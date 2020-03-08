package com.umeng.jiaqizuoye1_26.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.umeng.jiaqizuoye1_26.R;
import com.umeng.jiaqizuoye1_26.adapter.HomeAdapter;
import com.umeng.jiaqizuoye1_26.base.BaseActivity;
import com.umeng.jiaqizuoye1_26.bean.CartBean;
import com.umeng.jiaqizuoye1_26.bean.DetailBean;
import com.umeng.jiaqizuoye1_26.bean.GoodsShoppingBottomListBean;
import com.umeng.jiaqizuoye1_26.bean.PageBean;
import com.umeng.jiaqizuoye1_26.interfaces.shop.GoodsShoppingConstract;
import com.umeng.jiaqizuoye1_26.presenter.GoodsShoppingPercenter;
import com.umeng.jiaqizuoye1_26.utils.SpUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


//商品购买详情页      由 分类SortDescActivity item点击 ， hotActivity中 item点击跳转至此
public class GoodsShoppingActivity extends BaseActivity<GoodsShoppingConstract.View, GoodsShoppingConstract.Percenter> implements GoodsShoppingConstract.View {

    private ImageView selectNum;
    private Banner banner;
    private RecyclerView recGoodsshopping;
    private ArrayList<PageBean.DataBean.CategoryListBean.GoodsListBean> lists;
    private HomeAdapter rec_home_livingHomeAdapter;
    private TextView textCollect;
    private ImageView imgCart;
    private TextView textShop;
    private TextView textCart;
    private DetailBean detailBean;
    private PopupWindow pop;

/*    @BindView(R.id.webview)
    WebView web;*/

    @BindView(R.id.shop_cart_num)
    TextView shop_num;
    private TextView num;

    @Override
    protected int getLayout() {
        return R.layout.activity_goods_shopping;
    }

    @Override
    protected void initView() {
        textCollect = (TextView) findViewById(R.id.text_collect);
        imgCart = (ImageView) findViewById(R.id.img_cart);
        textShop = (TextView) findViewById(R.id.text_shop);

        textCart = (TextView) findViewById(R.id.text_cart);
        textCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pop != null && pop.isShowing()){
                    String token = SpUtils.getInstance().getString("token");
                    pop.dismiss();
                    Alpha(1.0f);
                    //说明已经登录过了
                    if (!token.equals("")){
                        presenter.addCartData(detailBean.getData().getInfo().getId()+"",Integer.parseInt(num.getText().toString()),detailBean.getData().getGallery().get(0).getId()+"");
                    }else {
                        Intent intent = new Intent(GoodsShoppingActivity.this, LoginActivity.class);
                        startActivityForResult(intent,100);
                    }
                }else {
                    showPoP();
                    Alpha(0.5f);
                }

            }
        });

        selectNum = (ImageView) findViewById(R.id.select_num);
        banner = (Banner) findViewById(R.id.banner);
        recGoodsshopping = (RecyclerView) findViewById(R.id.rec_goodsshopping);
        recGoodsshopping.setLayoutManager(new GridLayoutManager(this,2));
        //复用适配器
        lists = new ArrayList<>();
        rec_home_livingHomeAdapter = new HomeAdapter(lists,this);
        recGoodsshopping.setAdapter(rec_home_livingHomeAdapter);

    }
    //选择购买数量 弹出popupwindow
    @OnClick(R.id.select_num)
    public void onNum(){
        showPoP();
        Alpha(0.5f);
    }


    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        if (requestCode == 100){

        }
    }

    private void Alpha(float alpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha =alpha;
        getWindow().setAttributes(lp);
    }


    private void showPoP() {
        View view = LayoutInflater.from(context).inflate(R.layout.shopping_pop, null);
        pop = new PopupWindow(view, RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(null);
        pop.setOutsideTouchable(true);

        TextView add = view.findViewById(R.id.pop_add);
        TextView price = view.findViewById(R.id.pop_price);
        num = view.findViewById(R.id.pop_num);
        TextView fini = view.findViewById(R.id.pop_finishi);
        TextView subtract = view.findViewById(R.id.pop_subtract);
        ImageView img = view.findViewById(R.id.pop_img);

        if (detailBean != null){

            Glide.with(context).load(detailBean.getData().getInfo().getList_pic_url()).into(img);
            price.setText("价格："+detailBean.getData().getInfo().getRetail_price());

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    num.setText(Integer.parseInt(num.getText().toString())+1+"");
                }
            });
            subtract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = Integer.parseInt(num.getText().toString());
                    if (i == 1){
                        return;
                    }else {
                        num.setText(Integer.parseInt(num.getText().toString())-1+"");
                    }
                }
            });

            fini.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pop.dismiss();
                    Alpha(1.0f);
                }
            });
        }
        pop.showAtLocation(selectNum, Gravity.BOTTOM,0,110);
    }

    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("id", -1);
        presenter.getDetailData(id);
        presenter.getGoodsShoppingBottomListData(id);
    }

    @Override
    protected GoodsShoppingConstract.Percenter createPersenter() {
        return new GoodsShoppingPercenter();
    }

    @Override
    public void DetailDataReturn(DetailBean detailBean) {

        this.detailBean = detailBean;
        List<DetailBean.DataBeanX.GalleryBean> gallery = detailBean.getData().getGallery();
        //设置banner
        setBaaner(gallery);
        //设置web
        String goods_desc = detailBean.getData().getInfo().getGoods_desc();
        //setWeb(goods_desc);
    }

    private void setWeb(String goods_desc) {
        String css_str = getResources().getString(R.string.css_goods);
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head>");
        sb.append("<style>"+css_str+"</style></head><body>");
        sb.append(goods_desc+"</body></html>");
      //  web.loadData(sb.toString(),"text/html","utf-8");
    }

    private void setBaaner(List<DetailBean.DataBeanX.GalleryBean> gallery) {
        ArrayList<String> banns = new ArrayList<>();
        for (int i = 0; i < gallery.size(); i++) {
            banns.add(gallery.get(i).getImg_url());
        }
        banner.setImages(banns).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load((String)path).into(imageView);
            }
        }).start();
    }

    @Override
    public void GoodsShoppingBottomListDataReturn(GoodsShoppingBottomListBean goodsShoppingBottomListBean) {
        List<GoodsShoppingBottomListBean.DataBean.GoodsListBean> goodsList = goodsShoppingBottomListBean.getData().getGoodsList();
        lists.clear();
        for (GoodsShoppingBottomListBean.DataBean.GoodsListBean item : goodsList){
            PageBean.DataBean.CategoryListBean.GoodsListBean goodsListBean = new PageBean.DataBean.CategoryListBean.GoodsListBean();
            goodsListBean.setName(item.getName());
            goodsListBean.setRetail_price(item.getRetail_price()+"");
            goodsListBean.setList_pic_url(item.getList_pic_url());
            lists.add(goodsListBean);
        }
        rec_home_livingHomeAdapter.notifyDataSetChanged();
    }

    @Override
    public void CartDataReturn(CartBean cartBean) {
        if (cartBean.getErrno()==400){
            Toast.makeText(context, cartBean.getErrmsg(), Toast.LENGTH_SHORT).show();
        }else {
            shop_num.setText(cartBean.getData().getCartList().size());
        }
    }
}
