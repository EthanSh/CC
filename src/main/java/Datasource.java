import java.io.*;
import java.util.*;
import java.util.zip.GZIPInputStream;

public class Datasource {
    public static final String filePath = App.class.getClassLoader().getResource("pageviews-20161109-000000.gz").getPath();

    public void readData() throws IOException {
        InputStream in = null;
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("./output")));
        TreeMap<String, Integer> treeMapMerge = new TreeMap<>();
        TreeMap<Integer, ArrayList<String>> treeMapSortKey = new TreeMap<>(
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2>o1?1:o2==o1?0:-1;
                    }
                }
        );

        try {
            in = new GZIPInputStream(new FileInputStream(filePath));
            Scanner sc=new Scanner(in);
            while(sc.hasNextLine()){
                String str1 = sc.nextLine();
                str1 = Filter.filter(str1);
                if(!str1.equals("")){
                    String[] tempStr = str1.split(" ");
                    if(treeMapMerge.get(tempStr[1])==null){
                        treeMapMerge.put(tempStr[1], Integer.parseInt(tempStr[2]));
                    }else{
                        treeMapMerge.put(tempStr[1], treeMapMerge.get(tempStr[1]) + Integer.parseInt(tempStr[2]));
                    }
                }
            }


            for(Map.Entry<String, Integer> entry : treeMapMerge.entrySet()){
                int key = entry.getValue();
                String value = entry.getKey();
                if(treeMapSortKey.get(key)==null){
                    ArrayList<String> arrayList = new ArrayList<>();
                    arrayList.add(value);
                    treeMapSortKey.put(key, arrayList);
                }else{
                    treeMapSortKey.get(key).add(value);
                }
            }

            for(Map.Entry<Integer, ArrayList<String>> entry : treeMapSortKey.entrySet()){
                ArrayList<String> arrayList = entry.getValue();
                int rank = entry.getKey();
                Collections.sort(arrayList);
                for(String s: arrayList){
                    bufferedWriter.write(s+"\t"+rank);
                    bufferedWriter.newLine();
                }
            }


            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
