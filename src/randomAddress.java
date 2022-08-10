public class randomAddress {
    public int[] address;
    public int [] produce(){
         address = new int[400];
        int i=0;
        while(i<200){
            address[i]= (int) (Math.random()*200);
            if(address[i]==199){
                address[i+1]=address[i];
            }else{
                address[i+1]=address[i]+1;
            }

            address[i+200] = (int) ((Math.random()*200)+200);
            if(address[i+200]==399){
                address[i+200+1]=address[i+200];
            }else{
                address[i+200+1] = address[i+200]+1;
            }
            i=i+2;
        }
        return address;
    }


    public void outputAddress(){
        int k=0;
        for(int i=0;i<40;i++){
            for(int j=0;j<10;j++){
                System.out.printf("%-3d ",address[k]);
                k++;
            }
            System.out.println();
        }
    }
}
