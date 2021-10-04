package baseball;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BaseBallGamePlayImplTest {

  @Test
  @RepeatedTest(100)
  void init() {
    BaseBallGamePlayImpl play = new BaseBallGamePlayImpl();
    play.init();
    assertThat(play.balls.size()).isEqualTo(3);
    assertThat(play.balls.containsValue(1)).isTrue();
    assertThat(play.balls.containsValue(2)).isTrue();
    assertThat(play.balls.containsValue(3)).isTrue();
  }

  @Test
  void runPlay_success() {
    Map<Integer, Integer> initMap = new HashMap<Integer, Integer>(){{
      put(7, 1);
      put(1, 2);
      put(3, 3);
    }};
    Map<Integer, Integer> inputMap = new HashMap<Integer, Integer>(){{
      put(7, 1);
      put(1, 2);
      put(3, 3);
    }};
    PlayResult playResult = new PlayResult(inputMap,initMap);
    assertThat(playResult.getResultString()).isEqualTo("3스트라이크");
    assertThat(playResult.isComplete()).isTrue();
  }
  @Test
  void runPlay_fail_1스트라이크2볼() {
    Map<Integer, Integer> initMap = new HashMap<Integer, Integer>(){{
      put(7, 1);
      put(1, 2);
      put(3, 3);
    }};
    Map<Integer, Integer> inputMap = new HashMap<Integer, Integer>(){{
      put(7, 1);
      put(3, 2);
      put(1, 3);
    }};
    PlayResult playResult = new PlayResult(inputMap,initMap);
    assertThat(playResult.getResultString()).isEqualTo("1스트라이크 2볼");
    assertThat(playResult.isComplete()).isFalse();
  }
  @Test
  void runPlay_fail_1스트라이크1볼() {
    Map<Integer, Integer> initMap = new HashMap<Integer, Integer>(){{
      put(7, 1);
      put(1, 2);
      put(3, 3);
    }};
    Map<Integer, Integer> inputMap = new HashMap<Integer, Integer>(){{
      put(7, 1);
      put(3, 2);
      put(4, 3);
    }};
    PlayResult playResult = new PlayResult(inputMap,initMap);
    assertThat(playResult.getResultString()).isEqualTo("1스트라이크 1볼");
    assertThat(playResult.isComplete()).isFalse();
  }
  @Test
  void runPlay_fail_낫싱() {
    Map<Integer, Integer> initMap = new HashMap<Integer, Integer>(){{
      put(7, 1);
      put(1, 2);
      put(3, 3);
    }};
    Map<Integer, Integer> inputMap = new HashMap<Integer, Integer>(){{
      put(4, 1);
      put(2, 2);
      put(5, 3);
    }};
    PlayResult playResult = new PlayResult(inputMap,initMap);
    assertThat(playResult.getResultString()).isEqualTo("낫싱");
    assertThat(playResult.isComplete()).isFalse();
  }

  @Test
  void convertInputBalls() throws Exception {
    BaseBallGamePlayImpl play = new BaseBallGamePlayImpl();
    Map<Integer, Integer> integerIntegerMap = play.convertInputBalls("713");
    assertThat(integerIntegerMap).isEqualTo(new HashMap<Integer, Integer>(){{
      put(7, 1);
      put(1, 2);
      put(3, 3);
    }});
  }

  @Test
  void convertInputBalls_duplicateValue() {
    BaseBallGamePlayImpl play = new BaseBallGamePlayImpl();
    assertThatThrownBy(() -> { play.convertInputBalls("711"); })
        .isInstanceOf(Exception.class)
        .hasMessageContaining("[ERROR] : 중복값 입력");
  }
  @Test
  void convertInputBalls_wrongValue() {
    BaseBallGamePlayImpl play = new BaseBallGamePlayImpl();
    {
      assertThatThrownBy(() -> { play.convertInputBalls("7a1"); })
          .isInstanceOf(Exception.class)
          .hasMessageContaining("[ERROR] : 숫자가 아닌 입력값");
    }
    {
      assertThatThrownBy(() -> { play.convertInputBalls("71"); })
          .isInstanceOf(Exception.class)
          .hasMessageContaining("[ERROR] : 입력값 개수 에러");
    }
    {
      assertThatThrownBy(() -> { play.convertInputBalls("7123"); })
          .isInstanceOf(Exception.class)
          .hasMessageContaining("[ERROR] : 입력값 개수 에러");
    }
    {
      assertThatThrownBy(() -> { play.convertInputBalls("712a"); })
          .isInstanceOf(Exception.class)
          .hasMessageContaining("[ERROR] : 입력값 개수 에러");
    }
  }
}