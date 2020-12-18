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

![image-20201217214853420](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217214853420.png)

必须是同一个对象锁

进入指进入同步域，即同步代码块、同步方法或显示锁锁定的代码

可重入锁的最大作用是避免死锁

#### 1.6.2.2 DEMO1：同步代码块

![image-20201217215653471](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217215653471.png)

LockA为同一把锁，不需要等到锁释放，可以重入，同一个线程可以多次获得属于自己的同一把锁

![image-20201217215813912](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217215813912.png)

#### 1.6.2.3 DEMO2：同步方法

![image-20201217215937619](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217215937619.png)

#### 1.6.2.2 DEMO3

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

实现机制：

![image-20201217221344166](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217221344166.png)

![image-20201217221404191](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217221404191.png)

![image-20201217221427028](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217221427028.png)

加锁几次就要解锁几次，加锁解锁次数完全匹配才能释放锁，否则会造成死锁。

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



## 1.10 LockSupport

![image-20201217221805712](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217221805712.png)

park()和unpark()相当于wait()和notify()

### 1.10.1 三种让线程等待或唤醒的方法：

![image-20201217222423698](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217222423698.png)

- Object类中的wait()方法让线程等待，notify()方法唤醒线程

  ![image-20201217222032498](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217222032498.png)

  局限1：没有synchronized不能使用wait和notify方法，wait和notify必须在同步代码块或同步方法里且成对使用

  ![image-20201217222153994](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217222153994.png)

  局限2：wait和notify有先后顺序，先wait再notify才可以

  ![image-20201217222231488](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217222231488.png)

  ![image-20201217222248106](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217222248106.png)

- JUC包下的Condition的await()方法让线程等待，signal()方法唤醒线程

  ![image-20201217222840206](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217222840206.png)

  ![image-20201217222912493](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217222912493.png)

  ![image-20201217222946367](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217222946367.png)

  ![image-20201217223044014](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217223044014.png)

  局限1：必须与lock配合使用，线程先要获得并持有锁，，必须再锁块中

  ![image-20201217223130110](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217223130110.png)

  局限2：await和signal也有先后顺序，必须先等待后唤醒

  ![image-20201217223235345](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217223235345.png)

- LockSupport类可以阻塞当前线程以及唤醒指定被阻塞的线程

### 1.10.2 LockSupport

通过park()和unpark(thread)方法来实现阻塞和唤醒线程的操作。

![image-20201217223719746](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217223719746.png)

- park()/park(Object blocker)：阻塞当前线程/阻塞传入的具体线程

![image-20201217224010132](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217224010132.png)

- unpark(Thread thread)：唤醒处于阻塞状态的指定线程

![image-20201217224151084](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217224151084.png)

![image-20201217224526336](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217224526336.png)

![image-20201217224613030](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217224613030.png)

![image-20201217224730692](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217224730692.png)

交换park和unpark的顺序，可以先通知再阻塞

![image-20201217225040066](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217225040066.png)

![image-20201217225057649](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217225057649.png)

时间戳一样，park()相当于没执行

![image-20201217225325839](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217225325839.png)

优点1：无锁块要求

优点2：支持先通知后等待

### 1.10.3 底层原理：

![image-20201217225448604](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217225448604.png)

![image-20201217225701825](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217225701825.png)

第二个park阻塞了线程a，说明unpark不能累加

![image-20201217225744394](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217225744394.png)

![image-20201217225828600](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217225828600.png)

![image-20201217225840422](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217225840422.png)



## 1.11 AQS(Abstract Queued Synchronizer)

用来构建锁或者其他同步器组件（CountDownLatch、读写锁等组件）的重量级基础框架及整个JUC的基石，通过内置的FIFO队列来完成资源获取线程的排队工作，并通过一个int类型变量表示持有锁的状态。（变量+队列）

![image-20201217230830567](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217230830567.png)

![image-20201217230322684](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217230322684.png)

