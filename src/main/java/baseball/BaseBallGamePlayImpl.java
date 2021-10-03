package baseball;

import nextstep.utils.Console;
import nextstep.utils.Randoms;

import java.util.HashMap;
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

  @Override
  public Boolean askRetry() {
    return null;
  }
}
