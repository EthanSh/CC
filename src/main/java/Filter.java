import java.util.Arrays;

public class Filter {
    private static final String[] fileBlackList = {"png","gif","jpg","jpeg","tiff","tif","xcf","mid","ogg","ogv","svg","djvu","oga","flac","opus","wav","webm","ico","txt"};
    private static final String[] specialCase = {"404.php","Main_Page","-"};

    public static String filter(String s){
        String[] strArr = s.split(" ");
        String title = strArr[1];
//        point 1 and point 3
        if(strArr.length==4 && (strArr[0].equals("en") || strArr[0].equals("en.m"))){
//           point 2
            title = PercentDecoder.decode(title);
//           point 4
            String[] list = BlackListGen.genBlackList();
            for(String prefixes : list){
                if(title.startsWith(prefixes)){
                    return "";
                }
            }
//  point 5
            char fisrtCh = title.charAt(0);
            if(Character.isLetter(fisrtCh) && Character.isLowerCase(fisrtCh)){
                return "";
            }
//          point 6
            String[] fileStr = title.split("\\.");
            if(fileStr.length>0&&Arrays.asList(fileBlackList).contains(fileStr[fileStr.length-1].toLowerCase())){
                return "";
            }
//          point 7
            String[] suffix = title.split("_");
            if(suffix.length>0&&suffix[suffix.length-1].equals("(disambiguation)")){
                return "";
            }
//            point 8
            if(Arrays.asList(specialCase).contains(title)){
                return "";
            }

            return String.join(" ",strArr);

        }
        return "";
    }





}
