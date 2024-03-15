package shared;

import client.Main;
import shared.CallbackHelper.CallbackState;
import shared.CallbackHelper.Operaion;

public class Callback {
    public static void Resolve(server.RuntimeServer ctx, String ip, int port, String name, String netMessage) {
        ctx.sendNetMessage(ip, port, name, netMessage);
        CallbackState.getInstance(name).setResponse(netMessage);
        CallbackState.getInstance(name).setProcessed();
    }

    public static Object Trigger(Main ctx, String name, String netMessage, Operaion action) {
        ctx.getCallbacks().add(CallbackState.register(name));
        ctx.sendNetMessage(name, netMessage);
        while (!CallbackState.getInstance(name).isProcessed()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String response = CallbackState.getInstance(name).getResponse();
        return action.execute(CallbackState.getInstance(name).getResponse());
    }
}