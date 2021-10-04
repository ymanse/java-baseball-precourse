package baseball;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    return strikeResult.equals(MAX_BALL_COUNT);
  }

  public String getResultString() {
    List<String> resultStrList = new ArrayList<>();
    if(strikeResult > 0) resultStrList.add(String.format("%d스트라이크", strikeResult));
    if(ballResult > 0) resultStrList.add(String.format("%d볼", ballResult));
    if (resultStrList.isEmpty()) {
      return "낫싱";
    }
    return String.join(" ", resultStrList);
  }
}
