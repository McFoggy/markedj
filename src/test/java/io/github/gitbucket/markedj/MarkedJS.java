package io.github.gitbucket.markedj;

import java.io.InputStreamReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MarkedJS {
    private ScriptEngineManager manager;
    private ScriptEngine engine;

    private final static MarkedJSHolder HOLDER = new MarkedJSHolder();

    private MarkedJS() {
        manager = new ScriptEngineManager();
        engine = manager.getEngineByName("javascript");

        try {
            InputStreamReader isr = new InputStreamReader(MarkedJS.class.getResource("/js/marked.js").openStream());
            engine.eval(isr);
        } catch (Exception e) {
            throw new IllegalStateException("cannot load marked.js", e);
        }
    }
    
    public String parse(String input) {
        Invocable invocable = (Invocable) engine;
        try {
            Object result = invocable.invokeFunction("marked", input);
            if (result == null) {
                return null;
            }
            return (String)result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static MarkedJS getInstance() {
        return HOLDER.init();
    }
    
    private static class MarkedJSHolder {
        private MarkedJS marker;
        
        private MarkedJS init() {
            if (marker == null) {
                marker = new MarkedJS();
            }
            
            return marker;
        }
    }
}
