在dim层Hbase有一个错误 原因是version版本落差过大造成的,我将1.3.3版本的改成了2.0.5

![image-20241214083038790](C:\Users\86131\AppData\Roaming\Typora\typora-user-images\image-20241214083038790.png)

在后面版本更换后HMaster进程容易掉以及进程初始化

解决方案:

​      1.进程容易掉

​              在Hbase.env.sh增加一个参数

​              export HBASE_CLASE_CLASSPATH=/bigdata/install

   2. 初始化问题

         原因是版本更换后zookeep中依旧有之前版本的注册的节点导致一直初始化

         只需要在zookeep/bin下zkCli.sh中删除 Hbase下的节点就可以解决该问题

         deletedadd /Hbase

      





