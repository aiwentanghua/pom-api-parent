package pom.api.gxg.Util;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

public class WebSocketClientManager extends WebSocketClient {

    static WebSocketClientManager webSocket = null;
    public static String data=null;




    public static void main(String[] args) {
        try {
            testcainiao("127.0.0.1");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void testcainiao(String ip) throws URISyntaxException {
        data="{\n" +
                "    \"cmd\": \"print\",\n" +
                "    \"requestID\": \"123458976\",\n" +
                "    \"version\": \"1.0\",\n" +
                "    \"task\": {\n" +
                "        \"taskID\": \"7293666\",\n" +
                "        \"preview\": false,\n" +
                "        \"printer\": \"\",\n" +
                "        \"notifyMode\": \"allInOne\",\n" +
                "        \"previewType\": \"pdf\",\n" +
                "        \"documents\": [\n" +
                "            {\n" +
                "                \"documentID\": \"0123456789\",\n" +
                "                \"contents\": [\n" +
                "                    {\n" +
                "                        \"data\": {\n" +
                "                            \"recipient\": {\n" +
                "                                \"address\": {\n" +
                "                                    \"city\": \"杭州市\",\n" +
                "                                    \"detail\": \"良睦路999号乐佳国际大厦2号楼小邮局\",\n" +
                "                                    \"district\": \"余杭区\",\n" +
                "                                    \"province\": \"浙江省\",\n" +
                "                                    \"town\": \"\"\n" +
                "                                },\n" +
                "                                \"mobile\": \"13012345678\",\n" +
                "                                \"name\": \"菜鸟网络\",\n" +
                "                                \"phone\": \"057112345678\"\n" +
                "                            },\n" +
                "                            \"routingInfo\": {\n" +
                "                                \"consolidation\": {\n" +
                "                                    \"name\": \"杭州\",\n" +
                "                                    \"code\": \"hangzhou\"\n" +
                "                                },\n" +
                "                                \"origin\": {\n" +
                "                                    \"name\": \"杭州\",\n" +
                "                                    \"code\": \"POSTB\"\n" +
                "                                },\n" +
                "                                \"sortation\": {\n" +
                "                                    \"name\": \"杭州\"\n" +
                "                                },\n" +
                "                                \"routeCode\": \"123A-456-789\"\n" +
                "                            },\n" +
                "                            \"sender\": {\n" +
                "                                \"address\": {\n" +
                "                                    \"city\": \"杭州市\",\n" +
                "                                    \"detail\": \"文一西路1001号阿里巴巴淘宝城5号小邮局\",\n" +
                "                                    \"district\": \"余杭区\",\n" +
                "                                    \"province\": \"浙江省\",\n" +
                "                                    \"town\": \"\"\n" +
                "                                },\n" +
                "                                \"mobile\": \"13012345678\",\n" +
                "                                \"name\": \"阿里巴巴\",\n" +
                "                                \"phone\": \"057112345678\"\n" +
                "                            },\n" +
                "                            \"shippingOption\": {\n" +
                "                                \"code\": \"COD\",\n" +
                "                                \"services\": {\n" +
                "                                    \"SVC-COD\": {\n" +
                "                                        \"value\": \"200\"\n" +
                "                                    },\n" +
                "                                    \"TIMED-DELIVERY\": {\n" +
                "                                        \"value\": \"SEVERAL-DAYS\"\n" +
                "                                    },\n" +
                "                                    \"PAYMENT-TYPE\": {\n" +
                "                                        \"value\": \"ON-DELIVERY\"\n" +
                "                                    },\n" +
                "                                    \"SVC-INSURE\": {\n" +
                "                                        \"value\": \"1000000\"\n" +
                "                                    },\n" +
                "         \"SVC-PROMISE-DELIVERY\": {\n" +
                "          \"promise-type\": \"SAMEDAY_DELIVERY\"\n" +
                "         }\n" +
                "                                },\n" +
                "                                \"title\": \"代收货款\"\n" +
                "                            },\n" +
                "                            \"waybillCode\": \"0123456789\"\n" +
                "                        },\n" +
                "                        \"signature\": \"19d6f7759487e556ddcdd3d499af087080403277b7deed1a951cc3d9a93c42a7e22ccba94ff609976c5d3ceb069b641f541bc9906098438d362cae002dfd823a8654b2b4f655e96317d7f60eef1372bb983a4e3174cc8d321668c49068071eaea873071ed683dd24810e51afc0bc925b7a2445fdbc2034cdffb12cb4719ca6b7\",\n" +
                "                        \"templateURL\": \"http://cloudprint.cainiao.com/cloudprint/template/getStandardTemplate.json?template_id=101&version=4\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";

        String uri = "ws://192.168.10.158:13528";
        webSocket = new WebSocketClientManager(new URI(uri), new Draft_17());
        //建立连接
        webSocket.connect();


    }

    public WebSocketClientManager(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {

        webSocket.send(data);

    }

    //WebSocket回调函数
    @Override
    public void onMessage(String message) {
        //TODO 对打印服务返回的数据进行处理
        System.out.println(message);
    }

    @Override
    public void onClose(int i, String s, boolean b) {

    }

    @Override
    public void onError(Exception e) {
        System.out.println(e);
    }
}