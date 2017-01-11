package com.spojen.basic;

import java.util.Arrays;
import java.util.Scanner;

/**
 * ACEPTED
 */
public class HYDRO {
	public static int wallCount;
	public static int[] wallConfig;
	public static int[] sortedWallConfig;
	public static boolean[] wallVisited;
	public static int[][] wallImage;

	/**
	 * fills up wall image with wall data.
	 * 1 - wall
	 * @param index
	 * @param size
	 */
	public static void addWall(int index, int size) {
		for(int i = 0; i < size; i ++) {
			wallImage[i][index] = 1;
		}
	}
	
	/**
	 * fills up water between two walls
	 * 2 - water
	 * @param ileft
	 * @param iright
	 * @param level
	 */
	public static void addWater(int ileft, int iright, int level) {
		for(int i = 0; i < level; i++) {
			for(int j = Math.min(ileft, iright); j <= Math.max(ileft, iright); j++) {
 				if(wallImage[i][j] == 0) {
					wallImage[i][j] = 2;
				}
			}
		}
	}
	
	/**
	 * gets index of wall of given height
	 * @param wallValue
	 * @param flag
	 * @return
	 */
	public static int getWallIndex(int wallValue, boolean flag) {
		for(int i = 0; i < wallConfig.length; i++) {
			if(wallConfig[i] == wallValue && !wallVisited[i]) {
				wallVisited[i] = flag;
				return i;
			}
		}
		
		return -1;
	}
	
	public static int getWaterVolume() {
		int volume = 0;
		
		for(int i = 0; i < wallImage.length; i++) {
			for(int j = 0; j < wallImage[i].length; j++) {
				volume += (wallImage[i][j] == 2) ? 1 : 0;
			}
		}
		
		return volume;
	}
	
	public static void main(String[] args) {
		Scanner consoleScanner = new Scanner(System.in);
		
		int damCount = Integer.valueOf(consoleScanner.nextLine());
		while(damCount-- > 0) {
			wallCount = Integer.valueOf(consoleScanner.nextLine());
			
			wallConfig = Arrays.stream(consoleScanner.nextLine().split(" "))
					.mapToInt(i -> Integer.valueOf(i)).toArray();
			
			wallVisited = new boolean[wallCount];
			sortedWallConfig = Arrays.copyOf(wallConfig, wallCount);
			Arrays.sort(sortedWallConfig);
			
			// create wall image
			wallImage = new int[sortedWallConfig[wallCount - 1]][wallCount];
			for(int i = 0; i < wallCount; i++) {
				addWall(i, wallConfig[i]);
			}
			
			int leftWallIndex = 0;
			int rightWallIndex = 0;
			
			int leftWall = 0;
			int rightWall = 0;
			
			for(int sortedWallIndex = 0; sortedWallIndex < wallCount - 1; sortedWallIndex++) {
				leftWall = sortedWallConfig[sortedWallIndex];
				rightWall = sortedWallConfig[sortedWallIndex + 1];
				
				if(leftWall > 0 && rightWall > 0) {
					leftWallIndex = getWallIndex(leftWall, true);
					rightWallIndex = getWallIndex(rightWall, false);
					
					addWater(leftWallIndex, rightWallIndex, Math.min(leftWall, rightWall));
				}
			}
			
			System.out.println(getWaterVolume());
		}
		
		consoleScanner.close();
	}
}
