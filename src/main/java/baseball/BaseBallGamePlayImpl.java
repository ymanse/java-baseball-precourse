package baseball;

import nextstep.utils.Console;
import nextstep.utils.Randoms;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.BooleanSupplier;

import static baseball.BaseBallGameConst.*;

public class BaseBallGamePlayImpl implements IGamePlay {
  protected  Map<Integer, Integer> balls = new HashMap<>();

  @Override
  public void init() {
    balls.clear();
    while (balls.size() < MAX_BALL_COUNT) {
      Integer ball = Randoms.pickNumberInRange(MIN_BALL, MAX_BALL);
      balls.putIfAbsent(ball, balls.size() + 1);
    }
  }

  @Override
  public Boolean runPlay() {
    while (true) {
      try {
        Map<Integer, Integer> intputBalls = getInputBalls();
        PlayResult playResult = new PlayResult(intputBalls, balls);
        System.out.println(playResult.getResultString());
        if (playResult.isComplete())
          return true;
      }
      catch (NoSuchElementException e){
        throw e;
      }
      catch (IllegalArgumentException e){
        System.out.println(e.getMessage());
      }
    }
  }

  protected Map<Integer, Integer> getInputBalls() throws IllegalArgumentException {
    System.out.print("숫자를입력해주세요: ");
    String strInputs = Console.readLine();
    Map<Integer, Integer> intputBalls = convertInputBalls(strInputs);
    return intputBalls;
  }

  protected Map<Integer, Integer> convertInputBalls(String strInputs) throws IllegalArgumentException {
    Map<Integer, Integer> balls = new HashMap<>();
    validationThrowException(()->strInputs.length() != MAX_BALL_COUNT, "[ERROR] : 입력값 개수 에러");
    for (int i = 0; i < strInputs.length(); i++) {
      char c = strInputs.charAt(i);
      validationThrowException(()->!Character.isDigit(c), "[ERROR] : 숫자가 아닌 입력값");
      balls.put(Character.getNumericValue(c), i + 1);
    }
    validationThrowException(()->balls.size() != MAX_BALL_COUNT, "[ERROR] : 중복값 입력");
    return balls;
  }

  private void validationThrowException(BooleanSupplier supplier, String s) throws IllegalArgumentException {
    if (supplier.getAsBoolean())
      throw new IllegalArgumentException(s);
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
