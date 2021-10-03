package baseball;

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
    return null;
  }

  @Override
  public Boolean askRetry() {
    return null;
  }
}
