public class Function {
    //  result[0]表示OPT命中率,result[1]表示FIFO命中率，result[2]表示LRU命中率
    double [][] result = new double[4][37];
    int [][] pageCase;
    public void OPT(int[] p_address, int ii){
        double missing=0;     //缺页次数,不能设为int类型，否则输出命中率全是1
        int currentPage=0;      //当前页框
        int allPage=ii;       //总页框数
        int [] address =p_address;
        /**
         * pageCase[currentPage][0] 存储页地址
         * pageCase[currentPage][1] 页地址在未来是否会出现
         * */
        pageCase = new int[allPage][2];
        //先将所有页框中的页地址都置为-1
        for(currentPage = 0;currentPage< allPage;currentPage++){
            pageCase[currentPage][0]=-1;
        }

        for(int k=0;k<400;k++){
            //判断是否页地址已存在
            for(currentPage=0;currentPage<allPage;currentPage++){
                //先判断页地址是否已存在
                if(pageCase[currentPage][0]==address[k]){
                    break;
                }
            }

            if(currentPage==allPage){
                //页框未满的情况
                for(currentPage=0;currentPage<allPage;currentPage++){
                    if(pageCase[currentPage][0]==-1){
                        pageCase[currentPage][0]=address[k];
                        break;
                    }
                }
                //置换页地址的情况
                if(currentPage==allPage){
                    int change=0;
                    for(int j=k+1,count=0;j<400;j++){
                        for(currentPage=0;currentPage<allPage;currentPage++){
                            if(address[j]==pageCase[currentPage][0]){
                                if(pageCase[currentPage][1]==0){
                                    pageCase[currentPage][1]=1;
                                    count++;
                                }
                                break;
                            }
                        }
                        if(count==allPage-1){
                            break;
                        }
                    }
                    for(currentPage=0;currentPage<allPage;currentPage++){
                        if(pageCase[currentPage][1]==0){
                            pageCase[currentPage][0]=address[k];
                            break;
                        }
                    }
                    missing++;
                    for(currentPage=0;currentPage<allPage;currentPage++){
                        pageCase[currentPage][1]=0;
                    }
                }
            }
        }
        result[0][allPage-4]=1-missing/400;

    }

    public void FIFO(int[] p_address, int ii){    //先进先出
        double missing=0;     //缺页次数,不能设为int类型，否则输出命中率全是1
        int currentPage=0;      //当前页框
        int allPage=ii;       //总页框数
        int [] address =p_address;
        /**
         * pageCase[currentPage][0] 存储页地址
         * pageCase[currentPage][1] 页地址在过去出现的次数
         * */
        int [][] pageCase = new int[allPage][2];
        for(currentPage=0;currentPage<allPage;currentPage++){
            pageCase[currentPage][0]=-1;
        }

        for(int k=0;k<400;k++){
            //页框中是否已存在该页地址
            for(currentPage=0;currentPage<allPage;currentPage++){
                if(pageCase[currentPage][0]==address[k]){
                    for(int i=0;i<allPage;i++){
                        if(pageCase[i][0]!=-1){
                            pageCase[i][1]++;
                        }
                    }
                    break;
                }
            }

            if(currentPage==allPage){
                //判断页框中是否还有空
                for(currentPage=0;currentPage<allPage;currentPage++){
                    if(pageCase[currentPage][0]==-1){
                        pageCase[currentPage][0]=address[k];
                        pageCase[currentPage][1]=0;
                        for(int j=0;j<allPage;j++){
                            if(pageCase[j][0]!=-1){
                                pageCase[j][1]++;
                            }
                        }
                        break;
                    }
                }
                //在页框为满的状态下换页,比较各个pageCase[currentPage][1]的大小
                if(currentPage==allPage){
                    int max = pageCase[0][1];
                    int number=0;
                    for(currentPage=1;currentPage<allPage;currentPage++){
                        if(pageCase[currentPage][1]>max){
                            max=pageCase[currentPage][1];
                            number=currentPage;
                        }
                    }
                    pageCase[number][0]=address[k];
                    pageCase[number][1]=0;
                    for(currentPage=0;currentPage<allPage;currentPage++){
                        pageCase[currentPage][1]++;
                    }
                    missing++;
                }
            }
        }
        result[1][allPage-4]=1-missing/400;

    }

    public void LRU(int[] p_address, int ii){
        double missing=0;     //缺页次数,不能设为int类型，否则输出命中率全是1
        int currentPage=0;      //当前页框
        int allPage=ii;       //总页框数
        int [] address =p_address;
        /**
         * pageCase[currentPage][0] 存储页地址
         * pageCase[currentPage][1] 页地址在过去出现的次数
         * */
        int [][] pageCase = new int[allPage][2];
        for(currentPage=0;currentPage<allPage;currentPage++){
            pageCase[currentPage][0]=-1;
        }
        for(int k=0;k<400;k++){
            //页框中是否已存在该页地址
            for(currentPage=0;currentPage<allPage;currentPage++){
                if(pageCase[currentPage][0]==address[k]){
                    pageCase[currentPage][1]=0;
                    break;
                }
            }
            if(currentPage==allPage){
                //页框中页未满
                for(currentPage=0;currentPage<allPage;currentPage++){
                    if(pageCase[currentPage][0]==-1){
                        pageCase[currentPage][0]=address[k];
                      //  pageCase[currentPage][1]=0;
                        for(int i=0;i<allPage;i++){
                            if(pageCase[i][0]!=-1){
                                pageCase[currentPage][1]++;
                            }
                        }
                        pageCase[currentPage][1]=0;
                        break;
                    }
                }
                //页框已满，换页
                if(currentPage==allPage){
                    int max=pageCase[0][1];
                    int number=0;
                    for(currentPage=1;currentPage<allPage;currentPage++){
                        if(pageCase[currentPage][1]>max){
                            max=pageCase[currentPage][1];
                            number=currentPage;
                        }
                    }
                    pageCase[number][0]=address[k];
                   // pageCase[number][1]=0;
                    for(currentPage=0;currentPage<allPage;currentPage++){
                        pageCase[currentPage][1]++;
                    }
                    pageCase[number][1]=0;
                    missing++;
                }
            }
        }
        result[2][allPage-4] = 1-missing/400;
    }

    public void outputResult(){
        System.out.println("----------------------------------------------");
        System.out.println("页框数     OPT命中率      FIFO命中率     LRU命中率");
        for(int j=0,i=4;i<=40;j++,i++){
            System.out.printf("%-10d%-13.4f%-14.4f%-9.4f",i,result[0][j],result[1][j],result[2][j]);
            System.out.println();
        }
        System.out.println();
        System.out.println("下面比较FIFO与LRU的命中率：");
        int FBL=0;
        int FSL=0;
        int FEL=0;
        for(int i=0;i<37;i++){
            if(result[1][i]>result[2][i]){
                FBL++;
            }
           else if(result[1][i]<result[2][i]){
                FSL++;
            }
           else{
               FEL++;
            }
        }
        System.out.println("FIFO > LRU :"+FBL+"次");
        System.out.println("FIFO < LRU :"+FSL+"次");
        System.out.println("FIFO = LRU :"+FEL+"次");
        System.out.println();
        System.out.println();
        System.out.println("-------------------------------------------------");
        FBL=0;
        FSL=0;
        FEL=0;
    }
}
