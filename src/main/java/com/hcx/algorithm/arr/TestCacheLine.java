package com.hcx.algorithm.arr;

/**
 * @Title: TestCacheLine.java
 * @Package com.hcx.algorithm.arr
 * @Description: (用一句话描述该文件做什么)
 * @Author: hongcaixia
 * @Date: 2024/12/27 14:37
 * @Version V1.0
 */
public class TestCacheLine {

    /**
     * 二维数组遍历，先行后列
     * @param a
     * @param rows
     * @param cols
     */
    public static void ij(int[][] a, int rows, int cols) {
        long sum = 0L;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sum += a[i][j];
            }
        }
        System.out.println(sum);
    }

    /**
     * 二维数组遍历，先列后行
     * @param a
     * @param rows
     * @param cols
     */
    public static void ji(int[][] a, int rows, int cols) {
        long sum = 0L;
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows; i++) {
                sum += a[i][j];
            }
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
        int rows = 1000000;
        int cols = 15;
        int[][] arr = new int[rows][cols];
        StopWatch sw = new StopWatch();
        sw.start();
        ij(arr, rows, cols);
        sw.stop();
        System.out.println("=====ij: "+sw.getElapsedTimeInNanos());

        sw.start();
        ji(arr, rows, cols);
        sw.stop();

        System.out.println("=====ji: "+sw.getElapsedTimeInNanos());

    }


    private static class StopWatch {
        private long startTime;
        private long endTime;
        private boolean running;

        public StopWatch() {
            this.running = false;
        }

        // 启动计时器
        public void start() {
            if (running) {
                throw new IllegalStateException("Stopwatch is already running.");
            }
            startTime = System.nanoTime();
            running = true;
        }

        // 停止计时器
        public void stop() {
            if (!running) {
                throw new IllegalStateException("Stopwatch is not running.");
            }
            endTime = System.nanoTime();
            running = false;
        }

        // 获取计时器已经运行的时间（秒）
        public long getElapsedTimeInMillis() {
            if (running) {
                throw new IllegalStateException("Stopwatch is still running.");
            }
            return (endTime - startTime) / 1_000_000;  // 纳秒转换为毫秒
        }

        // 获取计时器已经运行的时间（纳秒）
        public long getElapsedTimeInNanos() {
            if (running) {
                throw new IllegalStateException("Stopwatch is still running.");
            }
            return endTime - startTime;
        }

        // 检查计时器是否正在运行
        public boolean isRunning() {
            return running;
        }

        // 重置计时器
        public void reset() {
            startTime = 0;
            endTime = 0;
            running = false;
        }

        @Override
        public String toString() {
            return "Stopwatch [startTime=" + startTime + ", endTime=" + endTime + ", running=" + running + "]";
        }

    }
}
