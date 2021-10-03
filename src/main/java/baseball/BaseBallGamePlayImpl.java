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
  protected  Map<Integer, Integer> balls = new HashMap<>();

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
      Map<Integer, Integer> intputBalls = getInputBalls();
      if (intputBalls == null) continue;
      PlayResult playResult = new PlayResult(intputBalls, balls);
      System.out.println(playResult.getResultString());
      if (playResult.isComplete())
        return true;
    }
  }

  protected Map<Integer, Integer> getInputBalls() {
    System.out.print("숫자를입력해주세요: ");
    String strInputs = Console.readLine();
    Map<Integer, Integer> intputBalls = convertInputBalls(strInputs);
    if (intputBalls.size() != 3) {
      System.out.println("Error : 입력값 오류");
      return null;
    }
    return intputBalls;
  }

  protected Map<Integer, Integer> convertInputBalls(String strInputs) {
    Map<Integer, Integer> balls = new HashMap<>();
    if(strInputs.length() != 3) return balls;
    for (int i = 0; i < strInputs.length(); i++) {
      char c = strInputs.charAt(i);
      if (!Character.isDigit(c)){
        return new HashMap<>();
      }
      balls.put(Character.getNumericValue(c), i + 1);
    }
    return balls;
  }

  @Override
  public Boolean askRetry() {
    System.out.println("게임을새로시작하려면1,종료하려면2를입력하세요.");
    String s = Console.readLine();
    if (s.equals("1")) {
      return true;
    }
    if (s.equals("2")) {
      return false;
    }
    return false;
  }
}
