package baseball;

public class Application {
    public static void main(String[] args) {
        // TODO 숫자 야구 게임 구현
        IGamePlay gamePlay = new BaseBallGamePlayImpl();
        Boolean startGame = false;
        do{
            gamePlay.init();
            if(gamePlay.runPlay()){
                System.out.println("게임 끝");
                startGame = gamePlay.askRetry();
            }
        }
        while(startGame);
    }
}