### 1.11.1 作用

![image-20201217231052201](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217231052201.png)

![image-20201217231104823](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217231104823.png)

![image-20201217231122040](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217231122040.png)

![image-20201217231136921](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217231136921.png)

![image-20201217231205016](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201217231205016.png)

 





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

### 3.3.4 -Xmn 设置年轻代大小

即年轻代和老年代的比例，默认为（1：2），一般使用默认值

### 3.3.5 -XX:MetaspaceSize 设置元空间大小

元空间的本质和永久代类似，都是对JVM规范中方法区的实现。元空间和永久代之间最主要的区别是元空间不在虚拟机中，而是直接使用本地物理内存，永久代则是在堆里。因此，默认情况下，元空间的大小仅收到本地内存限制。

![image-20201212151754584](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201212151754584.png)

元空间默认使用20M左右。为防止OOM:Metaspace错误，可以将元空间的值调大。

常用参数典型配置案例：

![image-20201212152246320](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201212152246320.png)

### 3.3.6 -XX:+PrintGCDetails：打印详细GC收集日志信息

详细见2.12

### 3.3.7 -XX:SurvivorRatio：设置新生代中eden和S0/S1区空间的比例

S0和S1占用相同的空间

默认-XX:+SurvivorRatio = 8 即 Eden : S0 : S1 = 8 : 1 : 1

![image-20201212153716878](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201212153716878.png)

如果-XX:+SurvivorRatio = 4 则 Eden : S0 : S1 = 4 : 1 : 1

![image-20201212153741021](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201212153741021.png)

### 3.3.8 -XX:NewRatio：设置新生代和老年代在堆中所占比例

NewRatio的值即为老年代的占比，一般用默认

默认 -XX:NewRatio = 2 即 新生代 : 老年代 = 1 : 2

![image-20201212154258096](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201212154258096.png)

如果 -XX:NewRatio = 4 即 新生代 : 老年代 = 1 : 4

![image-20201212154330418](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201212154330418.png)

### 3.3.9 -XX:MaxTenuringThreshold：设置垃圾最大年龄

默认为15

![image-20201212154444425](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201212154444425.png)

![image-20201212154621845](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201212154621845.png)

## 3.4 四种引用

![image-20201212160448078](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201212160448078.png)

### 3.4.1 强引用

![image-20201212160541427](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201212160541427.png)

![image-20201212160902588](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201212160902588.png)

obj2引用的对象没有被垃圾回收。

### 3.4.2 软引用

为了降低OOM发生的概率

![image-20201212161040810](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201212161040810.png)

![image-20201212161256188](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201212161256188.png)

![image-20201212161453101](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201212161453101.png)

![image-20201212161538656](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201212161538656.png)

内存不够了软引用被回收变成了null

### 3.4.3 弱引用

![image-20201212161811222](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201212161811222.png)

![image-20201212161959218](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201212161959218.png)

#### 3.4.3.1 软引用和弱引用的适用场景

![image-20201212162415744](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201212162415744.png)

#### 3.4.3.2 WeakHashMap

![image-20201212162809409](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201212162809409.png)

普通HashMap：

![image-20201212163143880](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201212163143880.png)

WeakHashMap：

![image-20201212163348528](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201212163348528.png)

### 3.4.4 虚引用

主要在finalize方法时做对象回收的监控，类似于SpringAOP的后置通知

![image-20201212163724764](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201212163724764.png)

![image-20201212163821535](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201212163821535.png)

#### 3.4.4.1 引用队列

![image-20201212164235699](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201212164235699.png)

![image-20201212164539005](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201212164539005.png)

![image-20201212164913215](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201212164913215.png)

GC时虚引用会被放到引用队列里，可以执行一些后续动作，类似于AOP的后置通知。

![image-20201212165047905](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201212165047905.png)

![image-20201212165332416](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201212165332416.png)

## 3.5 OutOfMemoryError

### 3.5.1 StackOverflowError

