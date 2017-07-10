package suangrenduobao.daiqile.com.mvlib.mv;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.bugtags.library.Bugtags;

import suangrenduobao.daiqile.com.mvlib.utils.AppManager;


public abstract class BaseApp extends Application implements InitializeService.ServiceImpl {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化框架
        InitializeService.start(this, this);

        Bugtags.start("94d81b0b8e921aa45ce639b49e26f83a", this, Bugtags.BTGInvocationEventNone);

        registerActivityLifecycleCallbacks(new SwitchBackgroundCallbacks());
    }


    private class SwitchBackgroundCallbacks implements ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            AppManager.getAppManager().addActivity(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            AppManager.getAppManager().finishActivity(activity);
        }
    }
}
