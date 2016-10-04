package com.v.unsafe;

import sun.misc.Unsafe;

/**
 * Created by v on 2016/9/18.
 */
public class UnSafeTest {

    private static Unsafe unsafe;

    {
        unsafe = Unsafe.getUnsafe();
        /*Class unSafeClass = Unsafe.class;
        try {
            unsafe = (Unsafe) unSafeClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }*/
    }

}