栈默认514~1024k，方法递归调用超出栈的空间

![image-20201213145415960](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213145415960.png)

![image-20201213145604704](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213145604704.png)

### 3.5.2 OutOfMemoryError: Java heap space

![image-20201213145825666](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213145825666.png)

### 3.5.3 OutOfMemoryError: GC overhead limit exceeded

![image-20201213150226211](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213150226211.png)

![image-20201213151329481](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213151329481.png)

![image-20201213151552560](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213151552560.png)

![image-20201213151515866](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213151515866.png)

GC回收没有明显的效果，就会报错，GC收不收的效果差不多。

### 3.5.4 OutOfMemoryError: Direct buffer memory

![image-20201213152056168](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213152056168.png)

主要出现在NIO中。

Metaspace使用本地内存不在Java堆中，GC会回收。JVM堆内存够用，不会启动GC，所以本地内存也没有被回收，导致被填满。

JVM可以使用的最大直接内存（maxDirectMemory）默认为1/4的本机内存。

![image-20201213152751905](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213152751905.png)

![image-20201213152805693](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213152805693.png)

### 3.5.5 OutOfMemoryError: unable to create new native thread

![image-20201213153226454](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213153226454.png)

![image-20201213153501426](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213153501426.png)

![image-20201213153917307](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213153917307.png)

创建线程为本地方法

![image-20201213153726802](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213153726802.png)

![image-20201213154126995](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213154126995.png)

![image-20201213154240883](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213154240883.png)

### 3.5.6 OutOfMemoryError: Metaspace

元空间是方法区，装类的信息，即类的模板，包括运行时常量池（非字符串常量池）。

![image-20201213155054047](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213155054047.png)

![image-20201213155513373](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213155513373.png)

![image-20201213155545715](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213155545715.png)

使用spring的cglib的动态字节码技术创建静态内部类，加载到metaspace中。

![image-20201213155714885](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213155714885.png)

## 3.6 垃圾回收器

### 3.6.1 垃圾回收器和垃圾回收算法的关系

GC算法（计数法、复制法、标记清除、标记压缩）是内存回收的方法论。垃圾回收器是GC算法的实现。

没有完美的垃圾收集器，针对具体的场景选择合适的垃圾回收器，分代收集。

### 3.6.2 四种垃圾回收器

![image-20201213162354220](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213162354220.png)

#### 3.6.2.1 Serial 串行垃圾回收器

为单线程环境设计，且只使用一个线程回收垃圾，会暂停所有的用户线程。所以不适合服务器环境。

程序 -> GC -> 程序

#### 3.6.2.2 Parallel 并行垃圾回收器

多个垃圾收集线程并行工作，此时用户线程也是暂停的。适用于科学计算、大数据等弱交互的场景。

#### 3.6.2.3 CMS并发垃圾回收器

用户线程和垃圾回收线程同时执行（不一定是并行，可能交替执行），不需要停顿用户线程。适用于对响应时间有要求的场景。

![image-20201213163234647](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213163234647.png)

#### 3.6.2.4 G1

Java9开始的默认垃圾回收器。将堆内存分割成不同的区域然后并发对其进行垃圾回收。

### 3.6.3 查看默认的垃圾回收器

![image-20201213164259782](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213164259782.png)

Java8默认使用parallelGC。

### 3.6.4 Java默认的垃圾回收器

![image-20201213164519119](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213164519119.png)

还有一种UseSerialOldGC已经deprecated了。

![image-20201213165059384](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213165059384.png)

G1不区分新生代和老年代，都可以覆盖。

![image-20201213165439506](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213165439506.png)

新生代老年代的回收算法按照图中的线相互关联

![image-20201213165837080](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213165837080.png)

![image-20201213165932921](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213165932921.png)

### 3.6.5 新生代垃圾回收器

### 3.6.5.1 串行GC（Serial/Serial Copying）

一般不怎么用

