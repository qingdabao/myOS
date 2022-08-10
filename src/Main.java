import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int [] r_address = new int[400];
        int [] p_address = new int[400];
        int userInput=2;
        System.out.println("***************************************");
        System.out.println("*欢迎来到页面置换算法的模拟实现及命中率对比程序*");
        System.out.println("***************************************");
        while (true){
            try{
                System.out.println("是否开始模拟对比操作（1为是，2为否）：");
                Scanner input=new Scanner(System.in);
                userInput = input.nextInt();
                if(userInput==1){
                    randomAddress randomaddress = new randomAddress();
                    r_address = randomaddress.produce();
                    System.out.println("随机生成400条地址序列：");
                    randomaddress.outputAddress();
                    System.out.println("----------------------------------------------------");
                    System.out.println();
                    System.out.println("将指令地址流变换为页地址（页号）流：");
                    pageAddress pageaddress = new pageAddress();
                    p_address = pageaddress.producePageAddress(r_address);
                    pageaddress.outputPageAddress();
                    System.out.println("----------------------------------------------------");
                    System.out.println();
                    System.out.println("计算每个内存容量下不同页面置换算法的命中率：");
                    System.out.println();
                    Function function = new Function();
                    for(int i=4;i<=40;i++){
                        function.OPT(p_address,i);
                        function.FIFO(p_address,i);
                        function.LRU(p_address,i);
                    }
                    function.outputResult();
                }
                else if(userInput==2){
                    input.close();
                    break;
                }
                else{
                    System.out.println("请输入正确的命令！");
                }

            }catch(Exception e){

            }
        }

    }
}
