package Comp_P;
import com.sun.scenario.effect.impl.state.AccessHelper;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;
//Under Process
class WordLadderHandler
{
    char start_word[];
    char end_word[];
    String  list[];
    void input(@NotNull String start_word, @NotNull String end_word, String list[])
    {
        this.start_word=start_word.toCharArray();
        this.end_word=end_word.toCharArray();
        this.list=list;
    }
    String coversion(@NotNull char data[])
    {
        String temp="";
        for (int i=0;i<data.length;i++)
            temp+=data[i];
        return temp;
    }


    int search(char data[],int index)
    {
        String word=coversion(data);
        System.out.println(word);
        for (int o=index;o<list.length;o++)
            if (word.equals(list[o]))
                return o;
            return -1;
    }
    int minimum_transformation(int list_index,int depth)
    {

        if (list_index>=list.length)
            return 0;
        boolean flag=true;
        for (int i=0;i<start_word.length;i++)
            if (start_word[i]!=end_word[i])
            flag=false;
            if (flag)
                return depth;
        int result=Integer.MAX_VALUE;
        for (int k=list_index;k<list.length;k++) {
            for (int i = 0; i < start_word.length; i++) {
                if (start_word[i] != list[k].charAt(i)) {
                    char temp1 = start_word[i];
                    start_word[i] = list[k].charAt(i);
                    int index = search(start_word, list_index);
                    if (index != -1) {
                        int temp = minimum_transformation(index + 1, depth + 1);
                        if (temp != 0)
                            result = Math.min(result, temp);
                    }
                    start_word[i] = temp1;
                }

            }
        }
        return result==Integer.MAX_VALUE?0:result;
    }
}
public class WordLadder {
    public static void main(String[] args) {
        WordLadderHandler handler =new WordLadderHandler();
        System.out.println("Enter Begin Word");
        Scanner scanner=new Scanner( System.in);
        String start_word,end_word;
        start_word=scanner.nextLine();
        System.out.println("Enter Ending Word");
        end_word=scanner.nextLine();
        System.out.println("Enter Word List Length ");
        int length=Integer.parseInt(scanner.nextLine());
        String []list=new String[length];
        System.out.println("Enter Word list data ");
        for (int i=0;i<length;i++)
            list[i]=scanner.nextLine();
        handler.input(start_word,end_word,list);
        System.out.println("The Minimum Transformation is "+handler.minimum_transformation(0,0));
    }
}
