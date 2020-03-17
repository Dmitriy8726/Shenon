package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        //file();
        Shenon();
    }

    public static void file() throws IOException {
        Random random = new Random();
        int i = 0;
        int ran = 0;
        File Dir = new File("resource\\file");
        if (!Dir.exists()) {
            try {
                Dir.mkdir();
            } catch (SecurityException var10) {

            }
        }
        File F1 = new File("resource\\file", "F1.txt");
        File F2 = new File("resource\\file", "F2.txt");
        do {
            ran = random.nextInt(3);
            switch (ran) {
                case 0:
                    try (FileWriter writer = new FileWriter(F1, true)) {
                        writer.append("a");
                        writer.flush();
                    } catch (IOException ex) {

                        System.out.println(ex.getMessage());
                    }
                    break;
                case 1:
                    try (FileWriter writer = new FileWriter(F1, true)) {
                        writer.append("b");
                        writer.flush();
                    } catch (IOException ex) {

                        System.out.println(ex.getMessage());
                    }
                    break;
                case 2:
                    try (FileWriter writer = new FileWriter(F1, true)) {
                        writer.append("c");
                        writer.flush();
                    } catch (IOException ex) {

                        System.out.println(ex.getMessage());
                    }
                    break;
                default:
                    break;
            }
        } while (F1.length() < 10240);

        do {
            ran = random.nextInt(100);
            if (ran <= 19) {
                try (FileWriter writer = new FileWriter(F2, true)) {
                    writer.append("a");
                    writer.flush();
                } catch (IOException ex) {

                    System.out.println(ex.getMessage());
                }
            } else if (ran <= 39) {
                try (FileWriter writer = new FileWriter(F2, true)) {
                    writer.append("b");
                    writer.flush();
                } catch (IOException ex) {

                    System.out.println(ex.getMessage());
                }
            } else {
                try (FileWriter writer = new FileWriter(F2, true)) {
                    writer.append("c");
                    writer.flush();
                } catch (IOException ex) {

                    System.out.println(ex.getMessage());
                }
            }
        } while (F2.length() < 10240);
    }

    private static void  Shenon() throws IOException {
        FileReader reader_F1 = new FileReader("resource\\file\\F1.txt");
        FileReader reader_F2 = new FileReader("resource\\file\\F2.txt");
        FileReader reader_F3 = new FileReader("resource\\file\\F3.txt");
        int ch;
        int all_F1 = 0, all_F2 = 0, all_F3 = 0;
        int a_F1 = 0, b_F1 = 0, c_F1 = 0;
        int a_F2 = 0, b_F2 = 0, c_F2 = 0;
        float freq_a_F1 = 0, freq_b_F1 = 0, freq_c_F1 = 0;
        float freq_a_F2 = 0, freq_b_F2 = 0, freq_c_F2 = 0;
        HashMap<String, Float> map = new HashMap<>();
        HashMap<String, Float> map_prob_F1 = new HashMap<>(); //Вероятности
        HashMap<String, Float> map_prob_F2 = new HashMap<>(); //Вероятности
        HashMap<String, Float> map_prob_F3 = new HashMap<>(); //Вероятности

        //Подсчет вероятностей
        while ((ch = reader_F1.read()) != -1) {
            all_F1++;
            switch ((char) ch) {
                case 'a':
                    a_F1++;
                    break;
                case 'b':
                    b_F1++;
                    break;
                case 'c':
                    c_F1++;
                    break;
                default:
                    break;
            }
        }
        freq_a_F1 = (float) a_F1 / all_F1;
        freq_b_F1 = (float) b_F1 / all_F1;
        freq_c_F1 = (float) c_F1 / all_F1;
        map_prob_F1.put("a", freq_a_F1);
        map_prob_F1.put("b", freq_b_F1);
        map_prob_F1.put("c", freq_c_F1);

        while ((ch = reader_F2.read()) != -1) {
            all_F2++;
            switch ((char) ch) {
                case 'a':
                    a_F2++;
                    break;
                case 'b':
                    b_F2++;
                    break;
                case 'c':
                    c_F2++;
                    break;
                default:
                    break;
            }
        }
        freq_a_F2 = (float) a_F2 / all_F2;
        freq_b_F2 = (float) b_F2 / all_F2;
        freq_c_F2 = (float) c_F2 / all_F2;
        map_prob_F2.put("a", freq_a_F2);
        map_prob_F2.put("b", freq_b_F2);
        map_prob_F2.put("c", freq_c_F2);

        while ((ch = reader_F3.read()) != -1) {
            String str = String.valueOf((char) ch);
            str = str.toLowerCase();
            str = str.replaceAll("[^а-яё]", "");
            if (str.isEmpty()) {
                continue;
            }
            if (!map.containsKey(str)) {
                map.put(str, 1.0f);
            } else {
                float i = map.get(str) + 1;
                map.put(str, i);
            }
        }
        for (Map.Entry entry : map.entrySet()) {
            all_F3 += (Float) entry.getValue();
        }

        for (Map.Entry entry : map.entrySet()) {
            map_prob_F3.put(entry.getKey().toString(), (Float) entry.getValue() / all_F3);
        }

        ////////////////////////////
        ArrayList<Float> arr = new ArrayList<>(map_prob_F2.values());
        Collections.sort(arr);

        Map<Float, String> tt = new HashMap<>();
        for (float p: arr) {
            tt.put(p, "");
        }
        tt = Test(tt);
        System.out.println(tt);

        Map<String, String> map_code = new HashMap<>();
        for (Map.Entry entry1 : map_prob_F2.entrySet()) {
            for (Map.Entry entry2 : tt.entrySet()) {
                if ((float)entry2.getKey() == (float)entry1.getValue()) {
                    map_code.put(entry1.getKey().toString(), entry2.getValue().toString());
                    break;
                }
            }
        }
        System.out.println(map_code);

        FileWriter writer_F2 = new FileWriter("resource\\file\\F2_exmpl.txt");
        reader_F2 = new FileReader("resource\\file\\F2.txt");
        while((ch = reader_F2.read()) != -1) {
            if (map_prob_F2.containsKey(String.valueOf((char)ch))) {
                writer_F2.write(map_code.get(String.valueOf((char) ch)));
            }
        }
        writer_F2.flush();
        writer_F2.close();

    }

    private static Map<Float, String>  Test(Map<Float, String> tt)  {
        float i = 0f;
        Map<Float, String> temp = new HashMap<>();
        for (Map.Entry entry : tt.entrySet()) {
            int exp = 0;
            int l = 0;
            String str;
            while (true) {
                if (Math.pow(2, exp) - (float)entry.getKey() <= 0) {
                    l = Math.abs(exp);
                    break;
                }
                exp -= 1;
            }
            str = Bits(i, l);
            System.out.println(str + " " + l + " " + i);
            i += (float)entry.getKey();
            temp.put((float)entry.getKey(), str);
        }
      return temp;
    }


    private static String Bits(float i, int l) {
        String str = "";
        float tmp = i;
        for (int j = 0; j < l; j++) {
            tmp = tmp * 2;
            str += String.valueOf(tmp).substring(0, 1);
            tmp = Float.parseFloat(String.valueOf(tmp).substring(1));
        }

        return str;
    }
}
