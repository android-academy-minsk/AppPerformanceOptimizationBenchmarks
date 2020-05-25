package com.androidacademy.minsk.appoptimization.memoryleak;

public class OuterClass {
    private int sum;

    private void increaseSum() {
        ++sum;
    }

    private void decreaseSum() {
        --sum;
    }

    private abstract class InnerClass {
        private void plus() {
            increaseSum();
        }

        abstract void minus();
    }

    private void doSomething() {
        new InnerClass() {
            @Override
            void minus() {
                decreaseSum();
            }
        };
    }
}
