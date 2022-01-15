package fireround.rpc.example;

import java.io.Serializable;

public class CreateOrderRequest implements Serializable {

    private String requestNo;

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }
}
