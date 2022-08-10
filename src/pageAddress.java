public class pageAddress {
    public int [] pageAddress = new int[400];
    public int [] producePageAddress(int[] r_address){
        for(int i=0;i<400;i++){
            pageAddress[i] = r_address[i]/10;
        }
        return pageAddress;
    }

    public void outputPageAddress(){
        int k=0;
        for(int i=0;i<40;i++){
            for(int j=0;j<10;j++){
                    System.out.printf("%-3d ",pageAddress[k]);
                    k++;
                }
            System.out.println();
        }
    }
}
