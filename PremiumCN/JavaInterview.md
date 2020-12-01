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

#### 1.2.4.4 禁止指令重排原理

![image-20201125225547948](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201125225547948.png)

![image-20201125225919026](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201125225919026.png)

![image-20201125230220533](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201125230220533.png)

### 1.2.5 volatile的应用

- 单例模式双重检查锁（双重检查 + volatile）

![image-20201126215809284](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201126215809284.png)

![image-20201126220012690](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201126220012690.png)

![image-20201126220114290](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201126220114290.png)

![image-20201126220406807](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201126220406807.png)



## 1.3 CAS

### 1.3.1 比较并交换 (Compare And Swap)

如果compareAndSet的期望值跟内存中的真实值一样，则修改为update的值并返回true，若不一样则不修改并返回false。

![image-20201126221301884](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201126221301884.png)

![image-20201126221341639](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201126221341639.png)

### 1.3.2 CAS底层原理

#### 1.3.2.1 atomicInteger.getAndIncrement()

![image-20201126222339650](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201126222339650.png)

#### 1.3.2.2 Unsafe类

![image-20201126222722693](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201126222722693.png)

![image-20201126222902437](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201126222902437.png)

![image-20201126223105334](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201126223105334.png)

![image-20201126223306298](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201126223306298.png)

![image-20201126223946365](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201126223946365.png)

![image-20201126224958262](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201126224958262.png)

![image-20201126225150663](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201126225150663.png)

![image-20201126225209043](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201126225209043.png)

#### 1.3.2.3 CAS缺点

- 循环时间长，开销大

![image-20201126225448730](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201126225448730.png)

如果某个线程在获取主内存之后都有别的线程修改了主内存的值，那么将一直循环直到没有别的线程修改（一直自旋）。

- 只能保证一个共享变量的原子操作

![image-20201126225827440](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201126225827440.png)

- 引出ABA问题



## 1.4 ABA问题

### 1.4.1 原子类AtomicInteger的ABA问题的产生原因

CAS --> Unsafe类 --> CAS底层思想 --> ABA问题 --> 原子引用更新 --> 如何规避ABA问题

![image-20201130221853057](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201130221853057.png)

线程one会以为没有别的线程修改过内存中的A，从而继续对内存进行修改。实际上线程two对内存已经进行了修改，需要重新读取数据。根据业务需求判断是否需要保持初始状态和结束状态之间的状态不变，如果需要则会产生问题，如果不需要则不产生影响。

### 1.4.2 如何解决ABA问题：原子引用

原子引用，泛型类V为需要进行原子包装的自定义类，使类V变为原子类

![image-20201130222818666](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201130222818666.png)

![image-20201130223050457](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201130223050457.png)

![image-20201130223357811](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201130223357811.png)

![image-20201130223508542](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201130223508542.png)

### 1.4.3 时间戳原子引用

增加修改版本号机制（类似时间戳）

T1         100(1)                                      300(2)

T2         100(1)             200(2)              100(3)

T1线程提交的修改的版本号旧于T2线程修改后的版本号，所以不会被更新。即除了判断前后值是否相同，还要判断前后版本号是否一致，只有版本前后相同才会被修改。

![image-20201130224413141](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201130224413141.png)

ABA问题DEMO：

![image-20201130224729168](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201130224729168.png)

![image-20201130224812870](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201130224812870.png)

![image-20201130224923302](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201130224923302.png)

![image-20201130225745593](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201130225745593.png)

![image-20201130225634691](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201130225634691.png)



## 1.5 集合类不安全

### 1.5.1 ArrayList线程不安全

#### 1.5.1.1 现象

![image-20201201220759193](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201201220759193.png)

#### 1.5.1.2 原因

并发争抢修改导致，一个线程正在写入，另一个线程过来争抢锁，导致数据不一致异常：ConcurrentModificationException

#### 1.5.1.3 解决

- List<String> list = new Vector<>();
- Collections.synchronizedList(new ArrayList<>());
- new CopyOnWriteArrayList<>();

#### 1.5.1.4 CopyOnWriteArrayList原理：写时复制

![image-20201201223201895](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201201223201895.png)

![image-20201201223249165](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201201223249165.png)

![image-20201201223313884](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201201223313884.png)

### 1.5.2 HashSet线程不安全

#### 1.5.2.1 现象

![image-20201201223655887](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201201223655887.png)

#### 1.5.2. 解决

- Collections.synchronizedSet(new HashSet<>());
- new CopyOnWriteArraySet<>();

#### 1.5.2.3 原理

![image-20201201223852211](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201201223852211.png)

### 1.5.2 HashMap线程不安全

#### 1.5.2.1 现象

![image-20201201224259263](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201201224259263.png)

#### 1.5.2.2 解决

- Collections.synchronizedMap(new HashMap<>());
- new ConcurrentHashMap<>();



## 1.6 锁

### 1.6.1 公平锁和非公平锁

![image-20201201225140470](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201201225140470.png)

饥饿现象：线程一直被加塞，导致一直不能获得锁

区别：

![image-20201201225414557](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201201225414557.png)

![image-20201201225214008](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201201225214008.png)

![image-20201201225551075](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201201225551075.png)

![image-20201201225607322](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201201225607322.png)

### 1.6.2 可重入锁（又名递归锁）

![image-20201201225841177](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201201225841177.png)