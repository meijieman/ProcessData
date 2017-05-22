package com.major.processdata;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.litesuits.orm.LiteOrm;

import java.util.List;

public class OperationService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return new Operation();
    }

    class Operation extends OperationBinder.Stub {

        private LiteOrm mLiteOrm;

        public Operation() {
            mLiteOrm = LiteOrm.newSingleInstance(OperationService.this.getApplicationContext(), MainActivity.ELEGANT_DB);
        }

        @Override
        public long saveData(ElegantBean bean) throws RemoteException {
            return mLiteOrm.insert(bean);
        }

        @Override
        public List<ElegantBean> getData() throws RemoteException {
            return mLiteOrm.query(ElegantBean.class);
        }
    }
}
