package com.daiqile.xianjindai;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.daiqile.xianjindai.utils.ApiRequest;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import rx.Subscriber;
import suangrenduobao.daiqile.com.mvlib.utils.ToastUtils;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

//        assertEquals("com.daiqile.xianjindai", appContext.getPackageName());
//        DecimalFormat decimalFormat = new DecimalFormat("0.00");
//
//        String format = decimalFormat.format("20");
//        Log.d("BorrowActivity", format);

        int i = Integer.parseInt("202");

        System.out.print(i);
        Log.d("ExampleInstrumentedTest", "a");

    }
}
