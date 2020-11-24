# 1 并发

## 1.1 并发和并行的区别

**并发** ：多个线程访问同一个资源（秒杀）。

**并行**：同时执行动作。

## 1.2 Volatile

### 1.2.1 Volatile是JVM提供的一种轻量级的同步机制

- 保证可见性
- 不保证原子性
- 禁止指令重排

### 1.2.2 JMM (Java Memory Model) Java内存模型：

是一种抽象概念，并不实际存在，描述一组规则或规范，通过这个规范定义了了程序中各个变量（包括实例字段，静态字段和构成数组元素的对象）的访问方式。

JMM特性：

- 可见性
- 原子性
- 有序性

JMM关于同步的规定：

- 线程解锁前，必须将共享变量的值刷新回主内存
- 线程加锁前，必须读取主内存的最新值到自己的工作内存
- 加锁解锁为同一把锁

![image-20201124223526885](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201124223526885.png)

![image-20201124223604907](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201124223604907.png)

将t1在t1工作内存中对age的改动立刻通知其他线程（可见性），其他线程能立刻感知某线程对共享数据的修改

### 1.2.3 volatile的可见性

volatile可以保证可见性，及时通知其他线程主物理内存中的值已经被修改

![image-20201124224915564](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201124224915564.png)![image-20201124224947297](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201124224947297.png)![image-20201124225006521](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201124225006521.png)

![image-20201124225136632](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201124225136632.png)

![image-20201124225233453](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201124225233453.png)

![image-20201124225252806](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201124225252806.png)

![image-20201124225332311](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201124225332311.png)

![image-20201124225540798](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201124225540798.png)

### 1.2.4 volatile不保证原子性

