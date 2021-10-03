package baseball;

import java.util.Map;

public class PlayResult {
  private Integer strikeResult = 0;
  private Integer ballResult = 0;
  public PlayResult(Map<Integer, Integer> intputBalls, Map<Integer, Integer> balls) {
    for(Map.Entry<Integer, Integer> elem: intputBalls.entrySet()){
      if(balls.containsKey(elem.getKey()) && balls.get(elem.getKey()).equals(elem.getValue())){
        strikeResult++;
        continue;
      }
      if(balls.containsKey(elem.getKey())){
        ballResult++;
        continue;
      }
    }
  }

  public boolean isComplete() {
    return strikeResult.equals(3);
  }

  public Integer getStrikeResult() {
    return strikeResult;
  }

  public Integer getBallResult() {
    return ballResult;
  }
}
