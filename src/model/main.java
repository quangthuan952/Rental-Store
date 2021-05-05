package model;

import java.util.List;

public class main {
    public static void main(String[] args) {
//
//        Comic c3 = new Comic("Quang Thuận", "Huy","2012", "Thuận", 3.4f,
//                23, "A4","a");
//        Comic c4 = new Comic("Quang Thuận", "Huy","2012", "Thuận", 3.4f,
//                23, "A4","a");
//        Comic c5 = new Comic("Quang Thuận", "Huy","2012", "Thuận", 3.4f,
//                23, "A4","a");
//        Comic cm = new Comic();
//        cm.addProduct(c3);
//        cm.addProduct(c4);
//        cm.addProduct(c5);
//        //Ghi file
//        String dir = System.getProperty("user.dir");
//        try {
//            FileWriter fw = new FileWriter(dir + "\\src\\data\\daata.txt");
//            BufferedWriter bw = new BufferedWriter(fw);
//            for (Comic o: cm.getList()) {
//               bw.write(o.toString());
//               bw.newLine();
//            }
//            bw.close();
//            fw.close();
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        //Đọc file
//
//        try {
//            FileReader fd = new FileReader(dir +"\\src\\data\\daata.txt");
//            BufferedReader bd = new BufferedReader(fd);
//            String line = "";
//            while(true) {
//                line = bd.readLine();
//                if(line == null) {
//                    break;
//                }
//                String txt[] = line.split(";");
//                String lang = txt[2];
//                if(lang.equals("English")) {
//                    System.out.println("OK");
//                    System.out.println(line);
//                    break;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Comic c = new Comic();
        System.out.println(c);
    }
}
