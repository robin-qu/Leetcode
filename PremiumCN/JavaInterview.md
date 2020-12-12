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

### 1.7.4 应用：生产者消费者、线程池、消息中间件

#### 1.7.4.1 生产者消费者模式

![image-20201203230325284](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201203230325284.png)

![image-20201204192904879](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204192904879.png)

资源类自身高内聚，包含需要被操作的方法（空调的遥控，人为线程）

![image-20201205151358281](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201205151358281.png)

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

  Synchronized非公平锁

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

#### 1.7.4.4 阻塞队列生产者消费者：

多线程环境下不要用i++或++i，用AtomicInteger

![image-20201204202914004](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204202914004.png)

![image-20201204203219025](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204203219025.png)

![image-20201204203501596](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204203501596.png)

![image-20201204203850625](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204203850625.png)

![image-20201204203628915](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204203628915.png)

![image-20201204203641722](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204203641722.png)

![image-20201204204552702](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204204552702.png)![image-20201204203955126](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201204203955126.png)



## 1.8 线程池

### 1.8.1 Callable:

motivation：一百个线程其中两个出错，返回出错的线程 --> 返回值。

#### 1.8.1.1 与runnable区别：

- callable有返回值
- callable带泛型，泛型为返回值的类型
- 实现call()方法
- call()方法抛异常

#### 1.8.1.2 使用：

![image-20201205153823443](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201205153823443.png)

![image-20201205153838403](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201205153838403.png)

![image-20201205153850721](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201205153850721.png)

futureTask.get()：获得Callable接口的计算结果，如果没有计算完成就调用，会导致线程阻塞，直到计算完成，所以get()一般尽量晚的调用。

![image-20201205155322149](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201205155322149.png)

![image-20201205155333566](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201205155333566.png)

![image-20201205155417437](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201205155417437.png)

![image-20201205155435834](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201205155435834.png)

多个线程抢一个futureTask计算结果只算一次，要多算就要起多个futureTask。

### 1.8.2 为什么用线程池

![image-20201205162035531](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201205162035531.png)

### 1.8.3 架构说明

Java中的线程池是通过Executor框架实现的，该框架中用到了Executor，Executors，ExecutorService和ThreadPoolExecutor几个类。

![image-20201205162615806](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201205162615806.png)

![image-20201205162631525](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201205162631525.png)

有五种线程池：

#### 1.8.3.1 Executors.newScheduledThreadPool()：

线程池中线程每隔一段时间执行

#### 1.8.3.2 Executors.newWorkStealingPool(int)：

Java8新增，使用目前机器上可用的处理器作为并行级别

#### 1.8.3.3 Executors.newFixedThreadPool(int)：

使用场景：

执行长期的任务，性能好很多，任务可以轮替

使用：

![image-20201205163857695](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201205163857695.png)

![image-20201205163916702](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201205163916702.png)

![image-20201205164434299](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201205164434299.png)

#### 1.3.8.4 Executors.newSingleThreadExecutor()：

使用场景：

一个任务一个任务执行的场景

使用：

![image-20201205163951469](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201205163951469.png)

![image-20201205164017181](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201205164017181.png)

![image-20201205164529683](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201205164529683.png)

#### 1.3.8.5 Executors.newCachedThreadPool(int)：

使用场景：

执行很多短期异步的小程序或者负载较轻的服务器（方便扩容）。

使用：

![image-20201205164113174](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201205164113174.png)

![image-20201205164218926](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201205164218926.png)

![image-20201205164233220](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201205164233220.png)

![image-20201205164246342](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201205164246342.png)

![image-20201205164544848](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201205164544848.png)

### 1.8.4 线程池的参数：

![image-20201205165657142](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201205165657142.png)

#### 1.8.4.1 corePoolSize：线程池中的常驻核心线程数

![image-20201205170644110](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201205170644110.png)

![image-20201205170743665](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201205170743665.png)

#### 1.8.4.2 maximumPoolSize：线程池能够容纳的同时执行的最大线程数，必须大于等于1

![image-20201205171028723](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201205171028723.png)

![image-20201205172402518](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201205172402518.png)

#### 1.8.4.3 keepAliveTime：多余的空闲线程的存活时间

当前线程池中线程数量超过corePoolSize时，当空闲时间达到keepAliveTime时，多余的空闲线程会被销毁直至剩下coorPoolSize个线程为止。

#### 1.8.4.4 unit：keepAliveTime的单位

#### 1.8.4.5 workQueue：任务队列，被提交但尚未被执行的任务

![image-20201205171702328](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201205171702328.png)

#### 1.8.4.6 threadFactory：生成线程池中工作线程的线程工厂，用于创建线程，一般用默认的即可

#### 1.8.4.7 handler：拒绝策略，表示当队列满了且工作线程数大于线程池的最大线程数（maximumPoolSize）时执行

