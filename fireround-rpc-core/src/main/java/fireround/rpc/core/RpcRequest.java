package fireround.rpc.core;

import java.io.Serializable;

public class RpcRequest implements Serializable {
    private String methodName;
    private String className;
    private Object[] args;

    public RpcRequest() {
    }

    public RpcRequest(String methodName, String className, Object[] args) {
        this.methodName = methodName;
        this.className = className;
        this.args = args;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}