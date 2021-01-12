// class Foo {
//     private Semaphore s2;
//     private Semaphore s3;

//     public Foo() {
//         this.s2 = new Semaphore(0);
//         this.s3 = new Semaphore(0);
//     }

//     public void first(Runnable printFirst) throws InterruptedException {
//         // printFirst.run() outputs "first". Do not change or remove this line.
//         printFirst.run();
//         s2.release();
//     }

//     public void second(Runnable printSecond) throws InterruptedException {
//         s2.acquire();
//         // printSecond.run() outputs "second". Do not change or remove this line.
//         printSecond.run();
//         s3.release();
//     }

//     public void third(Runnable printThird) throws InterruptedException {
//         s3.acquire();
//         // printThird.run() outputs "third". Do not change or remove this line.
//         printThird.run();
//     }
// }


// class Foo {
//     private CountDownLatch c2;
//     private CountDownLatch c3;

//     public Foo() {
//         this.c2 = new CountDownLatch(1);
//         this.c3 = new CountDownLatch(1);
//     }

//     public void first(Runnable printFirst) throws InterruptedException {
//         // printFirst.run() outputs "first". Do not change or remove this line.
//         printFirst.run();
//         this.c2.countDown();
//     }

//     public void second(Runnable printSecond) throws InterruptedException {
//         this.c2.await();
//         // printSecond.run() outputs "second". Do not change or remove this line.
//         printSecond.run();
//         c3.countDown();
//     }

//     public void third(Runnable printThird) throws InterruptedException {
//         this.c3.await();
//         // printThird.run() outputs "third". Do not change or remove this line.
//         printThird.run();
//     }
// }



class Foo {
    private boolean c2;
    private boolean c3;

    public Foo() {
        this.c2 = false;
        this.c3 = false;
    }

    public synchronized void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        this.c2 = true;
        notifyAll();
    }

    public synchronized void second(Runnable printSecond) throws InterruptedException {
        while (!c2) {
            wait();
        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        c3 = true;
        notifyAll();
    }

    public synchronized void third(Runnable printThird) throws InterruptedException {
        while (!c3) {
            wait();
        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}