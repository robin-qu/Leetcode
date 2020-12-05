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

#### 1.6.2.1 是什么

![image-20201201225841177](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201201225841177.png)ReentrantLock和synchronized是两个典型的可重入锁

可重入锁的最大作用是避免死锁

#### 1.6.2.2 DEMO

![image-20201202214026425](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201202214026425.png)

![image-20201202214130499](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201202214130499.png)

![image-20201202214142471](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201202214142471.png)

同步方法可以进入另外一个同步方法

12线程在在外层方法（sendSMS）获取锁的时候，在进入内层方法（sendEmail）会自动获取锁

![image-20201202214833863](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201202214833863.png)

![image-20201202214914140](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201202214914140.png)

![image-20201202215051624](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201202215051624.png)

![image-20201202215147551](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201202215147551.png)

BTW：可以锁多次，只要能配对

![image-20201202215342494](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201202215342494.png)

### 1.6.3 自旋锁

![image-20201202220228789](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201202220228789.png)

手写一个自旋锁：

![image-20201202221656054](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201202221656054.png)    

![image-20201202221716220](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201202221716220.png)

![image-20201202221739054](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201202221739054.png)

### 1.6.4 独占锁（写锁）/共享锁（读锁）/互斥锁

![image-20201202223152394](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201202223152394.png)

读写锁：读的时候多个线程可以同时读，写的时候只有一个线程能写。Lock和synchronized读和写都只能一个线程操作。数据一致性可以保证但是并发量会下降。为增加并发量，可以允许多个线程同时读数据。

![image-20201202223644134](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201202223644134.png)

写操作：原子 + 独占，整个过程为完整的中间不能被分割打断

DEMO:

![image-20201202225751183](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201202225751183.png)

![image-20201202225810885](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201202225810885.png)

Result:

![image-20201202225941354](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201202225941354.png)

读写分离：写操作被严格控制，中间没有加塞。既保证了一致性又提高了并发性。读没有严格的控制顺序，属于共享操作。

不加锁 VS 加读写锁

![image-20201202230147349](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201202230147349.png)



## 1.7 JUC工具

### 1.7.1 CountDownLatch

![image-20201203214705671](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201203214705671.png)

![image-20201203215224921](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201203215224921.png)

![image-20201203215303993](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201203215303993.png)

让线程阻塞直到CountDownLatch减少到零时才继续进行。当线程调用await时该线程在计数结束之前会被阻塞。其他线程调用countDown时将计数减少1，调用countDown方法的线程不会被阻塞。当计数器减为零的时候，调用await的方法被唤醒并继续执行。

### 1.7.2 CyclicBarrier

让一组线程到达一个屏障（同步点）时被阻塞，直到最后一个线程到达屏障时屏障才会放开，所有被屏障拦截的线程才会继续执行，线程通过await方法进入屏障。

![image-20201203220948229](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201203220948229.png)

![image-20201203221200279](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201203221200279.png)

![image-20201203221217539](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201203221217539.png)

### 1.7.3 Semaphore

信号量主要用于两个目的，一个是用于多个共享资源的互斥使用（6辆车抢3个车位），另一个是用于并发线程数的控制。

![image-20201203221958392](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201203221958392.png)

![image-20201203222442098](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201203222442098.png)

![image-20201203222505490](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201203222505490.png)

semaphore的值是伸缩的，最后车都走完了之后又变成了6



## 1.7 阻塞队列

### 1.7.1 阻塞队列

![image-20201203223155829](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201203223155829.png)

Thread1为生产线程，Thread2为消费线程

![image-20201203223419377](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201203223419377.png)

### 1.7.2 好处

![image-20201203223600768](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201203223600768.png)

### 1.7.3 BlockingQueue架构

![image-20201203224409005](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201203224409005.png)

![image-20201203224149850](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201203224149850.png)

### 1.7.3 API

#### 1.7.3.1 BlockingQueue

![image-20201203224554157](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201203224554157.png)

![image-20201203225129362](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201203225129362.png)

 #### 1.7.3.2 SynchronousQueue

![image-20201203225551833](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201203225551833.png)

![image-20201203225918707](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201203225918707.png)

![image-20201203225826144](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201203225826144.png)

![image-20201203230105897](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201203230105897.png)

### 1.7.4 应用

#### 1.7.4.1 生产者消费者模式

![image-20201203230325284](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201203230325284.png)

![image-20201204192904879](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204192904879.png)

资源类自身高内聚，包含需要被操作的方法（空调的遥控，人为线程）

![image-20201204192742905](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204192742905.png)

![image-20201204192804273](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204192804273.png)



![image-20201204193055196](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204193055196.png)

![image-20201204193117017](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204193117017.png)

![image-20201204193132195](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204193132195.png)

![image-20201204193224927](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204193224927.png)

虚假唤醒：

![image-20201204193333983](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204193333983.png)

多线程判断要放在循环里面，用while不能用if，即被唤醒后要重新进行一次判断。

如果有两个线程生产两个线程消费：

![image-20201204193531452](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204193531452.png)

#### 1.7.4.2 Synchronized和Lock的区别：

- 原始构成：

  Synchronized是Java关键字，属于JVM层面，通过monitorenter和monitorexit实现。底层通过monitor对象来实现，wait和notify也依赖于monitor对象，只有在同步代码块和同步方法中才可以调用wait和notify等方法

  Lock是具体类(java.util.concurrent.locks.lock)，属于API层面。

- 使用方法：

  Synchronized不需要手动释放锁，当同步的代码执行完毕后系统会自动释放线程占用的锁

  Lock需要手动释放锁，若没有主动释放有可能导致死锁（lock()和unlock()需要配合try catch finally使用）。

- 是否可中断：

  Synchronized不可以中断，除非正常运行完整或抛出异常

  Lock可以中断，可以设置超时方法tryLock(long timeout, TimeUnit timeunit)，也可以将lockInterruptibly()放在代码块中调用interrupt()方法进行中断

- 加锁是否公平：

  Synchronized公平锁

  Lock默认非公平锁，构造器可以指定是否公平（true公平，false不公平）

- 锁绑定多个condition

  synchronized没有condition

  Lock用来实现分组唤醒需要唤醒的线程，可以精确唤醒，而不是像synchronized一样要么唤醒一个要么全部唤醒，例子：

![image-20201204201103999](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204201103999.png)

![image-20201204201511115](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204201511115.png)

![image-20201204201744613](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204201744613.png)

![image-20201204201450231](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204201450231.png)

![image-20201204201615249](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204201615249.png)

![image-20201204201653408](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204201653408.png)

![image-20201204201903852](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204201903852.png)

![image-20201204201926265](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204201926265.png)

阻塞队列生产者消费者：

多线程环境下不要用i++或++i，用AtomicInteger

![image-20201204202914004](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204202914004.png)

![image-20201204203219025](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204203219025.png)

![image-20201204203501596](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204203501596.png)

![image-20201204203850625](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204203850625.png)

![image-20201204203628915](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204203628915.png)

![image-20201204203641722](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204203641722.png)

![image-20201204204552702](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204204552702.png)![image-20201204203955126](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204203955126.png)













#### 1.7.4.3 线程池

#### 1.7.4.4 消息中间件