![image-20201213170310817](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213170310817.png)

![image-20201213170411477](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213170411477.png)

![image-20201213170438649](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213170438649.png)

![image-20201213170525247](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213170525247.png)

![image-20201214220728187](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214220728187.png)

![image-20201214220900828](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214220900828.png)

![image-20201214220938187](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214220938187.png)

#### 3.6.5.2 并行GC（ParNew）

![image-20201213171013966](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213171013966.png)

新生代创建对象较多，所以GC会频繁一些，故使用并行GC。

![image-20201213171242000](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213171242000.png)

![image-20201213171058994](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213171058994.png)

![image-20201213171316883](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213171316883.png)

![image-20201214221148378](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214221148378.png)

![image-20201214221308711](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214221308711.png)

#### 3.6.5.3 并行GC（Parallel/Parallel Scavenge）

默认GC。跟ParNew区别：ParNew只是新生代并行，老年代还是串行。Parallel新生代老年代都用并行。

![image-20201213172105010](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213172105010.png)

![image-20201213172134935](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213172134935.png)

![image-20201213172514283](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213172514283.png)

![image-20201213172614389](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213172614389.png)

![image-20201214221533070](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214221533070.png)

![image-20201214221630019](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214221630019.png)

### 3.6.5 老年代垃圾回收器

#### 3.6.5.1 并行GC（Parallel Old/Parallel CMS）

![image-20201213173331998](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213173331998.png)

![image-20201214221801323](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214221801323.png)

![image-20201214221900258](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214221900258.png)

![image-20201213172134935](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213172134935.png)

#### 3.6.5.2 并发标记清除GC（CMS）

![image-20201213173755712](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213173755712.png)

![image-20201213173913242](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213173913242.png)

![image-20201213173939624](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213173939624.png)

四步过程：

- 初始标记（CMS initial mark）：

  标记GCRoots能直接关联到的对象，速度很快，需要暂停所有工作线程。

- 并发标记（CMS concurrent mark）：

  进行GCRoot跟踪的过程，和用户线程一起工作，不需要暂停工作线程。主要标记过程，标记全部对象。

- 重新标记（CMS remark）：

  为了修正在并发标记期间因用户程序继续运行而导致标记产生变动的那一部分对象的标记记录，需要暂停所有的工作线程。由于并发标记时用户线程依然工作，因此在正式清理前需要再做调整（二次确认）。

- 并发清除（CMS concurrent sweep）：

  清除GCRoots不可达对象，和用户线程一起工作，不需要暂停用户线程。基于标记结果直接清除对象。

由于耗时最长的并发标记和并发清除过程中，垃圾收集线程可以和用户线程在一起并发工作，所以总体上来看CMS收集器的内存回收和用户线程是一起并发地执行

![image-20201213175126705](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213175126705.png)

优点：并发收集低停顿

缺点：

![image-20201213175348418](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213175348418.png)

![image-20201213175403601](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213175403601.png)

![image-20201214222007419](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214222007419.png)

![image-20201213175644989](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213175644989.png)

![image-20201213175718694](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213175718694.png)

#### 3.6.5.3 串行GC（Serial Old/Serial CMS）

![image-20201213175939571](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213175939571.png)

基本不用了

![image-20201214222059695](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214222059695.png)

![image-20201214222129060](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214222129060.png)

### 3.6.6 如何选择垃圾收集器

![image-20201213180406618](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213180406618.png)

![image-20201213180452601](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201213180452601.png)

 ### 3.6.7 G1垃圾回收器

![image-20201214222356380](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214222356380.png)

![image-20201214222439801](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214222439801.png)

堆只分了两部分

![image-20201214222532296](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214222532296.png)

#### 3.6.7.1 之前收集器的特点

- 新生代和老年代都是各自独立且连续的内存块
- 新生代使用单eden + S0 + S1进行复制算法
- 老年代收集必须扫描整个老年代区域
- 以尽可能少而快速的执行GC为设计原则

