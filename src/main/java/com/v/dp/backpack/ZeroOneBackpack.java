package com.v.dp.backpack;

import java.util.Arrays;

/**
 * Created by v on 2017/1/10.
 */
public class ZeroOneBackpack {

    public static class Goods implements Comparable<Goods> {

        public Goods(int weight, int volume) {
            this.weight = weight;
            this.volume = volume;
        }

        public int weight;

        public int volume;

        public int compareTo(Goods o) {
            return o.volume - volume;
        }

        @Override
        public String toString() {
            return "Goods{" +
                    "weight=" + weight +
                    ", volume=" + volume +
                    '}';
        }
    }

    public Goods [] goodies = new Goods[]{new Goods(1,2),new Goods(2,4),
            new Goods(4,6),new Goods(2,1),new Goods(5,12),new Goods(6,9),
            new Goods(3,7),new Goods(2,2), new Goods(3,4),new Goods(4,6)};



    public int zeroOneBackpack(Goods [] goodies,int from,int to,int volume){
        //如果数组已经结束，或者最后一个物品的体积大于背包所剩的体积，返回0
        if (from > to ){
            return 0;
        }
        if (goodies[from].volume > volume){
            return zeroOneBackpack(goodies,from + 1,to,volume);
        }
        return Math.max(zeroOneBackpack(goodies,from + 1,to,(volume -= goodies[from].volume)) + goodies[from].weight,
                zeroOneBackpack(goodies,from + 1,to,volume));
    }

    public static void main(String [] args){
        ZeroOneBackpack zeroOneBackpack = new ZeroOneBackpack();
        Arrays.sort(zeroOneBackpack.goodies);
        System.out.println(Arrays.toString(zeroOneBackpack.goodies));
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(zeroOneBackpack.zeroOneBackpack(zeroOneBackpack.goodies,0,zeroOneBackpack.goodies.length - 1,17));
    }
}
