package com.asterism.fresk.ui.fragment;

import android.view.View;

import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.asterism.fresk.R;
import com.asterism.fresk.contract.IBookContract;
import com.asterism.fresk.dao.BookDao;
import com.asterism.fresk.dao.bean.BookBean;
import com.asterism.fresk.presenter.BookPresenter;
import com.asterism.fresk.ui.adapter.BookshelfGridAdapter;

import java.util.List;

import butterknife.BindView;


/**
 * 书架页面Fragment类，继承base基类且泛型为当前模块Presenter接口类型，并实现当前模块View接口
 *
 * @author Ashinch
 * @email Glaxyinfinite@outlook.com
 * @date on 2019-07-11 18:37
 */
public class BookshelfFragment extends BaseFragment<IBookContract.Presenter>
        implements IBookContract.View {

    @BindView(R.id.gv_bookshelf)
    GridView gvBookshelf;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_bookshelf;
    }

    @Override
    protected IBookContract.Presenter setPresenter() {
        return new BookPresenter();
    }

    @Override
    protected void initialize() {
        mPresenter.getAllBooks(new IBookContract.OnBookListListener() {
            @Override
            public void onSuccess(final List<BookBean> bookList) {
                BookshelfGridAdapter adapter = new BookshelfGridAdapter(mContext, bookList);
                gvBookshelf.setAdapter(adapter);
            }

            @Override
            public void onError() {

            }
        });

        gvBookshelf.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, final View view,
                                    int i, long l) {
                BookDao bookDao=new BookDao(mContext);
                String s=(String) ((TextView)view.findViewById(R.id.tv_item_bookname))
                        .getText();
                bookDao.updateBookByBookName(s);
                BookshelfGridAdapter adapter = new BookshelfGridAdapter(mContext
                        ,bookDao.selectAll());
                gvBookshelf.setAdapter(adapter);
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRemoving() {

    }

    @Override
    public void hideRemoving() {

    }



}