#### 3.6.7.2 G1

![image-20201214223006179](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214223006179.png)

![image-20201214223029924](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214223029924.png)

![image-20201214223204228](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214223204228.png)

特点：

![image-20201214223411847](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214223411847.png)

#### 3.6.7.2 底层原理

Region区域化垃圾收集器，最大的好处是化整为零，避免全内存扫描，只需要按照区域来扫描即可。

![image-20201214223849006](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214223849006.png)

![image-20201214224035536](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214224035536.png)

![image-20201214224121295](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214224121295.png)

红色表示大对象，如果一个对象特别大，就不让他产生在Eden区，直接去养老区。

![image-20201214224255188](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214224255188.png)

![image-20201214224650535](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214224650535.png)

![image-20201214224736899](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214224736899.png)

![image-20201214224857212](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214224857212.png)

![image-20201214225058190](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214225058190.png)

![image-20201214225223862](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214225223862.png)

![image-20201214225335089](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214225335089.png)

![image-20201214225357665](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214225357665.png)

#### 3.6.7.3 常用参数

![image-20201214225530535](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214225530535.png)

![image-20201214225542929](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214225542929.png)

![image-20201214225638953](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214225638953.png)

#### 3.6.7.4 与CMS比较

![image-20201214225730292](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214225730292.png)

### 3.6.8 在SpringBoot中的实际应用

内部启动时JVM调优：

![image-20201214230545589](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214230545589.png)

外部启动：

![image-20201214230634359](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214230634359.png)

![image-20201214230808873](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214230808873.png)

![image-20201214230831243](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201214230831243.png)

## 3.7 环境调试

生产环境服务器变慢，诊断思路和性能评估。

### 3.7.1 整机：top

![image-20201215222627597](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201215222627597.png)

查看cpu和内存

load average：系统的负载均衡。三个值代表系统一分钟、五分钟、十五分钟的平均负载值，如果相加除以三乘百分百高于60%，说明系统负担压力重。

uptime：查看系统性能命令的精简版

### 3.7.2 CPU：vmstat

![image-20201215223246920](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201215223246920.png)

![image-20201215223326358](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201215223326358.png)

![image-20201215223545462](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201215223545462.png)

查看所有cpu核信息：mpstat -P ALL 2

![image-20201215223900623](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201215223900623.png)

idle值越高越好

每个进程使用cpu的用量分解信息：pidstat -u 1 -p 进程号（每1秒采样一次）

![image-20201215224147140](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201215224147140.png)

### 3.7.3 内存：free

应用程序可用的内存

![image-20201215224336009](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201215224336009.png)

查看额外：pidstat -p 进程号 -r 采样间隔秒数

![image-20201215224542443](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201215224542443.png)

### 3.7.4 硬盘：df

![image-20201215224630532](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201215224630532.png)

### 3.7.5 磁盘IO：iostat

![image-20201215224800931](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201215224800931.png)

![image-20201215224850610](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201215224850610.png)

查看额外：pidstat -d 采样间隔秒数 -p 进程号 

![image-20201215225112009](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201215225112009.png)

### 3.7.5 网络IO：ifstat

![image-20201215225309659](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201215225309659.png)

### 3.7.6 定位思路

- 先用top命令找出cpu占比最高的（记录pid）

- ps -ef或者jps进一步定位，查看具体信息

- 定位到具体的线程和代码：ps -mp 进程号 -o THREAD,tid,TIME

  -m：显示所有线程

  -p：pid进程使用cpu的事件

  -o：之后是用户自定义格式

  ![image-20201215225921589](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201215225921589.png)

- 将线程id转化成十六进制（英文小写格式）

- jstack 进程id | grep tid（十六进制线程id英文小写） -A60：定位到代码行数

  ![image-20201215230418218](C:\Users\RobinQu\AppData\Roaming\Typora\typora-user-images\image-20201215230418218.png)

