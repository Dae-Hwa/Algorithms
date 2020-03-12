import java.io.*;

class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  public static void main(String[] args) throws IOException {
    int num1 = Integer.parseInt(br.readLine());
    String[] input1 = br.readLine().split(" ");
    int num2 = Integer.parseInt(br.readLine());
    String[] input2 = br.readLine().split(" ");
    StringBuffer sb = new StringBuffer();
    int result = 0;

    for (int i = 0; i < num2; i++) {
      for (int j = 0; j < num1; j++) {
        if (Integer.parseInt(input1[j]) == Integer.parseInt(input2[i])) {
          result = 1;
          break;
        }
      }
      sb.append(result);
      if (i < num2 - 1) {
        sb.append("\r\n");
      }
      result = 0;
    }

    System.out.println(sb.toString());
  }
}