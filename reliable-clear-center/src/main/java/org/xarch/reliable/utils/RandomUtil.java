package org.xarch.reliable.utils;

public class RandomUtil {
	
	/**
	 * 随机分配一个红包
	 * @param mnoney
	 * @param minS：最小金额
	 * @param maxS：最大金额
	 * @param count
	 * @return
	 */
	public static int randomRedPacket(int money, int minS, int maxS, int count){
		//若只有一个，直接返回红包
		if (count==1) {
			return money;
		}
		//如果最大金额和最小金额相等，直接返回金额
		if (minS==maxS) {
			return minS;
		}
		int max=maxS>money?money:maxS;
		//分配红包正确情况，允许红包的最大值
		int maxY = money-(count-1)*minS;
		//分配红包正确情况，允许红包最小值
		int minY = money-(count-1)*maxS;
		//随机产生红包的最小值
		int min = minS>minY?minS:minY;
		//随机产生红包的最大值
		max = max>maxY?maxY:max;
		//随机产生一个红包
		return (int)Math.rint(Math.random()*(max-min) +min);
	}

}
