package com.asterism.fresk.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.asterism.fresk.R;
import com.asterism.fresk.dao.bean.BookBean;

import java.io.File;
import java.util.List;

/**
 * 书架GridView适配器类
 *
 * @author Lshz
 * @email 528118879@qq.com
 * @date on 2019-08-04 16:37
 */
public class BookshelfGridAdapter extends BaseAdapter {

    private List<BookBean> mList;          // 书籍信息集合
    private Context context;                   // 上下文对象

    public BookshelfGridAdapter(Context context, List<BookBean> list) {
        this.mList = list;
        this.context = context;
    }

    /**
     * 获取item总数
     *
     * @return 返回集合长度
     */
    @Override
    public int getCount() {
        return mList.size();
    }

    /**
     * 获取item对应的对象
     *
     * @param position 指定item位置
     *
     * @return 返回book对象
     */
    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    /**
     * 获取item的id
     *
     * @param position 指定item位置
     *
     * @return 返回id
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 获取item对应的View对象
     *
     * @param position    item位置
     * @param convertView item文件转换的View
     * @param parent      parent
     *
     * @return 返回item对应的View对象
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_grid_book, null);
            holder = new ViewHolder();
            holder.imgBook = convertView.findViewById(R.id.img_item_book);
            holder.tvBookName = convertView.findViewById(R.id.tv_item_bookname);
            holder.tvProgress = convertView.findViewById(R.id.tv_item_progress);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imgBook.setImageURI(Uri.fromFile(new File(mList.get(position).getPicName())));
        holder.tvBookName.setText(mList.get(position).getName().trim());
        holder.tvProgress.setText(mList.get(position).getReadProgress() + "%");
        return convertView;
    }

    static class ViewHolder {
        ImageView imgBook;    // 书籍图片 图片框
        TextView tvBookName;  // 书籍名称 文本框
        TextView tvProgress;  // 阅读进度 文本框
    }

}
