// OperationBinder.aidl
package com.major.processdata;
import com.major.processdata.entity.ElegantBean;

interface OperationBinder {

       long saveData(in ElegantBean bean);

       List<ElegantBean> getData();

}
