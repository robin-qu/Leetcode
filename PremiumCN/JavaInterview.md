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

#### 1.2.3.1 代码演示

![image-20201124224915564](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201124224915564.png)

![image-20201124224947297](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201124224947297.png)![image-20201124225006521](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201124225006521.png)

![image-20201124225136632](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201124225136632.png)

线程AAA对Data中number的修改对主线程不可见，所以主线程工作空间中的number值依然为0



![image-20201124225233453](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201124225233453.png)

![image-20201124225252806](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201124225252806.png)

![image-20201124225332311](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201124225332311.png)

#### 1.2.3.2 原理

由于number被volatile修饰，线程AAA对number的改动会立刻通知主线程，主线程中的number的值也会变成60

![image-20201124225540798](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201124225540798.png)

### 1.2.4 volatile不保证原子性

不可分割，完整性。即某个线程在执行某业务时中途不能被加塞或分割。需要整体完整，要么同时成功要么同时失败。

#### 1.2.4.1 代码演示

![image-20201125214545053](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201125214545053.png)

![image-20201125215020142](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201125215020142.png)

```java
while (Thread.activeCount() > 2) {
    // 正常有主线程和GC线程两个线程，大于二说明还有其他线程
}
```

![image-20201125215216987](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201125215216987.png)

#### 1.2.4.2 原理

![image-20201125220144236](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201125220144236.png)

put时出现了写覆盖，线程太快没有获取最新的值就将本工作内存中的值写入到了主内存，导致数据丢失。

#### 1.2.4.3 如何解决

- synchronized修饰
- 使用JUC下的AtomicInteger

![image-20201125221528790](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201125221528790.png)

![image-20201125222140896](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201125222140896.png)

![image-20201125222155331](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201125222155331.png)

![image-20201125222214991](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201125222214991.png)

![image-20201125222225922](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201125222225922.png)

### 1.2.4 volatile的有序性

![image-20201125223854821](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201125223854821.png)

单线程环境下不需要关心指令重排

#### 1.2.4.1 指令重排案例1

![image-20201125223808944](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201125223808944.png)

因为数据的依赖性，4不可以排在123前

#### 1.2.4.2 指令重排案例2

![image-20201125224223185](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201125224223185.png)

volatile禁止指令重排，必须按顺序执行

#### 1.2.4.3 指令重排案例3

![image-20201125225435297](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201125225435297.png)

有可能flag = true先于a = 1执行，flag = true之后a = 1之前执行method2。

将a和flag用volatile就不会出现flag = true先于a = 1执行的情况。

#### 1.4.2.4 禁止指令重排原理

![image-20201125225547948](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201125225547948.png)

![image-20201125225919026](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201125225919026.png)

![image-20201125230220533](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201125230220533.png)