package zsx.com.aiyamaya.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.Image;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import zsx.com.aiyamaya.R;
import zsx.com.aiyamaya.item.ArticleItem;
import zsx.com.aiyamaya.util.Constant;

public class HomeArticleAdapter extends RecyclerView.Adapter<HomeArticleAdapter.ViewHolder> {

    private Context mContext;

    private List<ArticleItem> articleList;

    public HomeArticleAdapter(Context mContext,List<ArticleItem> articleList) {
        this.mContext = mContext;
        this.articleList=articleList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_home_article, parent, false);
        ImageView imageIV = (ImageView) view.findViewById(R.id.icha_iv_image);
        TextView titleTV = (TextView) view.findViewById(R.id.icha_tv_title);
        TextView readNumTV = (TextView) view.findViewById(R.id.icha_tv_readnum);
        TextView contentTV = (TextView) view.findViewById(R.id.icha_tv_content);

        return new ViewHolder(view, imageIV, titleTV, readNumTV, contentTV);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final View view = holder.mView;
        ArticleItem articleItem=articleList.get(position);
        Glide.with(mContext)
                .load(Constant.DEFAULT_URL+Constant.IMAGE_URL+articleItem.getImageUrl())
                .into(holder.imageIV);
        holder.titleTV.setText(articleItem.getTitle());
        holder.readNumTV.setText("阅读量："+articleItem.getReadnum());
        holder.contentTV.setText(articleItem.getContent());
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView imageIV;
        public final TextView titleTV;
        public final TextView readNumTV;
        public final TextView contentTV;


        public ViewHolder(View view, ImageView imageIV
                , TextView titleTV, TextView readNumTV, TextView contentTV) {
            super(view);
            mView = view;
            this.imageIV = imageIV;
            this.titleTV = titleTV;
            this.readNumTV = readNumTV;
            this.contentTV = contentTV;
        }
    }
}
