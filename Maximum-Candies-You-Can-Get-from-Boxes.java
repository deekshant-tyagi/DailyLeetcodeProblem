class Solution {
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        HashSet<Integer> key = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        HashSet<Integer> waitBox = new HashSet<>();
        int c = 0;

        for (int i : initialBoxes) {
            if (status[i] == 1) {
                q.add(i);
            } else {
                waitBox.add(i);
            }
        }
        while (!q.isEmpty()) {

            int bx = q.poll();
            if (status[bx] == 1) {
                c += candies[bx];
                for (int k : keys[bx]) {
                    key.add(k);
                }
                for (int j : containedBoxes[bx]) {
                    if (status[j] == 1 || key.contains(j)) {
                        q.add(j);
                    } else {
                        waitBox.add(j);
                    }
                }
            } else {
                if (key.contains(bx)) {
                    key.remove(bx);
                    c += candies[bx];
                    for (int k : keys[bx]) {
                        key.add(k);
                    }
                    for (int j : containedBoxes[bx]) {
                        if (status[j] == 1 || key.contains(j)) {
                            q.add(j);
                        } else {
                            waitBox.add(j);
                        }
                    }
                }
            }
            HashSet<Integer>set=new HashSet<>();
            for (int l : waitBox) {
                if (key.contains(l) || status[l]==1) {
                    q.add(l);
                }
                else{
                    set.add(l);
                }
            }
            waitBox=set;
        }
        return c;
    }
}