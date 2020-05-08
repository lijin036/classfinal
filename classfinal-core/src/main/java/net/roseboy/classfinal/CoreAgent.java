package net.roseboy.classfinal;


import java.lang.instrument.Instrumentation;

import com.sunrisechina.SSTransformer;


/**
 * 监听类加载
 *
 * @author roseboy
 */
public class CoreAgent {
    /**
     * man方法执行前调用
     *
     * @param args 参数
     * @param inst inst
     */
    public static void premain(String args, Instrumentation inst) {
        //GO
        if (inst != null) {
            SSTransformer tran = new SSTransformer();
            inst.addTransformer(tran);
        }
    }
}
