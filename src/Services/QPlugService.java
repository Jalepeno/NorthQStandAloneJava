package Services;

import javax.ws.rs.core.Form;

public class QPlugService {

    NorthQRestfulUtils nq = new NorthQRestfulUtils();

    public void turnOnPlug() {

        String token = nq.getJsonMap(nq.getTokenJSON()).get("token").toString();
        updateQplugStatus(1, token);
    }

    public void turnOffPlug() {
        String token = nq.getJsonMap(nq.getTokenJSON()).get("token").toString();
        updateQplugStatus(0, token);
    }

    public void getStatus() {
        // TODO: Optional, call to get status of QPlug
    }

    public void updateQplugStatus(int status, String token) {
        /*
         * URL: https://homemanager.tv/main/setBinaryValue
         * â€¢ ON: user=2164&token=4pd-5f21e6aa764e71b78885&gateway=0000003009&node_id=2&pos=0
         * â€¢ OFF: user=2164&token=4pd-5f21e6aa764e71b78885&gateway=0000003009&node_id=2&pos=255
         */
        Form form = new Form();
        form.param("user", "2166");
        form.param("token", token);
        form.param("gateway", "0000003652");
        form.param("node_id", "2");
        String stat = "0";

        if (status > 0) {
            stat = "255";
        }
        form.param("pos", stat);
        System.out.println(
                nq.getHttpPostResponse("https://homemanager.tv/main/setBinaryValue", form).readEntity(String.class));

    }
}
