package com.len.core.RanGenerator;

import java.util.Random;

public class RandomGenerator {

    public static void main(String[] args) {
    /*    Random r = new Random(1);
        for(int i=0; i<5; i++){
            int ran1 = r.nextInt(100);
            System.out.println(ran1);
        }*/
   /*     int max =1000, min=1;
    for(int i=0; i<10; i++){
        int ran2 = (int) (Math.random()*(max-min)+min);
        System.out.println(ran2);
    }*/

  /*   for(int i=0; i<10; i++){
         long randomNum = System.currentTimeMillis();
         int ran3 = (int) (randomNum%(max-min)+min);
         System.out.println(ran3);
     }*/

  /*  for (int i =0; i<= 200; i++){
        int intFlag = (int)(Math.random()*1000000);
        String flag = String.valueOf(intFlag);
        if(flag.length()==6 && flag.substring(0,1).equals("9")){
            System.out.println(intFlag);
        }
        else {
            intFlag = intFlag + 100000;
            System.out.println(intFlag);
        }
    }*/
       RandomGenerator generator = new RandomGenerator();

      int[] a =new int[] {879071379,1090811618,340284439,840871431,852206635,887561597,717320927,1004666671};
      int count =0;
      while(count < a.length){
          boolean flag = true;
          int r =generator.RanGenerator();
          for (int i=0;i<a.length;i++){
              if(r == a[i]){
                  flag = false;
                  break;
              }
          }
          if(flag){
              a[count] = r;
              System.out.println(r);
              count++;
          }
      }
    }

 //   private boolean Flag1 = true;

    //生成单个设备编码
    private int RanGenerator(){
        boolean Flag1 = true;
        while(Flag1){
            int intFlag =(int) (Math.random() * 1000000000);
            String flag = String.valueOf(intFlag);
            if(flag.length() == 9 && !flag.substring(0,1).equals(9)){
                intFlag = intFlag +100000000;
            }
            boolean b= checkdeviceID(intFlag);
            if(b){

            }
        }


        /*else{
            intFlag = intFlag + 100000000;
          //  System.out.println("++"+intFlag);
        }*/
        //检查生成的随机数设备ID是否已存在，如果已存在，则重新再生成

      /*  if(b){
            return intFlag;
        }
        else {

        }*/
        return 1;
    }

    //未完成
    private boolean checkdeviceID(int singleRandom){

    return true;
    }
}
