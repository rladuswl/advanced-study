package study.advanced.trace.threadlocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import study.advanced.trace.threadlocal.code.FieldService;

@Slf4j
public class FieldServiceTest {

    private FieldService fieldService = new FieldService();

    @Test
    void field() {
        log.info("main start");

//        Runnable userA = new Runnable() {
//            @Override
//            public void run() {
//                fieldService.logic("userA");
//            }
//        }; // 줄여서 아래 람다 함수처럼 가능

        Runnable userA = () -> {
            fieldService.logic("userA");
        };
        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A"); // 위 userA 라는 로직 실행
        Thread threadB = new Thread(userB);
        threadA.setName("thread-B"); // 위 userA 라는 로직 실행
        
        threadA.start();
        // sleep(2000); // A가 완전히 끝난 후 B를 시작하겠다 -> 동시성 문제 발생 X
        sleep(100); // sleep 짧게 주기 -> 동시성 문제 발생 O
        threadB.start();
        sleep(2000); // 메인 쓰레드 종료 대기
        log.info("main exit");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
