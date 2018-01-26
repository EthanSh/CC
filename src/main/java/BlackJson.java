public class BlackJson {

    public static void main(String[] args){
        String[] black = BlackListGen.genBlackList();
        for(String s : black){
            System.out.println(s);
        }
    }
}
