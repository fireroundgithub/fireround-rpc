package fireround.rpc.core;

import java.io.Serializable;

public class RpcResponse implements Serializable {
    private Object result;

    public RpcResponse() {
    }

    public RpcResponse(Object result) {
        this.result = result;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}