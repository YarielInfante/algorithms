package com.company;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

//        1 - define the problem
//        2 - representation constraints
//        3 - approach/strategy how to approach the problem
//        4 - algorithm
//        5 - experiment/analysis


    public static void main(String[] args) {
         List<Integer> list = new ArrayList<>();

         list.add(4);
         list.add(6);
         list.add(5);

        List<Integer> collect = Arrays.stream(new int[]{4, 6, 5, 3, 3, 1}).boxed().collect(Collectors.toList());

        System.out.println(pickingNumbers(collect));
    }

    /*
     * Complete the 'pickingNumbers' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY a as parameter.
     */

    public static int pickingNumbers(List<Integer> a) {
        List<Integer> b = new ArrayList<>();

        Collections.sort(a);

        for (int i = 0; i < a.size(); i++) {

            if (Math.abs(a.get(i) - a.get(i + 1)) <= 1) {
                b.add(a.get(i));
                continue;
            }
        }

        return b.size();
    }


    /*
     * Complete the timeConversion function below.
     */
    static String timeConversion(String s) {

        String[] timeSplited = s.split(":");

        String amPm = timeSplited[2].substring(2);

        if (amPm.equalsIgnoreCase("PM")) {
            int hh = Integer.parseInt(timeSplited[0]);
            if (hh != 12)
                timeSplited[0] = String.valueOf(hh + 12);
        } else {
            if (timeSplited[0].equalsIgnoreCase("12"))
                timeSplited[0] = "00";
        }
        timeSplited[2] = timeSplited[2].substring(0, 2);

        return String.join(":", timeSplited);
    }

    public static int fib(int n) {
        int result = 0;
        if (n == 1 || n == 2) {
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }


    static int commonChild(String s1, String s2) {
        return LCSM4(s1.toCharArray(), s2.toCharArray(), s1.length(), s2.length());

    }

    public static int LCSM4(char[] X, char[] Y, int m, int n) {
        int memo[] = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            int prev = 0;
            for (int j = 1; j <= n; j++) {
                int temp = memo[j];
                if (X[i - 1] == Y[j - 1]) {
                    memo[j] = prev + 1;
                } else {
                    memo[j] = Math.max(memo[j], memo[j - 1]);
                }
                prev = temp;
            }

        }
        return memo[n];
    }

    static int commonChild2(String s1, String s2) {


        char[] charS1 = s1.toCharArray();
        char[] charS2 = s2.toCharArray();
        int count = 0;
        int index = 0;
        int indexPrimary = 0;

        for (int i = indexPrimary; i < charS1.length; i++) {

            if (charS1[i] == charS2[index]) {
                count++;
                ++index;
                indexPrimary = i + 1;
            } else {
                if (i + 1 == charS1.length) {
                    i = indexPrimary;
                    ++index;
                }

            }

        }

        return count;
    }


    // Complete the substrCount function below.
    static long substrCount(String s) {

        long count = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            for (int i1 = i + 1; i1 < s.length(); i1++) {
                char c1 = s.charAt(i1);
                if (c == c1) {
                    count++;
                }

                if (i1 + 1 < s.length() && c == s.charAt(i1 + 1))
                    continue;
                else
                    break;
            }
        }

        return count;
    }


    // Complete the isValid function below.
    static String isValid(String s) {

        Map<Character, Integer> map = new HashMap<>();

        for (char c : s.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);

        Integer[] array = map.values().toArray(new Integer[0]);
        Arrays.sort(array);

        if (array[0].equals(array[array.length - 1]))
            return "YES";

        --array[0];

        int value = array[0] == 0 && array.length > 1 ? array[1] : array[0];
        String result = "YES";

        for (int i = array[0] == 0 ? 1 : 0; i < array.length; i++) {
            if (value != array[i]) {
                result = "NO";
                break;
            }
        }

        if (result.equals("NO")) {
            ++array[0];

            value = --array[array.length - 1];
            result = "YES";

            for (int i = 0; i < (value == 0 ? array.length - 2 : array.length); i++) {
                if (value != array[i]) {
                    result = "NO";
                    break;
                }
            }
        }

        return result;
    }

    // Complete the alternatingCharacters function below.
    static int alternatingCharacters(String s) {

        int count = 0;

        char[] chars = s.toCharArray();

        for (int i = 1; i < chars.length; i++) {
            if (chars[i - 1] == chars[i])
                count++;
        }

        return count;
    }


    // Complete the makeAnagram function below.
    static int makeAnagram(String a, String b) {

        Map<Character, Integer> sa = new HashMap<>();
        Map<Character, Integer> sb = new HashMap<>();

        for (char ch : a.toCharArray())
            sa.put(ch, sa.getOrDefault(ch, 0) + 1);


        for (char ch : b.toCharArray())
            sb.put(ch, sb.getOrDefault(ch, 0) + 1);

        StringBuilder builderA = new StringBuilder();

        for (Map.Entry<Character, Integer> entry : sa.entrySet())
            if (sb.containsKey(entry.getKey()) && sa.get(entry.getKey()) > 0) {
                for (int i = 0; i < Math.min(entry.getValue(), sb.get(entry.getKey())); i++) {
                    builderA.append(entry.getKey());
                }
                sa.put(entry.getKey(), 0);
            }

        return (a.length() - builderA.length()) + (b.length() - builderA.length());
    }


    static int sum = 0;

    public static int mergeSort(int[] n) {


        if (n.length < 2)
            return sum;

        int middle = n.length / 2;

        int[] left = new int[middle];
        for (int i = 0; i < middle; i++)
            left[i] = n[i];

        int[] right = new int[n.length - middle];
        for (int i = middle; i < n.length; i++)
            right[i - middle] = n[i];

        mergeSort(left);
        mergeSort(right);

        sum += merge(left, right, n);

        return sum;
    }

    private static int merge(int[] left, int[] right, int[] result) {
        int i = 0, j = 0, k = 0, sum = 0;

        while (i < left.length && j < right.length)
            if (left[i] <= right[j])
                result[k++] = left[i++];
            else {
                result[k++] = right[j++];
                sum++;
            }

        while (i < left.length) {
            sum++;
            result[k++] = left[i++];
        }

        while (j < right.length) {
            sum++;
            result[k++] = right[j++];
        }

        return sum;
    }

    // Complete the countInversions function below.
    static long countInversions(int[] arr) {

        int midIndex = arr.length / 2;

        int[] left = new int[midIndex];
        for (int i = 0; i < midIndex; i++)
            left[i] = arr[i];

        int[] right = new int[midIndex + 1];
        for (int i = 0; i < arr.length - midIndex; i++)
            right[i] = arr[midIndex + i];


        return 1;


    }

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {
        int midFloorIndex = (int) Math.floor((d - 1) / 2);
        int midCeilIndex = (int) Math.ceil((d - 1) / 2);
        int notifications = 0;
        int medianEven = 0;
        int medianOdd = 0;

        int[] countingSort = new int[201];

        for (int i = d - 1; i >= 0; i--)
            countingSort[expenditure[i]]++;

        for (int i = d, l = expenditure.length; i < l; i++) {

            // Find median
            for (int j = 0, k = 0; k <= midFloorIndex; k += countingSort[j], j++) medianEven = j;
            for (int j = 0, k = 0; k <= midCeilIndex; k += countingSort[j], j++) medianOdd = j;
            int m = (medianEven + medianOdd) / 2;

            // Check if notification is given
            if (expenditure[i] >= m * 2) notifications++;

            // Replace subarray elements
            countingSort[expenditure[i - d]]--;
            countingSort[expenditure[i]]++;
        }

        return notifications;
    }


    static int maximumToys(int[] prices, long k) {

        Arrays.sort(prices);

        int toysCount = 0;

        for (Integer price : prices) {
            if (price <= k) {
                k -= price;
                toysCount += 1;
            } else
                break;
        }
        return toysCount;
    }


    // Complete the countSwaps function below.
    static void countSwaps(int[] a) {

        int n = a.length;
        int swaps = 0;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n - 1; j++) {
                // Swap adjacent elements if they are in decreasing order
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    swaps++;
                }
            }

        }

        System.out.println("Array is sorted in " + swaps + " swaps.");
        System.out.println("First Element: " + a[0]);
        System.out.println("Last Element: " + a[a.length - 1]);
    }

    static void swap(int[] n, int from, int to) {
        int temp = n[from];
        n[from] = n[to];
        n[to] = temp;
    }

    static List<Integer> freqQuery(List<List<Integer>> queries) {

        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> frequenciesFound = new ArrayList<>();
        final int INSERT = 1;
        final int DELETE = 2;
        final int FREQUENCY = 3;
        int frequencyMax = 0;

        for (List<Integer> query : queries) {
            switch (query.get(0)) {
                case INSERT: {
                    int value = 0;
                    if (map.containsKey(query.get(1))) {
                        value = map.get(query.get(1)) + 1;
                    } else {
                        value = 1;
                    }
                    map.put(query.get(1), value);
                    frequencyMax = Math.max(frequencyMax, value);

                    break;
                }
                case DELETE: {
                    int value = 0;
                    if (map.containsKey(query.get(1))) {
                        if (map.get(query.get(1)) > 1) {
                            value = map.get(query.get(1)) - 1;
                            map.put(query.get(1), value);

                        } else {
                            map.remove(query.get(1));
                        }
                    }
                    break;
                }
                case FREQUENCY: {
                    if (query.get(1) <= frequencyMax && map.containsValue(query.get(1))) {
                        frequenciesFound.add(1);
                    } else {
                        frequenciesFound.add(0);
                    }
                }
                break;
            }
        }

        return frequenciesFound;
    }


    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {

        Map<Long, Long> rightMap = getOccurenceMap(arr);
        Map<Long, Long> leftMap = new HashMap<>();
        long numberOfGeometricPairs = 0;

        for (long val : arr) {
            long countLeft = 0;
            long countRight = 0;
            long lhs = 0;
            long rhs = val * r;
            if (val % r == 0) {
                lhs = val / r;
            }
            Long occurence = rightMap.get(val);
            rightMap.put(val, occurence - 1L);

            if (rightMap.containsKey(rhs)) {
                countRight = rightMap.get(rhs);
            }
            if (leftMap.containsKey(lhs)) {
                countLeft = leftMap.get(lhs);
            }
            numberOfGeometricPairs += countLeft * countRight;
            insertIntoMap(leftMap, val);
        }
        return numberOfGeometricPairs;
    }

    private static Map<Long, Long> getOccurenceMap(List<Long> test) {
        Map<Long, Long> occurenceMap = new HashMap<>();
        for (long val : test) {
            insertIntoMap(occurenceMap, val);
        }
        return occurenceMap;
    }

    private static void insertIntoMap(Map<Long, Long> occurenceMap, Long val) {
        if (!occurenceMap.containsKey(val)) {
            occurenceMap.put(val, 1L);
        } else {
            Long occurence = occurenceMap.get(val);
            occurenceMap.put(val, occurence + 1L);
        }
    }

    // Complete the twoStrings function below.
    static String twoStrings(String s1, String s2) {

        char[] chars = s1.toCharArray();
        char[] chars2 = s2.toCharArray();

        Set<Character> set = new HashSet<>();

        for (char c : chars) {
            set.add(c);
        }

        for (char c : chars2) {
            if (set.contains(c)) {
                return "YES";
            }
        }

        return "NO";
    }

    // Complete the checkMagazine function below.
    static void checkMagazine(String[] magazine, String[] note) {
        Map<String, Integer> words = new HashMap<>();

        Arrays.stream(magazine).forEach(m -> {
            if (words.containsKey(m))
                words.put(m, words.get(m) + 1);
            else
                words.put(m, 1);
        });

        for (String n : note) {
            if (words.containsKey(n) && words.get(n) > 0) {
                words.put(n, words.get(n) - 1);
            } else {
                System.out.println("No");
                return;
            }
        }


        System.out.println("Yes");
    }

