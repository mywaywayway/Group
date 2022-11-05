import java.util.Objects;
import java.util.Scanner;

/**
 * @author 杨洹
 */
public class Show {


    public void run() {
        Scanner in = new Scanner(System.in);
        int i;
        System.out.println("---------------------------------");
        System.out.println("请输入您的选项！");
        System.out.println("1.群的判定。");
        System.out.println("0.退出。");
        System.out.println("---------------------------------");
        i = in.nextInt();
        while (true) {
            if (i == 0) {
                break;
            } else if (i == 1) {
                judmentGronp();
            } else {
                System.out.println("请重新输入正确的指令！");
            }
            System.out.println("---------------------------------");
            System.out.println("请输入您的选项！");
            System.out.println("1.群的判定。");
            System.out.println("0.退出。");
            System.out.println("---------------------------------");
            i = in.nextInt();
        }
    }

    public void judmentGronp() {
        boolean flag;
        boolean flag_1;
        boolean flag_2;
        boolean flag_3;
        Scanner in = new Scanner(System.in);
        Gronp gronp = new Gronp();
        System.out.println("进入函数判断");
        System.out.println("请输入集合（<a,b>)");
        int j = 0, k = 0;
        String str;
        StringBuilder str1 = new StringBuilder();
        str = in.nextLine();

        char[] chars = str.toCharArray();
        while (true) {
            if (chars[j] == '<' || chars[j] == '{') {
                str1 = new StringBuilder();
                j++;
                str1.append(chars[j]);
                j++;
            } else if (chars[j] == ',') {
                gronp.arraylist.add(str1.toString());
                str1 = new StringBuilder();
                j++;

            } else if (chars[j] == '>' || chars[j] == '}') {

                gronp.arraylist.add(str1.toString());
//                str1 = new StringBuilder();
                break;
            } else {
                str1.append(chars[j]);
                j++;
            }
        }
        //运算表的录入，通过已经录入的元素和算法符号来让用户输入运算的结果，将运算表存入suanfabiao【】；

        System.out.println("请输入运算符");
        Scanner in_1 = new Scanner(System.in);

        gronp.suan = in_1.next();
        SuanFaBiao[] suanfa = new SuanFaBiao[gronp.arraylist.size() * gronp.arraylist.size()];
        for (int i = 0; i < gronp.arraylist.size(); i++) {
            for (j = 0; j < gronp.arraylist.size(); j++) {
                System.out.print(gronp.arraylist.get(i) + gronp.suan + gronp.arraylist.get(j) + "=");
                str = in_1.next();
                suanfa[k] = new SuanFaBiao();
                suanfa[k].setsuanfabiao(gronp.arraylist.get(i), gronp.arraylist.get(j), str);
                k++;
            }
//             System.out.println("");
        }
//        for (j=0;j< suanfa.length;j++)
//        {
//            System.out.println(suanfa[j].x+gronp.suan+suanfa[j].y+"="+suanfa[j].z);
//        }

        flag = Panduanzifan(suanfa, gronp);
        if (flag) {
            System.out.println("满足封闭性");
            flag_1 = Panduanjiehe(suanfa, gronp);
            if (flag_1) {
                System.out.println("满足结合律");
                flag_2 = Panduanyaoyuan(suanfa, gronp);
                if (flag_2) {
                    System.out.println("存在幺元");
                    flag_3 = Panduanniyuan(suanfa, gronp);
                    if (flag_3) {
                        System.out.println("各个元素存在逆元");
                        System.out.println("是群");
                    } else {
                        System.out.println("不是每个元素都存在逆元");
                        System.out.println("不是群");
                    }
                } else {
                    System.out.println("不存在幺元");
                    System.out.println("不是群");
                }
            } else {
                System.out.println("不满足结合律");
                System.out.println("不是群");
            }

        } else {
            System.out.println("不满足封闭性");
            System.out.println("不是群");
        }


    }

