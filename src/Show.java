import java.util.Scanner;

public class Show {
    public void run(){
        Scanner in =new Scanner(System.in);
        int i;
        System.out.println("---------------------------------");
        System.out.println("请输入您的选项！");
        System.out.println("1.群的判定。");
        System.out.println("0.退出。");
        System.out.println("---------------------------------");
        i=in.nextInt();
        while (true) {
            if (i == 0) {
                break;
            } else if (i == 1) {
               judmentGronp();
            }
            else
            {
                System.out.println("请重新输入正确的指令！");
            }
            System.out.println("---------------------------------");
            System.out.println("请输入您的选项！");
            System.out.println("1.群的判定。");
            System.out.println("0.退出。");
            System.out.println("---------------------------------");
            i=in.nextInt();
        }
    }
    public void judmentGronp() {
        Scanner in = new Scanner(System.in);
        Gronp gronp = new Gronp();
        System.out.println("进入函数判断");
        System.out.println("请输入集合（<a,b>)");
        int j=0,k=0;
        String str,str1="";
        str = in.nextLine();

        char[] chars = str.toCharArray();
        while (true) {
            if (chars[j]=='<'||chars[j] == '{')
            {   str1="";
                j++;
                str1+=chars[j];
               j++;
            }
            else if (chars[j] ==',')
            {
                gronp.arraylist.add(str1);
                str1="";
                j++;

            }
            else if (chars[j]=='>'||chars[j] == '}')
            {

                gronp.arraylist.add(str1);
                str1="";
                break;
            }
            else
            {
                str1+=chars[j];
                j++;
            }
  }
        for (j=0;j<gronp.arraylist.size();j++)
        {
            System.out.println(gronp.arraylist.get(j));
        }
    }
}
