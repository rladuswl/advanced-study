package study.advanced.app.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.advanced.trace.TraceStatus;
import study.advanced.trace.logtrace.LogTrace;

@Service // @Component 컴포넌트 스캔
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId) {

        TraceStatus status = null;
        try {
            status = trace.begin("OrderService.orderItem()");
            orderRepository.save(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; // 예외를 꼭 다시 던져주어야 한다.
        }
    }
}
