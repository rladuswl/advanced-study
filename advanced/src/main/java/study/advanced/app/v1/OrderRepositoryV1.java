package study.advanced.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.advanced.trace.TraceStatus;
import study.advanced.trace.hellotrace.HelloTraceV1;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

    private final HelloTraceV1 trace;

    public void save(String itemId) {

        TraceStatus status = null;
        try {
            status = trace.begin("OrderRepository.save()");

            // 저장 로직
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!"); // itemId가 ex면 예외 터트리기
            }
            sleep(1000); // 정상적이면 1초 sleep (상품 저장할 때 1초 걸린다고 가정)

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; // 예외를 꼭 다시 던져주어야 한다.
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