//   index->	 1 2 3  4  5 6 7 8 9 10
//            [0,0,0, 0, 0,0,0,0,0, 0]
//            [3,3,3, 3, 3,0,0,0,0, 0]
//            [3,3,3,10,10,7,7,7,0, 0]
//            [3,3,3,10,10,8,8,8,1, 0]
//            [3,0,0, 7, -3,1,0,-7,-1, 0]

    // Complete the arrayManipulation function below.
    static long arrayManipulation(int n, int[][] queries) {
        int[] array = new int[n + 1];
        long max = 0;
        long sum = 0;

        for (int[] q : queries) {
            int a = q[0] - 1;
            int b = q[1];
            int k = q[2];
            array[a] += k;
            array[b] -= k;
        }

        for (int i : array) {
            sum += i;
            max = Math.max(max, sum);
        }

        return max;
    }


    public static boolean palimm(String str) {
        int left = 0;
        int right = str.length() - 1;

        while (left != right)
            if (str.charAt(left++) != str.charAt(right--))
                return false;


        return true;
    }


    private static class DoublyLinkedListNode {
        int data;
        DoublyLinkedListNode prev;
        DoublyLinkedListNode next;

        public DoublyLinkedListNode(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return
                    "data=" + data
                    ;
        }
    }


    // Complete the insertNodeAtPosition function below.

    /*
     * For your reference:
     *
     * SinglyLinkedListNode {
     *     int data;
     *     SinglyLinkedListNode next;
     * }
     *
     */
    static SinglyLinkedListNode insertNodeAtPosition(SinglyLinkedListNode head, int data, int position) {

        if (position == 0) {
            SinglyLinkedListNode node = new SinglyLinkedListNode(data);
            node.data = data;
            node.next = head;
            return node;
        }

        SinglyLinkedListNode current = head;
        SinglyLinkedListNode next = head.next;

        for (int i = 0; i < position; i++) {
            if (i == position - 1) {
                SinglyLinkedListNode node = new SinglyLinkedListNode(data);
                node.next = next;
                current.next = node;
            } else {
                current = current.next;
                next = current.next;
            }
        }

        return head;
    }

    private static class SinglyLinkedListNode {
        int data;
        SinglyLinkedListNode next;

        public SinglyLinkedListNode(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return
                    "data=" + data
                    ;
        }
    }

    static int minimumSwaps(int[] arr) {
        int swaps = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == i + 1)
                continue;

            int j = i + 1;

            while (arr[i] != i + 1) {
                if (arr[j] != i + 1)
                    j++;
                else {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                    swaps++;
                }
            }
        }
        return swaps;
    }


    // Complete the minimumBribes function below.
    static void minimumBribesm(int[] q) {
        int bribes = 0;
        for (int i = q.length - 1; i >= 0; i--) {
            if (q[i] - (i + 1) > 2) {
                System.out.println("Too chaotic");
                return;
            }
            for (int j = Math.max(0, q[i] - 2); j < i; j++)
                if (q[j] > q[i]) bribes++;
        }
        System.out.println(bribes);
    }

    // Complete the minimumBribes function below.
    static void minimumBribes(int[] q) {
        int bribes = 0;

        for (int i = 0; i < q.length; i++) {
            if (q[i] > i + 3) {
                System.out.println("Too chaotic");
                return;
            }
            for (int i1 = i + 1; i1 < q.length; i1++) {
                if (q[i] > q[i1]) {
                    int temp = q[i1];
                    q[i1] = q[i];
                    q[i] = temp;
                    bribes++;
                }
            }
        }
        System.out.println(bribes);
    }

    static int[] rotLeft(int[] a, int d) {
        int index = 0;
        int[] clone = a.clone();
        for (int i = 0; i < a.length; i++) {
            index = i;
            for (int ii = 0; ii < d; ii++) {
                index--;
                if (index == -1)
                    index = a.length - 1;
            }
            clone[index] = a[i];
        }

        return clone;
    }

    static boolean palim(String word) {
        char[] chars = word.toCharArray();
        String palim = "";
        for (int i = chars.length - 1; i >= 0; i--) {
            palim += chars[i];
        }

        return word.equalsIgnoreCase(palim);
    }

    // Complete the hourglassSum function below.
    static int hourglassSum(int[][] arr) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Integer::compareTo);
        TreeSet<Integer> sums = new TreeSet<>();

        for (int i = 0; i < arr.length; i++) {
            if (i + 2 >= 6)
                continue;
            for (int i1 = 0; i1 < arr[i].length; i1++) {
                if (i1 + 2 >= 6)
                    continue;
                int sum = arr[i][i1] + arr[i][i1 + 1] + arr[i][i1 + 2]
                        + arr[i + 1][i1 + 1] +
                        arr[i + 2][i1] + arr[i + 2][i1 + 1] + arr[i + 2][i1 + 2];
                priorityQueue.add(sum);
            }

        }

        return priorityQueue.remove();
    }

    // Complete the repeatedString function below.
    static long repeatedString(String s, long n) {
        double as = 0;

        for (char a : s.toCharArray()) {
            as += a == 'a' ? 1 : 0;
        }
        double length = s.toCharArray().length;

        long repeatedLength = n - (n % s.length());
        int endIndex = (int) (n % s.length());
        double v1 = (repeatedLength / length) * as;
        long plus = 0;
        if (n % s.length() != 0) {
            plus = repeatedString(s.substring(0, endIndex), s.substring(0, endIndex).length());
        }

        return (long) v1 + plus;
    }


    // Complete the jumpingOnClouds function below.
    static int jumpingOnClouds(int[] c) {
        int jumps = 0;

        for (int i = 0; i < c.length; ) {
            if (c[i] == 0) {
                int jump = 2;
                if (i + jump > c.length - 1 || c[i + jump] == 1) {
                    jump = 1;
                }
                i += jump;
                jumps += 1;
                if (c.length - 1 == i) {
                    break;
                }
            } else {
                i++;
            }
        }

        return jumps;
    }

    public static int segment(int x, List<Integer> space) {
        // Write your code here
        List<List<Integer>> subArray = new ArrayList<>();
        List<Integer> array = new ArrayList<>();

        for (int i = 0; i < space.size(); i++) {
            for (int ii = i; ii < i + x; ii++) {
                array.add(space.get(ii));
            }
            subArray.add(array);
            array = new ArrayList<>();
            if (subArray.get(subArray.size() - 1).get(x - 1).equals(space.get(space.size() - 1))) {
                break;
            }
        }

        List<Optional<Integer>> collect = subArray.stream().map(s -> s.stream().min(Comparator.naturalOrder())).collect(Collectors.toList());

        Optional<Optional<Integer>> max = collect.stream().max(Comparator.comparing(Optional::get));
        return max.get().get();
    }

    // Complete the formingMagicSquare function below.
    static int formingMagicSquare(int[][] s) {
        int n = s.length;
        int[][] cloneS = s.clone();
        int magicConstant = (int) (n * ((Math.pow(n, 2) + 1) / 2));
        Map<String, int[]> sums = new HashMap<>();
        int cost = 0;

        sums.put("rows", new int[n]);
        sums.put("columns", new int[n]);
        sums.put("diagonals", new int[2]);

        for (int i = 0; i < s.length; i++) {
            int[] rows = sums.get("rows");
            rows[i] = Arrays.stream(s[i]).sum();
            sums.putIfAbsent("rows", rows);

            int[] columns = sums.get("columns");
            int columnSum = 0;
            for (int ii = 0; ii < s.length; ii++) {
                columnSum += s[ii][i];
            }
            columns[i] = columnSum;
            sums.putIfAbsent("columns", columns);
        }

        int[] diagonals = sums.get("diagonals");
        int diagonalSum1 = 0;
        int diagonalSum2 = 0;
        for (int i = s.length - 1, ii = 0; i >= 0; i--, ii++) {
            diagonalSum1 += s[ii][ii];
            diagonalSum2 += s[i][ii];
        }
        diagonals[0] = diagonalSum1;
        diagonals[1] = diagonalSum2;
        sums.putIfAbsent("diagonals", diagonals);

        for (int i = 0; i < sums.get("rows").length; i++) {
            if (sums.get("rows")[i] < magicConstant) {
                int dif = magicConstant - sums.get("rows")[i];
                for (int i1 = 0; i1 < s.length; i1++) {
                    if (s[i][i1] + dif <= 9) {
                        cost += dif;
                        cloneS[i][i1] = s[i][i1] + dif;
                        if (formingMagicSquare(cloneS) == 0) {
                            break;
                        }
                    }
                }
            }
        }


        return cost;

    }

    /*
     * Complete the 'birthdayCakeCandles' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY candles as parameter.
     */

    public static int birthdayCakeCandles(List<Integer> candles) {
        // Write your code here

        Integer[] integers = candles.toArray(new Integer[0]);

        Object[] objects = Arrays.stream(integers).sorted().toArray();

        return (int) Arrays.stream(objects).filter(o -> o.equals(objects[objects.length - 1])).count();
    }

    // Complete the miniMaxSum function below.
    static void miniMaxSum(int[] arr) {

        long[] nums = Arrays.stream(arr).asLongStream().sorted().toArray();

        long sumMin = 0;
        long sumMax = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            sumMax += i == 0 ? 0 : nums[i];
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            sumMin += i == nums.length - 1 ? 0 : nums[i];
        }

        System.out.println(sumMin + " " + sumMax);
    }

    // Complete the staircase function below.
    static void staircase(int n) {
        for (int i = 1; i <= n; i++) {
            StringBuilder line = new StringBuilder();
            for (int ii = 1; ii <= n; ii++) {
                if (ii > n - i)
                    line.append("#");
                else
                    line.append(" ");

            }
            System.out.println(line);
        }
    }

    // Complete the plusMinus function below.
    static void plusMinus(int[] arr) {
        long numsPositive = Arrays.stream(arr).filter(n -> n > 0).count();
        long numNegative = Arrays.stream(arr).filter(n -> n < 0).count();
        long zeros = Arrays.stream(arr).filter(n -> n == 0).count();
        DecimalFormat decimalFormat = new DecimalFormat("#,###,###,##0.000000");

        System.out.println(decimalFormat.format(Double.parseDouble(String.valueOf(numsPositive)) / Double.parseDouble(String.valueOf(arr.length))));
        System.out.println(decimalFormat.format(Double.parseDouble(String.valueOf(numNegative)) / Double.parseDouble(String.valueOf(arr.length))));
        System.out.println(decimalFormat.format(Double.parseDouble(String.valueOf(zeros)) / Double.parseDouble(String.valueOf(arr.length))));

    }

    public static int countingValleys(int steps, String path) {

        char[] pathArray = path.toCharArray();
        int hikes = 0;
        int valley = 0;
        for (int i = 0; i < steps; i++) {
            hikes += pathArray[i] == 'U' ? 1 : -1;
            valley += hikes == 0 && pathArray[i] == 'U' ? 1 : 0;
        }
        return valley;
    }

    // Complete the compareTriplets function below.
    static List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {
        List<Integer> scores = new ArrayList<>();
        scores.add(0);
        scores.add(0);

        for (int alice = 0, bob = 0; alice < a.size(); alice++, bob++) {
            scores.set(0, a.get(alice) > b.get(bob) ? scores.get(0) + 1 : scores.get(0));
            scores.set(1, a.get(alice) < b.get(bob) ? scores.get(1) + 1 : scores.get(1));
        }

        return scores;
    }

    // Complete the aVeryBigSum function below.
    static long aVeryBigSum(long[] ar) {

        return Arrays.stream(ar).sum();
    }

    /*
     * Complete the 'diagonalDifference' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY arr as parameter.
     */

    public static int diagonalDifference(List<List<Integer>> arr) {
        // Write your code here
        int diagonalX = 0;
        int diagonalY = 0;
        for (int i = 0, i2 = arr.size() - 1; i < arr.size(); i++, i2--) {
            List<Integer> integers = arr.get(i);
            Integer x = integers.get(i);
            Integer y = integers.get(i2);
            diagonalX += x;
            diagonalY += y;
        }

        return Math.abs(Math.subtractExact(diagonalX, diagonalY));
    }


}

