package com.major.processdata;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.major.processdata.dao.Dao;
import com.major.processdata.dao.DaoProxy;
import com.major.processdata.entity.ElegantBean;

import java.util.List;

public class OperationService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return new Operation();
    }

    class Operation extends OperationBinder.Stub {

        private Dao mDao;

        public Operation() {
            mDao = new DaoProxy(getApplicationContext(), MainActivity.ELEGANT_DB);
        }

        @Override
        public long saveData(ElegantBean bean) throws RemoteException {
            return mDao.insert(bean);
        }

        @Override
        public List<ElegantBean> getData() throws RemoteException {
            return mDao.query(ElegantBean.class);
        }
    }
}
