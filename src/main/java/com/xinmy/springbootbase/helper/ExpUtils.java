package com.xinmy.springbootbase.helper;

import java.util.stream.IntStream;

public class ExpUtils {
	/**
	 * 计算某等级所需积分点数
	 * 
	 * @param level
	 */
	public static int pointsOf(int level) {
		if (level <= 0) {
			return 0;
		}
		if (level > 100) {
			throw new RuntimeException("系统最高等级为100");
		}
		return IntStream.range(0, level).reduce(0, (a, b) -> {
			return a + 60 + (20 + b * 10) * (b - 1) / 2;
		});
	}

	/**
	 *
	 */
	public static int diff2up(int level, int exp) {
		if (level > 100) {
			throw new RuntimeException("系统最高等级为100");
		}
		//
		int required = pointsOf(level);
		//
		return required - exp;
	}

	/**
	 * 
	 */
	public static int levelOf(int exp) {
		int tmp = 0;
		if (exp <= 0) {
			return 0;
		}
		int i = 0;
		for (; i < 100; i++) {
			tmp += 60 + (20 + i * 10) * (i - 1) / 2;
			if (exp < tmp) {
				return i;
			}
		}
		return i;
	}
}
