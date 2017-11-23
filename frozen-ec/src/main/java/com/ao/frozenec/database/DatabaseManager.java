package com.ao.frozenec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * com.ao.frozenec.database
 * <p>
 * <p>
 * Created by Leo on 2017/11/23.
 */

public class DatabaseManager {

    private DaoSession mDaoSession;
    private UserProfileDao mDao;

    private DatabaseManager(){
    }

    public void init(Context context){
        initDao(context);
    }

    private static final class Holder{
        private static final DatabaseManager INSTANCE = new DatabaseManager();

    }

    public static DatabaseManager getInstance(){
        return Holder.INSTANCE;
    }


    private void initDao(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "frozen_ec.db");
        final Database database = helper.getWritableDb();
        mDaoSession = new DaoMaster(database).newSession();
        mDao = mDaoSession.getUserProfileDao();
    }

    public UserProfileDao getDao() {
        return mDao;
    }
}
