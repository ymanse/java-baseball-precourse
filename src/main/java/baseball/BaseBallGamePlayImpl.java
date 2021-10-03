package baseball;

import nextstep.utils.Console;
import nextstep.utils.Randoms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseBallGamePlayImpl implements IGamePlay {
  static final Integer MinBall = 1;
  static final Integer MaxBall = 9;
  Map<Integer, Integer> balls = new HashMap<>();

  @Override
  public void init() {
    balls.clear();
    while (balls.size() < 3) {
      Integer ball = Randoms.pickNumberInRange(MinBall, MaxBall);
      balls.putIfAbsent(ball, balls.size() + 1);
    }
  }

  @Override
  public Boolean runPlay() {
    while (true) {
      Map<Integer, Integer> intputBalls = readInputBall();
      if (intputBalls.size() != 3) {
        System.out.println("Error : 입력값 오류");
        continue;
      }
      PlayResult playResult = new PlayResult(intputBalls, balls);
      printResult(playResult.getStrikeResult(), playResult.getBallResult());
      if (playResult.isComplete())
        return true;
    }
  }

  private Map<Integer, Integer> readInputBall() {
    System.out.print("숫자를입력해주세요: ");
    Map<Integer, Integer> balls = new HashMap<>();
    String strInputs = Console.readLine();
    for (int i = 0; i < 3; i++) {
      char c = strInputs.charAt(i);
      if (!Character.isDigit(c))
        continue;
      balls.put(Character.getNumericValue(c), i + 1);
    }
    return balls;
  }
  private void printResult(Integer strikeResult, Integer ballResult) {
    if(strikeResult == 0 && ballResult ==0){
      System.out.println("낫싱");
      return;
    }
    List<String> resultStr = Arrays.asList(
        strikeResult > 0 ? String.format("%d스트라이크", strikeResult): "",
        ballResult > 0 ? String.format("%d볼", ballResult): ""
    );
    System.out.println(String.join(" ", resultStr));
  }
  @Override
  public Boolean askRetry() {
    return null;
  }
}
