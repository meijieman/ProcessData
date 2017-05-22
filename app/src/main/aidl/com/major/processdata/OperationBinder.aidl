// OperationBinder.aidl
package com.major.processdata;
import com.major.processdata.ElegantBean;

interface OperationBinder {

       long saveData(in ElegantBean bean);

       List<ElegantBean> getData();

}
