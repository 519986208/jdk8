package com.cly.example.script;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cly.bean.Person;

public class ScriptEngineTest {

    public static void main(String[] args) throws Exception {
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine engine = sem.getEngineByName("javascript");
        engine.put("msg", "just a test");//向上下文中存入变量
        //定义类person
        String str = "msg += '!!!';var person = {firstName:'tom',lastName:'tomas'}; ";
        engine.eval(str);
        //从上下文引擎中取值
        String msg = (String) engine.get("msg");
        System.out.println(msg);
        Person person = JSONObject.parseObject(JSON.toJSONString(engine.get("person")), Person.class);
        System.out.println(person.getFirstName() + "\t " + person.getLastName());

        ////////////////////////////////////////////////////////////
        //定义数学函数
        engine.eval("function add (a, b) {c = a + b; return c; }");
        //取得调用接口
        Invocable jsInvoke = (Invocable) engine;
        //定义加法函数
        Object result1 = jsInvoke.invokeFunction("add", new Object[] { 10, 5 });
        System.out.println("js add(): " + result1);
        System.out.println();
        //////////////////////////////////////////////////////////

        //定义run()函数
        engine.eval("function run() {for(var i=0;i<10;i++){print('www.java.com'+i);}}");
        Invocable invokeEngine = (Invocable) engine;
        Runnable runner = invokeEngine.getInterface(Runnable.class);
        //定义线程运行之
        Thread t = new Thread(runner);
        t.start();
    }

    @Test
    public void loadScript() {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("nashorn");
            engine.eval("load('src/zhongan/func.js')");
            Invocable jsInvoke = (Invocable) engine;
            //定义加法函数
            Object res = jsInvoke.invokeFunction("add", new Object[] { 134, 16 });
            System.out.println(res.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
