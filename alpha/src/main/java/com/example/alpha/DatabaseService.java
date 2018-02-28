package com.example.alpha;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.hongfans.common.log.SL;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;

import java.util.List;

public class DatabaseService extends Service {

    private LiteOrm mLiteOrm;

    @Override
    public void onCreate() {
        super.onCreate();

        mLiteOrm = LiteOrm.newSingleInstance(this, "alpha_db");

    }

    @Override
    public IBinder onBind(Intent intent) {
        return new DatabaseBinder();
    }


    class DatabaseBinder extends IOperation.Stub {

        @Override
        public long insert(ContentValues values) throws RemoteException {
            SL.i("other process " + values);
            return 0;
        }

        @Override
        public int delete(String selection, List<String> selectionArgs) throws RemoteException {
            SL.i("other process " + selection + selectionArgs);
            return 0;
        }

        @Override
        public int update(ContentValues values, String selection, List<String> selectionArgs) throws RemoteException {
            SL.i("other process " + values + selection + selectionArgs);
            return 0;
        }

        @Override
        public String getTableName() throws RemoteException {
            return null;
        }

        @Override
        public String getTableIdFieldName() throws RemoteException {
            return null;
        }
    }
}
