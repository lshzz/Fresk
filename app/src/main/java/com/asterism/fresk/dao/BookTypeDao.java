package com.asterism.fresk.dao;

import android.content.Context;

import com.asterism.fresk.dao.bean.BookTypeBean;
import com.asterism.fresk.dao.core.DatabaseHelper;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * 书籍类型表访问器类
 *
 * @author Ashinch
 * @email Glaxyinfinite@outlook.com
 * @date on 2019-07-02 14:53
 */
public class BookTypeDao {
    private Context context;

    // ORMLite提供的DAO类对象
    // 第一个泛型是要操作的数据表映射成的实体类
    // 第二个泛型是这个实体类中ID的数据类型
    private Dao<BookTypeBean, Integer> dao;

    /**
     * 构造方法
     *
     * @param context 上下文对象
     */
    public BookTypeDao(Context context) {
        this.context = context;
        try {
            // 从数据库访问基类的单例对象获取Dao
            this.dao = DatabaseHelper.getInstance(context).getDao(BookTypeBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增
     *
     * @param data 欲增加的记录实体类
     */
    public void insert(BookTypeBean data) {
        if (isExistsByName(data.getType())) {
            return;
        }
        try {
            dao.create(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删
     *
     * @param data 欲删除的记录实体类
     */
    public void delete(BookTypeBean data) {
        try {
            dao.delete(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 改
     *
     * @param data 欲更改的记录实体类
     */
    public void update(BookTypeBean data) {
        try {
            dao.update(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有记录
     *
     * @return 返回所有记录实体类集合
     */
    public List<BookTypeBean> selectAll() {
        List<BookTypeBean> beanList = null;
        try {
            beanList = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beanList;
    }

    /**
     * 根据类型名查询记录
     *
     * @param name 类型名
     *
     * @return 返回所有记录实体类集合
     */
    public List<BookTypeBean> selectAllByName(String name) {
        List<BookTypeBean> beanList = null;
        try {
            beanList = dao.queryBuilder().where().eq("type", name).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beanList;
    }

    /**
     * 根据类型名判断是否存在于表中
     *
     * @param name 类型名
     *
     * @return 返回boolean值
     */
    public boolean isExistsByName(String name) {
        boolean b = false;
        try {
            List<BookTypeBean> beanList = dao.queryBuilder().where().eq("type", name).query();
            if (beanList.size() > 0) {
                b = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }
}