### 1.8.5 线程池工作原理

![image-20201206152856036](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206152856036.png)

![image-20201206152913676](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206152913676.png)

### 1.8.6 拒绝策略

阻塞队列已经排满了，而且线程池中的max线程数也达到了，无法为新任务服务，此时要使用拒绝策略机制处理后续的请求。JDK内置四种拒绝策略，均实现了RejectedExecutionHandler：

#### 1.8.6.1 AbortPolicy（默认)

直接抛出RejectedExecutionException阻止系统正常运行

#### 1.8.6.2 CallerRunsPolicy

“调用者运行”，一种调节机制，既不会抛弃任务也不会抛出异常，而是将某些任务回退到调用者，从而降低新任务的流量。

#### 1.8.6.3 DiscardOldestPolicy

抛弃队列中等待最久的任务，然后把当前任务加入到队列中尝试再次提交当前任务。

#### 1.8.6.4 DiscardPolicy

直接丢弃任务，既不处理任务也不抛出异常，如果允许任务丢失这是最好的一种方案。

### 1.8.7 实际使用：

#### 1.8.7.1 使用哪种线程池：自己创建线程池。

![image-20201206154651915](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206154651915.png)

![image-20201206154942002](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206154942002.png)

![image-20201206155111376](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206155111376.png)

LinkedBlockingQueue：无界（Integer.MAX_VALUE）队列，导致请求堆积。 

#### 1.8.7.2 自定义线程池：

以下图为例：

![image-20201206155713284](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206155713284.png)

![image-20201206155928051](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206155928051.png)

![image-20201206160109206](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206160109206.png)

![image-20201206160124258](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206160124258.png)

![image-20201206160221558](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206160221558.png)

![image-20201206160234630](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206160234630.png)

![image-20201206160301937](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206160301937.png)

![image-20201206160316195](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206160316195.png)

3 + 5 = 8

CallerRunsPolicy拒绝策略：

![image-20201206160514791](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206160514791.png)

![image-20201206160526924](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206160526924.png)

![image-20201206160650732](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206160650732.png)

回退到调用者（main线程），谁调的回退给谁。

DiscardOldestPolicy策略：

![image-20201206160848804](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206160848804.png)

![image-20201206160936810](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206160936810.png)

抛弃了两个

DiscardPolicy策略：

![image-20201206161030849](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206161030849.png)

![image-20201206161040765](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206161040765.png)

#### 1.8.7.3 如何配置线程池参数：

CPU密集型：

比如while循环，CPU一直在计算

![image-20201206161444441](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206161444441.png)

IO密集型：

比如需要频繁去数据库取数据

![image-20201206161641210](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206161641210.png)

![image-20201206161851414](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206161851414.png)



## 1.9 死锁

产生死锁的原因：

- 系统资源不足
- 进程运行推进的顺序不合适
- 资源分配不当

![image-20201206162950519](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206162950519.png)

DEMO:

![image-20201206163542133](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206163542133.png)

![image-20201206163558897](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206163558897.png)

![image-20201206163636384](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206163636384.png)

![image-20201206163653161](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206163653161.png)

定位：

- ps命令定位进程号
- jstack找到死锁查看

![image-20201206164038032](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206164038032.png)

![image-20201206164417999](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206164417999.png)

![image-20201206164534926](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206164534926.png)

![image-20201206164733462](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201206164733462.png)



# 2 JVM初级

## 2.1 JVM体系结构

JVM是运行在操作系统之上的，跟硬件没有直接的交互。

灰色线程私有，内存占的非常少，几乎不存在垃圾回收。

亮色所有线程共享，存在垃圾回收。

栈管运行、堆管存储。

![image-20201207220621519](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201207220621519.png)

## 2.2 类加载器

![image-20201207220812369](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201207220812369.png)

方法区放类的描述信息，可以理解为放模板的地方。将Car Class装载进方法区。

### 2.2.1 分类

![image-20201207221625868](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201207221625868.png)

自己定义的class用引用程序类加载器。系统自带的用Bootstrap加载器。自定义的类用AppClassLoader。

![image-20201207222104036](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201207222104036.png)

![image-20201207222552235](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201207222552235.png)

![image-20201207222654298](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201207222654298.png)

### 2.2.2 双亲委派与沙箱安全

从Bootstrap往下加载，最先找到的就加载，自己定义的类不能污染系统自带的类（沙箱安全)。

![image-20201208220832408](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201208220832408.png)

![image-20201207223216348](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201207223216348.png)

## 2.3 Execution Engine

执行引擎：负责解释命令，提交操作系统执行。

## 2.4 Native Interface

![image-20201207225501990](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201207225501990.png)

## 2.5 Native Method Stack

![image-20201207230038267](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201207230038267.png)

