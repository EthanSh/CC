import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class BlackListGen {

//    public static final String jsonFile =  BlackListGen.class.getClassLoader().getResource("Namespace.json").getPath();

    static String  jsonStr = "{\"batchcomplete\":\"qwe\",\"query\":{\"namespaces\":{\"-2\":{\"id\":-2,\"case\":\"first-letter\",\"canonical\":\"Media\",\"*\":\"Media\"},\"-1\":{\"id\":-1,\"case\":\"first-letter\",\"canonical\":\"Special\",\"*\":\"Special\"},\"0\":{\"id\":0,\"case\":\"first-letter\",\"content\":\"\",\"*\":\"\"},\"1\":{\"id\":1,\"case\":\"first-letter\",\"subpages\":\"\",\"canonical\":\"Talk\",\"*\":\"Talk\"},\"2\":{\"id\":2,\"case\":\"first-letter\",\"subpages\":\"\",\"canonical\":\"User\",\"*\":\"User\"},\"3\":{\"id\":3,\"case\":\"first-letter\",\"subpages\":\"\",\"canonical\":\"User talk\",\"*\":\"User talk\"},\"4\":{\"id\":4,\"case\":\"first-letter\",\"subpages\":\"\",\"canonical\":\"Project\",\"*\":\"Wikipedia\"},\"5\":{\"id\":5,\"case\":\"first-letter\",\"subpages\":\"\",\"canonical\":\"Project talk\",\"*\":\"Wikipedia talk\"},\"6\":{\"id\":6,\"case\":\"first-letter\",\"canonical\":\"File\",\"*\":\"File\"},\"7\":{\"id\":7,\"case\":\"first-letter\",\"subpages\":\"\",\"canonical\":\"File talk\",\"*\":\"File talk\"},\"8\":{\"id\":8,\"case\":\"first-letter\",\"canonical\":\"MediaWiki\",\"*\":\"MediaWiki\"},\"9\":{\"id\":9,\"case\":\"first-letter\",\"subpages\":\"\",\"canonical\":\"MediaWiki talk\",\"*\":\"MediaWiki talk\"},\"10\":{\"id\":10,\"case\":\"first-letter\",\"subpages\":\"\",\"canonical\":\"Template\",\"*\":\"Template\"},\"11\":{\"id\":11,\"case\":\"first-letter\",\"subpages\":\"\",\"canonical\":\"Template talk\",\"*\":\"Template talk\"},\"12\":{\"id\":12,\"case\":\"first-letter\",\"subpages\":\"\",\"canonical\":\"Help\",\"*\":\"Help\"},\"13\":{\"id\":13,\"case\":\"first-letter\",\"subpages\":\"\",\"canonical\":\"Help talk\",\"*\":\"Help talk\"},\"14\":{\"id\":14,\"case\":\"first-letter\",\"subpages\":\"\",\"canonical\":\"Category\",\"*\":\"Category\"},\"15\":{\"id\":15,\"case\":\"first-letter\",\"subpages\":\"\",\"canonical\":\"Category talk\",\"*\":\"Category talk\"},\"100\":{\"id\":100,\"case\":\"first-letter\",\"subpages\":\"\",\"canonical\":\"Portal\",\"*\":\"Portal\"},\"101\":{\"id\":101,\"case\":\"first-letter\",\"subpages\":\"\",\"canonical\":\"Portal talk\",\"*\":\"Portal talk\"},\"108\":{\"id\":108,\"case\":\"first-letter\",\"subpages\":\"\",\"canonical\":\"Book\",\"*\":\"Book\"},\"109\":{\"id\":109,\"case\":\"first-letter\",\"subpages\":\"\",\"canonical\":\"Book talk\",\"*\":\"Book talk\"},\"118\":{\"id\":118,\"case\":\"first-letter\",\"subpages\":\"\",\"canonical\":\"Draft\",\"*\":\"Draft\"},\"119\":{\"id\":119,\"case\":\"first-letter\",\"subpages\":\"\",\"canonical\":\"Draft talk\",\"*\":\"Draft talk\"},\"446\":{\"id\":446,\"case\":\"first-letter\",\"canonical\":\"Education Program\",\"*\":\"Education Program\"},\"447\":{\"id\":447,\"case\":\"first-letter\",\"subpages\":\"\",\"canonical\":\"Education Program talk\",\"*\":\"Education Program talk\"},\"710\":{\"id\":710,\"case\":\"first-letter\",\"canonical\":\"TimedText\",\"*\":\"TimedText\"},\"711\":{\"id\":711,\"case\":\"first-letter\",\"canonical\":\"TimedText talk\",\"*\":\"TimedText talk\"},\"828\":{\"id\":828,\"case\":\"first-letter\",\"subpages\":\"\",\"canonical\":\"Module\",\"*\":\"Module\"},\"829\":{\"id\":829,\"case\":\"first-letter\",\"subpages\":\"\",\"canonical\":\"Module talk\",\"*\":\"Module talk\"},\"2300\":{\"id\":2300,\"case\":\"first-letter\",\"canonical\":\"Gadget\",\"*\":\"Gadget\"},\"2301\":{\"id\":2301,\"case\":\"first-letter\",\"canonical\":\"Gadget talk\",\"*\":\"Gadget talk\"},\"2302\":{\"id\":2302,\"case\":\"case-sensitive\",\"canonical\":\"Gadget definition\",\"defaultcontentmodel\":\"GadgetDefinition\",\"*\":\"Gadget definition\"},\"2303\":{\"id\":2303,\"case\":\"case-sensitive\",\"canonical\":\"Gadget definition talk\",\"*\":\"Gadget definition talk\"}}}}";

    public static String[] genBlackList() {

        JSONObject nameList = JSON.parseObject(jsonStr).getJSONObject("query");

        JSONObject list = nameList.getJSONObject("namespaces");

        ArrayList<String> arrayList = new ArrayList<>();
        String[] blkList = {};
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("black.txt"));
            for (Map.Entry<String, Object> entry : list.entrySet()) {
                JSONObject jsb = (JSONObject)JSON.toJSON(entry.getValue());

                String namespace = jsb.get("*").toString();
                if(!namespace.equals("")){
                    String[] strArr = namespace.split(" ");
                    if(strArr.length>1){
                        namespace = String.join("_", strArr);
                    }
                    namespace = namespace+":";
                    arrayList.add(namespace);
                    writer.append(namespace);
                    writer.newLine();
                }
            }
            writer.flush();
            writer.close();
            blkList = arrayList.toArray(new String[arrayList.size()]);
            return blkList;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return blkList;
    }

}
