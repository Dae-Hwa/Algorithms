package prgrms.여행경로;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] result = null;

        String[][] input1 = new String[][]{
                {"ICN", "JFK"},
                {"HND", "IAD"},
                {"JFK", "HND"}
        };


        String[][] input2 = new String[][]{
                {"ICN", "SFO"},
                {"ICN", "ATL"},
                {"SFO", "ATL"},
                {"ATL", "ICN"},
                {"ATL", "SFO"}
        };

        String[][] input3 = new String[][]{
                {"ICN", "ICN"},
                {"ICN", "HND"},
                {"HND", "ICN"},
                {"ICN", "HND"}
        };

        String[][] input4 = new String[][]{
                {"ICN", "A"},
                {"A", "C"},
                {"A", "D"},
                {"D", "B"},
                {"B", "A"}
        };

        String[][] input5 = new String[][]{
                {"ICN", "B"},
                {"ICN", "A"},
                {"B", "ICN"}
        };

        String[][] input6 = new String[][]{
                {"ICN", "A"},
                {"A", "B"},
                {"B", "A"},
                {"A", "ICN"},
                {"ICN", "A"}
        };

        String[][] input7 = {
                {"ICN", "BOO"},
                {"ICN", "COO"},
                {"COO", "DOO"},
                {"DOO", "COO"},
                {"BOO", "DOO"},
                {"DOO", "BOO"},
                {"BOO", "ICN"},
                {"COO", "BOO"}
        };

        String[][] input8 = {{"ICN", "COO"}, {"ICN", "BOO"}, {"COO", "ICN"}, {"BOO", "DOO"}};

        result = new Solution().solution(input8);

        System.out.println(Arrays.toString(result));
    }
}

class Solution {
    public String[] solution(String[][] tickets) {
        Map<String, List<Ticket>> ticketMap = new HashMap<>();

        for (String[] ticket : tickets) {
            List<Ticket> currentTickets = ticketMap.getOrDefault(ticket[0], new ArrayList<>());
            currentTickets.add(new Ticket(ticket[0], ticket[1]));
            Collections.sort(currentTickets);
            ticketMap.put(ticket[0], currentTickets);
        }

        Deque<Ticket> arrivals = new ArrayDeque<>();

        arrivals.add(new Ticket("", "ICN"));

        Stack<Ticket> dfs = new Stack<>();

        for (Ticket ticket : ticketMap.get("ICN")) {
            ticket.setLevel(1);
            dfs.add(ticket);
        }

        while (!dfs.isEmpty()) {
            Ticket lastestTicket = dfs.pop();

            if (lastestTicket.isChecked()) {
                continue;
            }

            while (lastestTicket.getLevel() < arrivals.size()) {
                arrivals.pollLast().setChecked(false);
            }

            lastestTicket.setChecked(true);
            arrivals.add(lastestTicket);

            if (arrivals.size() == tickets.length + 1) {
                break;
            }

            List<Ticket> currentTickets = ticketMap.getOrDefault(lastestTicket.getTo(), Collections.emptyList());

            for (Ticket ticket : currentTickets) {
                if (!ticket.isChecked()) {
                    ticket.setLevel(arrivals.size());
                    dfs.add(ticket);
                }
            }
        }


        return arrivals.stream()
                .map(Ticket::getTo)
                .toArray(size -> new String[size]);
    }
}

class Ticket implements Comparable<Ticket> {
    private String from;
    private String to;
    private boolean checked;
    private int level;

    public Ticket(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int compareTo(Ticket o) {
        if (from.equals(o.from)) {
            return to.compareTo(o.to) * -1;
        }

        return from.compareTo(o.from) * -1;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}