## 2.6 程序计数器

通俗来讲就是一个指针，记录了方法之间的调用和执行情况，指向下一条要执行的指令。

![image-20201208215614613](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201208215614613.png)

## 2.7 方法区

![image-20201208221053194](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201208221053194.png)

存放类的模板

![image-20201208221743025](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201208221743025.png)

方法区是一套规范，类比于接口：

![image-20201208222124446](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201208222124446.png)

## 2.8 栈

![image-20201208222839026](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201208222839026.png)

![image-20201208223107642](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201208223107642.png)

栈帧可以理解为Java方法，Java方法被扔进虚拟机后就成了栈帧。栈帧中的数据也跟随方法压栈。

理解：Debug的时候能看到每个方法里的变量。

![image-20201208223834883](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201208223834883.png)

![image-20201208223920298](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201208223920298.png)

每个stack frame可以当作是一个方法被调用。

StackOverflow：

![image-20201208224220178](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201208224220178.png)

![image-20201208224451072](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201208224451072.png)

![image-20201208224650051](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201208224650051.png)

类元数据：即为类的结构信息

## 2.9 堆

![image-20201208230311916](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201208230311916.png)

![image-20201208230550542](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201208230550542.png)

物理上分为新生区和养老区，只有新生区和养老区干活。

## 2.10 垃圾回收

![image-20201209215647971](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201209215647971.png)

Survivor0（S0）区又叫from区，Survivor1（S1）区又叫to区。

![image-20201209220439019](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201209220439019.png)

新生区内存分配8：1：1，S0和S1区永远相等。

from和to区名分和位置不是固定的，每次GC后都会交换，谁空谁是to。

![image-20201209221743081](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201209221743081.png)

临时对象：用完即被回收，一直存在于Eden中。

![image-20201209222035492](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201209222035492.png)

即方法区是一个规范，有不同的实现永久代（Java7）和原空间（Java8）。

![image-20201209222712045](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201209222712045.png)

元数据：可以理解为jar包（比如rt.jar，Spring的jar包，jdbc的驱动jar包）

## 2.11 堆参数调优

Java7：

![image-20201209223256850](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201209223256850.png)

新生代参数Xmn一般不调。

Java8：

![image-20201209223552849](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201209223552849.png)

![image-20201209223925283](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201209223925283.png)

jvm一般用物理内存的四分之一。

![image-20201209224806508](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201209224806508.png)

生产环境中Xms和Xmx必须一样大，防止GC频繁收集和应用程序争抢内存（内存值忽高忽低，产生异常）。

![image-20201209225349407](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201209225349407.png)

![image-20201209225632710](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201209225632710.png)

![image-20201209225727425](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201209225727425.png)

## 2.12 GC日志分析

![image-20201209225703482](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201209225703482.png)

OOM：DEMO

![image-20201209225841152](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201209225841152.png)

![image-20201209225929056](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201209225929056.png)

![image-20201209225948534](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201209225948534.png)

![image-20201209230133186](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201209230133186.png)

![image-20201209230201767](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201209230201767.png)

老年代full GC之后依然空间不足，则OOM。

![image-20201210215525639](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201210215525639.png)

Allocation failure：分配失败

2048：GC之前新生代用了多少

488：GC之后新生代用了多少

![image-20201210215707488](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201210215707488.png)

![image-20201210220509188](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201210220509188.png)

![image-20201210220427939](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201210220427939.png)

## 2.13 GC算法

GC是什么：分代收集算法

- 次数上频繁收集新生代
- 次数上较少收集老年代
- 基本不动元空间

![image-20201210221110575](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201210221110575.png)

![image-20201210221156667](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201210221156667.png)

### 2.13.1 引用计数法

![image-20201210221821173](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201210221821173.png)

一个对象有一个引用就加一，少一个引用就减一，直到为零时（没有被引用）被回收。用的很少。

循环引用DEMO:

![image-20201210222207933](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201210222207933.png)

![image-20201210222220116](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201210222220116.png)

System.gc()：手动触发GC，但不是立刻触发，实际开发中一般不要用，使用系统默认的GC即可。

### 2.13.2 复制算法（Copying）

年轻代中使用的是minorGC，这种GC算法采用的是复制算法。MinorGC的复制交换过程。

![image-20201210223600863](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201210223600863.png)

![image-20201210223754757](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201210223754757.png)

![image-20201210224103247](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201210224103247.png)

复制时费空间

![image-20201210224206932](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201210224206932.png)

![image-20201210224410745](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201210224410745.png)

![image-20201210224426201](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201210224426201.png)

![image-20201210224726449](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201210224726449.png)

### 2.13.3 标记清除（Mark-Sweep）

一般用在老年代，老年代一般是由标记清除或者是由标记清除和标记压缩的混和实现。

