## app 模块
app 模块为测试 LiteOrm 和 GreenDao 跨进程访问数据的问题

切换数据库框架的方法为修改 com.major.processdata.dao.DaoProxy 的构造器中初始化方法。

存在问题
使用 LiteOrm 在一个进程对原来为空的表做插入操作，在另一个进程中访问这个表，发现为空。
GreenDao 则不会





GreenDao 与 ContentProvider 的结合
https://stackoverflow.com/questions/26073144/using-green-dao-with-content-provider?answertab=active#tab-top