    public boolean Panduanzifan(SuanFaBiao[] suanfabiao, Gronp gronp) {
        int i, j;
        for (i = 0; i < suanfabiao.length; i++) {
            for (j = 0; j < suanfabiao.length; j += gronp.arraylist.size()) {
                if (Objects.equals(suanfabiao[i].z, suanfabiao[j].x)) {
                    break;
                } else if (j + gronp.arraylist.size() >= suanfabiao.length && !Objects.equals(suanfabiao[i].z, suanfabiao[j].x)) {
                    return false;
                }
            }
        }
        return true;
    }

    public String getz(SuanFaBiao[] suanfabiao, String x, String y) {
        for (int i = 0; i < suanfabiao.length; i++) {
            if (Objects.equals(suanfabiao[i].x, x)) {
                if (Objects.equals(suanfabiao[i].y, y)) {
                    return suanfabiao[i].z;
                }
            }
        }
        return "nul";
    }

    public String getx(SuanFaBiao[] suanfabiao, String y, String z) {
        for (int i = 0; i < suanfabiao.length; i++) {
            if (Objects.equals(suanfabiao[i].y, y)) {
                if (Objects.equals(suanfabiao[i].z, z)) {
                    return suanfabiao[i].x;
                }
            }
        }
        return "nul";
    }

    public String gety(SuanFaBiao[] suanfabiao, String x, String z) {
        for (int i = 0; i < suanfabiao.length; i++) {
            if (Objects.equals(suanfabiao[i].x, x)) {
                if (Objects.equals(suanfabiao[i].z, z)) {
                    return suanfabiao[i].y;
                }
            }
        }
        return "nul";
    }

    public boolean Panduanjiehe(SuanFaBiao[] suanfabiao, Gronp gronp) {
        for (int i = 0; i < gronp.arraylist.size(); i++) {
            for (int j = 0; j < gronp.arraylist.size(); j++) {
                for (int k = 0; k < gronp.arraylist.size(); k++) {

                    if (k + 1 >= gronp.arraylist.size() && !Objects.equals(getz(suanfabiao, getz(suanfabiao, gronp.arraylist.get(i), gronp.arraylist.get(j)), gronp.arraylist.get(k)), getz(suanfabiao, getz(suanfabiao, gronp.arraylist.get(k), gronp.arraylist.get(j)), gronp.arraylist.get(i)))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean Panduanyaoyuan(SuanFaBiao[] suanfabiao, Gronp gronp) {
        String[] strings_L = new String[gronp.arraylist.size()];
        String[] strings_R = new String[gronp.arraylist.size()];
        for (int i = 0; i < strings_L.length; i++) {
            strings_L[i] = getx(suanfabiao, gronp.arraylist.get(i), gronp.arraylist.get(i));//每个元素的左运算等于本身的元素
        }
        for (int i = 0; i < strings_L.length; i++) {
            strings_R[i] = gety(suanfabiao, gronp.arraylist.get(i), gronp.arraylist.get(i));//每个元素的右运算等于本身的元素
        }
        for (int i = 1; i < strings_L.length; i++) {
            if (!Objects.equals(strings_L[0], strings_L[i])) {
                return false;
            }
            if (!Objects.equals(strings_R[0], strings_R[i])) {
                return false;
            }
        }
        if (!Objects.equals(strings_L[0], strings_R[0])) {
            return false;
        }

        gronp.yaoyuan = strings_L[0];
        return true;
    }

    public boolean Panduanniyuan(SuanFaBiao[] suanfabiao, Gronp gronp) {

        for (int i = 0; i < gronp.arraylist.size(); i++) {
            if (Objects.equals(getx(suanfabiao, gronp.arraylist.get(i), gronp.yaoyuan), "nul")) {
                return false;
            }
            if (Objects.equals(gety(suanfabiao, gronp.arraylist.get(i), gronp.yaoyuan), "null")) {
                return false;
            }
            if (!Objects.equals(getx(suanfabiao, gronp.arraylist.get(i), gronp.yaoyuan), gety(suanfabiao, gronp.arraylist.get(i), gronp.yaoyuan))) {
                return false;
            }

        }
        return true;
    }

}