![image-20201210225201889](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201210225201889.png)

![image-20201210225409700](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201210225409700.png)

一次标记，一次清除。把垃圾收走剩下的内存有碎片（内存不连续)。

![image-20201210225620140](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201210225620140.png)

### 2.13.4 标记压缩（Mark-Compact）

标记 -> 清除 -> 压缩

![image-20201210225903419](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201210225903419.png)

理论上效果最好，但耗时也最长。

![image-20201210230047036](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201210230047036.png)

![image-20201210230116964](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201210230116964.png)

### 2.13.4 总结：

新生代用复制，老年代用标记清除压缩（分代收集）。

![image-20201210230403233](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201210230403233.png)

![image-20201210230506474](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201210230506474.png)

![image-20201210230642295](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201210230642295.png)

![image-20201210230657957](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201210230657957.png)

# 3 JVM高级

## 3.1 GCRoot的理解

### 3.1.1 垃圾回收时如何确定垃圾

内存中不再使用的空间就是垃圾。

#### 3.1.1.1 引用计数法：

![image-20201211192655247](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201211192655247.png)

#### 3.1.1.2 枚举根节点做可达性分析（根搜索路径）：

![image-20201211192850227](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201211192850227.png)

![image-20201211193059477](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201211193059477.png)

### 3.1.2 GC Root：

- 虚拟机栈（栈帧中的局部变量区，也叫局部变量表）中引用的对象。

![image-20201211193933698](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201211193933698.png)

m1方法在栈里，t1方法里的一个局部变量，即为虚拟机栈中引用的对象。

- 方法区中类静态属性引用的对象。即上图中的t2。

- 方法区中常量引用的对象。即上图中的t3。
- 本地方法栈中JNI（Native方法）引用的对象。

### 3.2 如何查看JVM系统默认值

### 3.2.1 JVM参数类型：

#### 3.2.1.1标配参数：

- -version
- -help
- java -showversion

#### 3.2.1.2 X参数（了解）：

- -Xint：解释执行（interpreted）
- -Xcomp：第一次使用就编译成本地代码（compiled）
- -Xmixed：混合模式

#### 3.2.1.3 XX参数：主要使用jps和jinfo两个命令

- Boolean类型：-XX:+ 或者 -某个属性值 +表示开启 -表示关闭
  - 是否打印GC收集细节：-XX:(-/+)PrintGCDetails
  - 是否使用串行垃圾回收器：-XX:(-/+)UseSerialGC

![image-20201211195949386](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201211195949386.png)

![image-20201211195852985](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201211195852985.png)

PrintGCDetails参数关闭

![image-20201211200019727](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201211200019727.png)

![image-20201211200057733](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201211200057733.png)

PrintGCDetails参数开启。

jinfo -flag 配置项 进程编号：查看某一进程某一个属性是否开启或数值

- KV设值类型：-XX:属性key=属性值value
  - -XX:MetaSpaceSize=128m
  - -XX:MaxTenuringThreshold=15

![image-20201211200900597](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201211200900597.png)

![image-20201211201001798](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201211201001798.png)

![image-20201211201021439](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201211201021439.png)

jinfo flags 进程号：显示全部参数

![image-20201211201357524](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201211201357524.png)

Command Line为自定义配置的值

简写：

-Xms：等价于-XX:InitialHeapSize

-Xmx：等价于-XX:MaxHeapSize

### 3.2.2 查看JVM参数默认值：

#### 3.2.2.1 -XX:+PrintFlagsInitial：查看初始默认值

java -XX:+PrintFlagsInitial

![image-20201211202118880](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201211202118880.png)

#### 3.2.2.1 -XX:+PrintFlagsFinal：查看修改后更新值

java -XX:+PrintFlagsFinal

![image-20201211202327005](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201211202327005.png)

纯等号为没有被修改过的

冒号等号为被修改过的值

![image-20201211202411605](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201211202411605.png)

![image-20201211202930673](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201211202930673.png)

![image-20201211202847490](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201211202847490.png)

T为运行Java类的名字

-XX:+PrintCommandLineFlags：打印命令行参数

![image-20201211203120231](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201211203120231.png)

偏重于查看默认的垃圾回收器

## 3.3 JVM常用基本参数

### 3.3.1 -Xms：初始内存大小

默认为物理内存的1/64，等价于 -XX:InitialHeapSize

### 3.3.2 -Xmx：最大内存大小

默认为物理内存的1/4，等价于-XX:MaxHeapSize

### 3.3.3 -Xss：设置单个线程栈的大小

依赖于平台，一般默认为512k~1024k，等价于-XX:ThreadStackSize

![image-20201211204151102](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201211204151102.png)

0代表使用默认出厂值

![image-20201211204340677](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201211204340677.png)

![image-20201211204356804](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201211204356804.png)

























