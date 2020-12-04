package prgrms.디스크컨트롤러;

import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;

        Queue<RequestedJob> requestedQueue = new PriorityQueue<>();

        for (int[] job : jobs) {
            requestedQueue.offer(new RequestedJob(job[0], job[1]));
        }

        Queue<WaitingJob> waitingQueue = new PriorityQueue<>();

        while (!requestedQueue.isEmpty()) {
            waitingQueue.offer(requestedQueue.poll().toWaitingJob());

            while (!waitingQueue.isEmpty()) {
                WaitingJob current = waitingQueue.poll();
                answer += current.getElapsedTime();

                for (RequestedJob requestedJob : requestedQueue) {
                    requestedJob.setCurrentTime(current.getEndTime());
                }
                for (WaitingJob waitingJob : waitingQueue) {
                    waitingJob.setCurrentTime(current.getEndTime());
                }

                while (!requestedQueue.isEmpty() && requestedQueue.peek().canWaiting()) {
                    waitingQueue.offer(requestedQueue.poll().toWaitingJob());
                }
            }
        }

        return answer / jobs.length;
    }
}

abstract class AbstractJob implements Comparable<AbstractJob> {
    private int requestTime;
    private int requiredTime;
    private int currentTime;

    public AbstractJob(int requestTime, int requiredTime) {
        this.requestTime = requestTime;
        this.requiredTime = requiredTime;
    }

    public AbstractJob(int requestTime, int requiredTime, int currentTime) {
        this.requestTime = requestTime;
        this.requiredTime = requiredTime;
        this.currentTime = currentTime;
    }

    public int getRequestTime() {
        return requestTime;
    }

    public int getRequiredTime() {
        return requiredTime;
    }

    public boolean canWaiting() {
        return requestTime <= currentTime;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    public int getEndTime() {
        return currentTime <= requestTime ? requiredTime + requestTime : requiredTime + currentTime;
    }

    public int getElapsedTime() {
        int elapsedTime = requiredTime;

        if (requestTime < currentTime) {
            elapsedTime += currentTime - requestTime;
        }

        return elapsedTime;
    }

    @Override
    public abstract int compareTo(AbstractJob o);
}

class RequestedJob extends AbstractJob {
    public RequestedJob(int requestTime, int requiredTime) {
        super(requestTime, requiredTime);
    }

    public WaitingJob toWaitingJob() {
        return new WaitingJob(getRequestTime(), getRequiredTime(), getCurrentTime());
    }

    @Override
    public int compareTo(AbstractJob target) {
        int result = Integer.compare(getRequestTime(), target.getRequestTime());
        return result != 0 ? result : Integer.compare(getRequiredTime(), target.getRequiredTime());
    }
}

class WaitingJob extends AbstractJob {
    public WaitingJob(int requestTime, int requiredTime, int currentTime) {
        super(requestTime, requiredTime, currentTime);
    }

    @Override
    public int compareTo(AbstractJob target) {
        int result = Integer.compare(getRequiredTime(), target.getRequiredTime());
        return result != 0 ? result : Integer.compare(getRequestTime(), target.getRequestTime());
    }
}

public class Main {
    public static void main(String[] args) {
        List<Object> inputs = new ArrayList<>(Arrays.asList(
//                new ArrayList<Object>(Arrays.asList(
//                        new int[][]{
//                                new int[]{0, 3},
//                                new int[]{1, 9},
//                                new int[]{2, 6}
//                        },
//                        new int[][]{}
//                )),
//                new ArrayList<Object>(Arrays.asList(
//                        new int[][]{
//                                new int[]{0, 1},
//                                new int[]{1, 2},
//                                new int[]{500, 6}
//                        },
//                        new int[][]{}
//                )),
//                new ArrayList<Object>(Arrays.asList(
//                        new int[][]{
//                                new int[]{0, 10},
//                                new int[]{2, 10},
//                                new int[]{9, 10},
//                                new int[]{15, 2}
//                        },
//                        new int[][]{}
//                )),
//                new ArrayList<Object>(Arrays.asList(
//                        new int[][]{
//                                new int[]{0, 10},
//                                new int[]{2, 10},
//                                new int[]{25, 10},
//                                new int[]{25, 2}
//                        },
//                        new int[][]{}
//                )),
                new ArrayList<Object>(Arrays.asList(
                        new int[][]
                                {{24, 10}, {28, 39}, {43, 20}, {37, 5}, {47, 22}, {20, 47}, {15, 2}, {15, 34}, {35, 43}, {26, 1}}
                                ,
                        new int[][]{}
                )),
                new ArrayList<Object>(Arrays.asList(
                        new int[][]
                                {{24, 10}, {28, 39}, {43, 20}, {37, 5}, {47, 22}, {20, 47}, {15, 34}, {15, 2}, {35, 43}, {26, 1}}
                        ,
                        new int[][]{}
                ))
        ));

        for (Object inputObj : inputs) {
            List<Object> input = (List) inputObj;

            int result = new Solution().solution((int[][]) input.get(0));
            System.out.println(result);
        }
    }
}



