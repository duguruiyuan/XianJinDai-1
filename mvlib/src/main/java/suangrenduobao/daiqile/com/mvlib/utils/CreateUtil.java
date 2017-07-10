package suangrenduobao.daiqile.com.mvlib.utils;

import java.lang.reflect.ParameterizedType;

public final class CreateUtil {

    public static <T> T getT(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass().getGenericSuperclass()))
                    .getActualTypeArguments()[i]).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}